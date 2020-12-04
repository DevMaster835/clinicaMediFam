/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Conexion.conexion;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Martha
 */
public class DetalleFacturacion {
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
     private StringProperty idDetalle;
     private StringProperty idFacturacion;
     private StringProperty idProducto;
     private StringProperty Cantidad;
    

public DetalleFacturacion(String idDetalle,String idFacturacion,String idProducto,String Cantidad){
    
    this.idDetalle= new SimpleStringProperty(idDetalle);
    this.idFacturacion = new SimpleStringProperty(idFacturacion);
    this.idProducto= new SimpleStringProperty(idProducto);
    this.Cantidad= new SimpleStringProperty(Cantidad);
}
    
   public String getidDetalle(){
        
        return idDetalle.get();
    }
    
    public void setidDetalle(String idDetalle){
       this.idDetalle = new SimpleStringProperty(idDetalle);
    }
    
    public StringProperty idDetalleProperty(){
        return idDetalle;
    }
    
    
     public String getidFacturacion(){
        
        return idFacturacion.get();
    }
    
    public void setidFacturacion(String idFacturacion){
       this.idFacturacion = new SimpleStringProperty(idFacturacion);
    }
    
    public StringProperty idFacturacionProperty(){
        return idFacturacion;
    }
    
     public String getidProducto(){
        
        return idProducto.get();
    }
    
    public void setidProducto(String idProducto){
       this.idProducto = new SimpleStringProperty(idProducto);
    }
    
    public StringProperty idProductoProperty(){
        return idProducto;
    }
    
     public String getCantidad(){
        
        return Cantidad.get();
    }
    
    public void setCantidad(String Cantidad){
       this.Cantidad = new SimpleStringProperty(Cantidad);
    }
    
    public StringProperty CantidadProperty(){
        return Cantidad;
    }
    
    public int AgregarDetalle(Connection connection) {
        
        try{
            
         PreparedStatement pps= cone.prepareStatement("Insert into detalle_facturacion(idDetalle,idFacturacion,idProducto,Cantidad) values(?,?,?,?)");
           pps.setString(1, idDetalle.get());
           pps.setString(2, idFacturacion.get());
           pps.setString(3, idProducto.get());
           pps.setString(4, Cantidad.get());
            
           return pps.executeUpdate();
        
            
        } catch(SQLException e){
            
            e.printStackTrace();
            return 0;
        }

        
    }
}