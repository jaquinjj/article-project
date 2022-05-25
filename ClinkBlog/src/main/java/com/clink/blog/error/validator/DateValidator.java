package com.clink.blog.error.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

	public DateValidator() {
	}

	@Override
	public void initialize(ValidDate contactNumber) {
		ConstraintValidator.super.initialize(contactNumber);

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

//		
//		if (value.length() > 100) {
//			return false;
//		}
		
//		if (GenericValidator.isDate(value,"yyyy",true)) {
//			return true;
//		}
//		
//		if (GenericValidator.isDate(value,"yyyy-MM",true)) {
//			return true;
//		}
//		
//		if (GenericValidator.isDate(value,"yyyy-MM-dd",true)) {
//			return true;
//		}
//		
//		if (GenericValidator.isDate(value,"yyyy-MM-ddThh",true)) {
//			return true;
//		}
//		
//		if (GenericValidator.isDate(value,"yyyy-mm-ddThhZ",true)) {
//			return true;
//		}
//		
//		if (GenericValidator.isDate(value,"yyyy-mm-ddThh:mm",true)) {
//			return true;
//		}
//
//		if (GenericValidator.isDate(value,"yyyy-mm-ddThh:mmZ",true)) {
//			return true;
//		}
//		
//		if (GenericValidator.isDate(value,"yyyy-mm-ddThh:mm:ssZ",true)) {
//			return true;
//		}
//		if (GenericValidator.isDate(value,"yyyy-mm-dd'T'hh:mm:ssZ",true)) {
//			return true;
//		}
		
		
 

		
 		return false;

	}
}
