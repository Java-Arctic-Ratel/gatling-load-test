package com.epam.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("search")
public class SearchProperties {
    private String[] account;
    private String[] translation;

    public String[] getAccount() {
        return account;
    }

    public void setAccount(String[] account) {
        this.account = account;
    }

    public String[] getTranslation() {
        return translation;
    }

    public void setTranslation(String[] translation) {
        this.translation = translation;
    }
}
