package com.epam.service;

import com.epam.model.CategoriesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    @Autowired
    CategoriesProperties categoriesProperties;

    public boolean checkCategoryWithId(Integer id) {
        return id > categoriesProperties.getMinId() && id <= categoriesProperties.getMaxId();
    }

    public boolean checkCategoryWithSubcategories(String subcategories) {
        boolean contains = false;
        for (String s : categoriesProperties.getSubcategories()) {
            if (subcategories.equalsIgnoreCase(s)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public boolean checkCategoryWithTranslations(String translation) {
        boolean contains = false;
        for (String s : categoriesProperties.getTranslation()) {
            if (translation.equals(s)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
