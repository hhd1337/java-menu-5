package menu.domain;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class MenuRecommendResult {
    private Map<DayOfWeek, FoodCategory> categories;
    private List<CoachMenuPlan> coachMenuPlans;

    public MenuRecommendResult(Map<DayOfWeek, FoodCategory> categories, List<CoachMenuPlan> coachMenuPlans) {
        this.categories = categories;
        this.coachMenuPlans = coachMenuPlans;
    }


}