package JavaSE.Reflect;

public class HelloWorld {
    public int i = 0;
    int j = 1;

    public HelloWorld() {
    }

    public HelloWorld(int i) {
        this.i = i;
    }

    public int getI() {
        System.out.println(i);
        return i;
    }

    public void setI(int i) {
        System.out.println(i);
        this.i = i;
    }
}
