package Negocio;

import Datos.S_POP;
import Datos.S_PROCESO;
import Datos.S_SMTP;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
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
        
        hermes_SMTP.setServidor_GMAIL("smtp.gmail.com");  // Datos Servidor
        hermes_SMTP.setPuerto_GMAIL("465");                     // Datos Servidor
        
        hermes_SMTP.setUsuario_GMAIL("USER");  // Datos Usuario
        hermes_SMTP.setPassword_GMAIL("PASSWORD");     // Datos Usuario
        
        hermes_SMTP.setDestinatarios_GMAIL("sariahllopez@gmail.com"); // Datos Destinatarios.
        hermes_SMTP.setDestinatarios_GMAIL("rapdecohh@gmail.com"); // Datos Destinatarios.
        hermes_SMTP.setDestinatarios_GMAIL("eyver.evm@gmail.com"); // Datos Destinatarios.
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
                Thread.sleep(5000);
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
        
        S_POP hermes_POP = new Datos.S_POP();
        S_PROCESO hodr = new Datos.S_PROCESO();
        
        hermes_POP.setServidor_POP("pop.gmail.com");
        System.out.println("[servidor] requerido ::: {pop.gmail.com}");
        hermes_POP.setPuerto_POP("995");
        System.out.println("[puerto]   requerido ::: {995}");
        hermes_POP.setUsuario_POP("USER");
        System.out.println("[email]    requerido ::: {eyver.evm@gmail.com}");
        hermes_POP.setPassword_POP("PASSWORD");
        
        hermes_POP.boot_internal();
        hermes_POP.iniciar_session_POP();
        this.setCadenaOP(hermes_POP.operacion_session_POP());
        hodr.separar_datos(this.getCadenaOP());
        hermes_POP.cerrar_session_POP();
        
        System.out.println(this.getCadenaOP());
        System.out.println(hodr.getFuncionVariable());
        System.out.println(hodr.getCuVariable());
    }
    
    /**
     * [03]Cargar el servicio de ::: BD.
     * @throws SQLException 
     */
    public void conexion_BD() throws SQLException {
        System.out.println("************************************");
        System.out.println("*******      BD SERVER       *******");
        System.out.println("************************************");
        
        String host = "localhost";
        String bd = "00_tewe";
        String puerto = ":"+"5432";
        String url = "jdbc:postgresql://"+host+puerto+"/"+bd+"";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","Estragon");
        props.setProperty("ssl","false");
        try (Connection conn = DriverManager.getConnection(url, props)){
            
            Thread.sleep(4000);
            conn.close();
        } catch (Exception e){
            System.out.println(e);
        }
        
//        String url = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
//        Connection conn = DriverManager.getConnection(url);
    }
}
