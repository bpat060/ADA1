/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1;

/**
 *
 * @author Owner
 * @param <Input>
 * @param <Output>
 */
public abstract class Task<Input, Output> implements Runnable {

    int id = 0;
    Input input;
    Output output;
    private boolean chopstick; // access to chopstick is synchronized
    Task task = new Task(input) {};

    public Task(Input input) {
        this.input = input;
        chopstick = true;
    }

    public int getID(Input input) { //need to change return to int
        id++;
        return id;
    }
    
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
                + " started with x = " + input);

        int loopIterations = 10;

        for (int i = 0; i < loopIterations; i++) {
            input++;
        }

        System.out.println("Thread " + Thread.currentThread()
                + " finishing with x = " + input);
    }

    public synchronized void releaseLock() {
        chopstick = true; // lock is now available for other threads
        notifyAll(); // notify one waiting thread
    }

    public void addListener(TaskObserver o) {

    }

    public void removeListener(TaskObserver o) {

    }

    protected void notifyAll(Output output) {

    }
}
