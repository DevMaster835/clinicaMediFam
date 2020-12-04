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
public class EstadoConsulta {
    int idEstado;
    String estado;

    public EstadoConsulta(int idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getEstado() {
        return estado;
    }
    
    public static void cmbEstado(Connection cone, ObservableList<EstadoConsulta>lista){
        Statement statement;
        try {
            statement = cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT * FROM estado_consultas");
            
            while(resultado.next()){
                lista.add(
                            new EstadoConsulta(
                                    resultado.getInt("idEstado"),
                                    resultado.getString("estado")                                  
                            ));                               
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadoConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    @Override
    public String toString(){
       return  estado;
    }
}
