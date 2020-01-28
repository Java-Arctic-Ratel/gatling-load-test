package com.epam.controller;

import com.epam.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping(params = {"account", "translation"})
    @ResponseBody
    public void getCategoryWithIdAndParams(@RequestParam("account") String account,
                                           @RequestParam("translation") String translation,
                                           HttpServletResponse response) {
        if (searchService.checkSearchWithAccount(account) &&
                searchService.checkSearchWithTranslations(translation)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
