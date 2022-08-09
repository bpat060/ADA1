/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1.B;

/**
 *
 * @author Owner
 */
public class Task<E, F> implements Runnable {

    E e;
    Task task = new Task(e);
    int id;

    public Task(E param) {
        this.id = id;
    }

    public int getID() { //need to change return to int
        UniqueIdentifier UI = new UniqueIdentifier(task);
        return UI.ID();
    }

    public void run() {

    }

    public void addListener(TaskObserver o) {

    }

    public void removeListener(TaskObserver o) {

    }

    protected void notifyAll(F progress) {

    }
}
