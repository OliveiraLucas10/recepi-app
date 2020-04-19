package com.oliveiralucaspro.recepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oliveiralucaspro.recepi.services.IngredientService;
import com.oliveiralucaspro.recepi.services.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
	log.debug("Getting ingredient list for recipe id: " + recipeId);

	// use command object to avoid lazy load errors in Thymeleaf.
	model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

	return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
	model.addAttribute("ingredient",
		ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
	return "recipe/ingredient/show";
    }
}
