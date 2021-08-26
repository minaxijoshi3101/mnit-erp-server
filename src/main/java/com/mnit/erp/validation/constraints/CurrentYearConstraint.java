package com.mnit.erp.validation.constraints;

import com.mnit.erp.validation.validators.CurrentYearValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrentYearValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentYearConstraint {
    String message() default "{year.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}