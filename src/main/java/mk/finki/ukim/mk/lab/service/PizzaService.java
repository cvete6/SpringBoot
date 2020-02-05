package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;

import java.util.List;

public interface PizzaService {

    List<Pizza> listPizzas();

    List<Pizza> listPizzasWithIngredient(String name);

    Pizza addNewPizza(String name, String description, boolean veggie, String ingredientId);

    Pizza editPizza(String name, String description, boolean veggie, String ingredientId);

    void deletePizza(String id);

    List<Pizza> listPizzasWithNumIngredients(Long numIngredients);

    Pizza getById(String name);

    List<Ingredient> getSameIngredients(String pizzaName1, String pizzaName2);
}
