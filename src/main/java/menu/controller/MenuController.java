package menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.Coach;
import menu.domain.CoachMenuPlan;
import menu.domain.MenuRecommendResult;
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
        outputView.printCoachNameInputPrompt();
        List<String> coachNames = inputHandler.inputCoachNames();

        List<Coach> coaches = new ArrayList<>();
        coachNames.forEach(name -> {
            outputView.printCoachNotEatingFoodInputPrompt(name);
            coaches.add(inputHandler.inputCoachNotEatingFoods(name));
        });

        WeeklyCategoryPlan weeklyCategoryPlan = new WeeklyCategoryPlan();
        weeklyCategoryPlan.generateWeeklyCategoryPlan();

        // 음식 카테고리와 못먹는 메뉴에 따라 추천할 점심 메뉴를 구성함
        List<CoachMenuPlan> coachMenuPlans = coaches.stream()
                .map(coach -> {
                    CoachMenuPlan coachMenuPlan = new CoachMenuPlan(coach.getName());
                    coachMenuPlan.generateFoods(weeklyCategoryPlan, coach);
                    return coachMenuPlan;
                })
                .collect(Collectors.toList());

        MenuRecommendResult menuRecommendResult = new MenuRecommendResult(weeklyCategoryPlan.getCategoriesByDay(),
                coachMenuPlans);
    }
}