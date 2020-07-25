package com.liferunner.learning.spring.data.conversion;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * 将 String 自定义转换为 {@link Properties}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see Properties
 * @since 2020/7/25
 **/
public class CustomStringToProperty extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
//        super.setAsText(text);
        // 1. 创建 Properties 对象
        Properties properties = new Properties();
        // 2. 转换
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        // 3. 临时存储 properties 对象到当前 bean
        this.setValue(properties);
        // 4. 之后会通过 #getValue() 获取出当前的临时 properties 进行使用
    }
}
