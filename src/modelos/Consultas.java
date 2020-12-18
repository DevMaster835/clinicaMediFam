/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import com.mysql.jdbc.Connection;
import java.sql.Date;
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
public class Consultas {
    String noConsulta;
    final Date fechaCreación;
    final Date fechaConsulta;
    String hora;
    String idPac;
    String paciente;
    String idMedico;
    String medico;
    String motivo;
    //String estadoConsulta;
    EstadoConsulta ec;

    public Consultas(String noConsulta, Date fechaCreación, Date fechaConsulta, String hora, String idPac, String paciente, String idMedico, String medico, String motivo, EstadoConsulta ec) {
        this.noConsulta = noConsulta;
        this.fechaCreación = fechaCreación;
        this.fechaConsulta = fechaConsulta;
        this.hora = hora;
        this.idPac=idPac;
        this.paciente = paciente;
        this.idMedico= idMedico;
        this.medico = medico;
        this.motivo = motivo;
        this.ec=ec;
    }

    public String getNoConsulta() {
        return noConsulta;
    }

    public Date getFechaCreación() {
        return fechaCreación;
    }

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public String getHora() {
        return hora;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getMedico() {
        return medico;
    }

    public String getMotivo() {
        return motivo;
    }

   /* public String getEstadoConsulta() {
        return estadoConsulta;
    }*/

    public EstadoConsulta getEc() {
        return ec;
    }

    public String getIdPac() {
        return idPac;
    }

    public String getIdMedico() {
        return idMedico;
    }
    

    
    public static void llenarTabla(Connection cone, ObservableList <Consultas>lista){
        try {
            Statement statement= cone.createStatement();
            
            ResultSet resultado = statement.executeQuery("SELECT consultas_medicas.noConsulta, consultas_medicas.fechaCreacion, consultas_medicas.fechaConsulta, consultas_medicas.horaConsulta, pacientes.idPaciente, pacientes.nombres, pacientes.apellidos, empleados.idEmpleado, empleados.nombres, empleados.apellidos, consultas_medicas.motivo, estado_consultas.idEstado, estado_consultas.estado FROM consultas_medicas INNER JOIN pacientes ON consultas_medicas.idPaciente=pacientes.idPaciente INNER JOIN empleados ON consultas_medicas.idMedico=empleados.idEmpleado INNER JOIN estado_consultas ON consultas_medicas.idEstadoConsulta=estado_consultas.idEstado");
            while(resultado.next()){
                lista.add(
                            new Consultas(
                                    resultado.getString("consultas_medicas.noConsulta"),
                                    resultado.getDate("consultas_medicas.fechaCreacion"),
                                    resultado.getDate("consultas_medicas.fechaConsulta"),
                                    resultado.getString("consultas_medicas.horaConsulta"),
                                    resultado.getString("pacientes.idPaciente"),
                                    resultado.getString("pacientes.nombres") + " " + resultado.getString("pacientes.apellidos"),
                                    resultado.getString("empleados.idEmpleado"),
                                    resultado.getString("empleados.nombres") + " " + resultado.getString("empleados.apellidos"),
                                    resultado.getString("consultas_medicas.motivo"),
                                    
                            new EstadoConsulta(
                                    resultado.getInt("estado_consultas.idEstado"),
                                    resultado.getString("estado_consultas.estado")
                            )
        
                            ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
