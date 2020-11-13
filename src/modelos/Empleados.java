/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author José
 */
public class Empleados {
    int idEmp;
    String nombres;
    String apellidos;
    String fechaNac;
    int idGenero;
    int idCorreo;
    int idNacionalidad;
    String direccion;
    int idTelefono;
    int idUsuario;

    public Empleados(int idEmp, String nombres, String apellidos, String fechaNac, int idGenero, int idCorreo, int idNacionalidad, String direccion, int idTelefono, int idUsuario) {
        this.idEmp = idEmp;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.idGenero = idGenero;
        this.idCorreo = idCorreo;
        this.idNacionalidad = idNacionalidad;
        this.direccion = direccion;
        this.idTelefono = idTelefono;
        this.idUsuario = idUsuario;
    }

    public int getIdEmp() {
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

    public int getIdGenero() {
        return idGenero;
    }

    public int getIdCorreo() {
        return idCorreo;
    }

    public int getIdNacionalidad() {
        return idNacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getIdTelefono() {
        return idTelefono;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    
}
