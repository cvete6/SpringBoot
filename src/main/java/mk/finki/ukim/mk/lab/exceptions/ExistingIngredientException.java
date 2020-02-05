package mk.finki.ukim.mk.lab.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Ingredient with same name already exists")
public class ExistingIngredientException extends RuntimeException {
}
