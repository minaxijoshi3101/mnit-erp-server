package com.mnit.erp.validation.validators;

import com.mnit.erp.validation.constraints.CurrentYearConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Year;

public class CurrentYearValidator implements
        ConstraintValidator<CurrentYearConstraint, Long> {

    @Override
    public void initialize(CurrentYearConstraint contactNumber) {
    }

    @Override
    public boolean isValid(Long yearField,
                           ConstraintValidatorContext cxt) {
        int currentYear = Year.now().getValue();
        return yearField != null && yearField.toString().matches("^\\d{4}$")
                && yearField == currentYear;
    }

}
