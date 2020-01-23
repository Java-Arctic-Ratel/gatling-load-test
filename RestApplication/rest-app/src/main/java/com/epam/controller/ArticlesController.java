package com.epam.controller;

import com.epam.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/articles")
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public void getCategoryWithId(@PathVariable Integer id, HttpServletResponse response) {
        if (articlesService.checkArticleWithId(id)) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
