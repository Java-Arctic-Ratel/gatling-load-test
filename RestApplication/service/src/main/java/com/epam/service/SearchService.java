package com.epam.service;

import com.epam.model.SearchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    SearchProperties searchProperties;

    public boolean checkSearchWithAccount(String account) {
        boolean contains = false;
        for (String s : searchProperties.getAccount()) {
            if (account.equalsIgnoreCase(s)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public boolean checkSearchWithTranslations(String translation) {
        boolean contains = false;
        for (String s : searchProperties.getTranslation()) {
            if (translation.equals(s)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
