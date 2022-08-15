/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A1;

/**
 *
 * @author ynb9219
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class application {

    public String text;
    private boolean stopRequested;
    public static final int PORT = 7777;

    public application(String text) {
        this.text = text;
        stopRequested = false;
    }

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
            while (!stopRequested) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection made with "
                        + socket.getInetAddress());
                Chat chat = new Chat(socket, text);
                Thread thread = new Thread(chat);
                thread.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Can't accept client connection: " + e);
        }
        System.out.println("Server finishing");
    }

    public void requestStop() {
        stopRequested = true;
    }

    public static void main(String[] args) {
        application server = new application("Hello");
        server.startServer();
    }

    private class Chat implements Runnable {

        private String text;
        private Socket socket;

        public Chat(Socket socket, String text) {
            this.text = text;
            this.socket = socket;
        }

        public void run() {
            PrintWriter pw;
            BufferedReader br;
            try {
                pw = new PrintWriter(socket.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                pw.println("Enter a messege");
                pw.close();
                br.close();
                System.out.println("Closing connection with "
                        + socket.getInetAddress());
                socket.close();
            } catch (IOException e) {
                System.err.println("Server error with room: " + e);
            }
        }
    }
}
