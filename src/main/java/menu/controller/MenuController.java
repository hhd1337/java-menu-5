package menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.Coach;
import menu.domain.CoachMenuPlan;
import menu.domain.FoodCategory;
import menu.domain.WeeklyCategoryPlan;
import menu.view.OutputView;

public class MenuController {

    private final InputHandler inputHandler;
    private final OutputView outputView;

    public MenuController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void process() {
        outputView.printServiceStartHeader();

        List<Coach> coaches = readCoaches();
        WeeklyCategoryPlan weeklyCategoryPlan = createWeeklyCategoryPlan();

        List<CoachMenuPlan> coachMenuPlans = createEmptyCoachMenuPlans(coaches);
        fillCoachMenuPlans(coaches, weeklyCategoryPlan, coachMenuPlans);

        printResult(weeklyCategoryPlan, coachMenuPlans);
    }

    private void printResult(WeeklyCategoryPlan weeklyCategoryPlan, List<CoachMenuPlan> coachMenuPlans) {
        outputView.printMenuRecommendResultHeader();

        List<FoodCategory> categories = weeklyCategoryPlan.getCategoriesByDayInOrder();
        outputView.printMenuRecommendResultCategories(categories);

        for (CoachMenuPlan plan : coachMenuPlans) {
            outputView.printCoachMenuRecommendResult(plan.getCoachName(), plan.getSortedRecommendFoods());
        }
        outputView.printMenuRecommendResultFooter();
    }

    private List<CoachMenuPlan> createEmptyCoachMenuPlans(List<Coach> coaches) {
        return coaches.stream()
                .map(coach -> new CoachMenuPlan(coach.getName()))
                .collect(Collectors.toList());
    }

    private void fillCoachMenuPlans(
            List<Coach> coaches,
            WeeklyCategoryPlan weeklyCategoryPlan,
            List<CoachMenuPlan> coachMenuPlans
    ) {
        List<FoodCategory> categories = weeklyCategoryPlan.getCategoriesByDayInOrder();

        for (FoodCategory category : categories) {              // 월~금
            for (int i = 0; i < coaches.size(); i++) {          // 코치 순서
                coachMenuPlans.get(i).addFoodForCategory(category, coaches.get(i));
            }
        }
    }

    private WeeklyCategoryPlan createWeeklyCategoryPlan() {
        WeeklyCategoryPlan weeklyCategoryPlan = new WeeklyCategoryPlan();
        weeklyCategoryPlan.generateWeeklyCategoryPlan();
        return weeklyCategoryPlan;
    }

    private List<Coach> readCoaches() {
        outputView.printCoachNameInputPrompt();
        List<String> coachNames = inputHandler.inputCoachNames();
        List<Coach> coaches = new ArrayList<>();
        coachNames.forEach(name -> {
            outputView.printCoachNotEatingFoodInputPrompt(name);
            coaches.add(inputHandler.inputCoachNotEatingFoods(name));
        });
        return coaches;
    }
}