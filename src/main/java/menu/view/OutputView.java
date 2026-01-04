package menu.view;

import java.util.List;
import menu.domain.FoodCategory;
import menu.util.ErrorMessage;

public class OutputView {

    public void printErrorMessage(Exception exception) {
        System.out.println(ErrorMessage.PREFIX + exception.getMessage());
    }

    public void printServiceStartHeader() {
        System.out.println("점심 메뉴 추천을 시작합니다.\n");
    }

    public void printCoachNameInputPrompt() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
    }

    public void printCoachNotEatingFoodInputPrompt(String coachName) {
        printEmptyLine();
        System.out.println(coachName + "(이)가 못 먹는 메뉴를 입력해 주세요.");
    }

    public void printMenuRecommendResultHeader() {
        printEmptyLine();
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
    }

    public void printMenuRecommendResultCategories(List<FoodCategory> categories) {
        System.out.print("[ 카테고리");
        categories.forEach(category -> System.out.print(" | " + category.getName()));
        System.out.println(" ]");

    }

    public void printCoachMenuRecommendResult(String coachName, List<String> foods) {
        System.out.print("[ " + coachName);
        foods.forEach(food -> System.out.print(" | " + food));
        System.out.println(" ]");
    }

    public void printMenuRecommendResultFooter() {
        printEmptyLine();
        System.out.println("추천을 완료했습니다.");
    }

    private void printEmptyLine() {
        System.out.println();
    }

}
