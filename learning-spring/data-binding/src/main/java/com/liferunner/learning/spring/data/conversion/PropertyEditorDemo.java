package com.liferunner.learning.spring.data.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * 用于演示 {@link PropertyEditor}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see PropertyEditorSupport
 * @see PropertyEditor
 * @since 2020/7/25
 **/
public class PropertyEditorDemo {

    public static void main(String[] args) {
        String demoText = "name=isaac \n age=18";
        PropertyEditor propertyEditor = new CustomStringToProperty();
        propertyEditor.setAsText(demoText);

        // 输出 {age=18, name=isaac }
        System.out.println(propertyEditor.getValue());
    }
}
