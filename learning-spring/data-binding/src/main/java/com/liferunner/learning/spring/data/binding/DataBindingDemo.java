package com.liferunner.learning.spring.data.binding;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/24
 **/
public class DataBindingDemo {

    public static void main(String[] args) {

        // 创建一个 POJO 对象
        Person person = new Person();

        // 创建 DataBinder, 并传入我们创建的 空白 POJO
        DataBinder dataBinder = new DataBinder(person, "person");

        // 创建 PropertyValues 对象，并赋值
        Map<String, Object> values = new HashMap<>();
        values.putIfAbsent("id", "18");
        values.put("name", "ZP");

        PropertyValues propertyValues = new MutablePropertyValues(values);

        // 数据绑定
        dataBinder.bind(propertyValues);

        // 输出绑定后的 POJO
        System.out.println(person);
    }
}
