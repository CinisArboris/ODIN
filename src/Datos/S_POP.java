package Datos;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author eyver-dev
 */
public class S_POP {
    private String puerto_POP;      //Usuario
    private String usuario_POP;     //Usuario
    private String servidor_POP;    //Usuario
    private String password_POP;    //Usuario
    private Properties slave;       // Sistema
    private Store store;            // Sistema
    private Session sesion;         // Sistema
    private Folder folder;          // Sistema
    private Message [] mensajes;    // Sistema

    /**
     * Constructur a nivel :: Usuario
     */
    public S_POP() {
        this.puerto_POP = "";
        this.usuario_POP = "";
        this.servidor_POP = "";
        this.password_POP = "";
        this.slave = new Properties();
    }

    // GET / SET :: NIVEL USUARIO
    public String getPuerto_POP() {
        return puerto_POP;
    }
    public void setPuerto_POP(String puerto_POP) {
        this.puerto_POP = puerto_POP;
    }
    public String getUsuario_POP() {
        return usuario_POP;
    }
    public void setUsuario_POP(String usuario_POP) {
        this.usuario_POP = usuario_POP;
    }
    public String getServidor_POP() {
        return servidor_POP;
    }
    public void setServidor_POP(String servidor_POP) {
        this.servidor_POP = servidor_POP;
    }
    public String getPassword_POP() {
        return password_POP;
    }
    public void setPassword_POP(String password_POP) {
        this.password_POP = password_POP;
    }
    
    
    // GET / SET :: NIVEL SISTEMA
    public Properties getSlave() {
        return slave;
    }
    public void setSlave(Properties slave) {
        this.slave = slave;
    }
    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }
    public Session getSesion() {
        return sesion;
    }
    public void setSesion(Session sesion) {
        this.sesion = sesion;
    }
    public Folder getFolder() {
        return folder;
    }
    public void setFolder(Folder folder) {
        this.folder = folder;
    }
    public Message[] getMensajes() {
        return mensajes;
    }
    public void setMensajes(Message[] mensajes) {
        this.mensajes = mensajes;
    }
    
    
    /**
     * [01]Cargar todos los datos necesarios, para iniciar las conexiones.
     */
    public void boot_internal() {
        //this.slave.put("mail.debug", "true");
        this.getSlave().put("mail.pop3.auth",
                "true");
        this.getSlave().put("mail.pop3.port",
                this.getPuerto_POP());// Default 995.
        this.getSlave().put("mail.pop3.socketFactory.port",
                this.getPuerto_POP());// Default 995.
        this.getSlave().put("mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        this.getSlave().put("mail.pop3.socketFactory.fallback",
                "false");
    }

    /**
     * [02] Crear la sesion, con el servidor.
     */
    public void iniciar_session_POP(){
        this.setSesion(Session.getInstance(this.getSlave()));
        //System.out.println("SERVIDOR :: sesión iniciada");
        try {
            this.setStore(this.getSesion().getStore("pop3"));
            this.getStore().connect(this.getServidor_POP(),
                    this.getUsuario_POP(),
                    this.getPassword_POP()
            );
            this.setFolder(this.getStore().getFolder("INBOX"));
            this.getFolder().open(Folder.READ_ONLY);
            this.setMensajes(this.getFolder().getMessages());
        } catch (MessagingException ex) {
            Logger.getLogger(S_POP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * [03] Metodo generico.
     */
    public String operacion_session_POP(){
        int mensajesTotales = mensajes.length-1;
        String from = "default";
        String subject = "default";
//        Header tmp;
//        Enumeration<Header> vistoMensaje;
        try {
            from = mensajes[mensajesTotales].getFrom()[0].toString();
            //System.out.println("De      :"+from);
            subject = mensajes[mensajesTotales].getSubject();
            //System.out.println("Asunto  :"+subject);
            
//            vistoMensaje = mensajes[mensajesTotales].getAllHeaders();
//            while (vistoMensaje.hasMoreElements()){
//                tmp = vistoMensaje.nextElement();
//                //System.out.println("  "+tmp.getName() +"  ||  "+ tmp.getValue());
//            }
        } catch (MessagingException ex) {
            Logger.getLogger(S_POP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subject;
    }

    /**
     * [04] Cerrar la sesion de [STORE].
     */
    public void cerrar_session_POP(){
        try {
            this.getStore().close();
            //System.out.println("SERVIDOR :: sesión cerrada");
        } catch (MessagingException ex) {
            Logger.getLogger(S_POP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
