/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import Conexion.conexion;
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
public class Empleados {
    String idEmp;
    String nombres;
    String apellidos;
    String fechaNac;
    String idGenero;
    String idNacionalidad;
    String telefono;
    String correo;
    String direccion;
    String tipoEmp;
    
   /* StringProperty idEmp;
    StringProperty nombre;
    StringProperty apellido;
    StringProperty fechaNac;
    IntegerProperty genero;
    IntegerProperty nacionalidad;
    StringProperty direccion;
    IntegerProperty tipoEmp;*/
    
    
    public Empleados(String idEmp, String nombres, String apellidos, String fechaNac, String idGenero, String idNacionalidad, String telefono, String correo, String direccion, String tipoEmp) {
        this.idEmp = idEmp;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.idGenero = idGenero;
        this.idNacionalidad = idNacionalidad;
        this.telefono=telefono;
        this.correo=correo;
        this.direccion = direccion;
        this.tipoEmp=tipoEmp;
    }

/*
    public Empleados(String idEmp, String nombre, String apellido, String fechaNac, int genero, int nacionalidad, String direccion, int tipoEmp ) {
        this.idEmp = new SimpleStringProperty(idEmp);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.fechaNac = new SimpleStringProperty(fechaNac);
        this.genero = new SimpleIntegerProperty(genero);
        this.nacionalidad = new SimpleIntegerProperty(nacionalidad);
        this.direccion = new SimpleStringProperty(direccion);
        this.tipoEmp = new SimpleIntegerProperty(tipoEmp);
    }

    public StringProperty getIdEmp() {
        return idEmp;
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public StringProperty getApellido() {
        return apellido;
    }

    public StringProperty getFechaNac() {
        return fechaNac;
    }

    public IntegerProperty getGenero() {
        return genero;
    }


    public IntegerProperty getNacionalidad() {
        return nacionalidad;
    }

    public StringProperty getDireccion() {
        return direccion;
    }


    public IntegerProperty getTipoEmp() {
        return tipoEmp;
    }

    public void setIdEmp(StringProperty idEmp) {
        this.idEmp = idEmp;
    }

    public void setNombre(StringProperty nombre) {
        this.nombre = nombre;
    }

    public void setApellido(StringProperty apellido) {
        this.apellido = apellido;
    }

    public void setFechaNac(StringProperty fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setGenero(IntegerProperty genero) {
        this.genero = genero;
    }

    public void setNacionalidad(IntegerProperty nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setDireccion(StringProperty direccion) {
        this.direccion = direccion;
    }

    public void setTipoEmp(IntegerProperty tipoEmp) {
        this.tipoEmp = tipoEmp;
    }
    
*/

    public String getIdEmp() {
        return idEmp;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public String getIdNacionalidad() {
        return idNacionalidad;
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
    public String getTipoEmp(){
        return tipoEmp;
    }

    
    public static void llenarTabla(Connection cone, ObservableList <Empleados>lista){
        try {
            Statement statement= cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT e.idEmpleado, e.nombres, e.apellidos, e.fechaNacimiento, gen.genero, nac.nacionalidad, tels.telefono, correo.correo, e.direccion, tpe.tipoEmpleado FROM empleados e,genero gen, nacionalidades nac, telefonos_empleados tels,correo_empleados correo,tipo_empleado tpe WHERE gen.idGenero=e.idGenero and nac.idNacionalidad=e.idNacionalidad and tels.idEmpleado=e.idEmpleado and correo.idEmpleado=e.idEmpleado and tpe.idTipoEmpleado=e.tipoEmpleado");
            while(resultado.next()){
                lista.add(
                            new Empleados(
                                    resultado.getString("e.idEmpleado"),
                                    resultado.getString("e.nombres"),
                                    resultado.getString("e.apellidos"),
                                    resultado.getString("e.fechaNacimiento"),
                                    resultado.getString("gen.genero"),
                                    resultado.getString("nac.nacionalidad"),
                                    resultado.getString("tels.telefono"),
                                    resultado.getString("correo.correo"),
                                    resultado.getString("e.direccion"),
                                    resultado.getString("tpe.tipoEmpleado")
                                    
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void cmbTipoEmpleado(Connection cone, ObservableList<Empleados>lista){
        Statement statement;
        try {
            statement = cone.createStatement();
            ResultSet resultado= statement.executeQuery("SELECT * FROM tipo_empleado");
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    /*
    StringProperty idEmp;
    StringProperty nombre;
    StringProperty apellido;
    StringProperty fechaNac;
    IntegerProperty genero;
    IntegerProperty correo;
    IntegerProperty nacionalidad;
    StringProperty direccion;
    IntegerProperty telefono;
    IntegerProperty tipoEmp;
    */
    
    /*
    public ObservableList<Empleados> getEmpleados(){
        ObservableList<Empleados> obs= FXCollections.observableArrayList();
        
        conexion con= new conexion();
        Connection cone= con.openConnection();
        
        try {
            PreparedStatement pps= cone.prepareStatement("SELECT * FROM CLIENTES");
            ResultSet rs= pps.getResultSet();
            
            while(rs.next()){
                String id= rs.getString("idEmpleado");
                String nombre= rs.getString("nombres");
                String apellido=rs.getString("apellidos");
                        
                      Empleados e= new Empleados(id,nombre,apellido);
                      obs.add(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    */
}
