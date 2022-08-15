
package proyecto;

import javax.swing.*;

public class ventas_realizadas {
    JPanel ventas_general = new JPanel();
    JTable tabla;
    JScrollPane sp;
    
    private void tabla(){
        String columnas []= {"No_Factura","NIT","Nombre","Fecha","Total"};
        VentasDAO vd = new VentasDAO();
        
        Object filas [][] = vd.listar_tabla();
        
        tabla = new JTable(filas,columnas);
        ventas_general.add(tabla);
    }
}
