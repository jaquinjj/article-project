package com.clink.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clink.blog.dao.ArticleRepository;
import com.clink.blog.model.Article;
import com.clink.blog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Override
	public Page<Article> findAll(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	@Override
	public Article save(Article entity) {
		return articleRepository.save(entity);
	}

	@Override
	public long countByCreatedAtLastSevenDays() {
		ArrayList<Article> articleList = new ArrayList<>();
		articleRepository.findAll().forEach(e -> articleList.add(e));

		LocalDate lastWeek = LocalDateTime.fromDateFields(new Date()).toLocalDate();
		DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();

		long betweenDateCount = articleList.stream()
				.filter(
						x -> Days.daysBetween(lastWeek, LocalDateTime.parse(x.getDate(),dateTimeFormatter).toLocalDate()).getDays() >= -7
						)
				.collect(Collectors.toList())
				.size();

		return betweenDateCount;
	}

}
