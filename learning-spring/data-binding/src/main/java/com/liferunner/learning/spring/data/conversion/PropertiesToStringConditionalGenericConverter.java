package com.liferunner.learning.spring.data.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;

/**
 * 利用 {@link Properties} & {@link ConditionalGenericConverter}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see Properties
 * @see ConditionalGenericConverter
 * @since 2020/7/25
 **/
public class PropertiesToStringConditionalGenericConverter implements ConditionalGenericConverter {

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Properties.class.equals(sourceType.getClass())
                && String.class.equals(targetType.getClass());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        Properties properties = (Properties) source;
        StringBuilder sb = new StringBuilder();
        properties.entrySet().forEach(e -> {
            sb.append(e.getKey()).append("=").append(e.getValue()).append(System.getProperty("line.separator"));
        });
        return sb.toString();
    }
}
