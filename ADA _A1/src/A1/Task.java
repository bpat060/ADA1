/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A1;

import Chapter_1.Lamports_Bakery_Algorithm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sheet
 */
public class Task implements Runnable
{
   private int x;
   private volatile int UniqueIdentifier;
   private boolean chopstick; // access to chopstick    is synchronized
   private static volatile List<Lamports_Bakery_Algorithm> threads = new ArrayList();
   
   public Task()
   {   
       x = 0;
       int id = UniqueIdentifier; 
       chopstick = true;
   }
   
   // repeatedly increment the value of x 
   public void run()
   {  
      acquireLock();
      releaseLock();
   }
   
   public synchronized void acquireLock()
   {  
       while (!chopstick) // wait for the lock available notification
      {  try
         {  wait();
         }
         catch (InterruptedException e)
         {  // ignore
         }
      }
      chopstick = false; // lock is now unavailable for other threads
      
      System.out.println("Thread " + Thread.currentThread()
         + " started with x = " + x);
      
      int loopIterations = 10;
      
      for (int i = 0; i < loopIterations; i++)
      {  
          x++; 
      }
      
      System.out.println("Thread " + Thread.currentThread()
         + " finishing with x = " + x); 
   }

   public synchronized void releaseLock()
   {  chopstick = true; // lock is now available for other threads
      notify(); // notify one waiting thread
   }

   
   public static void main(String[] args)
   {  // creates one thread
      Task task = new Task();
      
      for (int i = 0; i < 10; i++){
          Thread thread = new Thread(task);
          thread.start();
      }
   }
}
