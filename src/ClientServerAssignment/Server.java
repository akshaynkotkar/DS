package ClientServerAssignment;

import java.net.*;
import java.io.*;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private BufferedReader consoleInput = null;

    public Server(int port) {
        try {
            // Start server and wait for connection
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            // Accept connection
            socket = server.accept();
            System.out.println("Client connected");

            // Input from client and terminal
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            consoleInput = new BufferedReader(new InputStreamReader(System.in));

            // Output to client
            out = new DataOutputStream(socket.getOutputStream());

            String messageFromClient = "", messageToSend = "";

            // Start communication loop
            while (!messageFromClient.equalsIgnoreCase("Over")) {
                // Receive and print message from client
                messageFromClient = in.readUTF();
                System.out.println("Client: " + messageFromClient);

                // Send a response to client
                System.out.print("Enter message: ");
                messageToSend = consoleInput.readLine();
                out.writeUTF(messageToSend);
            }
        } catch (IOException i) {
            System.out.println("I/O error: " + i.getMessage());
        } finally {
            // Close connections
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (consoleInput != null) consoleInput.close();
                if (socket != null) socket.close();
                if (server != null) server.close();
            } catch (IOException i) {
                System.out.println("Error closing connection: " + i.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Server(5000);
    }
}
