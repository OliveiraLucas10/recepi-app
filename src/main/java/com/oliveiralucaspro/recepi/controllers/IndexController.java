package com.oliveiralucaspro.recepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oliveiralucaspro.recepi.services.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final RecipeService recipeService;

    @GetMapping({ "", "/", "/index" })
    public String getIndexPage(Model model) {
	log.debug("Getting Index page");

	model.addAttribute("recipes", recipeService.getRecipes());

	return "index";
    }
}
