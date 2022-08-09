/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A1;

/**
 *
 * @author Sheet
 */
public class Task implements Runnable
{
   private int x;
   private volatile int UniqueIdentifier;
   
   public Task()
   {   
       x = 0;
       int id = UniqueIdentifier; 
   }
   
   // repeatedly increment the value of x 
   public void run()
   {  
      System.out.println("Thread " + Thread.currentThread()
         + " started with x = " + x);
      int loopIterations = (x + 1) * 10;
      for (int i = 0; i < loopIterations; i++)
      {  
          x++; 
      }
      System.out.println("Thread " + Thread.currentThread()
         + " finishing with x = " + x); 
   }
   
   public static void main(String[] args)
   {  // creates one thread
      Task task = new Task();
      
      for (int i = 0; i < 10; i++){
          Thread thread = new Thread(task);
          thread.start();
        //System.out.println("The final value of x is " + task.x);
      }
      
      System.out.println("The final value of x is " + task.x);
   }
}
