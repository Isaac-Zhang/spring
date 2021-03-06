package com.liferunner.learning.spring.data.binding;

import com.liferunner.learning.spring.pojo.Family;
import com.liferunner.learning.spring.pojo.Person;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        // 使用 LinkedHashMap 是因为下面的values 执行顺序需要保证
        Map<String, Object> values = new LinkedHashMap<>();
        values.putIfAbsent("id", "18");
        values.put("name", "ZP");

        // 添加一个 POJO 不存在的属性
        // DataBinder 特性1： 会默认忽略掉不存在的字段属性
//        values.put("wife", "isa");

        // 1. 忽略未知字段（默认为true）dataBinder.setIgnoreUnknownFields
        // wife 字段在 person POJO 中不存在， 因此会报错
        // dataBinder.setIgnoreUnknownFields(false);

        // 2. 禁止自动生成 嵌套路径（默认为true）
        // family 对象不存在，会报错
        dataBinder.setAutoGrowNestedPaths(false);
        // 手动添加 family
        values.put("family", new Family());
        // 添加一个复合对象，比如 Family
        // DataBinder 特性2： 支持嵌套属性
        values.put("family.primaryAccount", "isaac");
        values.put("family.daughter", "dd");
        // 默认为 false, 需要配合上面的 setAutoGrowNestedPaths 来生效
        dataBinder.setIgnoreInvalidFields(true);

        // 用户设定 字段白名单
        // dataBinder.setAllowedFields;

        // 用于设定 字段黑名单
        // dataBinder.setDisallowedFields

        // 用于设定哪些字段属性是必须要设定的,我们不设置age,然后查看 BindResult,就会发现报错信息
        /**
         * org.springframework.validation.BeanPropertyBindingResult: 1 errors
         * Field error in object 'person' on field 'age': rejected value [];
         * codes [required.person.age,required.age,required.int,required];
         * arguments [org.springframework.context.support.DefaultMessageSourceResolvable:
         * codes [person.age,age]; arguments []; default message [age]];
         * default message [Field 'age' is required]
         */
        dataBinder.setRequiredFields("id","name","age");

        // 根据 Map 创建 PropertyValues
        PropertyValues propertyValues = new MutablePropertyValues(values);
        // 数据绑定
        dataBinder.bind(propertyValues);

        // 输出绑定后的 POJO
        System.out.println(person);

        // 绑定结果（包含错误信息，code，不会抛出异常）
        BindingResult result = dataBinder.getBindingResult();
        System.out.println(result);
    }
}
