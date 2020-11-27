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
public class Nacionalidades {
    int idNacionalidad;
    String nacionalidades;

    public Nacionalidades(int idNacionalidad, String nacionalidades) {
        this.idNacionalidad = idNacionalidad;
        this.nacionalidades = nacionalidades;
    }

    public int getIdNacionalidad() {
        return idNacionalidad;
    }

    public String getNacionalidades() {
        return nacionalidades;
    }
    
    
    public static void llenarTabla(Connection cone, ObservableList <Nacionalidades>lista){
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT idNacionalidad, nacionalidad FROM nacionalidades");
            while(resultado.next()){
                lista.add(
                            new Nacionalidades(
                                    resultado.getInt("idNacionalidad"),
                                    resultado.getString("nacionalidad")                                  
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public String toString(){
       return  nacionalidades;
    }
}

