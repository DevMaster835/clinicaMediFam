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
public class tipoSangre {
    int idSangre;
    String tipoSangre;

    public tipoSangre(int idSangre, String tipoSangre) {
        this.idSangre = idSangre;
        this.tipoSangre = tipoSangre;
    }

    public int getIdSangre() {
        return idSangre;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }
    
    public static void cmbTipoSangre(Connection cone, ObservableList<tipoSangre>lista){
        Statement statement;
        try {
            statement = cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT * FROM tipo_sangre");
            
            while(resultado.next()){
                lista.add(
                            new tipoSangre(
                                    resultado.getInt("idSangre"),
                                    resultado.getString("tipoSangre")                                  
                            ));                               
            }
        } catch (SQLException ex) {
            Logger.getLogger(tipoSangre.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public String toString() {
        return tipoSangre;
    }


    
    
    
}
