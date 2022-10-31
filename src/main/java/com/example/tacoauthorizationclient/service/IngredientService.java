package com.example.tacoauthorizationclient.service;

import com.example.tacoauthorizationclient.dto.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);
}
