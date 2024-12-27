import java.util.*;
import java.util.concurrent.CompletableFuture;

public class AsyncMaxDifference {

    static Random random = new Random();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        CompletableFuture asyncTask = CompletableFuture.supplyAsync(() -> {

            List<Double> numbers = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                numbers.add(random.nextDouble() * 100);
            }

            return numbers;
        }).thenApplyAsync(doubles -> {
            System.out.println("Створений масив: " + doubles);
            return doubles;
        }).thenApplyAsync(doubles -> {
            List<Double> differences = new ArrayList<>();
            for (int i = 0; i < doubles.size() - 1; i++) {
                differences.add(Math.abs(doubles.get(i) - doubles.get(i + 1)));
            }
            System.out.println("\nАбсолютні різниці між сусідніми елементами: " + differences);
            return differences;
        }).thenApplyAsync(Collections::max).thenAcceptAsync(maxDiff -> {
            System.out.printf("\nМаксимальна різниця між сусідніми елементами: %.2f\n", maxDiff);
        }).thenRunAsync(() -> {
            long endTime = System.nanoTime();  // Час завершення виконання
            long duration = endTime - startTime;  // Час виконання програми
            System.out.printf("\nЧас виконання асинхронних операцій: %.2f секунд\n", duration / 1_000_000_000.0);
        });

        asyncTask.join();
    }
}
