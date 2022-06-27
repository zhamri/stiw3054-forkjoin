package RecursiveTask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTask_E1 extends RecursiveTask<Integer> {

    private int myNumbers;

    public RecursiveTask_E1(int myNumbers) {
        this.myNumbers = myNumbers;
    }

    @Override
    protected Integer compute() {

        if (myNumbers > 100) {

            System.out.println("Run parallel..." + myNumbers);

            RecursiveTask_E1 task1 = new RecursiveTask_E1(myNumbers / 2);
            RecursiveTask_E1 task2 = new RecursiveTask_E1(myNumbers / 2);

            task1.fork();
            task2.fork();

            return task1.join() + task2.join();

        } else {
            System.out.println("Run sequential..." + myNumbers);
            return 2 * myNumbers;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveTask_E1 myObj = new RecursiveTask_E1(400);
        System.out.println(forkJoinPool.invoke(myObj));
    }
}
