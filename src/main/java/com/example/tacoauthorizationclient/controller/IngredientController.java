package com.example.tacoauthorizationclient.controller;

import com.example.tacoauthorizationclient.dto.Ingredient;
import com.example.tacoauthorizationclient.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.*;

@RestController
public class IngredientController {

    private IngredientService ingredientService;

    @Autowired
    private WebClient webClient;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @GetMapping("/api/hello")
    public String hello(Principal principal){
        return "hello " + principal.getName();
    }

//    @GetMapping("/ingredients")
//    public Iterable<Ingredient> getAllIngredients(){
//        return ingredientService.findAll();
//    }

    @GetMapping("/api/ingredients")
    public Iterable<Ingredient> getAllIngredients(@RegisteredOAuth2AuthorizedClient("taco-admin-client-authorization-code")
                                                              OAuth2AuthorizedClient client){
//        return ingredientService.findAll();
        return this.webClient
                .get()
                .uri("http://127.0.0.1:8090/api/ingredients")
                .attributes(oauth2AuthorizedClient(client))
                .retrieve()
                .bodyToMono(Iterable.class)
                .block();
    }
}
