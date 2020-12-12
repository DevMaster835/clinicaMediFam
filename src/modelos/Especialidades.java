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
public class Especialidades {
    int id;
    String especialidad;

    public Especialidades(int id, String especialidad) {
        this.id = id;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    public static void llenarEspecialidad(Connection cone, ObservableList <Especialidades>lista){
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT idEspecialidad, especialidad FROM especialidades");
            while(resultado.next()){
                lista.add(
                            new Especialidades(
                                    resultado.getInt("idEspecialidad"),
                                    resultado.getString("especialidad")                                  
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Especialidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String toString() {
        return especialidad;
    }
    
    
}
