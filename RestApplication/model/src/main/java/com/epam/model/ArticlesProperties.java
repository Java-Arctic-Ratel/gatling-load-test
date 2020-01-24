package com.epam.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("articles")
public class ArticlesProperties {
    private int minId;
    private int maxId;

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
}
