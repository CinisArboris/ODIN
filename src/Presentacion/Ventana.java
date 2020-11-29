package Presentacion;

import Negocio.Servidor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.mail.MessagingException;

/**
 *
 * @author eyver-dev
 */
public class Ventana {
    
    public static void main(String[] args) throws IOException, MessagingException {
        /**
         * Variables.
         */
        int opcion = 0; //Opciones del usuario en pantalla.
        boolean bandera = true;
        /**
         * Ciclo de servicio.
         */
        while (bandera){
            /**
             * Banner de operaciones permitidas para el usuario.
             */
            System.out.print(""
                + "  [1.MAIL >]"
                + "  [2.MAIL <]"
                + "  [3.MAIL <<<]"
                + "  [4.EXIT]"
                + "  [5.AYUDA]"
            + "");
            
            /**
             * Entrada del usuario.
             */
            System.out.println("");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            opcion = Integer.parseInt(buffer.readLine());
            
            /**
             * Ejecucion de la orden del usuario.
             */
            Servidor master;
            switch (opcion){
                case 1:
                    
                    master = new Negocio.Servidor();
                    master.iniciar_smtp();
                    System.out.println("MENSAJE ENVIADO :: STATUS :: 200 ::");
                    break;
                case 2:
                    master = new Negocio.Servidor();
                    master.iniciar_pop();
                    System.out.println("MENSAJE RECIBIDO :: STATUS :: 200 ::");
                    break;
                case 3:
                    System.out.println("Servicio de escucha.");
                    break;
                case 4:
                    System.out.println("Gracias, vuelta pronto :)");
                    bandera = false;
                    break;
                case 5:
                    System.out.println(" ... ... ... ... AYUDA");
                    break;
                default:
                    System.out.println("Opcion invalida, reintente");
            }
            /**
             * Division de iteracion.
             */
            System.out.println("==================================");
        }
    }
    
}
