package com.clink.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clink.blog.model.Article;

public interface ArticleService {

	Page<Article> findAll(Pageable pageable);

	Article save(Article entity);

	long countByCreatedAtLastSevenDays();

}
