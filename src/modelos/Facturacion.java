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
import Conexion.conexion;
import Conexion.conexion;
import Conexion.conexion;
import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




public class  Facturacion{
    
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
	private StringProperty idFactura;
	private Date fechaFactura;
	private StringProperty idEmpleado;
	private StringProperty idPaciente;
	//private VistaEmpleadosController empleados;
	//private VistaPacientesController pacientes;

	public Facturacion(String idFactura, Date fechaFactura, String idEmpleado, 
String idPaciente /*VistaEmpleadosController empleados, VistaPacientesController pacientes*/) { 
		this.idFactura = new SimpleStringProperty(idFactura);
		this.fechaFactura = fechaFactura;
		this.idEmpleado = new SimpleStringProperty(idEmpleado);
		this.idPaciente = new SimpleStringProperty(idPaciente);
		//this.empleados = empleados;
		///this.pacientes = pacientes;
	}

	//Metodos atributo: idFactura
	public String getIdFactura() {
		return idFactura.get();
	}
	public void setIdFactura(String idFactura) {
		this.idFactura = new SimpleStringProperty(idFactura);
	}
	public StringProperty IdFacturaProperty() {
		return idFactura;
	}
	//Metodos atributo: fechaFactura
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	//Metodos atributo: idEmpeado
	public String getIdEmpeado() {
		return idEmpleado.get();
	}
	public void setIdEmpeado(String idEmpeado) {
		this.idEmpleado = new SimpleStringProperty(idEmpeado);
	}
	public StringProperty IdEmpeadoProperty() {
		return idEmpleado;
	}
	//Metodos atributo: idPaciente
	public String getIdPaciente() {
		return idPaciente.get();
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = new SimpleStringProperty(idPaciente);
	}
	public StringProperty IdPacienteProperty() {
		return idPaciente;
	}
        /*
	//Metodos atributo: empleados
	public VistaEmpleadosController getEmpleados() {
		return empleados;
	}
	public void setEmpleados(VistaEmpleadosController empleados) {
		this.empleados = empleados;
	}
	//Metodos atributo: pacientes
	public VistaPacientesController getPacientes() {
		return pacientes;
	}
	public void setPacientes(VistaPacientesController pacientes) {
		this.pacientes = pacientes;
	}
       */
    public int AgregarFactura(Connection connection){
        //LocalDate fechaFactura= fechaFactura.getValue();
 
       
        try{
            
            PreparedStatement pps = connection.prepareStatement("Insert into facturacion (idFacturacion,fechaFactura,idEmpleado,idPaciente) values(?,?,?,?)");
      
             pps.setString(1, idFactura.get());
             pps.setDate(2, fechaFactura);
             pps.setString(3, idEmpleado.get());
             pps.setString(4, idPaciente.get());
           
           return  pps.executeUpdate();
             
             
            
  
        }catch(SQLException ex){
             
         ex.printStackTrace();
             return 0;
        }
        
        
        
    
}

    
}
