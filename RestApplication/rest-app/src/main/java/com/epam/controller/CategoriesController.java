package com.epam.controller;

import com.epam.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping
    public void getCategoryWithoutId(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public void getCategoryWithId(@PathVariable Integer id, HttpServletResponse response) {
        if (categoriesService.checkCategoryWithId(id)) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/all", params = {"subcategories", "translation"})
    @ResponseBody
    public void getCategoryWithIdAndParams(@PathVariable Integer id,
                                           @RequestParam("subcategories") String subcategories,
                                           @RequestParam("translation") String translation,
                                           HttpServletResponse response) {
        if (categoriesService.checkCategoryWithId(id) &&
                categoriesService.checkCategoryWithSubcategories(subcategories) &&
                categoriesService.checkCategoryWithTranslations(translation)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
