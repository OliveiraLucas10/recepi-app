package com.oliveiralucaspro.recepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oliveiralucaspro.recepi.commands.IngredientCommand;
import com.oliveiralucaspro.recepi.services.IngredientService;
import com.oliveiralucaspro.recepi.services.RecipeService;
import com.oliveiralucaspro.recepi.services.UnitOfMeasureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
	log.debug("Getting ingredient list for recipe id: " + recipeId);

	// use command object to avoid lazy load errors in Thymeleaf.
	model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

	return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
	model.addAttribute("ingredient",
		ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
	return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
	model.addAttribute("ingredient",
		ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

	model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
	return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
	IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

	log.debug("saved receipe id:" + savedCommand.getRecipeId());
	log.debug("saved ingredient id:" + savedCommand.getId());

	return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
