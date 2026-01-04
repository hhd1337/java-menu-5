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

    // 코치 별로, 못먹는 메뉴에 따라 음식 카테고리 참고하여 추천할 점심 메뉴를 구성함
    public void generateFoods(WeeklyCategoryPlan plan, Coach coach) {
        List<FoodCategory> foodCategoryList = new ArrayList<>(plan.getCategoriesByDay().values());

        foodCategoryList.forEach(foodCategory -> {
            sortedRecommendFoods.add(pickValidFood(foodCategory, coach));
        });
    }

    // 1. 코치가 못먹는 메뉴가 나오면 다시 랜덤 pick.
    // 2. sortedRecommendFoods에 이미 있는 음식이면 다시 pick.
    private String pickValidFood(FoodCategory foodCategory, Coach coach) {
        String food;
        do {
            food = foodCategory.findRandomFoodByCategory(foodCategory);
        } while (sortedRecommendFoods.contains(food) && coach.canNotEat(food));

        return food;
    }
}