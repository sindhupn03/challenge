package com.lowes.interview.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lowes.interview.sample.exception.ServiceException;
import com.lowes.interview.sample.pojo.QuizRoot;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/coding/exercise")
public class QuizController {
	@Autowired
	private QuizService quizService;

	@ApiResponses(value = {
			@ApiResponse(code = 200, response = QuizRoot.class, message = "") })
	@ApiOperation(value = "Hits the opentdb endpoint with amount 5, category 11& 12 and returns the combined result")
	@GetMapping("/quiz")
	public ResponseEntity<?> getCombinedResult() throws ServiceException {
		QuizRoot combinedResult=  quizService.getCombinedResult("https://opentdb.com/api.php?amount=5&category=11",
				"https://opentdb.com/api.php?amount=5&category=12");
		return new ResponseEntity<>(combinedResult, HttpStatus.OK);

	}

}
