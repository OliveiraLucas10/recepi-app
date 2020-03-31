package com.oliveiralucaspro.recepi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    
    @GetMapping({"", "/", "index", "index.html"})
    public String getIndexPage() {
	
	return "index";
    }

}
