
package proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentasDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionP conectar = new ConexionP();
    
    Object datos[][];
    
    public Object[][] listar_tabla() {
        String instruccion = "select * from compras";
        try {
            int x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();
            while (rs.next()) {
                x++;
            }

            datos = new Object[x][5];
            x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[x][0] = rs.getInt(1);
                datos[x][1] = rs.getInt(2);
                datos[x][2] = rs.getString(3);
                datos[x][3] = rs.getDate(4);
                datos[x][4] = rs.getDouble(5);
                x++;
            }

        } catch (Exception e) {
        }

        return datos;

    }
    
}
