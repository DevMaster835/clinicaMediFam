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
public class Medicos {
    String id;
    String medico;
    Especialidades esp;
    String licencia;
    String años;

    public Medicos(String id, String medico, Especialidades esp, String licencia, String años) {
        this.id = id;
        this.medico = medico;
        this.esp = esp;
        this.licencia = licencia;
        this.años = años;
    }

    public String getId() {
        return id;
    }

    public String getMedico() {
        return medico;
    }

    public Especialidades getEsp() {
        return esp;
    }

    public String getLicencia() {
        return licencia;
    }

    public String getAños() {
        return años;
    }
   /* 
    public static void llenarTabla(Connection cone, ObservableList <Medicos>lista){
        
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT empleados.idEmpleado, CONCAT(`empleados`.`nombres`, ' ', empleados.apellidos) as emp, especialidades.especialidad, medicos.licenciaMedica, medicos.añosExperiencia " +
                                                            "FROM empleados, especialidades, medicos " +
                                                            "WHERE empleados.idEmpleado=medicos.idEmpleado and medicos.idEspecialidad=especialidades.idEspecialidad");
            while(resultado.next()){
                lista.add(
                            new Medicos(
                                    resultado.getString("empleados.idEmpleado"),
                                    resultado.getString("emp"),
                                    resultado.getString("especialidades.especialidad"),
                                    resultado.getDate("medicos.licenciaMedica"),
                                    resultado.getString("gen.genero"),
                                    //resultado.getString("nac.nacionalidad"),
                            new Nacionalidades(
                                    resultado.getInt("emp.idNacionalidad"),
                                    resultado.getString("nac.nacionalidad")
                            ),
                            
                                    
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Medicos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    */
    
}
