package com.arek.warehousetransfer.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdminOrManagerValidation.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminOrManager {
	String message() default "{com.arek.warehousetransfer.validators.AdminOrManager.error.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
