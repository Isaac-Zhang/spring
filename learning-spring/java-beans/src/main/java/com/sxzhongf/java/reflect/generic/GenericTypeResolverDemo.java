package com.sxzhongf.java.reflect.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link GenericTypeResolver} 代码示例
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/25
 * @see ParameterizedType
 * @see TypeVariable
 **/
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        displayGenericInfo(GenericTypeResolverDemo.class, List.class, "getString");
        displayGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");
        displayGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

        // 因为泛型类型具体化，因此具备了参数类型
        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    /**
     * 常规类作为 方法返回值
     *
     * @return
     */
    public static String getString() {
        return null;
    }

    /**
     * // 泛型擦写原则（不会生成字节码记录），因此无法获取到返回类型信息
     *
     * @param <E>
     * @return
     */
    public static <E> List<E> getList() {
        return null;
    }

    /**
     * 泛型参数具体化（会生成字节码记录）
     * 具备了 ParameterizedType 返回，否则返回 null
     *
     * @return
     */
    public static StringList getStringList() {
        return null;
    }


    private static void displayGenericInfo(Class<?> clazz, Class<?> genericItf, String methodName, Class<?>... argumentTypes) throws NoSuchMethodException {
        Method method = GenericTypeResolverDemo.class.getMethod(methodName, argumentTypes);

        Class<?> returnTypeClazz = GenericTypeResolver.resolveReturnType(method, clazz);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s) = %s \n", method.getName(), clazz.getSimpleName(), returnTypeClazz);

        Class<?> returnArgumentTypeClazz = GenericTypeResolver.resolveReturnTypeArgument(method, genericItf);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s \n", method.getName(), clazz.getSimpleName(), returnArgumentTypeClazz);

    }

    // 泛型参数具体化（会生成字节码记录）
    static class StringList extends ArrayList<String> {

    }
}
