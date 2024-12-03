package ClientServerAssignment.rmi;

import java.rmi.*;

public interface AddServerIntf extends Remote {
    double add(double num1, double num2) throws RemoteException;
}