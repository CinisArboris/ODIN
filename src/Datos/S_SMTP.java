package Datos;

import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author eyver-dev
 */
public class S_SMTP {
    private String servidor_GMAIL;
    private String puerto_GMAIL;
    
    private String usuario_GMAIL;
    private String password_GMAIL;
    
    private ArrayList<String> dest_GMAIL;
    private String asunto;
    private String body;
    
    private Properties slave;

    public S_SMTP() {
        this.servidor_GMAIL = "default";    //Usuario
        this.puerto_GMAIL   = "default";    //Usuario
        this.usuario_GMAIL  = "default";    //Usuario
        this.password_GMAIL = "default";    //Usuario
        this.dest_GMAIL     = new ArrayList<>();    //Usuario
        this.asunto         = "";           //Usuario
        this.body           = "";           //Usuario
        this.slave          = new Properties(); //Sistema
    }
    
    public String getPuerto_GMAIL() {
        return puerto_GMAIL;
    }
    public void setPuerto_GMAIL(String puerto_GMAIL) {
        this.puerto_GMAIL = puerto_GMAIL;
    }
    public String getUsuario_GMAIL() {
        return usuario_GMAIL;
    }
    public void setUsuario_GMAIL(String usuario_GMAIL) {
        this.usuario_GMAIL = usuario_GMAIL;
    }
    public String getServidor_GMAIL() {
        return servidor_GMAIL;
    }
    public void setServidor_GMAIL(String servidor_GMAIL) {
        this.servidor_GMAIL = servidor_GMAIL;
    }
    public String getPassword_GMAIL() {
        return password_GMAIL;
    }
    public void setPassword_GMAIL(String password_GMAIL) {
        this.password_GMAIL = password_GMAIL;
    }
    public ArrayList<String> getDest_GMAIL() {
        return dest_GMAIL;
    }
    public void setDestinatarios_GMAIL(String destinatarios_GMAIL) {
        this.dest_GMAIL.add(destinatarios_GMAIL);
    }
    public Properties getSlave() {
        return slave;
    }
    public void setSlave(Properties slave) {
        this.slave = slave;
    }
    public String getAsunto() {
        return asunto;
    }
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public void boot() {
        this.getSlave().put("mail.smtp.auth",
                "true");
        this.getSlave().put("mail.smtp.port",
                this.getPuerto_GMAIL());// Default 465
//        this.getSlave().put("mail.debug",
//                "true");
        this.getSlave().put("mail.smtp.socketFactory.port",
                this.getPuerto_GMAIL());// Default 465
        this.getSlave().put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        this.getSlave().put("mail.smtp.socketFactory.fallback",
                "false");
    }
}
