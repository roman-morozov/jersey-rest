package com.epam.grow.jerseyrest.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = IdMatchConstraint.class)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdMatch {
    String message() default "Id mismatch error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
