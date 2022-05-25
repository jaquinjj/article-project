package com.clink.blog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.clink.blog.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>, PagingAndSortingRepository<Article, Long> {

	public Page<Article> findAll(Pageable pageable);
	public Article save(Article article);


//	@Query("select count(*) from ARTICLE where date between  ")
//	long countByCreatedAtBetween(Date startDate, Date endDate);

}