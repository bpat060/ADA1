/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter_1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ynb9219
 */
public class Lamports_Bakery_Algorithm extends Thread implements Runnable{

    private int id;
    private volatile int ticket;
    private static volatile List<Lamports_Bakery_Algorithm> threads = new ArrayList();
    private volatile boolean choosing;
    private volatile Thread running;

    public Lamports_Bakery_Algorithm() {
        running = new Thread();
        running.run();
    }

    public void run() {
        AquireLock();
        ReleaseLock();
        
    }

    public void AquireLock() {
        choosing = true;
        id = ++ticket;
        choosing = false;
        for (Lamports_Bakery_Algorithm thread : threads) {
            while (thread.choosing) {
                running.yield();
            }
            while ((thread.id != 0 && thread.id < this.id)
                    || (thread.id == this.id && thread.running.getId() < this.running.getId())) {
                running.yield();
            }
        }
        System.out.println("Acquired Lock: " + running.getId());
    }

    public void ReleaseLock() {
        id = 0;
        System.out.println("Released Lock: " + running.getId());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Lamports_Bakery_Algorithm thread = new Lamports_Bakery_Algorithm();
            threads.add(thread);
        }
        for (Lamports_Bakery_Algorithm thread : threads) {
            thread.running.start();
        }
        
    }
}
