
package proyecto;

import javax.swing.*;

public class Administrador extends JFrame{
    JTabbedPane pestañas = new JTabbedPane();
    
    private void inicio(){
        setTitle("Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 30, 900, 700); 
        setVisible(true);
        
        Sucursales_Vistas sv = new Sucursales_Vistas();
        sv.ejecutar();
        Productos_Vistas ps = new Productos_Vistas();
        ps.ejecutar();
        Clientes_Vistas cs = new Clientes_Vistas();
        cs.ejecutar();
        Vendedores_Vistas vs = new Vendedores_Vistas();
        vs.ejecutar();
        
        pestañas.addTab("Sucursales", sv.sucursales);
        pestañas.addTab("Productos", ps.productos);
        pestañas.addTab("Clientes", cs.clientes);
        pestañas.addTab("Vendedores", vs.vendedores);
        
        add(pestañas);
        
    }
    
    public void ejecutar(){
        inicio();
    }
    
    public static void main(String[] args) {
        Administrador ad = new Administrador();
        ad.ejecutar();
    }
}
