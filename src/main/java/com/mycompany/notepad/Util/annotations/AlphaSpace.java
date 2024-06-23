package com.mycompany.notepad.Util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^[a-zA-Z ]*$")  // Only alphabets and spaces are allowed
@ReportAsSingleViolation
public @interface AlphaSpace {
    String message() default "Invalid characters in name. Only alphabets and spaces are allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
