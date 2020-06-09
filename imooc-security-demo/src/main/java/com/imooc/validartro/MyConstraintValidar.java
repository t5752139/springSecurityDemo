package com.imooc.validartro;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

//泛型第一个是定义的注解
                                            //第二个是定义的参数
public class MyConstraintValidar implements ConstraintValidator<MyConstarint,Object> {

    /**
     * 初始化加载
     */
    @Override
    public void initialize(MyConstarint constraintAnnotation) {

    }

    @Override
    /**
     * 真正的校验逻辑
     * 在这个类里面可以用 注入spring容器
     */
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
