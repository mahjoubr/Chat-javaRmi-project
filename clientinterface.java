import java.rmi.Remote;
import java.rmi.RemoteException;

public interface clientinterface extends Remote {
    void receiveMessage(String sender, String message) throws RemoteException;
    
}
