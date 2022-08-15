
package proyecto;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ProductosDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionP conectar = new ConexionP();
    
    Object datos [][];
    
    public Object [][] listar_tabla(){
        String instruccion = "select * from productos";
        try{
            int x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();
            
            while(rs.next()){
                x++;
            }
            datos = new Object[x][5];
            
            x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();
            while(rs.next()){
                datos[x][0] = rs.getInt(1);
                datos[x][1] = rs.getString(2);
                datos[x][2] = rs.getString(3);
                datos[x][3] = rs.getInt(4);
                datos[x][4] = rs.getFloat(5);
                x++;
            }
        }catch (Exception e){
            
        }
        return datos;
    }
    
    public void crear(int codigo, String nombre, String descripcion, int cantidad, float precio){
        String sql = "insert into productos(Codigo,Nombre,Descripcion,Cantidad,Precio) values (?,?,?,?,?)";
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, descripcion);
            ps.setInt(4, cantidad);
            ps.setFloat(5, precio);
            ps.executeUpdate();
        }catch(Exception e){
            
        }
    }
    
    public void modificar(int cod, String nom, String descripcion, int cant, float precio){
        String sql = "update productos set Nombre='"+nom+"', Descripcion='"+descripcion+"', Cantidad='"+cant+"', Precio='"+precio+"' where Codigo='"+cod+"'";
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void eliminar(int cod){
        String sql = "delete from productos where codigo= " + cod;
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void pdf() throws FileNotFoundException, DocumentException {

        FileOutputStream gen = new FileOutputStream("Reporte-Productos.pdf");
        Document documento = new Document();

        PdfWriter.getInstance(documento, gen);
        documento.open();

        Paragraph parrafo = new Paragraph("Reporte de Productos Base de datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("\n"));

        String sql = "select * from productos";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                 documento.add(new Paragraph("Código: "+rs.getInt(1)));
                 documento.add(new Paragraph("Nombre: "+rs.getString(2)));
                 documento.add(new Paragraph("Descripcion: "+rs.getString(3)));
                 documento.add(new Paragraph("Cantidad: "+rs.getInt(4)));
                 documento.add(new Paragraph("Precio: "+rs.getFloat(5)));
                documento.add(new Paragraph("\n\n"));
            }
        } catch (Exception e) {
        }
               
         
        documento.close();
        JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
        try {
            File sucursales_doc = new File("Reporte-Productos.pdf");
            Desktop.getDesktop().open(sucursales_doc);
        } catch (Exception e) {
        }
    }
}
