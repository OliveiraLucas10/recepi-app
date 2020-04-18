package com.oliveiralucaspro.recepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oliveiralucaspro.recepi.commands.RecipeCommand;
import com.oliveiralucaspro.recepi.services.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {

	model.addAttribute("recipe", recipeService.findById(new Long(id)));

	return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {
	model.addAttribute("recipe", new RecipeCommand());

	return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
	RecipeCommand saveRecipeCommand = recipeService.saveRecipeCommand(command);

	return "redirect:/recipe/show/" + saveRecipeCommand.getId();
    }

}
