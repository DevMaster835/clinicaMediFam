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
public class tipoCorreoE {
    int idTipo;
    String tipoCorreo;

    public tipoCorreoE(int idTipo, String correo) {
        this.idTipo = idTipo;
        this.tipoCorreo = correo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getCorreo() {
        return tipoCorreo;
    }
    
    public static void cmbTipoCorreoE(Connection cone, ObservableList<tipoCorreoE>lista){
        Statement statement;
        try {
            statement = cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT * FROM tipo_correo");
            
            while(resultado.next()){
                lista.add(
                            new tipoCorreoE(
                                    resultado.getInt("idTipoCorreo"),
                                    resultado.getString("tipoCorreo")                                  
                            ));                               
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    @Override
    public String toString(){
       return  tipoCorreo;
    }
}
