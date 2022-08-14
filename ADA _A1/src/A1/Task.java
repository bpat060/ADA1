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
 * @param <E>
 * @param <F>
 * 
 */

public abstract class Task<E, F> implements Runnable {
    
    E input;
    F output;
    int id;
    //private boolean chopstick; // access to chopstick is synchronized
    private final List<Task> TaskObserver = new ArrayList<>();
    
    //Task task = new Task(input) {};

    public Task(E input, F output) {
        
        this.input = input;
        this.output = output;
        //chopstick = true;
    }
    
    public E getE() {
        
        return this.input;
    }
    
    public F getF() {
        
        return this.output;
    }

    public int getID(int id) { //need to change return to int
        id++;
        return id;
    }
    
    public void addListener(TaskObserver o) {
        TaskObserver.add((Task) o);
    }

    public void removeListener(TaskObserver o) {
        
        TaskObserver.remove((Task) o);
    }

    protected void notifyAll(F output) {
        
       for(Task o: TaskObserver) {
           // o.add(id);
        }
        
    }
    
    @Override
    public void run() {
      //acquireLock();
      //releaseLock();
    }
    
}
