import java.util.HashMap;
import java.util.Map;

public class MostCommonChar {


    public static char mostCommonChar(String text) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c: text.toCharArray()) {
            if (!charCount.containsKey(c)) {
                charCount.put(c, 1);
            } else {
                int count = charCount.get(c);
                charCount.put(c, count + 1);
            }
        }

        char mostCommon = '~';
        int mostCount = -1;
        for (char c: charCount.keySet()) {
            if (charCount.get(c) > mostCount) {
                mostCommon = c;
                mostCount = charCount.get(c);
            }
        }

        return mostCommon;
    }

    public static void main(String[] args) {

        String s = "jakke";
        System.out.println(mostCommonChar(s));
    }
}
