package com.allen.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.allen.bean.EssayBean;

@Entity
@Table(name="ZAL_ESSAY")
public class Essay {

	private String id;
	
	private String title;
	
	private String content;
	
	private String author;
	
	private Date date;
	
	public Essay() {
		
	}
	
	public Essay(EssayBean essayBean) throws ParseException {
		this.author = essayBean.getAuthor();
		this.content = essayBean.getContent();
		this.id = essayBean.getId();
		this.title = essayBean.getTitle();
		this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(essayBean.getDate());
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="AUTHOR")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name="CREATED_DATE",columnDefinition="TIMESTAMP")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
