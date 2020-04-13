package com.oliveiralucaspro.recepi.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
    public void testMockMVC() throws Exception {
	MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    public void testGetIndexPage() {
	String viewName = indexController.getIndexPage(model);

	assertEquals("index", viewName);
	verify(recipeService, times(1)).getRecipes();
    }

}
