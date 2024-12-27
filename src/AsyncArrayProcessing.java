import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncArrayProcessing  {


    private static char generateRandomChar() {
        int random = (int) (Math.random() * 5); // Рандомізуємо вибір між 5 варіантами
        return switch (random) {
            case 0 -> '\t'; // Табуляція
            case 1 ->
                // Генеруємо випадкову цифру (0-9)
                    (char) ('0' + Math.random() * 10);
            case 2 ->
                // Генеруємо випадкову маленьку літеру (a-z)
                    (char) ('a' + Math.random() * 26);
            case 3 ->
                // Генеруємо випадкову велику літеру (A-Z)
                    (char) ('A' + Math.random() * 26);
            case 4 -> ' '; // Пробіл
            default -> ' '; // Якщо випадково вибраний інший варіант, повертаємо пробіл
        };
    }

    public static void main(String[] args) {

        CompletableFuture<char[]> generateArrayFuture = CompletableFuture.supplyAsync(() -> {
            long startTime = System.nanoTime();
            char[] array = new char[20];
            for (int i = 0; i < array.length; i++) {
                array[i] = generateRandomChar();
            }
            long endTime = System.nanoTime();
            System.out.println("Time taken to generate array: " + (endTime - startTime) / 1_000_000 + " ms");
            return array;
        });
        generateArrayFuture.thenAcceptAsync(array -> {
            long startTime = System.nanoTime();
            System.out.println("Initial array: ");
            System.out.println(Arrays.toString(array));
            System.out.println();
            long endTime = System.nanoTime();
            System.out.println("Time taken to display array: " + (endTime - startTime) / 1_000_000 + " ms");
        });



        CompletableFuture<Void> filterFuture = generateArrayFuture.thenRunAsync(() -> {
            long startTime = System.nanoTime();
            char[] alphabetChars = new char[20];
            char[] spaceChars = new char[20];
            char[] tabChars = new char[20];
            char[] otherChars = new char[20];
            int[] counts = {0, 0, 0, 0};


            try {
                char[] array = generateArrayFuture.get();
                for (char c : array) {
                    if (Character.isAlphabetic(c)) {
                        alphabetChars[counts[0]++] = c;
                    } else if (c == ' ') {
                        spaceChars[counts[1]++] = c;
                    } else if (c == '\t') {
                        tabChars[counts[2]++] = c;
                    } else {
                        otherChars[counts[3]++] = c;
                    }
                }
                System.out.println("Alphabet characters: ");
                for (int i = 0; i < counts[0]; i++) {
                    System.out.print(alphabetChars[i] + "-");
                }
                System.out.println();

                System.out.println("Space characters: ");
                for (int i = 0; i < counts[1]; i++) {
                    System.out.print(spaceChars[i] + "-");
                }
                System.out.println();

                System.out.println("Tab characters: ");
                for (int i = 0; i < counts[2]; i++) {
                    System.out.print(tabChars[i] + "-");
                }
                System.out.println();

                System.out.println("Other characters: ");
                for (int i = 0; i < counts[3]; i++) {
                    System.out.print(otherChars[i] + "-");
                }
                System.out.println();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            long endTime = System.nanoTime();
            System.out.println("Time taken to filter array: " + (endTime - startTime) / 1_000_000 + " ms");
        });


        generateArrayFuture.join();
        filterFuture.join();
    }
}