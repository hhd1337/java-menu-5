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

        List<Coach> coaches = readCoaches();
        WeeklyCategoryPlan weeklyCategoryPlan = createWeeklyCategoryPlan();
        List<CoachMenuPlan> coachMenuPlans = createCoachMenuPlans(coaches, weeklyCategoryPlan);
        MenuRecommendResult menuRecommendResult = new MenuRecommendResult(weeklyCategoryPlan, coachMenuPlans);
        
    }

    private List<CoachMenuPlan> createCoachMenuPlans(List<Coach> coaches, WeeklyCategoryPlan weeklyCategoryPlan) {
        return coaches.stream()
                .map(coach -> {
                    CoachMenuPlan coachMenuPlan = new CoachMenuPlan(coach.getName());
                    coachMenuPlan.generateFoods(weeklyCategoryPlan, coach);
                    return coachMenuPlan;
                })
                .collect(Collectors.toList());
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