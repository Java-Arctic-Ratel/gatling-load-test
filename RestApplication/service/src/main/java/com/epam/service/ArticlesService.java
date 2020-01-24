package com.epam.service;

import com.epam.model.ArticlesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {

    @Autowired
    ArticlesProperties articlesProperties;

    public boolean checkArticleWithId(Integer id) {
        return id > articlesProperties.getMinId() && id <= articlesProperties.getMaxId();
    }
}
