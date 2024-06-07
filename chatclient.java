
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.io.Serializable;



public class chatclient implements Serializable{
    private String username;
    private chatinterface chat;

    public chatclient(String username, chatinterface chat) {
        this.username = username;
        this.chat = chat;
    }

    public void register() {
        try {
            chat.register(username, new ClientImpl());
            System.out.println("You have joined the chat.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String receiver, String message) {
        try {
            chat.sendMessage(username, receiver, message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void leaveChat() {
        try {
            chat.leaveChat(username);
            System.out.println("You have left the chat.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            chatinterface chat = (chatinterface) Naming.lookup("rmi://localhost/chat");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            chatclient client = new chatclient(username, chat);
            client.register();

            while (true) {
                System.out.print("Enter receiver's username ('quit' to leave): ");
                String receiver = scanner.nextLine();
                if (receiver.equalsIgnoreCase("quit")) {
                    client.leaveChat();
                    break;
                }

                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                client.sendMessage(receiver, message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ClientImpl implements clientinterface,Serializable {
        @Override
        public void receiveMessage(String sender, String message) throws RemoteException {
            System.out.println(sender + ": " + message);
        }
    }
}
