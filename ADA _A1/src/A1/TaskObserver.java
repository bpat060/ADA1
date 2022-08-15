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
public interface TaskObserver{
    
    public List<Task> TaskObserver = new ArrayList<>();
   
    public void addListener(TaskObserver o);
    public void removeListener(TaskObserver o);
    public void notifyAll(TaskObserver o);
    
}
