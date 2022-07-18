import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> wordsCount = new TreeMap<>();
        Arrays.stream(sc.nextLine().split("\\s")).forEach(s -> wordsCount.containsKey(s) ?
                wordsCount.put(s, wordsCount.get(s) + 1) :
                wordsCount.put(s, 1));
    }
}