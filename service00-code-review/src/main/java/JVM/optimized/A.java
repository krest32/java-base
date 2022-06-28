package JVM.optimized;

public class A {
    int[] storage = new int[102400];
    int[] extra = new int[200000];
    public int[] execute() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return storage;
    }
}
