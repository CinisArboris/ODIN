package Negocio;

/**
 *
 * @author eyver-dev
 */
public class Utilidades {
    
    /**
     * Si todo esta bien en formato, return true;
     * @param cadena
     * @return 
     */
    public boolean verificarSegmentos(String cadena) {
        if (!cadena.contains("{")) return false;
        if (!cadena.contains("}")) return false;
        return true;
    }
    
}
