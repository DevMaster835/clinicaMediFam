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
public class tipoEmpleados {
    int idTipo;
    String tipoEmpleado;

    public tipoEmpleados(int idTipo, String tipoEmpleado) {
        this.idTipo = idTipo;
        this.tipoEmpleado = tipoEmpleado;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }
    
    public static void cmbTipoEmpleado(Connection cone, ObservableList<tipoEmpleados>lista){
        Statement statement;
        try {
            statement = cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT * FROM tipo_empleado");
            
            while(resultado.next()){
                lista.add(
                            new tipoEmpleados(
                                    resultado.getInt("idTipoEmpleado"),
                                    resultado.getString("tipoEmpleado")                                  
                            ));
                               

            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    @Override
    public String toString(){
       return  tipoEmpleado;
    }
    
}


