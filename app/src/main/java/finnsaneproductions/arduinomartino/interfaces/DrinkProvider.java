package finnsaneproductions.arduinomartino.interfaces;

import java.util.Collection;

public interface DrinkProvider {
     Collection<String> getDrinkNames();
     Drink getDrink(String name);
}
