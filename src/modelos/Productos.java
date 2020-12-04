/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author José
 */
public class Productos {
    int id;
    String nombre;
    int precio;
    Date fechaVen;
    int stock;
    int contNeto;

    public Productos(int id, String nombre, int precio, Date fechaVen, int stock, int contNeto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaVen = fechaVen;
        this.stock = stock;
        this.contNeto = contNeto;
    }
    
   

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public Date getFechaVen() {
        return fechaVen;
    }

    public int getStock() {
        return stock;
    }

    public int getContNeto() {
        return contNeto;
    }
    
    public static void llenarTabla(Connection cone, ObservableList <Productos>lista){
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT idProducto, nombre, idPrecioHis, fechaVencimiento, stock, contenidoNeto FROM productos");
            while(resultado.next()){
                lista.add(
                            new Productos(
                                    resultado.getInt("idProducto"),
                                    resultado.getString("nombre"),
                                    resultado.getInt("idPrecioHis"),                                  
                                    resultado.getDate("fechaVencimiento"),
                                    resultado.getInt("stock"),
                                    resultado.getInt("contenidoNeto")
         
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
