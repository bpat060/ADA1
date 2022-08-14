/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A1;

import java.util.Scanner;

/**
 *
 * @author Sheet
 */
public class TaskSubClass extends Task {
    
    public static void main(String[] args){
        
        int input;
        int output = 0;
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");

        // Numerical input
        input = scanner.nextInt();
        
        if (input >= 0){
            
            output = input * 10;
        }
        else{
            System.err.println("Input invalid!");
        }

        // Output input by user
        //System.out.println("Input: " + input);
        
        Task<Integer, Integer> task;
        task = new Task<Integer, Integer>(input, output) {
        };
        
        System.out.println("Input was: " + task.getE());
        System.out.println("Output is: " + task.getF());
    }

    public TaskSubClass(Object input, Object output) {
        super(input, output);
    }
    
}
