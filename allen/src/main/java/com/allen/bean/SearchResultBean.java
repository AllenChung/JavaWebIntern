package com.allen.bean;

// bean for search result
public class SearchResultBean {

	private String name;
	
	private int score;
	
	public SearchResultBean() {}
	
	public SearchResultBean(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
