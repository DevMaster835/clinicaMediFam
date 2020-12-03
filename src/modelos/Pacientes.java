/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import com.mysql.jdbc.Connection;
import java.sql.Date;
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
public class Pacientes {
    String id;
    String nombres;
    String apellidos;
    Date fechaNacimiento;
    String genero;
    Nacionalidades nac;
    String direccion;
    String peso;
    String altura;
    tipoSangre ts;

    public Pacientes(String id, String nombres, String apellidos, Date fechaNacimiento, String genero, Nacionalidades nac, String direccion, String peso, String altura, tipoSangre ts) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.nac = nac;
        this.direccion = direccion;
        this.peso = peso;
        this.altura = altura;
        this.ts = ts;
    }

    public String getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public Nacionalidades getNac() {
        return nac;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPeso() {
        return peso;
    }

    public String getAltura() {
        return altura;
    }

    public tipoSangre getTs() {
        return ts;
    }
    
    public static void llenarTabla(Connection cone, ObservableList <Pacientes>lista){
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT pac.idPaciente, pac.nombres, pac.apellidos, pac.fechaNacimiento, gen.genero, pac.idNacionalidad, nac.nacionalidad, pac.direccion, pac.peso, pac.altura, pac.tipoSangre, ts.tipoSangre FROM pacientes pac, genero gen, nacionalidades nac, tipo_sangre ts WHERE gen.idGenero=pac.idGenero and nac.idNacionalidad=pac.idNacionalidad and ts.idSangre=pac.tipoSangre");
            while(resultado.next()){
                lista.add(
                            new Pacientes(
                                    resultado.getString("pac.idPaciente"),
                                    resultado.getString("pac.nombres"),
                                    resultado.getString("pac.apellidos"),
                                    resultado.getDate("pac.fechaNacimiento"),
                                    resultado.getString("gen.genero"),
                                    //resultado.getString("nac.nacionalidad"),
                            new Nacionalidades(
                                    resultado.getInt("pac.idNacionalidad"),
                                    resultado.getString("nac.nacionalidad")
                            ),
                                   /* resultado.getString("tels.telefono"),
                                    resultado.getString("correo.correo"),*/
                            
                                    resultado.getString("pac.direccion"),
                                    resultado.getString("pac.peso"),
                                    resultado.getString("pac.altura"),
                            
                            new tipoSangre(
                                    resultado.getInt("pac.tipoSangre"),
                                    resultado.getString("ts.tipoSangre")
                            )
                                    
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
