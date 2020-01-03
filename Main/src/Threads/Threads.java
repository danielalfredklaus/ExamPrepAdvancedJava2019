package Threads;

import java.util.stream.IntStream;

public class Threads {

    String[] array = {"Nein!", "Doch!", "Oohhh!"};

    public void printIt(){
        for(int i=0; i<3; i++){
            System.out.println(array[i]);
        }

    }

    static int sum = 0;

    public static void main(String[] args) throws Exception {
        Threads threads = new Threads();
        System.out.println(threads.fibonacci(6));
        //System.out.println(threads.fuckhisthreadedfib(35));

    }

    public static void mainA(String[] args) throws Exception {

        Threads main = new Threads();
        main.printIt();

        Thread thread1 = new Thread(() -> {
            synchronized (System.out){
                IntStream.range(0, 10_000).forEach(System.out::println);
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (System.out){
                IntStream.range(0, 10_000).forEach(System.out::println);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join(); //Join makes all the code below execute only when this thread finished
        thread2.join();

        System.out.println("Finished");
    }

    public static void main2(String[] args) throws Exception {

        /*
        public int FibonacciThreads (int N){
            int[] fiboArray = new int[10_000];
            Thread fibo1 = new Thread(()->{

                for(int i=0; i<=N-1; i++){
                    if (i==0 || i==1) {
                        fiboArray[i] = 1;
                    } else {
                        fiboArray[i] = fiboArray[i-1] + fiboArray[i-2];
                    }
                }
            });

        } */


    }
    public static int fibonacci(int N) throws Exception {
        return fibonacci(N, 2, 1, 1);
    }

    private static int fibonacci(int N, int I, int first, int second) throws Exception {
        if (I<N) return fibonacci(N, ++I, second, second+=first);
        else return second;
    }

    //DONT RUN THIS CRAP, IT SHUTS DOWN YOUR PC
    public int fuckhisthreadedfib(int n) {
        if(n==1) return 1;
        if(n==2) return 1;

        int[] result1={0, 0}; //You can store results from a thread in an array, but not in a single value
        int result2=0;

        Thread t1 = new Thread(() -> result1[0] = fuckhisthreadedfib(n-1));
        Thread t2 = new Thread(()-> result1[1] = fuckhisthreadedfib(n-2));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            return result1[0] + result1[1];
        } catch (Exception E) {
            //wont happen anyway
            return -1;
        }
    }


}
