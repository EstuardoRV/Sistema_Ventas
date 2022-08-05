
package proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VendedoresDAO1 {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionP conectar = new ConexionP();
    
    public Object [][] listar_tabla(){
        String instruccion = "select * from vendedores";
        
        return null;
    }
}
