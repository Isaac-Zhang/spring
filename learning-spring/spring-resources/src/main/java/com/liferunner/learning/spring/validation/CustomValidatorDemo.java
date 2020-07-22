package com.liferunner.learning.spring.validation;

import com.liferunner.learning.spring.pojo.Person;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;

import java.util.Locale;

import static com.liferunner.learning.spring.validation.SpringValidationErrorsDemo.getMessageSource;

/**
 * 自定义 {@link Validator} 示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/22
 **/
public class CustomValidatorDemo {

    public static void main(String[] args) {
        Person p = new Person();
        // 1. 创建 Validator
        PersonValidator validator = new PersonValidator();

        // 2. 验证 validator 是否支持
        System.out.println("当前类是否支持验证：" + validator.supports(p.getClass()));

        // 3. 创建 MessageSource
        MessageSource messageSource = getMessageSource();

        // 4. 创建 Error
        Errors errors = new BeanPropertyBindingResult(p, "person");

        // 5. 绑定校验
        validator.validate(p, errors);
        for (ObjectError e : errors.getAllErrors()) {
            // 6. MessageSource 绑定 Error codes
            String message = messageSource.getMessage(e.getCode(), e.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static class PersonValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return Person.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            Person p = (Person) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
            int age = p.getAge();
            if (age < 18) {
                errors.rejectValue("age", "age.min",
                        new Object[]{Integer.valueOf(18)},
                        "The age must be at least [" + 18 + "] years.");
            }
        }
    }
}
