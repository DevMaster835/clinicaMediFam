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
public class Telefonos {
    String id;
    String nombre;
    String numero;
    

    public Telefonos(String id, String nombre, String numero) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
       
    }
    
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Telefonos other = (Telefonos) obj;
        if (!this.numero.equals(other.numero)) {
            return false;
        }
        return true;
    }


    


    
}
