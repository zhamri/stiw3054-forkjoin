import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ForkJoinTask extends RecursiveTask<Integer> {

    int[] myArray;
    int start, end;
    int key;

    public ForkJoinTask(int[] myArray, int start, int end, int key) {
        this.myArray = myArray;
        this.start = start;
        this.end = end;
        this.key = key;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread());
        int size = end - start + 1;
        if (size > 3) {
            int mid = (end + start) / 2;
            ForkJoinTask task1 = new ForkJoinTask(myArray, start, mid, key);
            ForkJoinTask task2 = new ForkJoinTask(myArray, mid + 1, end, key);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        } else {
            return findNumber();
        }
    }

    private Integer findNumber() {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (myArray[i] == key) {
                count++;
            }
        }
        return count;
    }
}

public class SearchingNumber2 {

    public static void main(String[] args) {
        int[] myArray = {8, 3, 5, 4, 6, 3, 7, 7, 3, 3, 10, 2};
        int key = 3;
        int start = 0;
        int end = myArray.length - 1;

        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinTask task = new ForkJoinTask(myArray, start, end, key);
        int result = pool.invoke(task);

        System.out.printf("%d found %d times", key, result);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nExecution time: " + elapsedTime);
    }
}