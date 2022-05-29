package com.clink.blog.dto;

import java.util.List;

import com.clink.blog.model.*;

public class ArticlePagedDto extends PagedDataDto {
	 private List<Article> list;

	public List<Article> getList() {
		return list;
	}

	public void setList(List<Article> list) {
		this.list = list;
	}
	

}
