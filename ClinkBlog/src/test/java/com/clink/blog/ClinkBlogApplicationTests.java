package com.clink.blog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.clink.blog.model.Article;
import com.clink.blog.service.ArticleService;
import com.clink.blog.vm.ResultVm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ClinkBlogApplicationTests {

	@MockBean
	private ArticleService articleService;

 
 
	private Article article = new Article();
	private BindingResult bindingResult = new BindException(article, "article");

	@Autowired
	Validator validator;

 
	@Test
	public void give_NullContent_thenReturnFieldError() {
		article.setTitle("title");
		article.setAuthor("author");
		article.setDate("2020202020");
		validator.validate(article, bindingResult);
		assertThat(bindingResult.getFieldError().toString()).contains("Content cannot be null");
	}

	@Test
	public void give_BiggerThan100Title_thenReturnFieldError() {
		article.setTitle(
				"fdmskdfmnddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
		article.setAuthor("author");
		article.setContent("content");
		article.setDate("2020202020");
		validator.validate(article, bindingResult);
		assertThat(bindingResult.getFieldError().toString()).contains("Title cannot be bigger than 100");
	}

	@Test
	public void given_BiggerThan100Title_thenReturnValidationError() throws Exception {
		article.setTitle(
				"fdmskdfmnddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
		article.setAuthor("author");
		article.setContent("content");
		article.setDate("2020202020");

		TestRestTemplate testRestTemplate = new TestRestTemplate("mmert", "12345");
		ResponseEntity<ResultVm> response = testRestTemplate.postForEntity("http://localhost:9000/api/article/articles",
				article, ResultVm.class);

		assertThat(response.getBody().resultMessages).contains("Title cannot be bigger than 100");
	}

	@Test
	public void given_InvalidArticle_thenReturnValidationError() throws Exception {

		TestRestTemplate testRestTemplate = new TestRestTemplate("mmert", "12345");
		ResponseEntity<ResultVm> response = testRestTemplate.postForEntity("http://localhost:9000/api/article/articles",article,
				ResultVm.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(response.getBody().isSuccess, false);
	}

	@Test
	public void given_InvalidDate_thenNoValidationError() {

	}

	@Test
	public void given_UsingHaveUserRole_thenAuthorizedException() {
		TestRestTemplate testRestTemplate = new TestRestTemplate("mmert", "12345");
		ResponseEntity<ResultVm> response = testRestTemplate
				.getForEntity("http://localhost:9000/api/article/getArticlesStatistics", ResultVm.class);

		assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
		assertEquals(response.getBody().isSuccess, false);
		assertThat(response.getBody().resultMessages).contains("User:mmert, Role:ROLE_USER haven't authorize this page!");

	}
		
	@Test
	public void given_UsingHaveAdminRole_thenOkResult() {
		TestRestTemplate testRestTemplate = new TestRestTemplate("admin", "12");
		ResponseEntity<ResultVm> response = testRestTemplate
				.getForEntity("http://localhost:9000/api/article/getArticlesStatistics", ResultVm.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().isSuccess, true);
 
	}

 

}
