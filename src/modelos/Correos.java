/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Objects;

/**
 *
 * @author José
 */
public class Correos {
    String id;
    String nombre;
    String correo;
    String tipoCorreo;

    public Correos(String id, String nombre, String correo, String tipoCorreo) {
        this.id = id;
        this.nombre=nombre;
        this.correo = correo;
        this.tipoCorreo = tipoCorreo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipoCorreo() {
        return tipoCorreo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Correos other = (Correos) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        return true;
    }

    public Correos(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }
    
    
    
}
