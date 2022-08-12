/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A1;

/**
 *
 * @author Sheet
 */
public class TaskSubClass {
    
    public static void main(String[] args){
        
        Task<Integer, Integer> task = new Task<Integer, Integer>(15, 12) {
        
        };
        
        System.out.println(task.getE());
        System.out.println(task.getF());

    }
    
}
