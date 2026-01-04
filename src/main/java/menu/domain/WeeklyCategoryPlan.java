package menu.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WeeklyCategoryPlan {
    private Map<DayOfWeek, FoodCategory> categoriesByDay;

    public WeeklyCategoryPlan() {
        this.categoriesByDay = new EnumMap<>(DayOfWeek.class);
    }

    public void generateWeeklyCategoryPlan() {
        categoriesByDay.clear();
        FoodCategory randomCategory;
        for (int i = 1; i <= 5; i++) {
            do {
                randomCategory = FoodCategory.findBySymbol(Randoms.pickNumberInRange(1, 5));
            } while (categoryCountsInMap(randomCategory) >= 2);

            categoriesByDay.put(DayOfWeek.of(i), randomCategory);
        }
    }

    private int categoryCountsInMap(FoodCategory randomCategory) {
        return (int) categoriesByDay.values()
                .stream()
                .filter(foodCategory -> foodCategory == randomCategory)
                .count();
    }

    public List<FoodCategory> getCategoriesByDayInOrder() {
        List<FoodCategory> categories = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            categories.add(categoriesByDay.get(DayOfWeek.of(i)));
        }
        return categories;
    }

}
