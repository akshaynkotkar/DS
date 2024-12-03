package ClientServerAssignment.rmi;

import java.rmi.*;

public class AddClient {
    public static void main(String[] args) {
        try {
            String serverURL = "rmi://" + args[0] + "/AddServer";
            AddServerIntf stub = (AddServerIntf) Naming.lookup(serverURL);

            double num1 = Double.parseDouble(args[1]);
            double num2 = Double.parseDouble(args[2]);

            System.out.println("Sum: " + stub.add(num1, num2));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
   // 1st Terminal:
    javac AddServerIntf.java AddServerImpl.java AddServer.java AddClient.java
    start rmiregistry


   // 2nd Terminal:
    java AddServer
   Server is ready...

   // 3rd Terminal:
    java AddClient localhost 5 7
   Sum: 12.0

*/