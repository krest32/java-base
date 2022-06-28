package JavaSE.NewFeature;

public interface NewInterface {
    void method1(String str);

    //a default method
    default void log(String str){
        System.out.println("I1 logging::"+str);
    }
}
