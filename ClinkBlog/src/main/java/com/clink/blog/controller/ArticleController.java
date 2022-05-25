package com.clink.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clink.blog.dao.ArticleRepository;
import com.clink.blog.model.Article;
import com.clink.blog.utils.RoleUser;
import com.clink.blog.vm.ResultVm;

@RestController
@Validated
@RequestMapping("api/article")
public class ArticleController {
	
	@Autowired
	ArticleRepository articleRepository;
	
 
	@GetMapping("/articles")
    public ResultVm getAllArticles(
			@PageableDefault(page = 0, size = 20)
			@SortDefault.SortDefaults({
					@SortDefault(sort = "title", direction = Sort.Direction.DESC) 
			})
		Pageable pageable)
    {
		
		ResultVm resultVm = new ResultVm();
		resultVm.isSuccess = true;
		resultVm.resultSet = articleRepository.findAll(pageable);
        return resultVm;
    }
	
	
 	@PostMapping("/articles")
    public ResultVm createArticle(@Valid @RequestBody Article article)
    {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.loadUserByUsername("user");
 		
 		ResultVm resultVm = new ResultVm();
		resultVm.isSuccess = true;
		resultVm.resultSet = articleRepository.save(article);
        return resultVm;
    }
 	
 	
 	@RoleUser(role_name = "ROLE_ADMIN")
 	@GetMapping("/getArticlesStatistics")
    public ResultVm getArticlesStatistics(
			@PageableDefault(page = 0, size = 20)
			@SortDefault.SortDefaults({
					@SortDefault(sort = "title", direction = Sort.Direction.DESC) 
			})
		Pageable pageable)
    {
		
		ResultVm resultVm = new ResultVm();
		resultVm.isSuccess = true;
		resultVm.resultSet = articleRepository.findAll(pageable);
        return resultVm;
    }
}
