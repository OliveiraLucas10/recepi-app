package com.oliveiralucaspro.recepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oliveiralucaspro.recepi.commands.RecipeCommand;
import com.oliveiralucaspro.recepi.services.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

	model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
	return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
	model.addAttribute("recipe", new RecipeCommand());

	return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
	model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
	return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
	RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

	return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {

	log.debug("Deleting id: " + id);

	recipeService.deleteById(Long.valueOf(id));
	return "redirect:/";
    }
}
