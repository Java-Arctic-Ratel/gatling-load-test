package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {

    @Autowired
    ArticlesProperties articlesProperties;

    public boolean checkArticleWithId(Integer id) {
        if (id > articlesProperties.getMin_id() && id <= articlesProperties.getMax_id()) {
            return true;
        } else {
            return false;
        }
    }
}
