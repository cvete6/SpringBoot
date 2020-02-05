package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.exceptions.InvalidIngredientException;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.jpa.IngredientsJpaRepository;
import mk.finki.ukim.mk.lab.repository.jpa.PizzaJpaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersistentPizzaServiceImpl implements PizzaService {

    private PizzaJpaRepository pizzaJpaRepository;
    private IngredientsJpaRepository ingredientsJpaRepository;

    public PersistentPizzaServiceImpl(PizzaJpaRepository pizzaJpaRepository, IngredientsJpaRepository ingredientsJpaRepository){
        this.pizzaJpaRepository=pizzaJpaRepository;
        this.ingredientsJpaRepository=ingredientsJpaRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return pizzaJpaRepository.findAll();
    }

    @Override
    public List<Pizza> listPizzasWithIngredient(String name) {
        return pizzaJpaRepository.getAllByIngredientName(name);
    }

    @Override
    public Pizza addNewPizza(String name, String description, boolean veggie, String ingredientId) {

        List<Ingredient> pizzaIngredients=new ArrayList<>();
        Ingredient ingredient=ingredientsJpaRepository.findByName(ingredientId);
        if(veggie && !ingredient.isVeggie()) throw new InvalidIngredientException();

        pizzaIngredients.add(ingredient);
        Pizza newPizza=new Pizza(name,description,pizzaIngredients,veggie);
        return pizzaJpaRepository.save(newPizza);

    }

    @Override
    public Pizza editPizza(String name, String description, boolean veggie, String ingredientId) {
        Pizza existingPizza=pizzaJpaRepository.findByName(name);
        existingPizza.setDescription(description);
        existingPizza.setVeggie(veggie);

        List<Ingredient> pizzaIngredients=existingPizza.getIngredients();
        Ingredient addIngredient=ingredientsJpaRepository.findByName(ingredientId);
        if(veggie && !addIngredient.isVeggie()) throw new InvalidIngredientException();

        if(!pizzaIngredients.contains(addIngredient)) pizzaIngredients.add(addIngredient);
        existingPizza.setIngredients(pizzaIngredients);
        System.out.println(addIngredient.getName());
        return pizzaJpaRepository.save(existingPizza);
    }

    @Override
    public void deletePizza(String id) {
        Pizza oldPizza=pizzaJpaRepository.findByName(id);
        pizzaJpaRepository.delete(oldPizza);
    }

    @Override
    public List<Pizza> listPizzasWithNumIngredients(Long numIngredients) {
        return pizzaJpaRepository.getAllByNumberOfIngredients(numIngredients);
    }

    @Override
    public Pizza getById(String name) {
        return pizzaJpaRepository.findByName(name);
    }

    @Override
    public List<Ingredient> getSameIngredients(String pizzaName1, String pizzaName2) {
        List<Ingredient> pizza1Ingredients=pizzaJpaRepository.getIngredients(pizzaName1);
        List<Ingredient> pizza2Ingredients=pizzaJpaRepository.getIngredients(pizzaName2);
        pizza1Ingredients.retainAll(pizza2Ingredients);
        return pizza1Ingredients;
    }
}
