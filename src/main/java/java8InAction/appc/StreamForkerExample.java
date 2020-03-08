package java8InAction.appc;

import java8InAction.chap6.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java8InAction.chap6.Dish.menu;

public class StreamForkerExample {

  public static void main(String[] args) {
    processMenu();
  }

  private static void processMenu() {
    Stream<Dish> menuStream = menu.stream();

    StreamForker.Results results = new StreamForker<>(menuStream)
            .fork("shortMenu", s -> s.map(Dish::getName).collect(joining(", ")))
            .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
            .fork("mostCaloricDish", s -> s.reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                    .orElse(null))
            .fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
            .getResults();

    String shortMeny = results.get("shortMenu");
    int totalCalories = results.get("totalCalories");
    Dish mostCaloricDish = results.get("mostCaloricDish");
    Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

    System.out.println("Short menu: " + shortMeny);
    System.out.println("Total calories: " + totalCalories);
    System.out.println("Most caloric dish: " + mostCaloricDish);
    System.out.println("Dishes by type: " + dishesByType);
  }
}
