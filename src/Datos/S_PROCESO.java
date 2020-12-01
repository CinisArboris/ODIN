package Datos;

import Negocio.Utilidades;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author eyver-dev
 */
public class S_PROCESO {
    private ArrayList<String> FUNCION;
    private ArrayList<String> CU;
    private ArrayList<String> PARAMETROS;
    private String funcionVariable;
    private String cuVariable;

    public S_PROCESO() {
        this.FUNCION = new ArrayList<>();
        this.CU = new ArrayList<>();
        this.PARAMETROS = new ArrayList<>();
        this.funcionVariable = "";
        this.cuVariable = "";
        
        this.setFUNCION("add");
        this.setFUNCION("delete");
        this.setFUNCION("edit");
        this.setFUNCION("list");
        this.setFUNCION("search");
        
        this.setCU("Persona");
    }

    public ArrayList<String> getFUNCION() {
        return FUNCION;
    }
    public void setFUNCION(String elemento) {
        this.FUNCION.add(elemento);
    }
    public ArrayList<String> getCU() {
        return CU;
    }
    public void setCU(String elemento) {
        this.CU.add(elemento);
    }
    public ArrayList<String> getPARAMETROS() {
        return PARAMETROS;
    }
    public void setPARAMETROS(String elemento) {
        this.PARAMETROS.add(elemento);
    }

    public String getFuncionVariable() {
        return funcionVariable;
    }
    public void setFuncionVariable(String funcionVariable) {
        this.funcionVariable = funcionVariable;
    }
    public String getCuVariable() {
        return cuVariable;
    }
    public void setCuVariable(String cuVariable) {
        this.cuVariable = cuVariable;
    }

    
    /**
     * 
     * @param cadenaOP 
     */
    public void separar_datos(String cadenaOP) {
        // Inicializar variables y utilidades.
        String filtro = "default";
        StringTokenizer freyja;
        String cadena = "default";
        boolean bandera = false;
        Utilidades utils = new Negocio.Utilidades();
        
        // Inicio de las operaciones.
        cadena = cadenaOP;
        bandera = utils.verificarSegmentos(cadena);
        if (bandera){
            filtro = "{}";
            freyja = new StringTokenizer(cadena, filtro);
            
            // Segunda parte.
            String F_CU;
            String params;
            F_CU = freyja.nextToken();
            params = freyja.nextToken();

            cadena = F_CU;
            filtro = "_";
            freyja = new StringTokenizer(cadena, filtro);
            this.setFuncionVariable(freyja.nextToken());
            this.setCuVariable(freyja.nextToken());
        }else{
            System.out.println("Error de formato.");
        }
    }
    
}
