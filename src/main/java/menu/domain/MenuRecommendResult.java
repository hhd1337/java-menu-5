package menu.domain;

import java.util.List;

public class MenuRecommendResult {
    private WeeklyCategoryPlan weeklyCategoryPlan;
    private List<CoachMenuPlan> coachMenuPlans;

    public MenuRecommendResult(WeeklyCategoryPlan weeklyCategoryPlan, List<CoachMenuPlan> coachMenuPlans) {
        this.weeklyCategoryPlan = weeklyCategoryPlan;
        this.coachMenuPlans = coachMenuPlans;
    }


}