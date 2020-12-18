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
    Nacionalidades nac;
 /*   Telefonos tel;
    Correos corr;*/
    String direccion;
    tipoEmpleados tipoE;
    
    
    public Empleados(String idEmp, String nombres, String apellidos, Date fechaNac, String idGenero, Nacionalidades nac, /*Telefonos tel, Correos corr,*/ String direccion, tipoEmpleados tipoE/*String tipoEmp*/) {
        this.idEmp = idEmp;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.idGenero = idGenero;
        this.nac = nac;
       /* this.tel=tel;
        this.corr=corr;*/
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

   /* public Telefonos getTel() {
        return tel;
    }

    public Correos getCorr() {
        return corr;
    }*/
    

    public String getDireccion() {
        return direccion;
    }

    public tipoEmpleados getTipoE() {
        return tipoE;
    }

    
    public static void llenarTabla(Connection cone, ObservableList <Empleados>lista){
        try {
            Statement statement= cone.createStatement();
            /*ResultSet resultado= statement.executeQuery("SELECT emp.idEmpleado, emp.nombres, emp.apellidos, emp.fechaNacimiento, emp.idGenero, gen.genero, emp.idNacionalidad, nac.nacionalidad, tel.idTelefono, tel.telefono, co.idCorreo, co.correo, emp.direccion, emp.tipoEmpleado, tp.tipoEmpleado " +
                "FROM empleados emp, genero gen, nacionalidades nac, telefonos_empleados tel, correo_empleados co, tipo_empleado tp " +
                "WHERE gen.idGenero=emp.idGenero and nac.idNacionalidad=emp.idNacionalidad and tel.idEmpleado=emp.idEmpleado and co.idEmpleado=emp.idEmpleado and tp.idTipoEmpleado=emp.tipoEmpleado;");*/
            ResultSet resultado = statement.executeQuery("SELECT distinct emp.idEmpleado, emp.nombres, emp.apellidos, emp.fechaNacimiento, emp.idGenero, gen.genero, emp.idNacionalidad, nac.nacionalidad, emp.direccion, emp.tipoEmpleado, tp.tipoEmpleado FROM empleados emp LEFT JOIN genero gen ON emp.idGenero= gen.idGenero LEFT JOIN nacionalidades nac on emp.idNacionalidad=nac.idNacionalidad LEFT JOIN telefonos_empleados tel on emp.idEmpleado=tel.idEmpleado LEFT join correo_empleados co ON emp.idEmpleado=co.idEmpleado INNER JOIN tipo_empleado tp ON emp.tipoEmpleado=tp.idTipoEmpleado WHERE emp.idEmpleado=tel.idEmpleado and emp.idEmpleado=co.idEmpleado GROUP BY tel.telefono, co.correo");
            while(resultado.next()){
                lista.add(
                            new Empleados(
                                    resultado.getString("emp.idEmpleado"),
                                    resultado.getString("emp.nombres"),
                                    resultado.getString("emp.apellidos"),
                                    resultado.getDate("emp.fechaNacimiento"),
                                    resultado.getString("gen.genero"),
                                    //resultado.getString("nac.nacionalidad"),
                            new Nacionalidades(
                                    resultado.getInt("emp.idNacionalidad"),
                                    resultado.getString("nac.nacionalidad")
                            ),
                            /*new Telefonos(
                                    resultado.getString("emp.idEmpleado"),
                                    resultado.getString("emp.nombres"),
                                    resultado.getString("tel.telefono")
                            ),
                            new Correos(
                                    resultado.getString("emp.idEmpleado"),
                                    resultado.getString("emp.nombres"),
                                    resultado.getString("co.correo")
                            ),*/
                            
                                    resultado.getString("emp.direccion"),
                                   // resultado.getString("tpe.tipoEmpleado")
                            
                            new tipoEmpleados(
                                    resultado.getInt("emp.tipoEmpleado"),
                                    resultado.getString("tp.tipoEmpleado")
                            )
                                    
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
}
