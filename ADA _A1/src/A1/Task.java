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
   private int getId;
   
   public Task()
   {   
       x = 0;
       int id = getId; 
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
          x++; 
      }
      System.out.println("Thread " + Thread.currentThread()
         + " finishing with x = " + x); 
   }
   
   public static void main(String[] args)
   {  // create two concurrent threads
      Task task = new Task();
      Thread threadA = new Thread(task);
      //Thread threadB = new Thread(task);
      threadA.start();
      //threadB.start();
      //try
      //{  // wait for both threads to finish
         //threadA.join();
         //threadB.join();
      //}
      //catch (InterruptedException e)
      //{  System.out.println("Interrupted " + e);
      //}
      System.out.println("The final value of x is " + task.x);
   }
}
