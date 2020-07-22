package com.liferunner.learning.spring.validation;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * {@link Errors} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/22
 **/
public class SpringValidationErrorsDemo {

    public static void main(String[] args) {
        // 1. 创建要验证的对象
        Person person = new Person();
        // 2. 创建要使用的 Errors -> BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(person, "personName");

        // 3. 关联文案(调用 reject / rejectValue 方法)
        errors.reject("person.required");
        // 3.1 下面的 name 绑定 == person.getName()
        errors.rejectValue("name", "name.required");

        // 4. 获取错误信息(获取 ObjectError / FieldError)
        //  4.1 getGlobalErrors() 获取传入对象的错误信息
        //  4.2 获取传入对象的属性的错误信息
        //  4.3 获取对象和属性的所有错误，因为 FieldError extends ObjectError
        List<ObjectError> globalObjectErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        // 5. 创建 MessageSource -> 使用 StaticMessageSource
        MessageSource messageSource = getMessageSource();

        for (ObjectError e : allErrors) {
            // 6. MessageSource 绑定 Error codes
            String message = messageSource.getMessage(e.getCode(), e.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    private static MessageSource getMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        // 对象错误信息
        messageSource.addMessage("person.required", Locale.getDefault(), "当前用户不能为空！");

        // 属性错误信息
        messageSource.addMessage("name.required", Locale.getDefault(), "用户名不能为空！");
        return messageSource;
    }


}
