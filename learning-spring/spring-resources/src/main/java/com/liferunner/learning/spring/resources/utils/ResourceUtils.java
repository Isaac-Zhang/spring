package com.liferunner.learning.spring.resources.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * {@link Resource} utils
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/19
 **/
public interface ResourceUtils {

    static String getContext(Resource resource) {
        try {
            return getContext(resource, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getContext(Resource resource, String encoding) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        try (Reader reader = encodedResource.getReader()) {
            return IOUtils.toString(reader);
        }
    }
}
