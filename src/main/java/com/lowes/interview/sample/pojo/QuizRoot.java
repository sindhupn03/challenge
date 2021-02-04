package com.lowes.interview.sample.pojo;

import java.util.List;

public class QuizRoot {
	List<Quiz> quiz;

	public List<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<Quiz> quiz) {
		this.quiz = quiz;
	}

	public QuizRoot(List<Quiz> quiz) {
		super();
		this.quiz = quiz;
	}
	
	
}
