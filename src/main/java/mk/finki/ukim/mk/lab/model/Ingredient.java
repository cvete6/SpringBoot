package mk.finki.ukim.mk.lab.model;

import javax.persistence.*;

@Entity
@Table(name="ingredients")
public class Ingredient {

    @Id
    private String name;

    private boolean spicy;

    private float amount;

    private boolean veggie;

    public Ingredient() {
    }

    public Ingredient(String name, boolean spicy, float amount, boolean veggie) {
        this.name = name;
        this.spicy = spicy;
        this.amount = amount;
        this.veggie = veggie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpicy() {
        return spicy;
    }

    public void setSpicy(boolean spicy) {
        this.spicy = spicy;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isVeggie() {
        return veggie;
    }

    public void setVeggie(boolean veggie) {
        this.veggie = veggie;
    }
}
