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
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import modelos.Correos;
import modelos.Nacionalidades;
import modelos.Pacientes;
import modelos.Telefonos;
import modelos.tipoCorreoE;
import modelos.tipoSangre;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaPacientesController implements Initializable {
    
    
    conexion con= new conexion();
    Connection cone= con.openConnection();
    
    PreparedStatement pps;
    ObservableList<Nacionalidades> listaNacionalidades;
    ObservableList<tipoSangre> listaSangre;
    ObservableList<tipoCorreoE> listaCorreos;
    ObservableList<Telefonos> listaContacto;
    ObservableList<Correos> listaCorreo;
    ObservableList<Pacientes> listaPacientes;

    @FXML
    private TextField txtidPaciente;
    @FXML
    private TextField txtNombrePaciente;
    @FXML
    private TextField txtApellidoPaciente;
    @FXML
    private TextField txtTelPaciente;
    @FXML
    private TextField txtCorreoPaciente;
    @FXML
    private TextArea txtDireccionPaciente;
    @FXML
    private TextField txtPesoPac;
    @FXML
    private TextField txtAlturaPac;
    @FXML
    private ComboBox<tipoSangre> cmbTipoSangre;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEliminar;
    @FXML
    private RadioButton rdbM;
    @FXML
    private ToggleGroup grupoGen;
    @FXML
    private RadioButton rdbF;
    @FXML
    private DatePicker txtFechaPac;
    @FXML
    private ComboBox<Nacionalidades> cmbNacionalidad;
    @FXML
    private ComboBox<tipoCorreoE> cmbCorreo;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Pacientes> tblPacientes;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nombre;
    @FXML
    private TableView<Telefonos> tablaTelefonosPac;
    @FXML
    private TableColumn<?, ?> colPac;
    @FXML
    private TableColumn<?, ?> colPaciente;
    @FXML
    private TableColumn<?, ?> colTelPac;
    @FXML
    private Button btnAgregarTelefono;
    @FXML
    private TableView<Correos> tablaCorreosPac;
    @FXML
    private TableColumn<?, ?> colCP;
    @FXML
    private TableColumn<?, ?> colPacCo;
    @FXML
    private TableColumn<?, ?> colTipoC;
    @FXML
    private Button btnAgregarCorreo;
    @FXML
    private TableColumn<?, ?> colCorreoPac;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colFechaNac;
    @FXML
    private TableColumn<?, ?> colGenero;
    @FXML
    private TableColumn<Pacientes, Nacionalidades> colNac;
    @FXML
    private TableColumn<?, ?> colDireccion;
    @FXML
    private TableColumn<?, ?> colPeso;
    @FXML
    private TableColumn<?, ?> colAltura;
    @FXML
    private TableColumn<Pacientes, tipoSangre> colSangre;

    private double xOffset = 0; 
    private double yOffset = 0;
    
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;
    @FXML
    private Label lbid;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbApellido;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbCorreo;
    @FXML
    private Button btnModificarTel;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnModificarCorreo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Tooltip tooltipClose = new Tooltip("Close");
        Close.setTooltip(tooltipClose);
        
        Tooltip tooltipMinimize = new Tooltip("Minimize");
        Minimize.setTooltip(tooltipMinimize);
        
        Tooltip tooltipReturn = new Tooltip("Return");
        Return.setTooltip(tooltipReturn);
        
        //inicializar
        listaNacionalidades= FXCollections.observableArrayList();
        listaSangre= FXCollections.observableArrayList();
        listaCorreos=FXCollections.observableArrayList();
        
        //llenarLista
        Nacionalidades.llenarTabla(cone, listaNacionalidades);
        tipoSangre.cmbTipoSangre(cone, listaSangre);
        tipoCorreoE.cmbTipoCorreoE(cone, listaCorreos);
        
        //EnlazarListas
        cmbNacionalidad.setItems(listaNacionalidades);
        cmbTipoSangre.setItems(listaSangre);
        cmbCorreo.setItems(listaCorreos);

         formatoFecha();
         seleccionar();
         inicializarAlertas();
         
         tablaPacientes();
        tablaContacto();
        seleccionarTelefono();
        seleccionarCorreo();
    }
    public void tablaPacientes(){
        //LLENAR TABLA PACIENTES
        listaPacientes= FXCollections.observableArrayList();
        Pacientes.llenarTabla(cone, listaPacientes);
        tblPacientes.setItems(listaPacientes);
        
        id.setCellValueFactory(new PropertyValueFactory("id"));
        nombre.setCellValueFactory(new PropertyValueFactory("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        colFechaNac.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
        colGenero.setCellValueFactory(new PropertyValueFactory("genero"));
        colNac.setCellValueFactory(new PropertyValueFactory("nac"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        colPeso.setCellValueFactory(new PropertyValueFactory("peso"));
        colAltura.setCellValueFactory(new PropertyValueFactory("altura"));
        colSangre.setCellValueFactory(new PropertyValueFactory("ts"));
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
    
    public void inicializarAlertas(){
        lbid.setVisible(false);
        lbNombre.setVisible(false);
        lbApellido.setVisible(false);
        lbTelefono.setVisible(false);
        lbCorreo.setVisible(false);
    }
    
    
    public void limpiarDatos(){
        txtidPaciente.setText("");
        txtNombrePaciente.setText("");
        txtApellidoPaciente.setText("");
        txtFechaPac.setValue(null);
        cmbNacionalidad.setValue(null);
        txtDireccionPaciente.setText("");
        txtPesoPac.setText("");
        txtAlturaPac.setText("");
        txtTelPaciente.setText(""); 
        
        tablaTelefonosPac.getItems().clear();
        tablaCorreosPac.getItems().clear();
        txtidPaciente.requestFocus();
    }
    
    public void formatoFecha(){
        txtFechaPac.setConverter(new StringConverter<LocalDate>() {
        String pattern = "dd-MM-yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);      
        {
                txtFechaPac.setPromptText(pattern.toLowerCase());
         
        }

             @Override
             public String toString(LocalDate date) {
                 if (date != null) {
                    return dateFormatter.format(date);
                }else {
                    return "";
                }
             }

             @Override
             public LocalDate fromString(String string) {
                 if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                }else {
                    return null;
                }
             }
        
        });
    }
    
    public void seleccionar(){
        tblPacientes.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Pacientes>(){
            @Override
            public void changed(ObservableValue<? extends Pacientes> arg0,
                 Pacientes valorAnterior, Pacientes valorSeleccionado) {
                
                if(valorSeleccionado!=null){
                txtidPaciente.setText(String.valueOf(valorSeleccionado.getId()));
                txtNombrePaciente.setText(String.valueOf(valorSeleccionado.getNombres()));
                txtApellidoPaciente.setText(String.valueOf(valorSeleccionado.getApellidos()));
                txtFechaPac.setValue(valorSeleccionado.getFechaNacimiento().toLocalDate());
                if(valorSeleccionado.getGenero().equals("Masculino")){
                    rdbM.setSelected(true);
                }else{
                    rdbF.setSelected(true);
                }          
                cmbNacionalidad.setValue(valorSeleccionado.getNac());
                txtDireccionPaciente.setText(valorSeleccionado.getDireccion());
                txtPesoPac.setText(valorSeleccionado.getPeso());
                txtAlturaPac.setText(valorSeleccionado.getAltura());
                cmbTipoSangre.setValue(valorSeleccionado.getTs());
                //System.out.println("Seleccionó un registro");
                
           try {
              //tablaContacto();
            tablaTelefonosPac.getItems().clear();
            pps=cone.prepareStatement("SELECT pac.idPaciente, pac.nombres, tel.idTelefono, tel.telefono FROM pacientes pac INNER JOIN telefonos_pacientes tel ON pac.idPaciente=tel.idPaciente WHERE pac.idPaciente=?");
            pps.setString(1, txtidPaciente.getText());
            
           ResultSet rs = pps.executeQuery();

           while(rs.next()){
               String num= rs.getString("tel.telefono");
               System.out.println(rs.getString("tel.idTelefono"));
               Telefonos ic = new Telefonos(txtidPaciente.getText(), txtNombrePaciente.getText(), num);

                if(!listaContacto.contains(ic)) {
                        listaContacto.add(ic);
                        tablaTelefonosPac.setItems(listaContacto);
                }
           }
           //tablaContacto();
           tablaCorreosPac.getItems().clear();
           PreparedStatement ps=cone.prepareStatement("SELECT pac.idPaciente, pac.nombres, co.correo, tc.tipoCorreo FROM pacientes pac LEFT JOIN correo_pacientes co ON pac.idPaciente=co.idPaciente INNER JOIN tipo_correo tc ON co.tipoCorreo=tc.idTipoCorreo WHERE pac.idPaciente=?");
           ps.setString(1, txtidPaciente.getText());
            
           ResultSet rrs = ps.executeQuery();

           while(rrs.next()){
               String email= rrs.getString("co.correo"); 
               String tipo= rrs.getString("tc.tipoCorreo");
               Correos co = new Correos(txtidPaciente.getText(), txtNombrePaciente.getText(), email, tipo);

                if(!listaCorreo.contains(co)) {
                        listaCorreo.add(co);
                        tablaCorreosPac.setItems(listaCorreo);
                }
           }
           
        }catch (SQLException ex) {
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                btnGuardar.setDisable(true);
                btnActualizar.setDisable(false);
                btnEliminar.setDisable(false);
                btnAgregarTelefono.setDisable(true);
                btnModificarTel.setDisable(false);
                btnAgregarCorreo.setDisable(true);
                btnModificarCorreo.setDisable(false);
  
            }
            }

        }
        );
    }
    
    public void seleccionarTelefono(){
        tablaTelefonosPac.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Telefonos>() {
             @Override
            public void changed(ObservableValue<? extends Telefonos> arg0,
                    Telefonos valorAnterior, Telefonos valorSeleccionado) {
                
                if(valorSeleccionado!=null){
                  txtTelPaciente.setText(valorSeleccionado.getNumero());
                }  
            }
            }
        );
    }
    
    public void seleccionarCorreo(){
        tablaCorreosPac.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Correos>() {
             @Override
            public void changed(ObservableValue<? extends Correos> arg0,
                    Correos valorAnterior, Correos valorSeleccionado) {
                
                if(valorSeleccionado!=null){
                  txtCorreoPaciente.setText(valorSeleccionado.getCorreo());
                }  
            }
            }
        );
     }
    

    
    //VALIDACIONES
    
    public boolean existePaciente(){
        try {
            Statement st = cone.createStatement();
            String sql = "Select nombres from pacientes where idPaciente = '"+txtidPaciente.getText()+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                JOptionPane.showMessageDialog(null, " Ya existe"+" el número de identidad: "+txtidPaciente.getText(), "Número de identidad ¡Ya existe!", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
            if(EMAIL_REGEX.matcher(email).matches()) {
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "El correo no es valido");
                return false;
            }
    }
    
    public boolean validarIdentidad(String identidad){
        String id = identidad.substring(0, 1);
        if(identidad.length() < 13){
             JOptionPane.showMessageDialog(null, "El número de identidad debe de tener 13 dígitos, ha ingresado solamente "+identidad.length()+" dígitos.", "Número de identidad invalido", JOptionPane.ERROR_MESSAGE);
        }
        if(identidad.length() == 13){
             if("0".equals(id)){
                 return true;
             }
             else if("1".equals(id)){
                 return true;
             }
             else{
                 JOptionPane.showMessageDialog(null, "El número de identidad sólo puede comenzar con 0 o 1 ", "Error en campo identidad", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
        }
        else{
           return false; 
        }    
    }
    
    private boolean validarLongitud(TextField texto, int longitud){
       if(texto.getText().length() >= longitud){
           return true;
       }
       else{
           return false;
       }
    }
    
    private boolean validarLongitudMax(String texto, int longitud) {
        if (texto.length() <= longitud) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void guardarPacientes(ActionEvent event) {
        int genero=0;
        
        if(rdbM.isSelected()==true){
            genero=1;
        }else if(rdbF.isSelected()==true){
            genero=2;
        }
        
        System.out.println(genero);
        
        int nacionalidad= cmbNacionalidad.getSelectionModel().getSelectedIndex() + 1;
        int tipoC= cmbCorreo.getSelectionModel().getSelectedIndex() + 1;
        int tipoS= cmbTipoSangre.getSelectionModel().getSelectedIndex() + 1;
        
        
        if (txtidPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Identidad está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtidPaciente.getText(), 13)) {
            JOptionPane.showMessageDialog(null, "La identidad del paciente ingresado es muy largo el máximo es de 13 dígitos, usted ingresó " + txtidPaciente.getText().length() + " dígitos.", "Longitud del número de identidad del empleado", JOptionPane.ERROR_MESSAGE);
        } else if (!validarIdentidad(txtidPaciente.getText())) {
        } else if (txtNombrePaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Nombres está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtNombrePaciente, 2)) {
            JOptionPane.showMessageDialog(null, "Los nombres ingresados son muy pequeños el mínimo es de 2 caracteres", "Longitud de los nombres", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtNombrePaciente.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "Los nombres del paciente ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtNombrePaciente.getText().length() + " caracteres.", "Longitud de los nombres del empleado", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellidoPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Apellidos está vacío, por favor ingrese los apellidos del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtApellidoPaciente, 2)) {
            JOptionPane.showMessageDialog(null, "Los apellidos ingresados son muy pequeños el mínimo es de 2 caracteres", "Longitud de los apellidos", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtApellidoPaciente.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "Los apellidos del paciente ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtApellidoPaciente.getText().length() + " caracteres.", "Longitud de los apellidos del empleado", JOptionPane.ERROR_MESSAGE);
        } else if (genero == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el género del paciente", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechaPac.getValue() == null) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento está vacía, por favor ingrese la fecha de nacimiento del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (cmbNacionalidad.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Seleccione la nacionalidad del paciente", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtDireccionPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Dirección' está vacío, por favor ingrese la dirección del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidAdd(txtDireccionPaciente.getText())) {
        } else if (txtPesoPac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Peso' está vacío, por favor ingrese el peso del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtPesoPac, 2)) {
            JOptionPane.showMessageDialog(null, "El peso ingresado es muy bajo, el mínimo es de 10 lbs", "Peso del paciente", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtPesoPac.getText(), 3)) {
            JOptionPane.showMessageDialog(null, "El peso ingresado es muy alto, el máximo es de 999 lbs, usted ingresó " + txtPesoPac.getText() + " caracteres.", "Longitud de nombre de usuario", JOptionPane.ERROR_MESSAGE);
        } else if (txtAlturaPac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Altura' está vacío, por favor ingrese la altura del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtAlturaPac, 2)) {
            JOptionPane.showMessageDialog(null, "La altura ingresada es muy baja", "Altura del paciente", JOptionPane.ERROR_MESSAGE);
        }  else if (!validarLongitudMax(txtAlturaPac.getText(), 3)) {
            JOptionPane.showMessageDialog(null, "La altura ingresada es muy alta", "Altura del paciente", JOptionPane.ERROR_MESSAGE);
        } else if (cmbTipoSangre.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de sangre", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (tablaTelefonosPac.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos un número de teléfono", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (tablaCorreosPac.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos una dirección de correo", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            
        try{
            if(existePaciente()){
            return;
            }            
           
            pps=cone.prepareStatement("INSERT INTO pacientes(idPaciente,nombres,apellidos,fechaNacimiento,idGenero,idNacionalidad,direccion,peso,altura,tipoSangre) VALUES(?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, txtidPaciente.getText());
            pps.setString(2, txtNombrePaciente.getText());
            pps.setString(3, txtApellidoPaciente.getText());
            pps.setString(4, txtFechaPac.getValue().toString());
            pps.setString(5, String.valueOf(genero));
            pps.setString(6, String.valueOf(nacionalidad));
            pps.setString(7, txtDireccionPaciente.getText());
            pps.setString(8, txtPesoPac.getText());
            pps.setString(9, txtAlturaPac.getText());
            pps.setString(10, String.valueOf(tipoS));
            pps.executeUpdate();
            
            //GUARDAR TELEFONOS
            for(int i=0;i<tablaTelefonosPac.getItems().size();i++){
            pps=cone.prepareStatement("INSERT INTO telefonos_pacientes(idPaciente,telefono) VALUES(?,?)");
            pps.setString(1, txtidPaciente.getText());
            pps.setString(2, String.valueOf(tablaTelefonosPac.getItems().get(i).getNumero()));
            pps.executeUpdate();    
            }
            
            //GUARDAR CORREOS
            for(int j=0;j<tablaTelefonosPac.getItems().size();j++){
                int tipoco=0;
                
                if(tablaCorreosPac.getItems().get(j).getTipoCorreo().equals("Personal")){
                    tipoco=1;
                }else if(tablaCorreosPac.getItems().get(j).getTipoCorreo().equals("Empresa")){
                    tipoco=2;
                }else if(tipoco==0){
                  JOptionPane.showMessageDialog(null, "Seleccione el tipo de correo", "Error", JOptionPane.PLAIN_MESSAGE);  
                }
               pps=cone.prepareStatement("INSERT INTO correo_pacientes(idPaciente,correo,tipoCorreo) VALUES(?,?,?)");
               pps.setString(1, txtidPaciente.getText());
               pps.setString(2, String.valueOf(tablaCorreosPac.getItems().get(j).getCorreo()));
               pps.setString(3, String.valueOf(tipoco));
               pps.executeUpdate(); 
               
               tablaPacientes();
               limpiarDatos();
            }
            
            
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del paciente", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        limpiarDatos();
    }

    @FXML
    private void eliminarPacientes(ActionEvent event) {
        try {
           if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar al paciente?", "Eliminar Paciente",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
               pps= cone.prepareStatement("DELETE FROM pacientes WHERE idPaciente=?");
               pps.setString(1, txtidPaciente.getText());
               int res= pps.executeUpdate();
            
                if(res>0){
                    JOptionPane.showMessageDialog(null, "El paciente ha sido eliminado", "Paciente eliminado", JOptionPane.PLAIN_MESSAGE);
                    limpiarDatos();
                    tblPacientes.getItems().clear();
                    Pacientes.llenarTabla(cone, listaPacientes);
                }else {
                   JOptionPane.showMessageDialog(null, "Error al eliminar Paciente", "Aviso", JOptionPane.INFORMATION_MESSAGE); 
                }
            }else{
            
           }
           
               
        } catch (SQLException ex) {
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            lbid.setVisible(true);
            lbid.setText("Sólo se permiten números");
        }else{
            lbid.setVisible(false);
        }
    }

    @FXML
    private void txtNombreKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car) && car>'\b'){
            event.consume();
            //JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
            lbNombre.setVisible(true);
            lbNombre.setText("Sólo se permiten letras");
        }else{
            lbNombre.setVisible(false);
        }
    }

    @FXML
    private void txtApellidoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car) && car>'\b'){
            event.consume();
            //JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
            lbApellido.setVisible(true);
            lbApellido.setText("Sólo se permiten letras");
        }else{
            lbApellido.setVisible(false);
        }
    }

    @FXML
    private void txtTelefonoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            //JOptionPane.showMessageDialog(null, "Sólo se permiten números");
            lbTelefono.setVisible(true);
            lbTelefono.setText("Sólo se permiten números");
        }else{
            lbTelefono.setVisible(false);
        }
    }

    @FXML
    private void txtpesoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'.' && car>'\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtalturaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'.' && car>'\b'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }


    @FXML
    private void agregarTelefono(ActionEvent event) {
        String numero= txtTelPaciente.getText();
        String idE= this.txtidPaciente.getText();
        String nombreE= txtNombrePaciente.getText() + " " + this.txtApellidoPaciente.getText();
        
        if (txtidPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La identidad del empleado no puede ir vacío");
            alert.showAndWait();
            txtidPaciente.requestFocus();
        } else if (txtNombrePaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del empleado no puede ir vacío");
            alert.showAndWait();
            txtNombrePaciente.requestFocus();
        } else if (txtTelPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El teléfono del empleado no puede ir vacío");
            alert.showAndWait();
            txtTelPaciente.requestFocus();
        }
        if (!validarLongitudTelefono(txtTelPaciente, 8)) {
            return;
        }
        if (!validarLongitudMax(txtTelPaciente.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del empleado ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelPaciente.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (existeTelefono()) {
            return;
        }
        
        Telefonos ic= new Telefonos(idE,nombreE,numero);
        
        if(!this.listaContacto.contains(ic)){
            this.listaContacto.add(ic);
            this.tablaTelefonosPac.setItems(listaContacto);
            txtTelPaciente.setText("");
            txtTelPaciente.requestFocus();
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El número de télefono ya existe");
            alert.showAndWait();
            txtTelPaciente.requestFocus();
        }
    }

    @FXML
    private void agregarCorreo(ActionEvent event) {
        String idE= this.txtidPaciente.getText();
        String nombreE= txtNombrePaciente.getText() + " " + this.txtApellidoPaciente.getText();
        String correo= txtCorreoPaciente.getText();
        String tipoC= String.valueOf(cmbCorreo.getValue());
        
        if (txtidPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La identidad del empleado no puede ir vacío");
            alert.showAndWait();
            txtidPaciente.requestFocus();
        } else if (txtNombrePaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del empleado no puede ir vacío");
            alert.showAndWait();
            txtNombrePaciente.requestFocus();
        } else if (txtCorreoPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El correo del empleado no puede ir vacío");
            alert.showAndWait();
            txtCorreoPaciente.requestFocus();
        } else if (!isEmailValid(txtCorreoPaciente.getText())) {
        } else if (cmbCorreo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Seleccione el tipo de correo");
            alert.showAndWait();
        } else {
            
            if (existeCorreo()) {
                    return;
            }
        
        Correos c= new Correos(idE,nombreE,correo,tipoC);
        
        if(!this.listaCorreo.contains(c)){
            this.listaCorreo.add(c);
            this.tablaCorreosPac.setItems(listaCorreo);
            txtCorreoPaciente.setText("");
            cmbCorreo.setValue(null);
            txtCorreoPaciente.requestFocus();
        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El correo ya existe");
            alert.showAndWait();
            txtCorreoPaciente.requestFocus();
        }
    }
    }

    @FXML
    private void buscarPaciente(KeyEvent event) {
        FilteredList<Pacientes> filteredData = new FilteredList<>(listaPacientes, p -> true);
      tblPacientes.setItems(filteredData); 
      
      txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
          filteredData.setPredicate(paciente -> {
              
              if(newValue==null || newValue.isEmpty()){
                  return true;
              }
              
              String lowerCaseFilter= newValue.toLowerCase();
              
              if(paciente.getId().toLowerCase().indexOf(lowerCaseFilter) != -1){
                  return true;
              }else if(paciente.getNombres().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                  return true;
              }else{
                  return false;
              }
              
          });
      });
        
      SortedList<Pacientes> sortedData = new SortedList<>(filteredData);
      sortedData.comparatorProperty().bind(tblPacientes.comparatorProperty());
      
      tblPacientes.setItems(sortedData);
    }
    
    public static boolean isValidAdd(String address) {
        final Pattern ADD_REGEX = Pattern.compile("[a-zA-Z\\,\\t\\h]+|(^$)", Pattern.CASE_INSENSITIVE);
        if(ADD_REGEX.matcher(address).matches()) {
            return true;
        }else{
         JOptionPane.showMessageDialog(null, "La direccion no es valida");
         return false;        
        }
      }

        
    @FXML
    private void modificarTelefono(ActionEvent event) {
        String numero = txtTelPaciente.getText();
        String idE = this.txtidPaciente.getText();
        String nombreE = txtNombrePaciente.getText() + " " + this.txtApellidoPaciente.getText();
        
        if (txtidPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La identidad del empleado no puede ir vacío");
            alert.showAndWait();
            txtidPaciente.requestFocus();
        } else if (txtNombrePaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del empleado no puede ir vacío");
            alert.showAndWait();
            txtNombrePaciente.requestFocus();
        } else if (txtTelPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El teléfono del empleado no puede ir vacío");
            alert.showAndWait();
            txtTelPaciente.requestFocus();
        }
        if (!validarLongitudTelefono(txtTelPaciente, 8)) {
            return;
        }
        if (!validarLongitudMax(txtTelPaciente.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del empleado ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelPaciente.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (existeTelefono()) {
            return;
        }
        
        Telefonos ic = new Telefonos(idE, nombreE, numero);
        listaContacto.set(tablaTelefonosPac.getSelectionModel().getSelectedIndex(), ic);
    }
        
    public boolean existeTelefono() {
        try {
            Statement st = cone.createStatement();
            String sql = "Select telefono from telefonos_pacientes where telefono = '" + txtTelPaciente.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, " Ya existe " + " el número de teléfono: " + txtTelPaciente.getText(), "Número de teléfono ¡Ya existe!", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean existeCorreo() {
        try {
            Statement st = cone.createStatement();
            String sql = "Select correo from correo_pacientes where correo = '" + txtCorreoPaciente.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, " Ya existe " + " la dirección de correo: " + txtCorreoPaciente.getText(), "Dirección de correo ¡Ya existe!", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean validarLongitudTelefono(TextField texto, int longitud) {
        if (texto.getText().length() == longitud) {
            Pattern pattern = Pattern.compile("[23789]");
            Matcher matcher = pattern.matcher(texto.getText().substring(0, 1));
            if (matcher.matches()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El número de teléfono debe comenzar con: 2,3,7,8 o 9");
                txtTelPaciente.requestFocus();
                return false;
            }
        } else {
        }
        JOptionPane.showMessageDialog(null, "El número de teléfono debe ser de 8 dígitos", "Longitud del número de telefono", JOptionPane.INFORMATION_MESSAGE);
        txtTelPaciente.requestFocus();
        return false;
    }
    
    
    @FXML
    private void actualizarPaciente(ActionEvent event) {
        int tipoS = cmbTipoSangre.getSelectionModel().getSelectedItem().getIdSangre();
        int nacionalidad = cmbNacionalidad.getSelectionModel().getSelectedItem().getIdNacionalidad();
        //int tipoCorreo = cmbtipoCorreo.getSelectionModel().getSelectedIndex();
        int genero = 0;
        if (rdbM.isSelected() == true) {
            genero = 1;
        } else if (rdbF.isSelected() == true) {
            genero = 2;
        }if (txtidPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Identidad está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtidPaciente.getText(), 13)) {
            JOptionPane.showMessageDialog(null, "La identidad del paciente ingresado es muy largo el máximo es de 13 dígitos, usted ingresó " + txtidPaciente.getText().length() + " dígitos.", "Longitud del número de identidad del empleado", JOptionPane.ERROR_MESSAGE);
        } else if (!validarIdentidad(txtidPaciente.getText())) {
        } else if (txtNombrePaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Nombres está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtNombrePaciente, 2)) {
            JOptionPane.showMessageDialog(null, "Los nombres ingresados son muy pequeños el mínimo es de 2 caracteres", "Longitud de los nombres", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtNombrePaciente.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "Los nombres del paciente ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtNombrePaciente.getText().length() + " caracteres.", "Longitud de los nombres del empleado", JOptionPane.ERROR_MESSAGE);
        } else if (txtApellidoPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Apellidos está vacío, por favor ingrese los apellidos del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtApellidoPaciente, 2)) {
            JOptionPane.showMessageDialog(null, "Los apellidos ingresados son muy pequeños el mínimo es de 2 caracteres", "Longitud de los apellidos", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtApellidoPaciente.getText(), 25)) {
            JOptionPane.showMessageDialog(null, "Los apellidos del paciente ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtApellidoPaciente.getText().length() + " caracteres.", "Longitud de los apellidos del empleado", JOptionPane.ERROR_MESSAGE);
        } else if (genero == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione el género del paciente", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtFechaPac.getValue() == null) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento está vacía, por favor ingrese la fecha de nacimiento del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (cmbNacionalidad.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Seleccione la nacionalidad del paciente", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (txtDireccionPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Dirección' está vacío, por favor ingrese la dirección del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidAdd(txtDireccionPaciente.getText())) {
        } else if (txtPesoPac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Peso' está vacío, por favor ingrese el peso del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtPesoPac, 2)) {
            JOptionPane.showMessageDialog(null, "El peso ingresado es muy bajo, el mínimo es de 10 lbs", "Peso del paciente", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitudMax(txtPesoPac.getText(), 3)) {
            JOptionPane.showMessageDialog(null, "El peso ingresado es muy alto, el máximo es de 999 lbs, usted ingresó " + txtPesoPac.getText() + " caracteres.", "Longitud de nombre de usuario", JOptionPane.ERROR_MESSAGE);
        } else if (txtAlturaPac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Altura' está vacío, por favor ingrese la altura del paciente.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (!validarLongitud(txtAlturaPac, 2)) {
            JOptionPane.showMessageDialog(null, "La altura ingresada es muy baja", "Altura del paciente", JOptionPane.ERROR_MESSAGE);
        }  else if (!validarLongitudMax(txtAlturaPac.getText(), 3)) {
            JOptionPane.showMessageDialog(null, "La altura ingresada es muy alta", "Altura del paciente", JOptionPane.ERROR_MESSAGE);
        } else if (cmbTipoSangre.getValue() == null) {
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de sangre", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (tablaTelefonosPac.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos un número de teléfono", "¡Error!", JOptionPane.ERROR_MESSAGE);
        } else if (tablaCorreosPac.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos una dirección de correo", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        try {
            
            PreparedStatement ps = cone.prepareStatement("UPDATE pacientes SET nombres=?, apellidos=?, fechaNacimiento=?, idGenero=?, idNacionalidad=?, direccion=?, peso=?, altura=?, tipoSangre=? WHERE idPaciente=?");
            ps.setString(1, txtNombrePaciente.getText());
            ps.setString(2, txtApellidoPaciente.getText());
            ps.setString(3, String.valueOf(txtFechaPac.getValue()));
            ps.setString(4, String.valueOf(genero));
            ps.setString(5, String.valueOf(nacionalidad));
            ps.setString(6, txtDireccionPaciente.getText());
            ps.setString(7, txtPesoPac.getText());
            ps.setString(8, txtAlturaPac.getText());
            ps.setString(9, String.valueOf(tipoS));
            ps.setString(10, txtidPaciente.getText());
            
            
            //MODIFICAR TELEFONOS
            for (int i = 0; i < tablaTelefonosPac.getItems().size(); i++) {
                
                  PreparedStatement  ps1 = cone.prepareStatement("UPDATE telefonos_pacientes SET telefono=? WHERE idPaciente=?");                  
                    ps1.setString(1, String.valueOf(tablaTelefonosPac.getItems().get(i).getNumero()));
                    ps1.setString(2, txtidPaciente.getText()); 
                    ps1.executeUpdate();
            }
            //midmodificar correo
            for (int i = 0; i < tablaCorreosPac.getItems().size(); i++) {
                int tipoco = 0;
                    if (tablaCorreosPac.getItems().get(i).getTipoCorreo().equals("Personal")) {
                        tipoco = 1;
                    } else if (tablaCorreosPac.getItems().get(i).getTipoCorreo().equals("Empresa")) {
                        tipoco = 2;
                    } else if (tipoco == 0) {
                        JOptionPane.showMessageDialog(null, "Seleccione el tipo de correo", "Error", JOptionPane.PLAIN_MESSAGE);
                    }
                    
                    PreparedStatement ps2 = cone.prepareStatement("UPDATE correo_pacientes SET correo=?, tipoCorreo=?  WHERE idPaciente=?");                  
                    ps2.setString(1, String.valueOf(tablaCorreosPac.getItems().get(i).getCorreo()));
                    ps2.setString(2, String.valueOf(tipoco));
                    ps2.setString(3, txtidPaciente.getText());
                    ps2.executeUpdate();
            }
            
            int mod = ps.executeUpdate();

            if (mod >= 1) {
                JOptionPane.showMessageDialog(null, "Se han actualizado los datos", "¡Confirmación!", JOptionPane.INFORMATION_MESSAGE);
                tablaPacientes();
                limpiarDatos();
            }

        } catch (SQLException ex) {
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void tablaContacto() {
        //TABLA DE TELEFONOS
        listaContacto = FXCollections.observableArrayList();
        colPac.setCellValueFactory(new PropertyValueFactory("id"));
        this.colPaciente.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colTelPac.setCellValueFactory(new PropertyValueFactory("numero"));

        //TABLA DE CORREOS
        listaCorreo = FXCollections.observableArrayList();
        colCP.setCellValueFactory(new PropertyValueFactory("id"));
        colPacCo.setCellValueFactory(new PropertyValueFactory("nombre"));
        colCorreoPac.setCellValueFactory(new PropertyValueFactory("correo"));
        colTipoC.setCellValueFactory(new PropertyValueFactory("tipoCorreo"));
    }

    @FXML
    private void modificarCorreo(ActionEvent event) {
        String idE = this.txtidPaciente.getText();
        String nombreE = txtNombrePaciente.getText() + " " + this.txtApellidoPaciente.getText();
        String correo = txtCorreoPaciente.getText();
        String tipoC = String.valueOf(cmbCorreo.getValue());

        if (txtidPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("La identidad del paciente no puede ir vacío");
            alert.showAndWait();
            txtCorreoPaciente.requestFocus();
        } else if (txtNombrePaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El nombre del empleado no puede ir vacío");
            alert.showAndWait();
            txtNombrePaciente.requestFocus();
        } else if (txtCorreoPaciente.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El correo del empleado no puede ir vacío");
            alert.showAndWait();
            txtNombrePaciente.requestFocus();
        } else if (!isEmailValid(txtCorreoPaciente.getText())) {
        } else if (cmbCorreo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Seleccione el tipo de correo");
            alert.showAndWait();
        } else {
            Correos c = new Correos(idE, nombreE, correo, tipoC);
            listaCorreo.set(tablaCorreosPac.getSelectionModel().getSelectedIndex(), c);
        }
    }
    
    private void buscarPacientes(KeyEvent event) {
        FilteredList<Pacientes> filteredData = new FilteredList<>(listaPacientes, p -> true);
        tblPacientes.setItems(filteredData);

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(paciente -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (paciente.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (paciente.getNombres().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<Pacientes> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblPacientes.comparatorProperty());

        tblPacientes.setItems(sortedData);
        
    }
    
        
        
    }
    
    

