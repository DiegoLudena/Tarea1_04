package caso1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private final int PUERTO = 1234;
	private final String HOST = "localhost";
	private Socket socketCliente;
	private ServerSocket socketServidor;
	private DataInputStream flujoServidor;
	private DataOutputStream flujoCliente;
	


	
	
	public void iniciarservidor() {
		
		try {
			//creo los sockets y lo pongo en escucha
			socketServidor = new ServerSocket(PUERTO);
			socketCliente = new Socket();
			
			//Cuando llega una petición la acepta. Pongo mensaje para comprobarlo.
			socketCliente = socketServidor.accept();
			System.out.println("Cliente conectado");
			
			//abro el flujo desde el cliente
			flujoServidor = new DataInputStream(socketCliente.getInputStream());
            // Leo los números y la opción enviados por el cliente
            int num1 = flujoServidor.readInt();
            int num2 = flujoServidor.readInt();
            String opcion = flujoServidor.readUTF();
			
            //Hago el cálculo con los datos del usuario
            String resultado = calculadora(num1, num2, opcion);
            
            //Creo el flujo de salida para enviar el mensaje
            flujoCliente = new DataOutputStream(socketCliente.getOutputStream());
            
            //Envio el mensaje
            flujoCliente.writeUTF(resultado);
			
            //Cierro los flujos
            flujoServidor.close();
            flujoCliente.close();
            
            //Cierro la conexión
			System.out.println("Fin de la conexión.");
			socketServidor.close();
            
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	//Creo la calculadora. Inicialmente quería devolver un Int, pero no se devolver una cadena de error en el default, así que lo he convertido a String
	public String calculadora(int num1, int num2, String opcion) { 
	    switch(opcion) {
	        case "suma":
	            return String.valueOf(num1 + num2);
	        case "resta":
	            return String.valueOf(num1 - num2);
	        case "multiplicacion":
	            return String.valueOf(num1 * num2);
	        case "division":
	            // Evito el error en caso de que num 2 sea 0
	            if (num2 != 0) {
	                return String.valueOf(num1 / num2);
	            } else {
	                return "Error: División por cero";
	            }
	        default:
	            return "Opción inválida";
	    	}
		}

}
