/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1;


/**
 *
 * @author Owner
 */
public interface TaskObserver{
   
    public void addListener(TaskObserver o);
    public void removeListener(TaskObserver o);
    public void notifyAll(TaskObserver o);
    
}
