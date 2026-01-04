package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class CoachMenuPlan {
    private String coachName;
    private List<String> sortedRecommendFoods;

    public CoachMenuPlan(String coachName) {
        this.coachName = coachName;
        this.sortedRecommendFoods = new ArrayList<>();
    }

    // 1. 코치가 못먹는 메뉴가 나오면 다시 랜덤 pick.
    // 2. sortedRecommendFoods에 이미 있는 음식이면 다시 pick.
    private String pickValidFood(FoodCategory foodCategory, Coach coach) {
        String food;
        do {
            food = foodCategory.findRandomFoodByCategory();
        } while (sortedRecommendFoods.contains(food) || coach.canNotEat(food));

        return food;
    }

    public String getCoachName() {
        return this.coachName;
    }

    public List<String> getSortedRecommendFoods() {
        return this.sortedRecommendFoods;
    }

    public void addFoodForCategory(FoodCategory category, Coach coach) {
        sortedRecommendFoods.add(pickValidFood(category, coach));
    }

}