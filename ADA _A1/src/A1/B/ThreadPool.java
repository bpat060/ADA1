/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1.B;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public abstract class ThreadPool implements Runnable {

    Runnable runnable;
    List<Runnable> pool = new ArrayList<Runnable>();
    int initialSize;

    public ThreadPool(int initialSize) {
        this.initialSize = initialSize;
        this.runnable = runnable;
        this.pool = pool;
    }

    public int getSize() {
        return pool.size(); //dunno if get size is relative to initial size++?
    }

    public int getAvailable() { // get available threads?
        //Thread.currentThread().isAlive();
        int num;
        if (perform(runnable) == true) {
            num = 1;
        } else {
            num = 0;
        }
        return num;
    }

    public void resize(int newSize) {
        //if pool reaches max num threads
        //make pool list longer by a resonable amount for new size
    }

    public void destroyPool() {
        //all tasks executed in queue before destroying threadpool worker threads
    }

    public boolean perform(Runnable task) {

        return false;
    }

    public static void main(String[] args) {

        System.out.println("From primary() method: " + Thread.currentThread().getName());

        System.out.println("Implementing the runnable interface in Java");

        Runnable instance = new Runnable() {
            @Override
            public void run() {
                System.out.println("From run() method: " + Thread.currentThread().getName());

            }

        };

        System.out.println("Create a new instance of the thread object.");

        Thread test1 = new Thread(instance);
        System.out.println("Executing the thread!");
        test1.start();

        Thread test2 = new Thread(instance);
        System.out.println("Executing the thread!");
        test2.start();

        Thread test3 = new Thread(instance);
        System.out.println("Executing the thread!");
        test3.start();

        Thread test4 = new Thread(instance);
        System.out.println("Executing the thread!");
        test4.start();

    }

}
