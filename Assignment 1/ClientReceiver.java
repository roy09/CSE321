import java.net.Socket;

public class ClientReceiver implements Runnable
{
	static String out = "";

	private String client = "This is client ";
	NetworkUtil nc;

	public ClientReceiver(NetworkUtil nc){
		this.nc =  nc;
	}

	public void run() {
		try
		{
			while(true){
				System.out.println(nc.read());
			}
		}catch(Exception e)
		{
			System.out.println (e);
		}

	}

}