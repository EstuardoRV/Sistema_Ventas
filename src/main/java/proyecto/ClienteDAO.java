
package proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionP conectar = new ConexionP();
    
    public void filtro(int cod){
        String sql = "select * from productos where Codigo="+cod;
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (Exception e) {

        }
    }
}
