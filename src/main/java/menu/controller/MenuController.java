package menu.controller;

import java.util.List;
import menu.domain.Coach;
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

        List<Coach> coachList = inputHandler.inputCoachNames();

        coachList.forEach(coach -> {
            outputView.printCoachNotEatingFoodInputPrompt(coach.getName());
            inputHandler.inputCoachNotEatingFoods();
        });

        WeeklyCategoryPlan weeklyCategoryPlan = new WeeklyCategoryPlan();
        weeklyCategoryPlan.generateWeeklyCategoryPlan();
    }


}
