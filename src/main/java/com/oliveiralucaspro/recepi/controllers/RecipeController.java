package com.oliveiralucaspro.recepi.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.oliveiralucaspro.recepi.commands.RecipeCommand;
import com.oliveiralucaspro.recepi.exceptions.NotFoundException;
import com.oliveiralucaspro.recepi.services.RecipeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

	model.addAttribute("recipe", recipeService.findById(new Long(id)));

	return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model) {
	model.addAttribute("recipe", new RecipeCommand());

	return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
	model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
	return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) {

	if (bindingResult.hasErrors()) {

	    bindingResult.getAllErrors().forEach(objectError -> {
		log.debug(objectError.toString());
	    });

	    return RECIPE_RECIPEFORM_URL;
	}

	RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

	return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {

	log.debug("Deleting id: " + id);

	recipeService.deleteById(Long.valueOf(id));
	return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {

	log.error("Handling not found exception");
	log.error(exception.getMessage());

	ModelAndView modelAndView = new ModelAndView();

	modelAndView.setViewName("404error");
	modelAndView.addObject("exception", exception);

	return modelAndView;
    }

}
