import java.util.HashMap;
import java.util.Map;


public class Count {

    public static Map<Character, Integer> count(String text) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c: text.toCharArray()) {
            if (!charCount.containsKey(c)) {
                charCount.put(c, 1);
            } else {
                int count = charCount.get(c);
                charCount.put(c, count + 1);
            }
        }
        return charCount;
    }



    public static void main(String[] args) {

        String text = "bob";
        System.out.println(count(text));




    }
}
