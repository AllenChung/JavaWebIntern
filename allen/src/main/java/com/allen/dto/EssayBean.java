package com.allen.dto;

import java.text.SimpleDateFormat;

import com.allen.model.Essay;

//bean for translate entity
public class EssayBean {

	private String id;

	private String title;

	private String content;

	private String author;

	private String date;
	
	public EssayBean() {}

	public EssayBean(Essay essay) {
		this.author = essay.getAuthor();
		this.title = essay.getTitle();
		this.content = essay.getContent();
		this.id = essay.getId();
		this.date = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(essay.getDate());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
