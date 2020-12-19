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
public class Telefonos {
    String id;
    String nombre;
    String numero;
    

    public Telefonos(String id, String nombre, String numero) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
       
    }
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefonos other = (Telefonos) obj;
        if (!this.numero.equals(other.numero)) {
            return false;
        }
        return true;
        
        
    }
  /*  
    public static void llenarTabla(Connection cone, ObservableList <Telefonos>lista){
        try {
            Statement statement= cone.createStatement();
            
            ResultSet resultado = statement.executeQuery("SELECT emp.idEmpleado, emp.nombres, tel.telefono FROM empleados emp LEFT JOIN telefonos_empleados tel ON emp.idEmpleado=tel.idEmpleado");
            while(resultado.next()){
                lista.add(
                            new Telefonos(
                                    resultado.getString("emp.idEmpleado"),
                                    resultado.getString("emp.nombres"),
                                    resultado.getString("tel.telefono")         
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Telefonos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
*/
    @Override
    public String toString() {
        return numero;
    }


    


    
}
