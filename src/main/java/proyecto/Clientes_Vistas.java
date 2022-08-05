
package proyecto;

import javax.swing.*;

public class Clientes_Vistas {
    JPanel clientes = new JPanel();
    JTable tabla = new JTable();
    JScrollPane sp;
    
    private void botones(){
        clientes.setLayout(null);
        JButton crear = new JButton("Crear");
        crear.setBounds(500,100,130,50);
        clientes.add(crear);
        
        JButton carga = new JButton("Carga Masiva");
        carga.setBounds(670,100,130,50);
        clientes.add(carga);
        
        JButton actualizar = new JButton("Actualizar");
        actualizar.setBounds(500,250,130,50);
        clientes.add(actualizar);
        
        JButton eliminar = new JButton("Eliminar");
        eliminar.setBounds(670,250,130,50);
        clientes.add(eliminar);
        
        JButton exportar = new JButton("Exportar Listado a PDF");
        exportar.setBounds(500,400,300,50);
        clientes.add(exportar);
    }
    
    private void tabla(){
        String columnas []= {"Código","Nombre","NIT","Correo","Genero"};
        Object filas [][] = {{"2020","arturo","98541-5","correo","m"}};
        
        tabla = new JTable(filas,columnas);
        sp = new JScrollPane(tabla);
        sp.setBounds(10,20,430,600);
        clientes.add(sp);
    }
    
    
    public void ejecutar(){
        botones();
        tabla();
    }
}
