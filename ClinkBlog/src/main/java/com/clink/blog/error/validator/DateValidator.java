package com.clink.blog.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class DateValidator implements ConstraintValidator<ValidDate, String> {

	public DateValidator() {
	}

	@Override
	public void initialize(ValidDate date) {
		ConstraintValidator.super.initialize(date);

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {		
		try {
			DateTimeFormatter isoParser = ISODateTimeFormat.dateTimeNoMillis(); //I used joda-time because of too many different types of ISO 8601
			isoParser.parseDateTime(value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
 		}

	}
}
