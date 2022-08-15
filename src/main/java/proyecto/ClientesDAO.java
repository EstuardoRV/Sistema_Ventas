
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

public class ClientesDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConexionP conectar = new ConexionP();
    
    Object datos [][];
    String nombres[];
    
    public Object [][] listar_tabla(){
        String instruccion = "select * from clientes";
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
                datos[x][2] = rs.getInt(3);
                datos[x][3] = rs.getString(4);
                datos[x][4] = rs.getString(5);
                x++;
            }
        }catch (Exception e){
            
        }
        return datos;
    }
    
    public void crear(int codigo, String nombre, int nit, String correo, String genero){
        String sql = "insert into clientes(Codigo,Nombre,NIT,Correo,Genero) values (?,?,?,?,?)";
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, nit);
            ps.setString(4, correo);
            ps.setString(5, genero);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void modificar(int cod, String nom, int nit, String correo, String genero){
        String sql = "update clientes set Nombre='"+nom+"', NIT='"+nit+"', Correo='"+correo+"', Genero='"+genero+"' where Codigo='"+cod+"'";
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void eliminar(int cod){
        String sql = "delete from clientes where codigo= " + cod;
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void pdf() throws FileNotFoundException, DocumentException {

        FileOutputStream gen = new FileOutputStream("Reporte-Clientes.pdf");
        Document documento = new Document();

        PdfWriter.getInstance(documento, gen);
        documento.open();

        Paragraph parrafo = new Paragraph("Reporte de Clientes Base de datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("\n"));

        String sql = "select * from clientes";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                 documento.add(new Paragraph("Código: "+rs.getInt(1)));
                 documento.add(new Paragraph("Nombre: "+rs.getString(2)));
                 documento.add(new Paragraph("NIT: "+rs.getInt(3)));
                 documento.add(new Paragraph("Correo: "+rs.getString(4)));
                 documento.add(new Paragraph("Genero: "+rs.getString(5)));
                documento.add(new Paragraph("\n\n"));
            }
        } catch (Exception e) {
        }
               
         
        documento.close();
        JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
        try {
            File sucursales_doc = new File("Reporte-Clientes.pdf");
            Desktop.getDesktop().open(sucursales_doc);
        } catch (Exception e) {
        }
    }
    
    public String[] filtro(String nombre, int nit, String correo, String genero) {

        String sql = "select * from clientes where nombre = '" + nombre + "'" + " or nit = " + "'" + nit + "'" + " or correo = " + "'" + correo + "'" + " or genero = " + "'" + genero + "'" + ";";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            int x =0;

            while (rs.next()) {
                x++;
            }
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            nombres = new String [x];
             x = 0;
            while (rs.next()) {
                nombres[x]= rs.getString(2);
                x++;
            }

        } catch (Exception e) {

        }

        return nombres;
    }
    
}
