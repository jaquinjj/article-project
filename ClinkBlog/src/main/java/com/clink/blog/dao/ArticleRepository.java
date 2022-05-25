package com.clink.blog.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.clink.blog.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>,PagingAndSortingRepository<Article, Long> {
	

 }