/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.conexion;
import com.mysql.jdbc.Connection;
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
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    private RadioButton rdbId;
    @FXML
    private ToggleGroup grupoBusqueda;
    @FXML
    private RadioButton rdbNom;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
        
        
        //TABLA DE TELEFONOS
         listaContacto= FXCollections.observableArrayList();
         colPac.setCellValueFactory(new PropertyValueFactory("id"));
         colPaciente.setCellValueFactory(new PropertyValueFactory("nombre"));
         colTelPac.setCellValueFactory(new PropertyValueFactory("numero"));
         
         //TABLA DE CORREOS
         listaCorreo= FXCollections.observableArrayList();
         colCP.setCellValueFactory(new PropertyValueFactory("id"));
         colPacCo.setCellValueFactory(new PropertyValueFactory("nombre"));
         colCorreoPac.setCellValueFactory(new PropertyValueFactory("correo"));
         colTipoC.setCellValueFactory(new PropertyValueFactory("tipoCorreo"));
         
         formatoFecha();
         seleccionar();
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
        this.tblPacientes.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Pacientes>(){
            @Override
            public void changed(ObservableValue<? extends Pacientes> arg0,
                 Pacientes valorAnterior, Pacientes valorSeleccionado) {
                
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
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione el género del paciente", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println(genero);
        
        int nacionalidad= cmbNacionalidad.getSelectionModel().getSelectedIndex() + 1;
        int tipoC= cmbCorreo.getSelectionModel().getSelectedIndex() + 1;
        int tipoS= cmbTipoSangre.getSelectionModel().getSelectedIndex() + 1;
        
        
        if (txtidPaciente.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de identidad esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtNombrePaciente.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Nombre esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtApellidoPaciente.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Apellido esta vacío, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (genero == 0 ) {
            JOptionPane.showMessageDialog(null, "Seleccione una opcion de genero valida, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtFechaPac.getValue() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de fecha esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtDireccionPaciente.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Direccion esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtPesoPac.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Peso esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (txtAlturaPac.getText() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Altura esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (cmbTipoSangre.getValue() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Tipo de sangre esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if (cmbNacionalidad.getValue() == null ) {
            JOptionPane.showMessageDialog(null, "El campo de Nacionalidad esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
      //  }else if (cmbCorreo.getValue() == null ) {
         //  JOptionPane.showMessageDialog(null, "El campo de Correo esta vacio, por favor complete el formulario.", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else{
            
        try{
            if(existePaciente()){
            return;
            }
          /*  if(!isEmailValid(txtCorreoPaciente.getText())){
            return;
            }*/
            
            if(!validarIdentidad(txtidPaciente.getText())){
            return;
            }
            
            if (!validarLongitudMax(txtNombrePaciente.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "Los nombres del paciente ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtNombrePaciente.getText().length() + " caracteres.", "Longitud de los nombres del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            if (!validarLongitudMax(txtApellidoPaciente.getText(), 40)) {
            JOptionPane.showMessageDialog(null, "Los apellidos del paciente ingresados son muy largos el máximo es de 40 caracteres, usted ingresó " + txtApellidoPaciente.getText().length() + " caracteres.", "Longitud de los apellidos del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            /*if (!validarLongitudMax(txtTelPaciente.getText(), 8)) {
            JOptionPane.showMessageDialog(null, "El teléfono del paciente ingresado es muy largo el máximo es de 8 dígitos, usted ingresó " + txtTelPaciente.getText().length() + " dígitos.", "Longitud del teléfono del empleado", JOptionPane.INFORMATION_MESSAGE);
            return;
            }*/
            if (!validarLongitudMax(txtidPaciente.getText(), 13)) {
             JOptionPane.showMessageDialog(null, "La identidad del paciente ingresado es muy largo el máximo es de 13 dígitos, usted ingresó " + txtidPaciente.getText().length() + " dígitos.", "Longitud del número de identidad del empleado", JOptionPane.INFORMATION_MESSAGE);
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
            }
            
            
            JOptionPane.showMessageDialog(null, "Se ha registrado los datos del paciente", "Datos guardados", JOptionPane.PLAIN_MESSAGE);
        }catch(SQLException ex){
            Logger.getLogger(VistaPacientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void eliminarPacientes(ActionEvent event) {
    }

    @FXML
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtNombreKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtApellidoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isAlphabetic(car) && !Character.isSpaceChar(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten letras");
        }
    }

    @FXML
    private void txtTelefonoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtpesoKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'.'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtalturaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'.'){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void buscarPacientes(ActionEvent event) {
    }

    @FXML
    private void agregarTelefono(ActionEvent event) {
        int numero= Integer.parseInt(this.txtTelPaciente.getText());
        String idE= this.txtidPaciente.getText();
        String nombreE= txtNombrePaciente.getText() + " " + this.txtApellidoPaciente.getText();
        
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
