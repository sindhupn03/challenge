package com.lowes.interview.sample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowes.interview.sample.exception.ServiceException;
import com.lowes.interview.sample.pojo.ApiResponse;
import com.lowes.interview.sample.pojo.Quiz;
import com.lowes.interview.sample.pojo.QuizRoot;
import com.lowes.interview.sample.pojo.Result;
import com.lowes.interview.sample.pojo.TransformedResult;

@Service
public class QuizService {

	@Autowired
	RestTemplate restTemplate;

	public QuizRoot getCombinedResult(String endpoint1, String endpoint2) throws ServiceException {
		List<Result> results = new ArrayList<Result>();
		List<Quiz> quiz = new ArrayList<>();
		CompletableFuture<ApiResponse> result1 = getResults(endpoint1);
		CompletableFuture<ApiResponse> result2 = getResults(endpoint2);
		CompletableFuture.allOf(result1, result2).join();
		try {
			if (result1.get() == null || result2.get() == null) {
				throw new ServiceException("Unable to fetch data from rest endpoint");
			}
			results = result1.get().getResults();
			results.addAll(result2.get().getResults());

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		results.stream().collect(Collectors.groupingBy(Result::getCategory))
		.forEach((category, categoryList) -> {
			Quiz q = new Quiz();
			q.setCategory(category);
			q.setResults(categoryList.stream()
					.map(c -> new TransformedResult(c.getType(), c.getDifficulty(), c.getQuestion(),
							Stream.concat(c.getIncorrect_answers().stream(), Stream.of(c.getCorrect_answer()))
									.collect(Collectors.toList()),
							c.getCorrect_answer()))
					.collect(Collectors.toList()));
			quiz.add(q);
		});
		return new QuizRoot(quiz);
	}

	@Async
	public CompletableFuture<ApiResponse> getResults(String endpoint) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ApiResponse response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, ApiResponse.class).getBody();
		return CompletableFuture.completedFuture(response);
	}

}
