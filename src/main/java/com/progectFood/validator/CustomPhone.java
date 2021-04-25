package com.progectFood.validator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface CustomPhone {
    String message() default "Phone is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}