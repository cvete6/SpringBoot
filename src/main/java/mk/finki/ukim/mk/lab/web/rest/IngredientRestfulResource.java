package mk.finki.ukim.mk.lab.web.rest;


import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.impl.PersistentIngredientServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.PersistentPizzaServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rest/ingredients")
public class IngredientRestfulResource {

    private PersistentIngredientServiceImpl persistentIngredientService;
    private PersistentPizzaServiceImpl persistentPizzaService;

    public IngredientRestfulResource(PersistentIngredientServiceImpl persistentIngredientService, PersistentPizzaServiceImpl persistentPizzaService){
        this.persistentIngredientService=persistentIngredientService;
        this.persistentPizzaService=persistentPizzaService;
    }

    @GetMapping
    public Page<Ingredient> getAllIngredients(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                              @RequestHeader(name = "page-size", defaultValue = "5", required = false) int size,
                                              @RequestParam(value = "spicy", required = false) boolean spicy ){
        if(!spicy)
            return persistentIngredientService.getAllIngredients(page,size);
        else
            return persistentIngredientService.getSpicy(page,size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestParam("name") String name,
                                       @RequestParam("spicy") boolean spicy,
                                       @RequestParam(value = "amount") float amount,
                                       @RequestParam(value = "veggie") boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder){

        Ingredient newIngredient=persistentIngredientService.addNewIngredient(name,spicy,amount,veggie);
        response.setHeader("Location", builder.path("/rest/ingredients/{id}").buildAndExpand(newIngredient.getName()).toUriString());
        return newIngredient;
    }

    @PatchMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable String id,
                                 @RequestParam("spicy") boolean spicy,
                                 @RequestParam(value = "amount") float amount,
                                 @RequestParam(value = "veggie") boolean veggie) {
        return persistentIngredientService.editIngredient(id, spicy, amount, veggie);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        persistentIngredientService.deleteIngredient(id);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable String id){
        return persistentIngredientService.getById(id);
    }

    @GetMapping("/{id}/pizzas")
    public List<Pizza> getPizzasWithIngredient(@PathVariable String id){
        return persistentPizzaService.listPizzasWithIngredient(id);
    }


}
