package com.lowes.interview.sample.pojo;

import java.util.List;

public class Quiz{
    private String category;
    private  List<TransformedResult> results;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<TransformedResult> getResults() {
		return results;
	}
	public void setResults(List<TransformedResult> results) {
		this.results = results;
	}
    
    
}

