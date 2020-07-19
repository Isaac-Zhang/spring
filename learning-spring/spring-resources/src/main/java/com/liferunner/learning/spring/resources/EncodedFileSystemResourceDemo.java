package com.liferunner.learning.spring.resources;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 使用 {@link EncodedResource} 和 {@link FileSystemResource} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see EncodedResource
 * @see FileSystemResource
 * @since 2020/7/19
 **/
public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String currentFilePath = System.getProperty("user.dir") + "/spring-resources/src/main/java/com/liferunner/learning/spring/resources/EncodedFileSystemResourceDemo.java";
        File file = new File(currentFilePath);
        FileSystemResource fileSystemResource = new FileSystemResource(file);

        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        // 读取字节流
        Reader reader = encodedResource.getReader();

        System.out.println(IOUtils.toString(reader));
    }
}
