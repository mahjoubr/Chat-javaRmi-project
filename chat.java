import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class chat extends UnicastRemoteObject implements chatinterface {
    private Map<String, clientinterface> clients;

    public chat() throws RemoteException {
        super();
        clients = new HashMap<String, clientinterface>();

    }

    @Override
    public void register(String username, clientinterface client) throws RemoteException {
        clients.put(username, client);
        System.out.println(username + " has joined the chat.");
    }

    @Override
    public void sendMessage(String sender, String receiver, String message) throws RemoteException {
        clientinterface client = clients.get(receiver);
        if (client != null) {
            client.receiveMessage(sender, message);
            System.out.println(sender + " -> " + receiver + ": " + message);
        } else {
            System.out.println("User " + receiver + " not found.");
        }
    }

    @Override
    public void leaveChat(String username) throws RemoteException {
        clients.remove(username);
        System.out.println(username + " has left the chat.");
    }
}
