package com.lowes.interview.sample.exception;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lowes.interview.sample.pojo.QuizRoot;

@ControllerAdvice
public class QuizServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	protected ResponseEntity<Object> handleConflict(ServiceException ex, WebRequest request) {
		/*
		 * Silently catch exception and return empty list in
		 * case of service exception as per the statement 
		 * Status Code -200 Ok Just give one Reponse
		 */
		QuizRoot quiz= new QuizRoot(new ArrayList<>());
		return handleExceptionInternal(ex, quiz, new HttpHeaders(), HttpStatus.OK, request);
	}
}
