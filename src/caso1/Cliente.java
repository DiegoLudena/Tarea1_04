package caso1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	
	private final int PUERTO = 1234;
	private final String HOST = "localhost";
	private Socket socketCliente;
	
	public void iniciarCliente() {
		try {
			//Creo el socket e intento conectar con el servidor. Por si falla, hace falta rodearlo de try/catch
			socketCliente = new Socket(HOST,PUERTO);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
