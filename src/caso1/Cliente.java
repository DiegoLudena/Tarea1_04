package caso1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	private final int PUERTO = 1234;
	private final String HOST = "localhost";
	private Socket socketCliente;
	private DataOutputStream flujoServidor;
	private DataInputStream flujoCliente;

	
	Scanner scanner = new Scanner(System.in);

	
	public void iniciarCliente() {
		try {
			//Creo el socket e intento conectar con el servidor. Por si falla, hace falta rodearlo de try/catch
			socketCliente = new Socket(HOST,PUERTO);
			
			//Hago que el usuario introduzca los números y la opción que quiere
			 int num1 = obtenerNumero("Introduce el primer número:");
	         int num2 = obtenerNumero("Introduce el segundo número:");
			String opcion = obtenerOpcion("Indica la operación a realizar: \n Suma\n Resta \n Multiplicacion  \n División");
			
			//envío al servidor 
			//Flujo de datos hacia el servidor
			flujoServidor = new DataOutputStream(socketCliente.getOutputStream());
			
			flujoServidor.writeInt(num1);
			flujoServidor.writeInt(num2);
			flujoServidor.writeUTF(opcion);
			

			
			//Creo el flujo de entrada para recibir 
			flujoCliente = new DataInputStream(socketCliente.getInputStream());
			String resultado = flujoCliente.readUTF();
			
			//Imprimo el resultado
			System.out.println("El resultado de la " + opcion +" es: " + resultado);
			
			//Ciero el Scanner
			scanner.close();
			//Cierro los flujos			
			flujoServidor.close();
			flujoCliente.close();
			//Cierro el socket
			System.out.println("Desconexión");
			socketCliente.close();
			
			
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//Ya que hago varias veces el obtener un número, lo hago como un método
    private int obtenerNumero(String mensaje) {
        System.out.println(mensaje);
        int numero = scanner.nextInt();
        /* ESTE era el fallo. Cuando utilizas nextInt() para leer un número, el carácter de nueva línea (Enter o \n) aún queda en el búfer. 
         * Entonces al cambiar al nextLine lee primero ese salto y no recibe la opcion del usuario
         * Así que hay que limpiar el buffer antes de llamar a obtenerOpcion usando un método de Scanner que sí consuma todo el buffer, como nextLine.
        */
        scanner.nextLine();
        return numero;
    }
   //He hecho obtener opcion también un método porque pensaba que podía ser lo que cortaba la conexión, pero no.
    private String obtenerOpcion(String mensaje) {
        System.out.println(mensaje);
        String opcion = scanner.nextLine().toLowerCase();
        return opcion;
        
    }
   
}



