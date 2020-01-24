package com.epam.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("categories")
public class CategoriesProperties {
    private int minId;
    private int maxId;
    private String[] subcategories;
    private String[] translation;

    public int getMinId() {
        return minId;
    }

    public void setMinId(int minId) {
        this.minId = minId;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public String[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(String[] subcategories) {
        this.subcategories = subcategories;
    }

    public String[] getTranslation() {
        return translation;
    }

    public void setTranslation(String[] translation) {
        this.translation = translation;
    }
}
