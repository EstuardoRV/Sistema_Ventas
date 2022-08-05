
package proyecto;

import javax.swing.*;

public class Productos_Vistas {
    JPanel productos = new JPanel();
    JTable tabla = new JTable();
    JScrollPane sp;
    
    private void botones(){
        productos.setLayout(null);
        JButton crear = new JButton("Crear");
        crear.setBounds(500,100,130,50);
        productos.add(crear);
        
        JButton carga = new JButton("Carga Masiva");
        carga.setBounds(670,100,130,50);
        productos.add(carga);
        
        JButton actualizar = new JButton("Actualizar");
        actualizar.setBounds(500,250,130,50);
        productos.add(actualizar);
        
        JButton eliminar = new JButton("Eliminar");
        eliminar.setBounds(670,250,130,50);
        productos.add(eliminar);
        
        JButton exportar = new JButton("Exportar Listado a PDF");
        exportar.setBounds(500,400,300,50);
        productos.add(exportar);
    }
    
    private void tabla(){
        String columnas []= {"Código","Nombre","Descripción","Cantidad","Precio"};
        Object filas [][] = {{"2020","arturo","dlsfkjm","correo","150"}};
        
        tabla = new JTable(filas,columnas);
        sp = new JScrollPane(tabla);
        sp.setBounds(10,20,430,600);
        productos.add(sp);
    }
    
    public void ejecutar(){
        botones();
        tabla();
    }
}
