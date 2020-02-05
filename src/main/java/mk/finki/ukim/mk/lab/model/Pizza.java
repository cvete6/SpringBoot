package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pizzas")
@NoArgsConstructor
public class Pizza {

    @Id
    private String name;

    private  String description;

    @ManyToMany
    @JoinTable(name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_name"),
            inverseJoinColumns = @JoinColumn(name = "ingredient"))
    private List<Ingredient> ingredients;

    private boolean veggie;

    public Pizza(String name, String description) {
        this.name=name;
        this.description=description;
    }

    public Pizza(String name, String description, List<Ingredient> ingredients, boolean veggie) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.veggie = veggie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean isVeggie() {
        return veggie;
    }

    public void setVeggie(boolean veggie) {
        this.veggie = veggie;
    }
}
