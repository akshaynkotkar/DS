package ClientServerAssignment;

import java.net.*;
import java.io.*;

public class Client {
    private Socket socket = null;
    private BufferedReader consoleInput = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;

    public Client(String address, int port) {
        try {
            // Establish a connection
            socket = new Socket(address, port);
            System.out.println("Connected to server");

            // Input for terminal and server messages
            consoleInput = new BufferedReader(new InputStreamReader(System.in));
            in = new DataInputStream(socket.getInputStream());

            // Output to the server
            out = new DataOutputStream(socket.getOutputStream());

            String messageToSend = "", messageReceived = "";

            // Start communication loop
            while (!messageToSend.equalsIgnoreCase("Over")) {
                // Read message from console
                System.out.print("Enter message: ");
                messageToSend = consoleInput.readLine();
                out.writeUTF(messageToSend); // Send to server

                // Receive and print message from server
                messageReceived = in.readUTF();
                System.out.println("Server: " + messageReceived);
            }
        } catch (IOException i) {
            System.out.println("I/O error: " + i.getMessage());
        } finally {
            // Close connections
            try {
                if (consoleInput != null) consoleInput.close();
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (IOException i) {
                System.out.println("Error closing connection: " + i.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 5000);
    }
}
