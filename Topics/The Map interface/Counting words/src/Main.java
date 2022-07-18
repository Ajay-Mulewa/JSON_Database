import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        SortedMap<String, Integer> charCountMap = new TreeMap<>();
        for (String word : strings) {
            if (charCountMap.containsKey(word)) {
                charCountMap.put(word, charCountMap.get(word) + 1);
            } else {
                charCountMap.put(word, 1);
            }
        }
        return charCountMap;
    }

    public static void printMap(Map<String, Integer> map) {
        map.forEach((word, count) -> System.out.println(word + " : " + count));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}