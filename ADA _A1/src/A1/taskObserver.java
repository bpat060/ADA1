package A1;

/**
 * A class that represents a server in a number guessing game where GuessClient
 * objects connect to this GuessServer and try to guess a random integer value
 * between min (incl) and max (excl) The game initiates with a response from the
 * server and ends when the server responds with "Correct guess!"
 *
 * @author Andrew Ensor
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TaskObserver {

    private int min, max; // minimum (incl) and maximum (excl) range
    private boolean stopRequested;
    private Random generator;
    public static final int PORT = 7777; // some unused port number

    public TaskObserver(int min, int max) {
        this.min = min;
        this.max = max;
        stopRequested = false;
        generator = new Random();
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
                Task game = new Task(socket,
                        generator.nextInt(max - min) + min);
                Thread thread = new Thread(game);
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
        TaskObserver server = new TaskObserver(1, 100);
        server.startServer();
    }
}
