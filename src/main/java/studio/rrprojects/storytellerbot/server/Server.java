package studio.rrprojects.storytellerbot.server;

import studio.rrprojects.storytellerbot.MainController;
import studio.rrprojects.util_library.DebugUtils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port; //Ports not changing at the moment
    private final MainController mainController; //For passing back inputs on the socket

    // Constructor with port
    public Server(MainController mainController, int port) {
        this.mainController = mainController;
        this.port = port;
        OpenConnection();
    }

    private void OpenConnection() {
        // starts server and waits for a connection
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            Socket socket = server.accept();
            System.out.println("Client accepted");

            // Takes input from the client socket
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line;

            try {
                line = in.readUTF();

                //TODO: Process the line input
                DebugUtils.UnknownMsg("INCOMING DATA: " + line);

            } catch(IOException i) {
                System.out.println("Inner catch: " + i);
                CloseConnectionAndRestart(server, socket, in);
            }

            //Then closes and restarts as the client side has also closed the connection
            CloseConnectionAndRestart(server, socket, in);
        } catch(IOException i) {
            System.out.println("Outer Catch: " + i);
        }
    }


    public void CloseConnectionAndRestart(ServerSocket server, Socket socket, DataInputStream in) {
        System.out.println("Closing Connection");

        // Close connection
        try {
            server.close();
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Restarting...");
        OpenConnection();
    }
}
