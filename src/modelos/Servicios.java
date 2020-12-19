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
    double subtotal;

        
    public Servicios(int id, String servicio, double precio) {
        this.id = id;
        this.servicio = servicio;
        this.precio = precio;
    }

    public Servicios(int id, String servicio, double precio, int cantidad, double subtotal) {
        this.id = id;
        this.servicio = servicio;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal=subtotal;
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

    public double getSubtotal() {
        return subtotal;
    }

    
    
    
}
