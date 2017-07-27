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

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="ID")
	private String id;
	
	@Column(name="TITLE")
	private String title;

	@Column(name="CONTENT")
	private String content;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="CREATED_DATE",columnDefinition="TIMESTAMP")
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
