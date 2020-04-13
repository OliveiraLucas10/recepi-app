package com.oliveiralucaspro.recepi.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.oliveiralucaspro.recepi.services.RecipeService;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	indexController = new IndexController(recipeService);
    }

    @Test
    public void testGetIndexPage() {
	String viewName = indexController.getIndexPage(model);

	assertEquals("index", viewName);
	verify(recipeService, times(1)).getRecipes();
    }

}
