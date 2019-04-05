import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllUnique {

    public static boolean allUnique(List<Integer> items) {
        Set<Integer> set = new HashSet<>();
        for (int item: items) {
            if (set.contains(item)) {
                return false;
            } else {
                set.add(item);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(1);
        System.out.println(allUnique(test));
    }
}
