package Negocio;

import Datos.S_BD;
import Datos.S_POP;
import Datos.S_PROCESO;
import Datos.S_SMTP;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author eyver-dev
 */
public class Servidor {
    private String cadenaOP;

    public Servidor() {
        this.cadenaOP = "default";
    }
    public String getCadenaOP() {
        return cadenaOP;
    }
    public void setCadenaOP(String cadenaOP) {
        this.cadenaOP = cadenaOP;
    }
    
    
    /**
     * [01]Cargar el servicio de ::: SMTP.
     */
    public void iniciar_smtp(){
        System.out.println("************************************");
        System.out.println("*******      SMTP SERVER     *******");
        System.out.println("************************************");
        S_SMTP hermes_SMTP = new S_SMTP();
        int date;
        
/**
 * Asignacion de parametros por el usuario y el sistema.
 * [DS] = Datos del Servidor.
 * [DU] = Datos del Usuario.
 */
hermes_SMTP.setServidor_GMAIL("smtp.gmail.com");// DS
System.out.println("[servidor SMTP] ::: {smtp.gmail.com}");// DS

hermes_SMTP.setPuerto_GMAIL("465");// DS
System.out.println("[puerto SMTP]   ::: {465}");// DS

hermes_SMTP.setUsuario_GMAIL("eyver.evm@gmail.com");// DU
System.out.println("[usuario]       ::: {eyver.evm@gmail.com}");// DU

hermes_SMTP.setPassword_GMAIL("8888888888");// DU
System.out.println("[password]      ::: {********}");// DU

hermes_SMTP.setDestinatarios_GMAIL("sariahllopez@gmail.com");// Datos Destinatarios.
hermes_SMTP.setDestinatarios_GMAIL("rapdecohh@gmail.com");// Datos Destinatarios.
hermes_SMTP.setDestinatarios_GMAIL("eyver.evm@gmail.com");// Datos Destinatarios.
//hermes_SMTP.setDestinatarios_GMAIL("eyver.evm@gmail.com"); // Datos Destinatarios.
//hermes_SMTP.setDestinatarios_GMAIL("eyver.evm@gmail.com"); // Datos Destinatarios.
        
        //Date date=java.util.Calendar.getInstance().getTime();   // Obtener fecha.
        date= (int)(Math.random()*10000);
        
        hermes_SMTP.setAsunto("list_Persona{...}" + date); // Datos Mensaje.
        hermes_SMTP.setBody("Listar las personas del sistema.\n"
                + "Mensaje Linea 02.\n"
                + "Mensaje Linea 03.");                         // Datos Mensaje.
        
        hermes_SMTP.boot();       // Cargar todo.
        
        Session session = Session.getDefaultInstance(hermes_SMTP.getSlave());
        MimeMessage message = new MimeMessage(session);
        try{
            for (String i : hermes_SMTP.getDest_GMAIL()){
                
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(i));
                date= (int)(Math.random()*10000);
                message.setSubject(hermes_SMTP.getAsunto()+"-"+date);
                message.setContent(hermes_SMTP.getBody(), "text/html");

                Transport t = session.getTransport("smtp");
                
                t.connect(hermes_SMTP.getServidor_GMAIL(),
                        hermes_SMTP.getUsuario_GMAIL(),
                        hermes_SMTP.getPassword_GMAIL());
                
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                t.close();
                System.out.println(i);
                Thread.sleep(3000);
            }
        System.out.println("Mensaje enviado :: 200 :: key["+date+"]");
        } catch (MessagingException ex){
            System.out.println(ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
    
    /**
     * [02]Cargar el servicio de ::: POP.
     */
    public void iniciar_pop(){
        System.out.println("************************************");
        System.out.println("*******      POP SERVER      *******");
        System.out.println("************************************");
        
        /**
         * PARTE 01.
         */
        S_POP hermes_POP = new Datos.S_POP();
        hermes_POP.setServidor_POP("pop.gmail.com");
        System.out.println("[servidor] requerido ::: {pop.gmail.com}");
        hermes_POP.setPuerto_POP("995");
        System.out.println("[puerto]   requerido ::: {995}");
        hermes_POP.setUsuario_POP("eyver.evm@gmail.com");
        System.out.println("[email]    requerido ::: {eyver.evm@gmail.com}");
        hermes_POP.setPassword_POP("8888888888");
        System.out.println("[password] requerido ::: {********}");
        
        /**
         * PARTE 02.
         */
        hermes_POP.boot_internal();
        hermes_POP.iniciar_session_POP();
        this.setCadenaOP(hermes_POP.operacion_session_POP());
        
        
        /**
         * PARTE 03.
         */
        S_PROCESO hodr = new Datos.S_PROCESO();
        hodr.separar_datos(this.getCadenaOP());
        hermes_POP.cerrar_session_POP();
        System.out.println(this.getCadenaOP());
        System.out.println(hodr.getFuncionVariable());
        System.out.println(hodr.getCuVariable());
        
        /**
         * PARTE 04.
         */
        try {
            this.iniciar_BD(
                    hodr.getFuncionVariable(),
                    hodr.getCuVariable(),
                    hodr.getPARAMETROS()
            );
        } catch (SQLException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * [03]Cargar el servicio de ::: BD.
     * @param funcionVariable
     * @param cuVariable
     * @param parametros
     * @throws SQLException 
     */
    public void iniciar_BD(String funcionVariable, String cuVariable, ArrayList<String> parametros) throws SQLException {
        System.out.println("************************************");
        System.out.println("*******      BD SERVER       *******");
        System.out.println("************************************");
        S_BD hermes_BD = new Datos.S_BD();
        hermes_BD.setHost_BD("localhost");
        hermes_BD.setPuerto_BD("5432");
        hermes_BD.setNombre_BD("00_tewe");
        hermes_BD.setUrl_BD("jdbc:postgresql://"+
                hermes_BD.getHost_BD() +":"+
                hermes_BD.getPuerto_BD() +"/"+
                hermes_BD.getNombre_BD()
            );
        hermes_BD.getPropiedades_BD().setProperty("user","postgres");
        hermes_BD.getPropiedades_BD().setProperty("password","9999999999");
        hermes_BD.getPropiedades_BD().setProperty("ssl","false");
        
        hermes_BD.iniciar_session_BD();
        hermes_BD.operacionA(funcionVariable);
        hermes_BD.cerrar_session_BD();
    }
}
