import java.rmi.Remote;
import java.rmi.RemoteException;

public interface chatinterface extends Remote {
    void register(String username, clientinterface client) throws RemoteException;
    void sendMessage(String sender, String receiver, String message) throws RemoteException;
   
    void leaveChat(String username) throws RemoteException;
}
