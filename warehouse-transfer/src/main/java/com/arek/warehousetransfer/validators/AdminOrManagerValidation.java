package com.arek.warehousetransfer.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.validation.Validator;

public class AdminOrManagerValidation implements ConstraintValidator<AdminOrManager,String> {
	@Override
	public void initialize(AdminOrManager constraintAnnotation) {

	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return s.equalsIgnoreCase("admin") || s.equalsIgnoreCase(("manager"));
	}
}
