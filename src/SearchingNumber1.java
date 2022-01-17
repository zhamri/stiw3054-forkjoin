public class SearchingNumber1 {
    public static int searchNumber(int[] myArray, int key) {
        int count = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] == key) {
                count++;
            }
        }
        return count;
    }

    public static void main(String a[]) {
        int[] myArray = {8, 3, 5, 4, 6, 3, 7, 7, 3, 3, 10, 2};
        int key = 3;
        long startTime = System.currentTimeMillis();
        System.out.printf("%d found %d times", key, searchNumber(myArray, key));
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nExecution time: " + elapsedTime);
    }
}


/*
3 found 4 times
Execution time: 19
 */