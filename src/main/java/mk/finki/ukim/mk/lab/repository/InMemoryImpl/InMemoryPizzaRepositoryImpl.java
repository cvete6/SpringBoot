package mk.finki.ukim.mk.lab.repository.InMemoryImpl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryPizzaRepositoryImpl implements PizzaRepository {

    @Override
    public List<Pizza> getAllPizzas() {
        System.out.println("[WP-Log] getAllPizzas from InMemoryPizzaRepositoryImpl");
        return new ArrayList<>(DataHolder.pizzas);
    }
    //DataHolder.pizzas - za da zemame pod od dataHolder
}
