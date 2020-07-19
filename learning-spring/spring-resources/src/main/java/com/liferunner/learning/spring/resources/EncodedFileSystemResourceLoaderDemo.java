package com.liferunner.learning.spring.resources;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 基于 {@link FileSystemResourceLoader} 的示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see FileSystemResourceLoader
 * @see EncodedResource
 * @see FileSystemResource
 * @since 2020/7/19
 **/
public class EncodedFileSystemResourceLoaderDemo {
 
    public static void main(String[] args) {
        String currentFilePath = "/" + System.getProperty("user.dir") + "/spring-resources/src/main/java/com/liferunner/learning/spring/resources/EncodedFileSystemResourceLoaderDemo.java";
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(currentFilePath);

        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
