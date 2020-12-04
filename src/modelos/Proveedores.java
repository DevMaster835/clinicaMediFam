/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import com.mysql.jdbc.Connection;
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
public class Proveedores {
    String id;
    String rtn;
    String proveedor;
    String contacto;
    String direccion;

    public Proveedores(String id, String rtn, String proveedor, String contacto, String direccion) {
        this.id = id;
        this.rtn = rtn;
        this.proveedor = proveedor;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public String getRtn() {
        return rtn;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public String getDireccion() {
        return direccion;
    }
    
    public static void llenarTabla(Connection cone, ObservableList <Proveedores>lista){
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT idProveedor, RTN, nombreProveedor, nombreContacto, direccion FROM proveedores");
            while(resultado.next()){
                lista.add(
                            new Proveedores(
                                    resultado.getString("idProveedor"),
                                    resultado.getString("RTN"),
                                    resultado.getString("nombreProveedor"),                                  
                                    resultado.getString("nombreContacto"),
                                    resultado.getString("direccion")
         
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
