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
public class ThreadPool {

    Task E;
    int initialSize;

    public ThreadPool(int initialSize) {
        this.initialSize = initialSize;
        this.E = E;

    }

    public int getSize() {
        return 0; //dunno if get size is relative to initial size++?
    }

    public int getAvailable() { // get available threads?
        //Thread.currentThread().isAlive();
        int num;
        if (perform(E) == true) {
            num = 1;
        } else {
            num = 0;
        }
        return num;
    }

    public void resize(int newSize) {

    }

    public void destroyPool() {

    }

    public boolean perform(Runnable task) {

        return false;
    }
}
