package Datos;

import Negocio.Servidor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eyver-dev
 */
public class S_BD {
    private String host_BD;
    private String puerto_BD;
    private String nombre_BD;
    private String url_BD;
    private Properties propiedades_BD;
    private Connection conexion_BD;

    public S_BD() {
        this.host_BD    = "default";
        this.puerto_BD  = "default";
        this.nombre_BD  = "default";
        this.url_BD     = "default";
        /**
         * [propiedades_BD] no debe inicializar en null.
         */
        this.propiedades_BD = new Properties();
        this.conexion_BD    = null;
    }
    
    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    /**
     * Obtener [IP][DOMINIO] del servidor.
     * @return
     */
    public String getHost_BD() {
        return host_BD;
    }
    /**
     * Almacenar [IP][DOMINIO] del servidor.
     * @param host_BD 
     */
    public void setHost_BD(String host_BD) {
        this.host_BD = host_BD;
    }
    public String getPuerto_BD() {
        return puerto_BD;
    }
    public void setPuerto_BD(String puerto_BD) {
        this.puerto_BD = puerto_BD;
    }
    public String getNombre_BD() {
        return nombre_BD;
    }
    public void setNombre_BD(String nombre_BD) {
        this.nombre_BD = nombre_BD;
    }
    public String getUrl_BD() {
        return url_BD;
    }
    public void setUrl_BD(String url_BD) {
        this.url_BD = url_BD;
    }
    public Properties getPropiedades_BD() {
        return propiedades_BD;
    }
    public void setPropiedades_BD(Properties propiedades_BD) {
        this.propiedades_BD = propiedades_BD;
    }
    public Connection getConexion_BD() {
        return conexion_BD;
    }
    public void setConexion_BD(Connection conexion_BD) {
        this.conexion_BD = conexion_BD;
    }
    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    /**
     * Abrir conexion BD.
     */
    public void iniciar_session_BD() {
        try {
            System.out.println(this.getUrl_BD());
            System.out.println(this.getPropiedades_BD());
            this.setConexion_BD(
                DriverManager.getConnection(
                    this.getUrl_BD(),
                    this.getPropiedades_BD()
                )
            );
        } catch (SQLException ex) {
            Logger.getLogger(S_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexion BD [abierta] :: 200");
    }
    /**
     * Cerrar conexion BD.
     */
    public void cerrar_session_BD() {
        try {
            this.getConexion_BD().close();
        } catch (SQLException ex) {
            Logger.getLogger(S_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexion BD [cerrada] :: 200");
    }
    // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    public void operacionA(String funcionVariable) {
        if ("list".equals(funcionVariable)) this.list_BD();
        if ("add".equals(funcionVariable)) this.add_BD();
        if ("delete".equals(funcionVariable)) this.delete_BD();
        if ("search".equals(funcionVariable)) this.search_BD();
                
        
        System.out.println("OPERATION :: A");
        System.out.println(funcionVariable);
        
        
        Statement st;
        try {
            st = this.getConexion_BD().createStatement();
            try (ResultSet rs = st.executeQuery("SELECT * FROM personas")) {
                while (rs.next()){
                    System.out.print(rs.getString(1));
                    System.out.print(" | ");
                    System.out.print(rs.getString(2));
                    System.out.print(" | ");
                    System.out.print(rs.getString(3));
                    System.out.println("");
                }
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(S_BD.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    private void list_BD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void search_BD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void delete_BD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void add_BD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
