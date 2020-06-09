package com.imooc.validartro;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解
 */

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidar.class)
public @interface MyConstarint {
    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
