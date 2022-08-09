/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Owner
 */
// inner class that represents a single game played across a socket
class Task implements Runnable {

    private int min, max; // minimum (incl) and maximum (excl) range

    private int value; // value to guess
    private Socket socket; // socket for client/server communication

    // constructor for a guess game to guess value across a socket
    // for client/server communication
    public Task(Socket socket, int value) {
        this.value = value;
        this.socket = socket;
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
            pw.println("Guess the number between " + min + " and "
                    + (max - 1) + " inclusive");
            int guess = min - 1;
            do {
                String clientGuess = br.readLine();
                String response;
                if (clientGuess == null) {
                    response = "Nothing entered, try again";
                } else {
                    try {
                        guess = Integer.parseInt(clientGuess);
                        if (guess < value) {
                            response = "Guess too low, try again";
                        } else if (guess > value) {
                            response = "Guess too high, try again";
                        } else {
                            response = "Correct guess!";
                        }
                    } catch (NumberFormatException e) {
                        response = "Not an int value, try again";
                    }
                }
                pw.println(response);
            } while (guess != value);
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
