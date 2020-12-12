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
public class ProductoC {
    int codigo;
    String producto;
    double precio;
    int contNeto;
    int cantidad;

    public ProductoC(int codigo, String producto, double precio, int contNeto, int cantidad) {
        this.codigo = codigo;
        this.producto = producto;
        this.precio = precio;
        this.contNeto = contNeto;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getProducto() {
        return producto;
    }

    public int getContNeto() {
        return contNeto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    
}
