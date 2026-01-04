package menu.domain;

import java.util.List;

public class Coach {
    private String name;
    private List<String> neverEatingFoods;

    public Coach(String name, List<String> neverEatingFoods) {
        this.name = name;
        this.neverEatingFoods = neverEatingFoods;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getNeverEatingFoods() {
        return this.neverEatingFoods;
    }
}
