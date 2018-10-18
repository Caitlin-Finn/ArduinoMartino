package finnsaneproductions.arduinomartino.interfaces;

import java.util.List;

/**
 * Created by Tony on 2/22/14.
 */
public interface Drink {
    public String getName();
    public String getDescription();
    public List<PreparationStep> getPreparation();
}