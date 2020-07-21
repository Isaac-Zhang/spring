package com.liferunner.learning.spring.resources.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 动态更新资源 {@link MessageSource} 实现
 * <p>
 * 实现步骤：
 * 1. 定位资源（Properties 文件）
 * 2. 初始化 Properties( loadProperties() )
 * 3. 实现 AbstractMessageSource#resolveCode 方法
 * 4. 监听文件变化（利用 Java NIO 2 {@link WatchService} 来实现）
 * 5. 使用线程池{@link ExecutorService}异步处理文件变化
 * 6. 重新加载 Properties 对象
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/21
 **/
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource source = new DynamicResourceMessageSource();

        for (int i = 0; i < 10000; i++) {
            String message = source.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000L);
        }
    }

    private static final String RESOURCE_NAME = "dev.properties";
    private static final String RESOURCE_PATH = "/META-INF/";
    private static final String ENCODING = "UTF-8";
    private final ExecutorService executorService;
    private final Properties messageProperties;
    private final Resource propertiesResource;
    private ResourceLoader resourceLoader;

    public DynamicResourceMessageSource() {
        this.propertiesResource = getMessageResource();
        this.messageProperties = loadProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        // 监听文件变化
        onPropertiesChanged();
    }

    /**
     * 异步处理文件变化
     *
     * @param watchService
     */
    private void processPropertiesChanged(WatchService watchService) {
        CompletableFuture.runAsync(() -> {
            while (true) {
                // 代表每一次 监视的 状态变动
                WatchKey watchKey = null;
                try {
                    watchKey = watchService.take();
                    // 判断是否有效
                    if (watchKey.isValid()) {
                        List<WatchEvent<?>> watchEventList = watchKey.pollEvents();
                        WatchKey finalWatchKey = watchKey;
                        watchEventList.stream().forEach(watchEvent -> {
                            // watchable 代表我们之前监听的路径目录
                            Path dirPath = (Path) finalWatchKey.watchable();

                            // watchEvent（每一个事件） 则代表我们监听的目录中的每一个 文件 / 子文件夹
                            // 这里的路径代表相对路径，也就是相对于我们监听的目录的 路径
                            Path fileRelativeSourcePath = (Path) watchEvent.context();

                            // 将上述两个 path 拼接转化为 完整的文件绝对路径
                            Path fileFullPath = dirPath.resolve(fileRelativeSourcePath);

//                            System.out.println(fileFullPath);

                            // 判断当前监听路径是否为 我们关注的 文件
                            if (fileRelativeSourcePath.endsWith(RESOURCE_NAME)) {
                                // 将这个路径转化为 file
                                File watchedFile = fileFullPath.toFile();
                                // 通过文件形式，加载 Properties
                                try {
                                    Properties properties = loadProperties(new FileReader(watchedFile));
                                    this.messageProperties.clear();
                                    this.messageProperties.putAll(properties);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (watchKey != null) {
                        watchKey.reset();
                    }
                }
            }
        }, this.executorService);
    }

    /**
     * 监听文件变化
     */
    private void onPropertiesChanged() {
        // 判断是否为一个文件，如果在一个 jar 包中，就不需要修改了
        if (this.propertiesResource.isFile()) {
            try {
                // 获取文件
                File file = this.propertiesResource.getFile();
                // 获取我们需要监听的文件所在目录
                Path dirPath = file.toPath().getParent();
                // 获取当前系统的 文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                // 创建 WatchService
                WatchService watchService = fileSystem.newWatchService();
                // 监听目录下的 文件【修改】事件
                dirPath.register(watchService, java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY);
                // 异步处理文件变化
                processPropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Properties loadProperties() {
        EncodedResource es = new EncodedResource(this.propertiesResource, ENCODING);
        try {
            return loadProperties(es.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties loadProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return properties;
    }


    private Resource getMessageResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        return resourceLoader.getResource(RESOURCE_PATH + RESOURCE_NAME);
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private ResourceLoader getResourceLoader() {
        return (this.resourceLoader == null ? new DefaultResourceLoader() : this.resourceLoader);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = this.messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

}
