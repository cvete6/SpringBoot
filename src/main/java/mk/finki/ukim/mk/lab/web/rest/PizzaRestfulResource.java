package mk.finki.ukim.mk.lab.web.rest;


import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.impl.PersistentIngredientServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.PersistentPizzaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/pizzas")
public class PizzaRestfulResource {

    private PersistentPizzaServiceImpl persistentPizzaService;
    private PersistentIngredientServiceImpl persistentIngredientService;

    public PizzaRestfulResource(PersistentPizzaServiceImpl persistentPizzaService, PersistentIngredientServiceImpl persistentIngredientService){
        this.persistentPizzaService=persistentPizzaService;
        this.persistentIngredientService=persistentIngredientService;
    }

    @GetMapping
    public List<Pizza> getAllPizzas(@RequestParam(value = "totalIngredients", required = false) Long totalIngredients){
        if(totalIngredients!=null)
            return persistentPizzaService.listPizzasWithNumIngredients(totalIngredients);

        return persistentPizzaService.listPizzas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createPizza(@RequestParam("name") String name,
                                       @RequestParam("description") String description,
                                       @RequestParam(value = "ingredientId") String ingredientId,
                                       @RequestParam(value = "veggie", defaultValue = "false") boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder){
        Pizza newPizza=persistentPizzaService.addNewPizza(name,description,veggie,ingredientId);
        response.setHeader("Location", builder.path("/rest/pizzas/{id}").buildAndExpand(newPizza.getName()).toUriString());
        return newPizza;
    }

    @PutMapping("/{id}")
    public Pizza updatePizza(@PathVariable String id,
                             @RequestParam("description") String description,
                             @RequestParam(value = "ingredientId") String ingredientId,
                             @RequestParam(value = "veggie", defaultValue = "false") boolean veggie){

        return persistentPizzaService.editPizza(id, description, veggie, ingredientId);
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable String id) {
        persistentPizzaService.deletePizza(id);
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable String id){
        return persistentPizzaService.getById(id);
    }

    @GetMapping("/compare")
    public List<Ingredient> getSameIngredients(@RequestParam("pizza1") String pizzaName1,
                                               @RequestParam("pizza2") String pizzaName2){

        return persistentPizzaService.getSameIngredients(pizzaName1,pizzaName2);

    }
}
