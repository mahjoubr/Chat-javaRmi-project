import java.rmi.Naming;
import java.rmi.RemoteException;


public class chatserver {
    public static void main(String[] args) {
        try {
            

            chat chat = new chat();

            Naming.rebind("rmi://localhost/chat", chat);

            System.out.println("chat server started.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
