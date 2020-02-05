package mk.finki.ukim.mk.lab.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Can not add more spicy ingredients")
public class SpicyIngredientsNumberExceededException extends RuntimeException{
}
