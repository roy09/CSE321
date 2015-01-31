import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.*;

public class Server{
    private ServerSocket ServSock;
    // HashMap<String, NetworkUtil> users = new HashMap<String, NetworkUtil>();
    Server()  
    {
        try
        {   
            ServSock = new ServerSocket(33333);
            System.out.println("Server!");
            System.out.println("Server is running on port " + 33333);
            while (true)
            {
                ServerThread m = new ServerThread(ServSock.accept());
            }
        }catch(Exception e){
            System.out.println("Server starts:"+e);
        }
    }

    public static void main(String args[])  throws UnknownHostException, IOException 
    {
        Server objServer = new Server();
        int num = 0;
    }
}

class ServerThread extends Server implements Runnable
{
    private Socket ClientSock;
    private Thread thr;
    private NetworkUtil nc;

    static int num = 1;
    static HashMap<String, NetworkUtil> users = new HashMap<String, NetworkUtil>();

    ServerThread(Socket client) 
    {
        this.ClientSock = client;
        this.thr = new Thread(this);
        // System.out.println(ClientSock);
        thr.start();
    }

    public void run() 
    {
        
        this.nc = new NetworkUtil(ClientSock);
        
        // register the user
        String name = "Client" + Integer.toString(num++);
        users.put(name, nc);
        System.out.println(name + " is connected!");
        
        while(true){
            try{
                Object stuffObject = nc.read();
                String stuff = (String) stuffObject;
                if (stuff.equals("disconnect")){
                    nc.closeConnection();
                    break;
                }
                String to = stuff.substring(0, stuff.indexOf(" "));
                String message = stuff.substring(stuff.indexOf(" ") + 1, stuff.length());
                
                NetworkUtil receiver = users.get(to);
                receiver.write(message);
            } catch (Exception e){
                System.out.println("Wrong username!");
            }
        }
    }
}

