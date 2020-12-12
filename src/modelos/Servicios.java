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
public class Servicios {
    int id;
    String servicio;
    double precio;
    int cantidad;

        
    public Servicios(int id, String servicio, double precio) {
        this.id = id;
        this.servicio = servicio;
        this.precio = precio;
    }

    public Servicios(int id, String servicio, double precio, int cantidad) {
        this.id = id;
        this.servicio = servicio;
        this.precio = precio;
        this.cantidad = cantidad;
    }


    public int getId() {
        return id;
    }

    public String getServicio() {
        return servicio;
    }

    public double getPrecio() {
        return precio;
    }
    
    public int getCantidad() {
        return cantidad;
    }


    
    
}
