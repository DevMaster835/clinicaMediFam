/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.conexion;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelos.Consultas;
import modelos.Empleados;
import modelos.EstadoConsulta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaConsultasController implements Initializable {
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;
    ResultSet rs;
    
    ObservableList<Consultas> consultas;
    ObservableList<EstadoConsulta> estadoConsulta;

    @FXML
    private TextField txtnoConsulta;
    @FXML
    private DatePicker txtfechaCreacion;
    @FXML
    private DatePicker txtfechaCita;
    @FXML
    private TextField txtidPaciente;
    @FXML
    private TextField txtnombrePac;
    @FXML
    private ComboBox<EstadoConsulta> cmbestadoC;
    @FXML
    private TextArea txtmotivo;
    @FXML
    private TextField txtidMedico;
    @FXML
    private TextField txtMedico;
    @FXML
    private Button btnbuscarPac;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnbuscarMedico;
    @FXML
    private TextField txthoraCita;
    @FXML
    private TableView<Consultas> tablaConsultas;
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;
    
    private double xOffset = 0; 
    private double yOffset = 0;
    @FXML
    private TableColumn<?, ?> colConsulta;
    @FXML
    private TableColumn<?, ?> colFechaCreacion;
    @FXML
    private TableColumn<?, ?> colFechaCita;
    @FXML
    private TableColumn<?, ?> colHora;
    @FXML
    private TableColumn<?, ?> colPaciente;
    @FXML
    private TableColumn<?, ?> colMedico;
    @FXML
    private TableColumn<?, ?> colMotivo;
    @FXML
    private TableColumn<?, ?> colEstado;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnImprimir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        estadoConsulta=FXCollections.observableArrayList();
        EstadoConsulta.cmbEstado(cone, estadoConsulta);
        cmbestadoC.setItems(estadoConsulta);
        
        Tooltip tooltipClose = new Tooltip("Close");
        Close.setTooltip(tooltipClose);
        
        Tooltip tooltipMinimize = new Tooltip("Minimize");
        Minimize.setTooltip(tooltipMinimize);
        
        Tooltip tooltipReturn = new Tooltip("Return");
        Return.setTooltip(tooltipReturn);
        
        tablaConsultas();
        seleccionarConsulta();
        
        
    } 
    
    @FXML
    private void exitButtonOnAction(ActionEvent event){
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }

    @FXML
    private void minimizeButtonOnAction(ActionEvent event){
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).setIconified(true);
    }
    
    @FXML
    void ReturnButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaMenu.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(false);
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    public static boolean isValidHour(String hora) {
        final Pattern HOUR_REGEX = Pattern.compile("/^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/", Pattern.CASE_INSENSITIVE);
        if(HOUR_REGEX.matcher(hora).matches()) {
            return true;
        }else{
         JOptionPane.showMessageDialog(null, "La hora no es valida");
           return false;      
        }
      }

    @FXML
    private void eliminarConsulta(ActionEvent event) {
        try {
           if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar la cita?", "Eliminar Cita",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
               pps= cone.prepareStatement("DELETE FROM consultas_medicas WHERE noConsulta=?");
               pps.setString(1, txtnoConsulta.getText());
               int res= pps.executeUpdate();
            
                if(res>0){
                    JOptionPane.showMessageDialog(null, "La cita ha sido eliminada", "Cita eliminada", JOptionPane.INFORMATION_MESSAGE);
                    limpiarDatos();
                    tablaConsultas.getItems().clear();
                    tablaConsultas();
                }else {
                   JOptionPane.showMessageDialog(null, "Error al eliminar la cita", "Aviso", JOptionPane.ERROR_MESSAGE); 
                }
            }else{
            
           }
           
               
        } catch (SQLException ex) {
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        limpiarDatos();
        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnImprimir.setDisable(true);
    }
    
    private void limpiarDatos(){
    
        txtnoConsulta.setText("");
        txtfechaCreacion.setValue(null);
        txtfechaCita.setValue(null);
        txthoraCita.setText("");
        txtidPaciente.setText("");
        txtnombrePac.setText("");
        txtidMedico.setText("");
        txtMedico.setText("");
        txtmotivo.setText("");
        cmbestadoC.setValue(null);
        txtfechaCreacion.requestFocus();
    }
    
    public void tablaConsultas(){
        consultas= FXCollections.observableArrayList();
        Consultas.llenarTabla(cone, consultas);
        tablaConsultas.setItems(consultas);
        
        colConsulta.setCellValueFactory(new PropertyValueFactory("noConsulta"));
        colFechaCreacion.setCellValueFactory(new PropertyValueFactory("fechaCreación"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory("fechaConsulta"));
        colHora.setCellValueFactory(new PropertyValueFactory("hora"));
        colPaciente.setCellValueFactory(new PropertyValueFactory("paciente"));
        colMedico.setCellValueFactory(new PropertyValueFactory("medico"));
        colMotivo.setCellValueFactory(new PropertyValueFactory("motivo"));
        colEstado.setCellValueFactory(new PropertyValueFactory("ec"));
        
    }
    
    public void seleccionarConsulta(){
      tablaConsultas.getSelectionModel().selectedItemProperty().addListener(
           new ChangeListener<Consultas>(){
          @Override
          public void changed(ObservableValue<? extends Consultas> arg0, 
                  Consultas oldValue, Consultas valorSeleccionado) {
            
              if(valorSeleccionado!=null){
                  txtnoConsulta.setText(String.valueOf(valorSeleccionado.getNoConsulta()));
                  txtfechaCreacion.setValue(valorSeleccionado.getFechaCreación().toLocalDate());
                  txtfechaCita.setValue(valorSeleccionado.getFechaConsulta().toLocalDate());
                  txthoraCita.setText(valorSeleccionado.getHora());
                  txtmotivo.setText(valorSeleccionado.getMotivo());
                  cmbestadoC.setValue(valorSeleccionado.getEc());
                  txtidPaciente.setText(valorSeleccionado.getIdPac());
                  txtnombrePac.setText(valorSeleccionado.getPaciente());
                  txtidMedico.setText(valorSeleccionado.getIdMedico());
                  txtMedico.setText(valorSeleccionado.getMedico());
                  
                  btnGuardar.setDisable(true);
                  btnActualizar.setDisable(false);
                  btnEliminar.setDisable(false);
                  btnImprimir.setDisable(false);
              }
          }
               
           }
      
      );
    }
    
    public void imprimirConsulta(){
        try {
            JasperReport reporte =null;
            String path="src//reportes//imprimirCitas.jasper";
            
            Map parametro= new HashMap();
            parametro.put("noConsulta", txtnoConsulta.getText());
            
            reporte= (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametro, cone);
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void agregarConsulta(ActionEvent event) {
        LocalDate fechaCreacion= txtfechaCreacion.getValue();
        LocalDate fechaCita=txtfechaCita.getValue();
        int estadoCon= cmbestadoC.getSelectionModel().getSelectedIndex() + 1;
       
        System.out.println(fechaCreacion);
        if(txtnoConsulta.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No. Consulta está vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtfechaCreacion.getValue() == null) {
            JOptionPane.showMessageDialog(null, "La fecha de creación está vacía, por favor seleccione una fecha.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtfechaCita.getValue() == null) {
            JOptionPane.showMessageDialog(null, "La fecha de la cita está vacía, por favor seleccione una fecha.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txthoraCita.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La Hora está vacía, por favor ingrese la hora de la cita.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if(isValidHour(txthoraCita.getText())){           
        }else if (txtidPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, " La identidad del paciente está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtnombrePac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El Nombre del paciente está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtidMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La identidad del médico está vacía, por favor ingrese la identidad del médico.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del médico está vacío, por favor ingrese el nombre del médico.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtmotivo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El motivo de la cita está vacío, por favor ingrese el motivo.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (cmbestadoC.getValue() == null) {
            JOptionPane.showMessageDialog(null, "El estado de la cita está vacío, por favor ingrese el estado de la cita.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        else{
        
        try {
            pps=cone.prepareStatement("INSERT INTO consultas_medicas(fechaCreacion,fechaConsulta,horaConsulta,idPaciente,idMedico,motivo,idEstadoConsulta) VALUES(?,?,?,?,?,?,?)");
            pps.setString(1, String.valueOf(fechaCreacion));
            pps.setString(2, String.valueOf(fechaCita));
            pps.setString(3, txthoraCita.getText());
            pps.setString(4, txtidPaciente.getText());
            pps.setString(5, txtidMedico.getText());
            pps.setString(6, txtmotivo.getText());
            pps.setString(7, String.valueOf(estadoCon));
            pps.executeUpdate();
            
            tablaConsultas();
            JOptionPane.showMessageDialog(null, "Se ha registrado la consulta medica", "Datos guardados", JOptionPane.PLAIN_MESSAGE);           
            limpiarDatos();
            imprimirConsulta();
        } catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    
    @FXML
    private void modificarConsulta(ActionEvent event) {
        int estado= cmbestadoC.getSelectionModel().getSelectedItem().getIdEstado();
        
        if(txtnoConsulta.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No. Consulta está vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtfechaCreacion.getValue() == null) {
            JOptionPane.showMessageDialog(null, "La fecha de creación está vacía, por favor seleccione una fecha.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtfechaCita.getValue() == null) {
            JOptionPane.showMessageDialog(null, "La fecha de la cita está vacía, por favor seleccione una fecha.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txthoraCita.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La Hora está vacía, por favor ingrese la hora de la cita.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if(isValidHour(txthoraCita.getText())){
        } else if (txtidPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, " La identidad del paciente está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtnombrePac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El Nombre del paciente está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtidMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La identidad del médico está vacía, por favor ingrese la identidad del médico.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtMedico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del médico está vacío, por favor ingrese el nombre del médico.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtmotivo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El motivo de la cita está vacío, por favor ingrese el motivo.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (cmbestadoC.getValue() == null) {
            JOptionPane.showMessageDialog(null, "El estado de la cita está vacío, por favor ingrese el estado de la cita.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        else{
        try {
            pps=cone.prepareStatement("UPDATE consultas_medicas SET fechaCreacion=?, fechaConsulta=?, horaConsulta=?, idPaciente=?, idMedico=?, motivo=?, idEstadoConsulta=? WHERE noConsulta=?");
            pps.setString(1, txtfechaCreacion.getValue().toString());
            pps.setString(2, txtfechaCita.getValue().toString());
            pps.setString(3, txthoraCita.getText());
            pps.setString(4, txtidPaciente.getText());
            pps.setString(5, txtidMedico.getText());
            pps.setString(6, txtmotivo.getText());
            pps.setString(7, String.valueOf(estado));
            pps.setString(8, txtnoConsulta.getText());
            
           int mod= pps.executeUpdate();
           if(mod>=1){
             tablaConsultas();
             JOptionPane.showMessageDialog(null, "Se ha actualizado la cita", "¡Confirmación!", JOptionPane.INFORMATION_MESSAGE); 
             limpiarDatos();
             imprimirConsulta();
           }
        } catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void buscarPaciente(ActionEvent event) {
        
        try{
            pps=cone.prepareStatement("SELECT * FROM pacientes where idPaciente=?");
            pps.setString(1, txtidPaciente.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtnombrePac.setText(rs.getString("nombres"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun paciente con identidad: " + txtidPaciente.getText(), "No existe paciente", JOptionPane.INFORMATION_MESSAGE);
                txtidPaciente.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buscarMedico(ActionEvent event) {
        try{
            String prueba;
            pps=cone.prepareStatement("SELECT * FROM empleados where idEmpleado=?");
            pps.setString(1, txtidMedico.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtMedico.setText(rs.getString("nombres"));
                prueba= rs.getString("apellidos");
                System.out.println(prueba);
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun empleado con identidad: " + txtidPaciente.getText(), "No existe empleado", JOptionPane.INFORMATION_MESSAGE);
                txtidMedico.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void txtConsultaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b' ){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtidEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car > '\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void buscarConsulta(KeyEvent event) {
        FilteredList<Consultas> filteredData = new FilteredList<>(consultas, p -> true);
        tablaConsultas.setItems(filteredData);

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(consulta -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (consulta.getNoConsulta().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (consulta.getIdPac().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(consulta.getPaciente().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }else if(consulta.getFechaConsulta().toString().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }else if(consulta.getMedico().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    return true;
                }else {
                    return false;
                }

            });
        });

        SortedList<Consultas> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaConsultas.comparatorProperty());

        tablaConsultas.setItems(sortedData);
    }

    @FXML
    private void imprimirConsultas(ActionEvent event) {
        imprimirConsulta();
    }

    @FXML
    private void txthoraKeyTyped(KeyEvent event) {
    }

    
    
}
