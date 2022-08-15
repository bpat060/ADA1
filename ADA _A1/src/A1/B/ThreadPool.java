/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public class ThreadPool implements Runnable {

    Runnable runnable;
    int initialSize;
    ArrayList<Runnable> pool = new ArrayList<Runnable>(initialSize);
    int newSize;

    public ThreadPool(int initialSize) {
        this.initialSize = initialSize;
        this.runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing: " + Thread.currentThread().getName());
            }
        };
        this.pool = pool;
    }

    public int getSize() {
        return pool.size(); //dunno if get size is relative to initial size++?
    }

    public int getAvailable() { // get available threads.
        int count = 0;
        for (int i = 0; i < pool.size(); i++) {
            if (Thread.currentThread().isAlive()) {
                count++;
            }
        }
        return count;
    }

    public void resize(int newSize) {
        if (getAvailable() < (pool.size() / 3)) {
            pool.ensureCapacity(pool.size() * 2);
            //doubles pool threads
        } else if (getAvailable() > ((pool.size() / 3) * 2)) {
            pool.ensureCapacity(pool.size() / 2);
            //halves pool threads
        } else {
            pool.ensureCapacity(newSize);
            //custom size
        }
    }

    public void destroyPool() {
        //all tasks executed in queue before destroying threadpool worker threads
    }

    public boolean perform(Runnable task) {
        //not sure how this is working with other functions?
        return false;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {

        System.out.println("From primary() method: " + Thread.currentThread().getName());

        System.out.println("Implementing the runnable interface in Java");

        Runnable instance = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing: " + Thread.currentThread().getName());
            }
        };
        System.out.println("Creating a new instance of the thread object.");

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
