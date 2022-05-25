package com.clink.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.clink.blog.error.validator.ValidDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "ARTICLE")
@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max=100, message = "Title cannot be bigger than 100")
	@Column(name = "TITLE")
	private String title;

	@Column(name = "AUTHOR")
	@NotNull(message = "Author cannot be null")
	private String author;

	@Column(name = "CONTENT")
	@NotNull(message = "Content cannot be null")
	private String content;

//	@ValidDate
	@Column(name = "DATE")
	@NotNull(message = "Date cannot be null")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mmX")
	private String date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
