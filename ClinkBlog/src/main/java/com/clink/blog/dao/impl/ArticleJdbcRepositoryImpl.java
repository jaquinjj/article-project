package com.clink.blog.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clink.blog.dao.ArticleJdbcRepository;
import com.clink.blog.dto.ArticlePagedDto;
import com.clink.blog.dto.PagedDataDto;
import com.clink.blog.model.Article;

@Repository
public class ArticleJdbcRepositoryImpl implements ArticleJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public ArticlePagedDto getPagedArticleList(PagedDataDto pagedData) {
		List<Article> articleList = jdbcTemplate.query("SELECT * FROM Article ORDER BY ID",
				new BeanPropertyRowMapper<Article>(Article.class));
		
		Integer totalCount = articleList.size();
		Integer pageSize = pagedData.getPageSize();
		Integer page = pagedData.getPage();
		
 		
 		
 		List<Article> resultArticleList = articleList
 				.stream()
 				.skip(pageSize*page)
 				.limit(pageSize)
 				.collect(Collectors.toList());
 		
		ArticlePagedDto articlePagedDto = new ArticlePagedDto();
 		articlePagedDto.setTotalCount(totalCount);
 		articlePagedDto.setPageSize(totalCount / pageSize);
 		articlePagedDto.setPage(page);
 		articlePagedDto.setList(resultArticleList);
		

		return articlePagedDto;
	}

}
