package Threads;

import java.util.function.BiFunction;
import java.util.function.Function;

//File provided by Kitlei
public class Threading
{
    public static void main(String[] args) throws Exception {
        // main2(args);
        // main3(args);
        // main3b(args);
        main3c(args);
        // main4(args);
        // main5(args);
    }

    public static void main5(String[] args) throws Exception {
        MyClass myClass = new MyClass();

        Thread t1 = new Thread(() -> {
            myClass.myFun1();
            myClass.myFun2();
        });
        Thread t2 = new Thread(() -> {
            myClass.myFun1();
            myClass.myFun2();
        });

        t1.start();		// race condition
        t2.start();
        t1.join();
        t2.join();

        System.out.println(myClass.myData1);
        System.out.println(myClass.myData2);
    }

    public static void main4(String[] args) throws Exception {
        new Thread(() -> {
            for (int i = 0; i < 3000; ++i) {
                synchronized (System.out) {
                    // System.out.println("Hello");
                    for (char c : "Hello".toCharArray()) {
                        System.out.print(c);
                    }
                    System.out.println();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3000; ++i) {
                synchronized (System.out) {
                    // System.out.println("world");
                    for (char c : "world".toCharArray()) {
                        System.out.print(c);
                    }
                    System.out.println();
                }
            }
        }).start();
    }

    public static void main3c(String[] args) throws Exception {
        Object lock = new Object();
        int[] counter = { 0 };
        String txt = "abc";

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; ++i) {
                // monitor; lock; mutual exclusion; critical section
                synchronized (counter) {
                    ++counter[0];
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; ++i) {
                synchronized (counter) {
                    // synchronized (txt) {
                    // synchronized (lock) {
                    // synchronized (Parhuzamossag.class) {
                    // synchronized (System.out) {
                    ++counter[0];
                }
            }
        });

        // t1.run();
        // t2.run();
        t1.start();		// race condition
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter[0]);
    }

    public static void main3b(String[] args) throws Exception {
        int[] counter = { 0 };
        // counter = new int[]{ 123 };

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; ++i) {
                ++counter[0];
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; ++i) {
                ++counter[0];
            }
        });

        // t1.run();
        // t2.run();
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter[0]);
    }

    // public static void main3(String[] args) throws Exception {
    //	// final int counter = 0;
    //	int counter = 0;		// cannot be (effectively) final

    //	Thread t1 = new Thread(() -> {
    //		for (int i = 0; i < 100_000; ++i) {
    //			++counter;
    //		}
    //	});

    //	Thread t2 = new Thread(() -> {
    //		for (int i = 0; i < 100_000; ++i) {
    //			++counter;
    //		}

    //	});
    //	t1.start();
    //	t2.start();
    //	t1.join();
    //	t2.join();

    //	System.out.println(counter);
    // }

    public static void main2(String[] args) throws Exception {
        // var a = 1;
        // var r = () -> {
        //	for (int i = 0; i < 3000; ++i) {
        //		System.out.println("Hello world " + i);
        //	}
        // };
        // var r2 = (Runnable)() -> {
        //	for (int i = 0; i < 3000; ++i) {
        //		System.out.println("Hello world " + i);
        //	}
        // };

        // nĂŠvtelen fĂźggvĂŠny (lambda-fĂźggvĂŠny)
        Runnable r = () -> {
            for (int i = 0; i < 3000; ++i) {
                System.out.println("Hello world " + i);
            }
        };

        Function<String, Runnable> f = str -> () -> {
            for (int i = 0; i < 3000; ++i) {
                System.out.println(str + " " + i);
            }
        };

        BiFunction<String, String, Runnable> f2 =
                (str1, str2) -> () -> {
                    for (int i = 0; i < 3000; ++i) {
                        System.out.println(str1 + " " + i + " " + str2);
                    }
                };

        // NOT concurrent
        // r.run();
        // r.run();
        // r.run();
        // r.run();

        // new Thread(r);

        // NOT concurrent
        // new Thread(r).run();
        // new Thread(r).run();
        // new Thread(r).run();
        // new Thread(r).run();

        // new Thread(r).start();
        // new Thread(r).start();
        // new Thread(r).start();
        // new Thread(r).start();

        // new Thread(f("a")).start();

        // new Thread(f.apply("a")).start();
        // new Thread(f.apply("b")).start();
        // new Thread(f.apply("c")).start();
        // new Thread(f.apply("d")).start();

        // new Thread(() -> {
        //	for (int i = 0; i < 3000; ++i) {
        //		System.out.println("Hello world " + i);
        //	}
        // }).start();

        // Thread x = new Thread(threadWithText("x", 3000)).start();

        // new Thread(threadWithText("a", 3000)).start();
        // new Thread(threadWithText("b", 3000)).start();
        // new Thread(threadWithText("c", 3000)).start();
        Thread t = new Thread(threadWithText("d", 3000));
        t.start();
        Thread te = new Thread(threadWithText("e", 3000));
        te.start();

        // NEVER use these:
        // t.stop();
        // System.exit(123);

        // Thread t1 = new MyThread();
        // Thread t2 = new Thread(new MyRunnable());

        // // // t1.start();
        // // // t2.start();

        // // // t1.join();
        // // // t2.join();
        t.join();
        te.join();

        System.out.println("All done.");

    }

    static Runnable threadWithText(String id, int stepCount) {
        return () -> {
            for (int i = 0; i < stepCount; ++i) {
                System.out.println(id + " " + i);
            }
        };
    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread");
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("MyRunnable");
    }
}

class MyClass {
    int myData1;
    int myData2;

    public void myFun1() {
        for (int i = 0; i < 100000; ++i) {
            ++myData1;
        }
    }

    public synchronized void myFun2() {
        synchronized (this) {
            for (int i = 0; i < 100000; ++i) {
                ++myData2;
            }
        }
    }

    public void myFun3() {
        for (int i = 0; i < 100000; ++i) {
            synchronized (this) {
                ++myData2;
            }
        }
    }

    public synchronized void myFun4() {
        for (int i = 0; i < 100000; ++i) {
            ++myData2;
        }
    }

    public static synchronized void myFun5() {
        // some activity
    }

    public static void myFun5b() {
        synchronized (MyClass.class) {
            // some activity
        }
    }
}
