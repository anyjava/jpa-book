/**
 * Created by anyjava on 2016. 3. 21..
 */
public class JavaTest {

    public static void main(String[] args) {

        repeat(10, () -> System.out.println("Hello, wordl"));

        int a = 10;


        new Thread(new Runnable(){

            public void run() {
                System.out.println("a = " + a);
            }

        }).run();
    }

    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) action.run();
    }
}
