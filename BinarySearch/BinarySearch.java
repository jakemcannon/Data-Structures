public class BinarySearch {

    private static <E extends Comparable<E>> int binarySearch(E[] arr, E target) {
        return binarySearch(arr, target, 0, arr.length-1);
    }

    public static <E extends Comparable<E>> int binarySearch(E[] arr, E target, int start, int end) {
        if (end < start) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (arr[middle].compareTo(target) == 0) {
            return middle;
        } else if (target.compareTo(arr[middle]) < 0) {
            return binarySearch(arr ,target ,start , middle -1 );
        } else {
            return binarySearch(arr, target, middle + 1, end);
        }

    }


    public static void main(String[] args) {
        Integer[] arr = {1, 3, 5, 6, 7, 8 , 9, 100, 101};

        System.out.println(binarySearch(arr, 100));
    }
}
