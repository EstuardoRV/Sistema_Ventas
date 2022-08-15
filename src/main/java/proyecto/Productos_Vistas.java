
package proyecto;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        ActionListener funcion_crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear();
            }
        };
        crear.addActionListener(funcion_crear);
        
        JButton carga = new JButton("Carga Masiva");
        carga.setBounds(670,100,130,50);
        productos.add(carga);
        
        ActionListener funcion_carga = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    carga_masiva();
                } catch (IOException ex) {
                    Logger.getLogger(Sucursales_Vistas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Sucursales_Vistas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        carga.addActionListener(funcion_carga);
        
        JButton actualizar = new JButton("Actualizar");
        actualizar.setBounds(500,250,130,50);
        productos.add(actualizar);
        
        ActionListener funcion_actualizar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        };

        actualizar.addActionListener(funcion_actualizar);
        
        JButton eliminar = new JButton("Eliminar");
        eliminar.setBounds(670,250,130,50);
        productos.add(eliminar);
        
        ActionListener funcion_eliminar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcion_eliminar();
            }
        };

        eliminar.addActionListener(funcion_eliminar);
        
        JButton exportar = new JButton("Exportar Listado a PDF");
        exportar.setBounds(500,400,300,50);
        productos.add(exportar);
        
        ActionListener funcion_pdf = new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e){
              ProductosDAO pd = new ProductosDAO();
              try {
                  pd.pdf();
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(Productos_Vistas.class.getName()).log(Level.SEVERE, null, ex);
              } catch (DocumentException ex) {
                  Logger.getLogger(Productos_Vistas.class.getName()).log(Level.SEVERE, null, ex);
              }
          }  
        };
        
        exportar.addActionListener(funcion_pdf);
    }
    
    private void tabla(){
        String columnas []= {"Codigo","Nombre","Descripci�n","Cantidad","Precio"};
        ProductosDAO sd = new ProductosDAO();
        Object filas [][] = sd.listar_tabla();
        
        tabla = new JTable(filas,columnas);
        sp = new JScrollPane(tabla);
        sp.setBounds(10,20,430,600);
        productos.add(sp);
    }
    
    private void crear(){
        
        JFrame frame_producto = new JFrame();
        frame_producto.setTitle("Nuevo Producto");
        frame_producto.setLocationRelativeTo(null);
        frame_producto.setBounds(50, 175, 350, 400);
        frame_producto.setVisible(true);
        
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        frame_producto.add(p1);
        
        
        JLabel l1 = new JLabel("Codigo:");
        l1.setBounds(50,20,80,50);
        JTextField t1 = new JTextField();
        t1.setBounds(150,32,130,25);
        p1.add(t1);
        p1.add(l1);
        
        JLabel l2 = new JLabel("Nombre:");
        l2.setBounds(50,80,80,50);
        JTextField t2 = new JTextField();
        t2.setBounds(150,92,130,25);
        p1.add(t2);
        p1.add(l2);
        
        JLabel l3 = new JLabel("Descripcion:");
        l3.setBounds(50,140,80,50);
        JTextField t3 = new JTextField();
        t3.setBounds(150,152,130,25);
        p1.add(t3);
        p1.add(l3);
        
        JLabel l4 = new JLabel("Cantidad:");
        l4.setBounds(50,200,80,50);
        JTextField t4 = new JTextField();
        t4.setBounds(150,212,130,25);
        p1.add(t4);
        p1.add(l4);
        
        JLabel l5 = new JLabel("Precio:");
        l5.setBounds(50,260,80,50);
        JTextField t5 = new JTextField();
        t5.setBounds(150,272,130,25);
        p1.add(t5);
        p1.add(l5);
        
        JButton b1 = new JButton("Guardar");
        b1.setBounds(130,320,80,25);
        p1.add(b1);
        
        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductosDAO Pd = new ProductosDAO();
                Pd.crear(Integer.parseInt(t1.getText()), t2.getText(), t3.getText(), Integer.parseInt(t4.getText()), Float.parseFloat(t5.getText()));
                frame_producto.setVisible(false);
            }
        };
        b1.addActionListener(guardar);
    }
    
    private void modificar(){
        JFrame fra_mod = new JFrame();
        fra_mod.setTitle("Modificar Producto");
        fra_mod.setLocationRelativeTo(null);
        fra_mod.setBounds(50,175,350,400);
        fra_mod.setVisible(true);
        
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        fra_mod.add(p1);
        
        JLabel l1 = new JLabel("Código: ");
        l1.setBounds(50,20,80,50);
        p1.add(l1);
        
        JTextField t1 = new JTextField();
        t1.setBounds(150,32,130,25);
        t1.setText(tabla.getValueAt(tabla.getSelectedRow(), 0)+"");
        t1.setEnabled(false);
        p1.add(t1);
        
        JLabel l2 = new JLabel("Nombre: ");
        l2.setBounds(50,80,80,50);
        p1.add(l2);
        
        JTextField t2 = new JTextField();
        t2.setBounds(150,92,130,25);
        t2.setText(tabla.getValueAt(tabla.getSelectedRow(), 1)+"");
        p1.add(t2);
        
        JLabel l3 = new JLabel("Descripcion: ");
        l3.setBounds(50,140,80,50);
        p1.add(l3);
        
        JTextField t3 = new JTextField();
        t3.setBounds(150,152,130,25);
        t3.setText(tabla.getValueAt(tabla.getSelectedRow(), 2)+"");
        p1.add(t3);
        
        JLabel l4 = new JLabel("Cantidad: ");
        l4.setBounds(50,200,80,50);
        p1.add(l4);
        
        JTextField t4 = new JTextField();
        t4.setBounds(150,212,130,25);
        t4.setText(tabla.getValueAt(tabla.getSelectedRow(), 3)+"");
        p1.add(t4);
        
        JLabel l5 = new JLabel("Precio: ");
        l5.setBounds(50,260,80,50);
        p1.add(l5);
        
        JTextField t5 = new JTextField();
        t5.setBounds(150,272,130,25);
        t5.setText(tabla.getValueAt(tabla.getSelectedRow(), 4)+"");
        p1.add(t5);
        
        JButton b1 = new JButton("Actualizar");
        b1.setBounds(130,310,100,30);
        p1.add(b1);
        
        ActionListener actualiza = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ProductosDAO sd = new ProductosDAO();
                sd.modificar(Integer.parseInt(t1.getText()), t2.getText(), t3.getText(), Integer.parseInt(t4.getText()), Float.parseFloat(t5.getText()));
                fra_mod.setVisible(false);
                tabla.setVisible(false);
                tabla();
            }
        };
        b1.addActionListener(actualiza);
    }
    
    private void opcion_eliminar(){
        ProductosDAO pd = new ProductosDAO();
        pd.eliminar(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0)+""));
    }
    
    private String leerarchivo() {

        JPanel c1 = new JPanel();
        JFileChooser fc = new JFileChooser();
        int op = fc.showOpenDialog(c1);
        String content = "";
        if (op == JFileChooser.APPROVE_OPTION) {

            File pRuta = fc.getSelectedFile();
            String ruta = pRuta.getAbsolutePath();
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;

            try {
                archivo = new File(ruta);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                String linea = "";

                while ((linea = br.readLine()) != null) {

                    content += linea + "\n";
                }
                return content;

            } catch (FileNotFoundException ex) {
                String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
            } catch (IOException ex) {
                String resp = (String) JOptionPane.showInputDialog(null, "No se pudo abrir el archivo");
            } finally {
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
                    return "";
                }
            }
            return content;
        }
        return null;
    }
    
    private void carga_masiva() throws FileNotFoundException, IOException, ParseException {

        String archivo_retorno = leerarchivo();

        JsonParser parse = new JsonParser();
        JsonArray matriz = parse.parse(archivo_retorno).getAsJsonArray();

        for (int i = 0; i < matriz.size(); i++) {
            JsonObject objeto = matriz.get(i).getAsJsonObject();
            ProductosDAO pd = new ProductosDAO();
            pd.crear(objeto.get("codigo").getAsInt(), objeto.get("nombre").getAsString(), objeto.get("descripcion").getAsString(), objeto.get("cantidad").getAsInt(), objeto.get("precio").getAsInt());
        }
    }
    
    public void ejecutar(){
        botones();
        tabla();
    }
}
