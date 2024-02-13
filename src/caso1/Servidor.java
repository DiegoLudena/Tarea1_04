package caso1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private final int PUERTO = 1234;
	private final String HOST = "localhost";
	private Socket socketCliente;
	private ServerSocket socketServidor;
	
	
	public void iniciarservidor() {
		
		try {
			//creo los sockets y lo pongo en escucha
			socketServidor = new ServerSocket(PUERTO);
			socketCliente = new Socket();
			
			//Cuando llega una petición la acepta
			socketCliente = socketServidor.accept();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
