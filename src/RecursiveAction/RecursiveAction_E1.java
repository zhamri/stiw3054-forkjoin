package RecursiveAction;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RecursiveAction_E1 extends RecursiveAction {

    private int myNumbers;

    public RecursiveAction_E1(int myNumbers) {
        this.myNumbers = myNumbers;
    }

    @Override
    protected void compute() {

        if (myNumbers > 100) {

            System.out.println("Run parallel..." + myNumbers);

            RecursiveAction_E1 task1 = new RecursiveAction_E1(myNumbers / 2);
            RecursiveAction_E1 task2 = new RecursiveAction_E1(myNumbers / 2);

            task1.fork();
            task2.fork();

        } else {
            System.out.println("Run sequential..." + myNumbers);
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveAction_E1 myObj = new RecursiveAction_E1(800);
        forkJoinPool.invoke(myObj);
    }
}
