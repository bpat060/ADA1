/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author
 */
public class TueNightCodeTask<E, F> implements Runnable {

    public E thread;
    public Object item;
    public TueNightCodeTask task = new TueNightCodeTask(item);
    private int x;
    private boolean chopstick; // access to chopstick is synchronized
    int id;

    public TueNightCodeTask() {
        x = 0;
        chopstick = true;
        this.id = id;
    }

    public TueNightCodeTask(E param) {
        x = 0;
        chopstick = true;
        this.id = id;
    }

    public int getID() { //need to change return to int
        UniqueIdentifier UI = new UniqueIdentifier(task);
        return UI.ID();
    }

    //acquired lock then then thats finished release lock
    public void run() {
        acquireLock();
        releaseLock();
    }

    public synchronized void acquireLock() {
        while (!chopstick) // wait for the lock available notification
        {
            try {
                wait();
            } catch (InterruptedException e) {  // ignore
            }
        }
        chopstick = false; // lock is now unavailable for other threads

        System.out.println("Thread " + Thread.currentThread()
                + " started with x = " + x);

        int loopIterations = 10;

        for (int i = 0; i < loopIterations; i++) {
            x++;
        }

        System.out.println("Thread " + Thread.currentThread()
                + " finishing with x = " + x);
    }

    public synchronized void releaseLock() {
        chopstick = true; // lock is now available for other threads
        notifyAll(); // notify one waiting thread
    }

    public static void main(String[] args) {  // creates task
        //creates 10 threads accoring to loop
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }

    public void addListener(TaskObserver o) {

    }

    public void removeListener(TaskObserver o) {

    }

    protected void notifyAll(TueNightCodeTask progress) {

    }

    public interface TaskObserver<E, F> {

    }
}
