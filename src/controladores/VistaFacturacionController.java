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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import modelos.ProductoC;
import modelos.Servicios;
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
public class VistaFacturacionController implements Initializable {
    conexion con= new conexion();
    
    Connection cone= con.openConnection();
    PreparedStatement pps;
    ResultSet rs;
    
    @FXML
    private TextField txtidEmp;
    @FXML
    private TextField txtempleado;
   
    @FXML
    private TextField txtFactura;
    @FXML
    private TextField txtcodigoProd;
    @FXML
    private TextField txtproducto;
    @FXML
    private Button btnbuscarProd;
    @FXML
    private TextField txtidPac;
    @FXML
    private TextField txtpaciente;
    @FXML
    private Button btnbuscarPac;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtprecioProd;
    @FXML
    private TextField txtcantidad;
    @FXML
    private TextField txtConNeto;
            
    ObservableList <ProductoC>  listadetalle;
    ObservableList<Servicios> servicios;
    
     private double xOffset = 0; 
     private double yOffset = 0;
    
    @FXML
    private Button btnAñadirProd;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colConNeto;
    @FXML
    private TableColumn<?, ?> colCantidad;
    @FXML
    private Button btnAñadirServ;
    @FXML
    private TableView<ProductoC> tablaProductos;
    @FXML
    private TableView<Servicios> tablaServicios;
    @FXML
    private TextField txtTelPac;
    @FXML
    private TextField txtcorreoPac;
    @FXML
    private ComboBox<String> cmbConceptos;
    
    ObservableList<String> items =FXCollections.observableArrayList();
    @FXML
    private Tab paneProducto;
    @FXML
    private Tab paneServicio;
    @FXML
    private TableColumn<?, ?> colCodServ;
    @FXML
    private TableColumn<?, ?> colServicio;
    @FXML
    private TableColumn<?, ?> colPrecioS;
    @FXML
    private TableColumn<?, ?> colCantidadS;
    @FXML
    private TableColumn<?, ?> colSubTotalS;
    @FXML
    private TextField txtcodigoS;
    @FXML
    private TextField txtservicio;
    @FXML
    private TextField txtprecioS;
    @FXML
    private TextField txtcantidadS;
    @FXML
    private Button btnBuscarServ;
    @FXML
    private Tab paneDatos;
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;
    @FXML
    private Button Return;
    
   
    /**
     * Initializes the controller class.
     */
    
    String idEmpl= VistaLoginController.idEmp;
    @FXML
    private TableColumn<?, ?> colSubtotal;
    @FXML
    private TextField txtFecha;
    @FXML
    private Label lbidemp;
    @FXML
    private Label lbidpac;
    @FXML
    private Label lbcodprod;
    @FXML
    private Label lbcantidad;
    @FXML
    private Label lbcodservicio;
    @FXML
    private Label lbcantidad1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       inicializarAlertas();
       
       comboFactura();
       paneProducto.setDisable(true);
       paneServicio.setDisable(true);
       tablaProducto();
       tablaServicios();
       
       txtempleado.setText(VistaLoginController.nombresEmp + " " + VistaLoginController.apellidosEmp);
       txtidEmp.setText(idEmpl);
       txtFecha.setText(mostrarFecha());
       seleccionarProducto();
       seleccionarServicio();
       
        seleccionarProducto();
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Eliminar");
        menuItem1.setOnAction((event) -> {
            eliminarProducto();
            
        });
        contextMenu.getItems().addAll(menuItem1);
        tablaProductos.setContextMenu(contextMenu);
        
        final ContextMenu contextMenu1 = new ContextMenu();
        MenuItem menuItem12 = new MenuItem("Eliminar");
        menuItem12.setOnAction((event) -> {
            eliminarServicio();
        });
        contextMenu1.getItems().addAll(menuItem12);
        tablaServicios.setContextMenu(contextMenu1);

    } 
    
    public void inicializarAlertas(){
        lbidpac.setVisible(false);
        lbidemp.setVisible(false);
        lbcodprod.setVisible(false);
        lbcantidad.setVisible(false);
        lbcodservicio.setVisible(false);
        lbcantidad1.setVisible(false);
    }
    
    public void tablaProducto(){
        listadetalle=FXCollections.observableArrayList();
       colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
       colNombre.setCellValueFactory(new PropertyValueFactory("producto"));
       colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
       colConNeto.setCellValueFactory(new PropertyValueFactory("contNeto"));
       colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
       colSubtotal.setCellValueFactory(new PropertyValueFactory("subtotal"));
    }
    
    public void tablaServicios(){
        servicios=FXCollections.observableArrayList();
        colCodServ.setCellValueFactory(new PropertyValueFactory("id"));
        colServicio.setCellValueFactory(new PropertyValueFactory("servicio"));
        colPrecioS.setCellValueFactory(new PropertyValueFactory("precio"));
        colCantidadS.setCellValueFactory(new PropertyValueFactory("cantidad")); 
        colSubTotalS.setCellValueFactory(new PropertyValueFactory("subtotal"));
    }
    
    public void comboFactura(){
        items.addAll("Seleccionar","Productos", "Servicios", "Producto-Servicio");
        cmbConceptos.setItems(items);
        
        
    }
    
    public void seleccionarProducto() {
        tablaProductos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<ProductoC>() {
            @Override
            public void changed(ObservableValue<? extends ProductoC> arg0,
                    ProductoC valorAnterior, ProductoC valorSeleccionado) {

                if (valorSeleccionado != null) {
                    txtcodigoProd.setText(String.valueOf(valorSeleccionado.getCodigo()));
                    txtprecioProd.setText(String.valueOf(valorSeleccionado.getPrecio()));
                    txtproducto.setText(String.valueOf(valorSeleccionado.getProducto()));
                    txtcantidad.setText(String.valueOf(valorSeleccionado.getCantidad()));
                    txtConNeto.setText(String.valueOf(valorSeleccionado.getContNeto()));
                }
            }
        }
        );
    }
    
    @FXML
    private void eliminarProducto(){
        tablaProductos.getItems().removeAll(tablaProductos.getSelectionModel().getSelectedItem());
        limpiarProductos();
    }
    
    public void seleccionarServicio() {
        tablaServicios.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Servicios>() {
            @Override
            public void changed(ObservableValue<? extends Servicios> arg0,
                    Servicios valorAnterior, Servicios valorSeleccionado) {

                if (valorSeleccionado != null) {
                    txtcodigoS.setText(String.valueOf(valorSeleccionado.getId()));
                    txtservicio.setText(String.valueOf(valorSeleccionado.getServicio()));
                    txtprecioS.setText(String.valueOf(valorSeleccionado.getPrecio()));
                    txtcantidadS.setText(String.valueOf(valorSeleccionado.getCantidad()));
                }
            }
        }
        );
    }
    
    @FXML
    private void eliminarServicio(){
        tablaServicios.getItems().removeAll(tablaServicios.getSelectionModel().getSelectedItem());
        limpiarServicios();
    }
    

    public void noFactura(){
        try {
            pps=cone.prepareStatement("SELECT COUNT(idFacturacion) + 1 as ID FROM facturacion;");
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtFactura.setText(rs.getString("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiarDatos(){
        noFactura();
        cmbConceptos.setValue("Seleccionar");
        txtidPac.setText("");
        txtpaciente.setText("");
        txtTelPac.setText("");
        txtcorreoPac.setText("");
        txtidEmp.setText("");
        txtempleado.setText("");
        
    }
    
    public void limpiarProductos(){
        txtcodigoProd.setText("");
        txtproducto.setText("");
        txtConNeto.setText("");
        txtprecioProd.setText("");
        txtcantidad.setText("");
    }
    
    public void limpiarServicios(){
        txtcodigoS.setText("");
        txtservicio.setText("");
        txtprecioS.setText("");
        txtcantidadS.setText("");
    }
    
    private String mostrarFecha(){
        int dia,mes,año;
        dia= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mes=Calendar.getInstance().get(Calendar.MONTH)+ 1;
        año= Calendar.getInstance().get(Calendar.YEAR);
        
        return dia + "-" + mes + "-" + año ;
    }
    
    private String fecha(){
        int dia,mes,año;
        dia= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        mes=Calendar.getInstance().get(Calendar.MONTH)+ 1;
        año= Calendar.getInstance().get(Calendar.YEAR);
        
        return año + "-" + mes + "-" + dia ;
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
    
    private void buscarEmpleado(ActionEvent event) {
        
          try{
            pps=cone.prepareStatement("SELECT * FROM empleados where idEmpleado=?");
            pps.setString(1, txtidEmp.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtempleado.setText(rs.getString("nombres"));
               
                
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun Empleado con esta identidad: " + txtidEmp.getText(), "No existe empleado", JOptionPane.INFORMATION_MESSAGE);
                txtcodigoProd.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void buscarProducto(ActionEvent event) {
         try{
            pps=cone.prepareStatement("SELECT * FROM productos where idProducto=?");
            pps.setString(1, txtcodigoProd.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                txtproducto.setText(rs.getString("nombre"));
                txtConNeto.setText(rs.getString("contenidoNeto"));
                txtprecioProd.setText(rs.getString("idPrecioHis"));
              
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun Producto con este codigo: " + txtcodigoProd.getText(), "No existe producto", JOptionPane.INFORMATION_MESSAGE);
                txtcodigoProd.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
          
        }
            
    }
    
    @FXML
    private void buscarServicio(ActionEvent event) {
        try {
            pps=cone.prepareStatement("SELECT * FROM servicios where idServicio=?");
            pps.setString(1, txtcodigoS.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
              txtservicio.setText(rs.getString("servicio"));
              txtprecioS.setText(rs.getString("precio"));
            }else{
               JOptionPane.showMessageDialog(null, "No existe ningun servicio con este codigo: " + txtcodigoS.getText(), "No existe servicio", JOptionPane.INFORMATION_MESSAGE); 
               txtcodigoS.requestFocus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
    

    @FXML
    private void buscarPaciente(ActionEvent event) {
         try{
            String num=null;
            String num2;
            String nombres;
            String apellidos;
            pps=cone.prepareStatement("SELECT pacientes.idPaciente, pacientes.nombres, pacientes.apellidos, telefonos_pacientes.telefono, correo_pacientes.correo FROM pacientes, telefonos_pacientes, correo_pacientes " +
                             "WHERE pacientes.idPaciente=telefonos_pacientes.idPaciente and pacientes.idPaciente=correo_pacientes.idPaciente and pacientes.idPaciente=?");
            pps.setString(1, txtidPac.getText());
            rs=pps.executeQuery();
            
            if(rs.next()){
                nombres= rs.getString("pacientes.nombres");
                apellidos= rs.getString("pacientes.apellidos");
                txtpaciente.setText(nombres + " " + apellidos);
                num=rs.getString("telefonos_pacientes.telefono");
                num2=rs.getString("telefonos_pacientes.telefono");
                txtTelPac.setText(num + " // " + num2);
                txtcorreoPac.setText(rs.getString("correo_pacientes.correo"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe ningun paciente con identidad: " + txtidPac.getText(), "No existe paciente", JOptionPane.INFORMATION_MESSAGE);
                txtidPac.requestFocus();
            }
        }catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   
    @FXML
    private void guardarFactura(ActionEvent event) {
       if(txtFecha.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Fecha' está vacío, por favor seleccione la fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidEmp.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtempleado.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Empleado' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidPac.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpaciente.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Paciente' está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else{
  
        try {           
           noFactura();
           String valor = cmbConceptos.getSelectionModel().getSelectedItem();
           switch (valor) {
         
       
            case "Productos":{
                pps=cone.prepareStatement("INSERT INTO facturacion(fechaFactura, idEmpleado, idPaciente) VALUES(?,?,?)");
                //pps.setString(1, txtfechaFactura.getValue().toString());
                pps.setString(1, fecha());
                pps.setString(2, idEmpl);
                pps.setString(3, txtidPac.getText());
                pps.executeUpdate();
                
                for(int i=0;i<tablaProductos.getItems().size();i++){
                     pps=cone.prepareStatement("INSERT INTO detalle_facturacion(idFacturacion, idProducto, cantidad) VALUES(?,?,?)");
                     pps.setString(1, txtFactura.getText());
                     pps.setString(2, String.valueOf(tablaProductos.getItems().get(i).getCodigo()));
                     pps.setString(3, String.valueOf(tablaProductos.getItems().get(i).getCantidad()));
                     pps.executeUpdate();
                     
               PreparedStatement p1=cone.prepareStatement("UPDATE productos,detalle_facturacion SET stock= (stock-" + tablaProductos.getItems().get(i).getCantidad()  + ") "
                                            + " WHERE productos.idProducto=? and detalle_facturacion.idFacturacion=?");
                p1.setString(1, String.valueOf(tablaProductos.getItems().get(i).getCodigo()));
                p1.setString(2, txtFactura.getText());
                p1.executeUpdate();
                System.out.println(p1);
                }
                
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("La Factura se ha guardado exitosamente");
                alert.showAndWait();
                
                imprimirFactura1();               
                limpiarDatos();
                limpiarProductos();
                tablaProductos.getItems().clear();
             
            }
                break;
            case "Servicios":{
                pps=cone.prepareStatement("INSERT INTO facturacion(fechaFactura, idEmpleado, idPaciente) VALUES(?,?,?)");
                //pps.setString(1, txtfechaFactura.getValue().toString());
                pps.setString(1, fecha());
                pps.setString(2, idEmpl);
                pps.setString(3, txtidPac.getText());
                pps.executeUpdate();
            
                for(int i=0;i<tablaServicios.getItems().size();i++){
                   pps=cone.prepareStatement("INSERT INTO detalle_servicios(idFacturacion, idServicio, cantidad) VALUES(?,?,?)");
                   pps.setString(1, txtFactura.getText());
                   pps.setString(2, String.valueOf(tablaServicios.getItems().get(i).getId()));
                   pps.setString(3, String.valueOf(tablaServicios.getItems().get(i).getCantidad()));
                   pps.executeUpdate();                   
                }
                
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("La Factura se ha guardado exitosamente");
                alert.showAndWait();
                
                imprimirFactura2();
                limpiarDatos();
                limpiarServicios();
                tablaServicios.getItems().clear();              
            }
                break;
            case "Producto-Servicio":{
                pps=cone.prepareStatement("INSERT INTO facturacion(fechaFactura, idEmpleado, idPaciente) VALUES(?,?,?)");
                //pps.setString(1, txtfechaFactura.getValue().toString());
                pps.setString(1, fecha());
                pps.setString(2, idEmpl);
                pps.setString(3, txtidPac.getText());
                pps.executeUpdate();
            
                for(int i=0;i<tablaProductos.getItems().size();i++){
                     pps=cone.prepareStatement("INSERT INTO detalle_facturacion(idFacturacion, idProducto, cantidad) VALUES(?,?,?)");
                     pps.setString(1, txtFactura.getText());
                     pps.setString(2, String.valueOf(tablaProductos.getItems().get(i).getCodigo()));
                     pps.setString(3, String.valueOf(tablaProductos.getItems().get(i).getCantidad()));
                     pps.executeUpdate();
                     
                     PreparedStatement p1=cone.prepareStatement("UPDATE productos,detalle_facturacion SET stock= (stock-" + tablaProductos.getItems().get(i).getCantidad()  + ") "
                                            + " WHERE productos.idProducto=? and detalle_facturacion.idFacturacion=?");
                                p1.setString(1, String.valueOf(tablaProductos.getItems().get(i).getCodigo()));
                                p1.setString(2, txtFactura.getText());
                                p1.executeUpdate();
                }
                
                for(int i=0;i<tablaServicios.getItems().size();i++){
                   pps=cone.prepareStatement("INSERT INTO detalle_servicios(idFacturacion, idServicio, cantidad) VALUES(?,?,?)");
                   pps.setString(1, txtFactura.getText());
                   pps.setString(2, String.valueOf(tablaServicios.getItems().get(i).getId()));
                   pps.setString(3, String.valueOf(tablaServicios.getItems().get(i).getCantidad()));
                   pps.executeUpdate();
                }
                
                Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("La Factura se ha guardado exitosamente");
                alert.showAndWait();
                
                imprimirFactura3();
                
                limpiarDatos();
                limpiarProductos();
                limpiarServicios();
                tablaProductos.getItems().clear();
                tablaServicios.getItems().clear();
                
            }
                break;
            default:
                break;
            }
           
        }catch (SQLException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        limpiarDatos();
        limpiarProductos();
        limpiarServicios();
        tablaProductos.getItems().clear();
        tablaServicios.getItems().clear();
        
    }

    //EVENTOS KEY-TYPED
    @FXML
    private void txtidEmpKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            lbidemp.setVisible(true);
            lbidemp.setText("Sólo se permiten números");
        }else{
            lbidemp.setVisible(false);
        }
    }

    @FXML
    private void txtFacturaKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtcodProdKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            lbcodprod.setVisible(true);
            lbcodprod.setText("Sólo se permiten números");
        }else{
            lbcodprod.setVisible(false);
        }
    }

    @FXML
    private void txtcantidadKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            lbcantidad.setVisible(true);
            lbcantidad.setText("Sólo se permiten números");
        }else{
            lbcantidad.setVisible(false);
        }
    }

    @FXML
    private void txtidPacKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            lbidpac.setVisible(true);
            lbidpac.setText("Sólo se permiten números");
        }else{
            lbidpac.setVisible(false);
        }
    }
    
     @FXML
    private void txtcodServKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            lbcodservicio.setVisible(true);
            lbcodservicio.setText("Sólo se permiten números");
        }else{
            lbcodservicio.setVisible(false);
        }
    }

    @FXML
    private void txtcantKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car) && car>'\b'){
            event.consume();
            lbcantidad1.setVisible(true);
            lbcantidad1.setText("Sólo se permiten números");
        }else{
            lbcantidad1.setVisible(false);
        }
    }
 
    @FXML
    private void añadirProducto(ActionEvent event) {
        
        
        if(txtcodigoProd.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El código del producto no puede ir vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
            txtcodigoProd.requestFocus();          
        }else if(txtproducto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El nombre del producto no puede ir vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if(txtConNeto.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El contenido neto no puede ir vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if(txtprecioProd.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El precio del producto no puede ir vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if(txtcantidad.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "La cantidad no puede ir vacía", "¡Error!", JOptionPane.ERROR_MESSAGE);
            txtcantidad.requestFocus();
        }else{
            int codigo = Integer.parseInt(txtcodigoProd.getText());
            String nombre = txtproducto.getText();      
            int conNeto = Integer.parseInt(txtConNeto.getText());
            Double precio = Double.parseDouble(txtprecioProd.getText());
            int cantidad = Integer.parseInt(txtcantidad.getText());
            double subtotal=0;
        
            subtotal+= (cantidad*precio);
            
            try {
                int existencia=0;
                pps=cone.prepareStatement("SELECT idProducto, nombre, stock FROM productos WHERE idProducto=?");
                pps.setString(1, txtcodigoProd.getText());
                rs=pps.executeQuery();
            
            if(rs.next()){
               existencia= Integer.parseInt(rs.getString("stock"));
            }
            if(existencia==0){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No hay existencia del producto: "+ txtproducto.getText());
                alert.showAndWait();
            }else{
                ProductoC pro= new ProductoC(codigo,nombre,precio,conNeto,cantidad, subtotal);       
                listadetalle.add(pro);
                tablaProductos.setItems(listadetalle);
                limpiarProductos();
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            eliminarProducto();
            limpiarProductos();
            
        }
        
    }

    @FXML
    private void añadirServicio(ActionEvent event) {
        if(txtcodigoS.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El código del servicio no puede ir vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
            txtcodigoS.requestFocus();
        }else if(txtservicio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El nombre del servicio no puede ir vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if(txtprecioS.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El precio del servicio no puede ir vacío", "¡Error!", JOptionPane.ERROR_MESSAGE);
        }else if(txtcantidadS.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "La cantidad no puede ir vacía", "¡Error!", JOptionPane.ERROR_MESSAGE);
            txtcantidadS.requestFocus();
        }else{
            int codigo = Integer.parseInt(txtcodigoS.getText());
            String servicio= txtservicio.getText();
            Double precio= Double.parseDouble(txtprecioS.getText());
            int cantidad = Integer.parseInt(txtcantidadS.getText());      
            double subtotal=0;      
            subtotal+= (cantidad*precio);
       
            Servicios serv= new Servicios(codigo,servicio,precio,cantidad, subtotal);
       
            servicios.add(serv);
            tablaServicios.setItems(servicios);
            limpiarServicios();
        } 
            eliminarServicio();
    }

    @FXML
    private void seleccionar(ActionEvent event) {       
        String valor = cmbConceptos.getSelectionModel().getSelectedItem();
        switch (valor) {
            case "Seleccionar":{
                paneProducto.setDisable(true);
                paneServicio.setDisable(true);
               }
               break;
            case "Productos":{
                paneProducto.setDisable(false);
                paneServicio.setDisable(true);
            }
                break;
            case "Servicios":{
                paneProducto.setDisable(true);
                paneServicio.setDisable(false);
                
            }
                break;
            case "Producto-Servicio":{
                paneProducto.setDisable(false);
                paneServicio.setDisable(false);
            }
                break;
            default:
                break;
        }
    }

    
    public void imprimirFactura1(){
        try {
            JasperReport reporte =null;
            String path="src//reportes//facturacion.jasper";
            
            Map parametro= new HashMap();
            parametro.put("noFactura", txtFactura.getText());
            
            reporte= (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametro, cone);
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void imprimirFactura2(){
        try {
            JasperReport reporte =null;
            String path="src//reportes//facturacionServicio.jasper";
            
            Map parametro= new HashMap();
            parametro.put("noFactura", txtFactura.getText());
            
            reporte= (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametro, cone);
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void imprimirFactura3(){
        try {
            JasperReport reporte =null;
            String path="src//reportes//facturaProdServicio.jasper";
            
            Map parametro= new HashMap();
            parametro.put("noFactura", txtFactura.getText());
            
            reporte= (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint= JasperFillManager.fillReport(reporte, parametro, cone);
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException ex) {
            Logger.getLogger(VistaFacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   


}
