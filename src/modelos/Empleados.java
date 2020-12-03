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
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author José
 */
public class Empleados {
    String idEmp;
    String nombres;
    String apellidos;
    final Date fechaNac;
    String idGenero;
    /*String idNacionalidad;*/
    Nacionalidades nac;
    String telefono;
    String correo;
    String direccion;
    /*String tipoEmp;*/
    tipoEmpleados tipoE;
    
   /* StringProperty idEmp;
    StringProperty nombre;
    StringProperty apellido;
    StringProperty fechaNac;
    IntegerProperty genero;
    IntegerProperty nacionalidad;
    StringProperty direccion;
    IntegerProperty tipoEmp;*/
    
    
    public Empleados(String idEmp, String nombres, String apellidos, Date fechaNac, String idGenero, Nacionalidades nac, /*String telefono, String correo,*/ String direccion, tipoEmpleados tipoE/*String tipoEmp*/) {
        this.idEmp = idEmp;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.idGenero = idGenero;
        this.nac = nac;
     /*   this.telefono=telefono;
        this.correo=correo;*/
        this.direccion = direccion;
        this.tipoE=tipoE;
    }

    public String getIdEmp() {
        return idEmp;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public Nacionalidades getNac() {
        return nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public tipoEmpleados getTipoE() {
        return tipoE;
    }

    
    public static void llenarTabla(Connection cone, ObservableList <Empleados>lista){
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT e.idEmpleado, e.nombres, e.apellidos, e.fechaNacimiento, gen.genero, e.idNacionalidad, nac.nacionalidad, tels.telefono, correo.correo, e.direccion, e.tipoEmpleado, tpe.tipoEmpleado FROM empleados e,genero gen, nacionalidades nac, telefonos_empleados tels,correo_empleados correo,tipo_empleado tpe WHERE gen.idGenero=e.idGenero and nac.idNacionalidad=e.idNacionalidad and tels.idEmpleado=e.idEmpleado and correo.idEmpleado=e.idEmpleado and tpe.idTipoEmpleado=e.tipoEmpleado");
            while(resultado.next()){
                lista.add(
                            new Empleados(
                                    resultado.getString("e.idEmpleado"),
                                    resultado.getString("e.nombres"),
                                    resultado.getString("e.apellidos"),
                                    resultado.getDate("e.fechaNacimiento"),
                                    resultado.getString("gen.genero"),
                                    //resultado.getString("nac.nacionalidad"),
                            new Nacionalidades(
                                    resultado.getInt("e.idNacionalidad"),
                                    resultado.getString("nac.nacionalidad")
                            ),
                                   /* resultado.getString("tels.telefono"),
                                    resultado.getString("correo.correo"),*/
                            
                                    resultado.getString("e.direccion"),
                                   // resultado.getString("tpe.tipoEmpleado")
                            
                            new tipoEmpleados(
                                    resultado.getInt("e.tipoEmpleado"),
                                    resultado.getString("tpe.tipoEmpleado")
                            )
                                    
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
}
