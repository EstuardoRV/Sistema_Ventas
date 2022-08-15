
package proyecto;

import javax.swing.*;

public class Vendedor extends JFrame{
    JTabbedPane pestañas = new JTabbedPane();
    
    public void inicio(){
        setTitle("Ventas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 175, 900, 800);
        setVisible(true);

        Nueva_venta nv = new Nueva_venta();
        nv.ejecutar();

        ventas_realizadas vr = new ventas_realizadas();

        pestañas.addTab("Nueva venta", nv.general);
        pestañas.addTab("Productos", vr.ventas_general);
        add(pestañas);
    }
    public static void main(String[] args) {
        Vendedor v = new Vendedor();
        v.inicio();
    }
}
