import java.util.ArrayList;
import java.util.Collections;

public class ArrayListExercises {

    public static void main(String[] args) {

        //2.1 and 2.2 ArrayList
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(2);
        myList.add(5);
        myList.add(30);
        myList.add(19);
        myList.add(57);
        myList.add(1);
        myList.add(15);

        //2.3 ArrayList
        int n = 3;
        ArrayList<String> myStringList = new ArrayList<>();
        myStringList.add("I");
        myStringList.add("like");
        myStringList.add("to");
        myStringList.add("eat");
        myStringList.add("eat");
        myStringList.add("eat");
        myStringList.add("apples");
        myStringList.add("and");
        myStringList.add("bananas");

        //2.4
        ArrayList<String> permutation1 = new ArrayList<>();
        permutation1.add("a");
        permutation1.add("b");
        permutation1.add("c");
        ArrayList<String> permutation2 = new ArrayList<>();
        permutation2.add("a");
        permutation2.add("b");
        permutation2.add("c");


        //2.5
        String h = "Hello World";

        //2.6
        int m =5;
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(4);
        intList.add(5);
        intList.add(6);
        intList.add(5);
        intList.add(5);
        intList.add(2);
        intList.add(5);

//        for (int i = 0; i < myList.size(); i++) {
//            System.out.println(myStringList.get(i));
//        }

        unique(myList);
        allMultiples(myList);
        allStringsOfSize(myStringList, n);
        stringToListOfWords(h);
        removeAllInstances(intList, m);

        System.out.println(stringToListOfWords(h));

    }

    //2.1
    public static <E> boolean unique(ArrayList<E> myList) {
        for (int i = 0; i < myList.size(); i++) {
            for (int j = i + 1; j < myList.size(); j++) {
                if(myList.get(i).equals(myList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    //2.2
    public static ArrayList<Integer> allMultiples(ArrayList<Integer> myList) {
        ArrayList<Integer> mults = new ArrayList<>();
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i) % 5 == 0) {
                mults.add(myList.get(i));
            }
        }
        return mults;
    }

    //2.3
    public static ArrayList<String> allStringsOfSize(ArrayList<String> myStringList, int n) {
        ArrayList<String> lengthList = new ArrayList<>();
        for (int i = 0; i < myStringList.size(); i++) {
            String str = myStringList.get(i);
            if (str.length() == n) {
                lengthList.add(str);
            }
        }
        return lengthList;
    }

    //2.4 isPermutation
    public static <E> boolean isPermutation(ArrayList<E> A, ArrayList<E> B) {
        if (A.size() != B.size()) {
            return false;
        }

        for (E item: A) {
            int countA = 0;
            int countB = 0;

            for (int i = 0; i < A.size(); i++) {
                if(A.get(i).equals(item)) {
                    countA++;
                }
            }
            for (int i = 0; i < B.size(); i++) {
                if(B.get(i).equals(item)) {
                    countB++;
                }
            }
            if (countA != countB) {
                return false;
            }
        }
        return true;
    }


    public static ArrayList<String> stringToListOfWords(String s) {
        ArrayList<String> stringList = new ArrayList<>();
        String[] split = s.split("\\s+");
        for (int i = 0; i < split.length ; i++) {
            stringList.add(split[i]);
        }
        return stringList;
    }

    //2.6
    public static <E> void removeAllInstances(ArrayList<E> intList, E item) {
        for (int i = 0; i < intList.size(); i++) {
            if(intList.get(i) == item) {
                intList.remove(i);
                i--;
            }
        }
    }
}
