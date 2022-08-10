/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package A1;

/**
 *
 * @author Sheet
 */

/**
 * A class that represents a server in a number guessing game where GuessClient
 * objects connect to this GuessServer and try to guess a random integer value
 * between min (incl) and max (excl) The game initiates with a response from the
 * server and ends when the server responds with "Correct guess!"
 *
 * @author Andrew Ensor
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Task {

    private int id;
    private int x; 
    private boolean stopRequested;
    public static final int PORT = 7777; // some unused port number

    public Task(int id, int x) {
        this.id = id;
        this.x = x;
        stopRequested = false;
    }

    // start the server if not already started and repeatedly listen
    // for client connections until stop requested
    public void startServer() {
        stopRequested = false;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started at "
                    + InetAddress.getLocalHost() + " on port " + PORT);
        } catch (IOException e) {
            System.err.println("Server can't listen on port: " + e);
            System.exit(-1);
        }
        try {
            while (!stopRequested) {  // block until the next client requests a connection
                // note that the server socket could set an accept timeout
                Socket socket = serverSocket.accept();
                System.out.println("Connection made with "
                        + socket.getInetAddress());
                // start a game with this connection, note that a server
                // might typically keep a reference to each game
                TaskObserver task = new TaskObserver(socket, x);
                Thread thread = new Thread(task);
                thread.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Can't accept client connection: " + e);
        }
        System.out.println("Server finishing");
    }

    // stops server AFTER the next client connection has been made
    // (since this server socket doesn't timeout on client connections)
    public void requestStop() {
        stopRequested = true;
    }

    // driver main method to test the class
    public static void main(String[] args) {
        Task server = new Task(1, 100);
        server.startServer();
    }

    // inner class that represents a single game played across a socket
    private class TaskObserver implements Runnable {

        private Socket socket; // socket for client/server communication

        private int x;
        private boolean chopstick; // access to chopstick is synchronized
        private int id;
        
        // constructor for a guess game to guess value across a socket
        // for client/server communication
        public TaskObserver(Socket socket, int x) {
            this.x = x;
            this.socket = socket;
            this.chopstick = true;
            this.id = id;
        }

        public void run() {
            
          PrintWriter pw; // output stream to client
            BufferedReader br; // input stream from client
            try {  // create an autoflush output stream for the socket
                pw = new PrintWriter(socket.getOutputStream(), true);
                // create a buffered input stream for this socket
                br = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                // play the game until value is correctly guessed
                pw.println("How many threads would you like processed?");
                int input;
                x = 1;
                do {
                    String clientInput = br.readLine();
                    String serverResponse;
                    if (clientInput == null) {
                        serverResponse = "Nothing entered, try again";
                    } else {
                        try {
                            input = Integer.parseInt(clientInput);
                            
                            if (input > 0 && input <= 20){
                                while (!chopstick) // wait for the lock available notification
                                {
                                    try {
                                        wait();
                                    } catch (InterruptedException e) {  
                                        // ignore
                                    }
                                }
                                    chopstick = false; // lock is now unavailable for other threads
                                    int loopIterations = input; 
                                    pw.println("Thread " + Thread.currentThread() + " started with x = " + x);
                                    
                                    for (int i = 0; i < loopIterations; i++) {
                                        x++;
                                    }
                                    //pw.println("Thread " + Thread.currentThread()+ " finishing with x = " + x);
                            }else{
                                chopstick = true; // lock is now available for other threads
                                notifyAll(); // notify one waiting thread
                            }
                        }catch (NumberFormatException e) {
                            serverResponse = "Not an int value, try again";
                        }
                    }
                    pw.println("Thread " + Thread.currentThread()+ " finishing with x = " + x);
                    //serverResponse = ("Thread " + Thread.currentThread()+ " finishing with x = " + x);
                } while (chopstick = false);
                pw.close();
                br.close();
                System.out.println("Closing connection with "
                        + socket.getInetAddress());
                socket.close();
            } catch (IOException e) {
                System.err.println("Server error with game: " + e);
            }
        }
    }
}

