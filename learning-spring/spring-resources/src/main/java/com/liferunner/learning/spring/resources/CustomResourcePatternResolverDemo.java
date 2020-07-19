package com.liferunner.learning.spring.resources;

import com.liferunner.learning.spring.resources.utils.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see Resource
 * @since 2020/7/19
 **/
public class CustomResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        String currentPackagePath = "/" + System.getProperty("user.dir") + "/spring-resources/src/main/java/com/liferunner/learning/spring/resources/";
        String locationPattern = currentPackagePath + "*.java";
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver(
                new FileSystemResourceLoader()
        );

        Resource[] resources = patternResolver.getResources(locationPattern);

        Stream.of(resources).map(ResourceUtils::getContext).forEach(System.out::println);
    }
}
