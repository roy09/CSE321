import java.util.Scanner;

public class ClientSender implements Runnable
{
	Scanner in = new Scanner(System.in);
	static String out = "There's nothing to see here";
	public NetworkUtil nc;

	public void run() {
		try
		{
			// take input
			System.out.println("Client!")
			// System.out.println("Please enter the server name: ");
			// String serverAddress = in.nextLine();
			// System.out.println("Please enter the server port");
			// String serverPort = in.nextLine();
			
			// establishing the connection
			String serverAddress="127.0.0.1";
			int serverPort=33333;
			nc = new NetworkUtil(serverAddress,serverPort);
			nc.write(client + " is connected to the server");
			
			ClientReceiver receiver = new ClientReceiver(nc);
			Thread sth = new Thread(receiver);
			sth.start();

			// System.out.println(receiver.nc);
			// start sending
			while(true){	
				out = in.nextLine();
				nc.write(out);
			}
		}catch(Exception e)
		{
			System.out.println (e);
		}

	}

}


class Client extends Thread{
	public static void main(String[] args){
		ClientSender bondhu = new ClientSender();
		Thread client = new Thread(bondhu);
		client.start();

		// start receiving
		
	}
}

