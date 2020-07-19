package com.liferunner.learning.spring.resources;

import com.liferunner.learning.spring.resources.utils.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
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

        // 应用自定义 PathMatcher 选择器
        patternResolver.setPathMatcher(new MyJavaFilePathMatcher());
        Resource[] resources = patternResolver.getResources(locationPattern);

        Stream.of(resources).map(ResourceUtils::getContext).forEach(System.out::println);
    }

    static class MyJavaFilePathMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
