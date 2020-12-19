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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
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
    private DatePicker txtfechaFactura;
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
    private TextField txtcodigoProd1;
    @FXML
    private TextField txtproducto1;
    @FXML
    private Button btnbuscarProd1;
    @FXML
    private TextField txtprecioProd1;
    @FXML
    private Button btnAñadir1;
    @FXML
    private Button btnAñadirProd1;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       comboFactura();
       paneProducto.setDisable(true);
       paneServicio.setDisable(true);
       tablaProducto();
       tablaServicios();
       
       txtempleado.setText(VistaLoginController.nombresEmp + " " + VistaLoginController.apellidosEmp);
       txtidEmp.setText(idEmpl);

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
      //  if (txtFactura.getText().isEmpty() ){
      //     JOptionPane.showMessageDialog(null, "El campo 'Factura No' está vacío, por favor ingrese el número de factura.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       if(txtfechaFactura.getValue() == null ){
           JOptionPane.showMessageDialog(null, "El campo 'Fecha' está vacío, por favor seleccione la fecha.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidEmp.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtempleado.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Empleado' está vacío, por favor ingrese el nombre del empleado.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtidPac.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Identidad' está vacío, por favor ingrese la identidad del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else if(txtpaciente.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "El campo 'Paciente' está vacío, por favor ingrese el nombre del paciente.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
      // }else if(txtcodigoProd.getText().isEmpty()){
       //    JOptionPane.showMessageDialog(null, "El campo 'Código' está vacío, por favor ingrese el código del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
      // }else if(txtproducto.getText().isEmpty()){
      //     JOptionPane.showMessageDialog(null, "El campo 'Producto' está vacío, por favor ingrese el nombre del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
      // }else if(txtContNeto.getText().isEmpty()){   
       //    JOptionPane.showMessageDialog(null, "El campo 'Contenido Neto' está vacío, por favor ingrese el contenido neto del producto.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
      // }else if(txtprecioProd.getText().isEmpty()){
       //    JOptionPane.showMessageDialog(null, "El campo 'Precio' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
      // }else if(txtcantidad.getText().isEmpty()){
       //    JOptionPane.showMessageDialog(null, "El campo 'Cantidad' está vacío, por favor ingrese el precio.", "¡Error!", JOptionPane.INFORMATION_MESSAGE);
       }else{
  
        try {           
           noFactura();
           String valor = cmbConceptos.getSelectionModel().getSelectedItem();
           switch (valor) {

            case "Productos":{
                pps=cone.prepareStatement("INSERT INTO facturacion(fechaFactura, idEmpleado, idPaciente) VALUES(?,?,?)");
                pps.setString(1, txtfechaFactura.getValue().toString());
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
                pps.setString(1, txtfechaFactura.getValue().toString());
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
                pps.setString(1, txtfechaFactura.getValue().toString());
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
                
                imprimirFactura1();
                imprimirFactura2();
                
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
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
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
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
    }

    @FXML
    private void txtcantidadKeyTyped(KeyEvent event) {
        char car= event.getCharacter().charAt(0);
        
        if(!Character.isDigit(car)){
            event.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten números");
        }
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
    private void añadirProducto(ActionEvent event) {
        
        int codigo = Integer.parseInt(txtcodigoProd.getText());
        String nombre = txtproducto.getText();      
        int conNeto = Integer.parseInt(txtConNeto.getText());
         Double precio = Double.parseDouble(txtprecioProd.getText());
        int cantidad = Integer.parseInt(txtcantidad.getText());
        double subtotal=0;
        
        subtotal+= (cantidad*precio);
        

        ProductoC pro= new ProductoC(codigo,nombre,precio,conNeto,cantidad, subtotal);
       // Productos prod = new Productos(codigo,nombre,precio,conNeto,cantidad);
        
        listadetalle.add(pro);
        tablaProductos.setItems(listadetalle);
        limpiarProductos();
        
        
    }
    /*
    public double calcularP(){
          
          double subtotal=0;
          for(int i=0;i<tablaProductos.getItems().size();i++){
              int cantidad=tablaProductos.getItems().get(i).getCantidad();
              double precio=tablaProductos.getItems().get(i).getPrecio();
              tablaProductos.setItems(listadetalle);
              subtotal+=precio*cantidad;
          
         
             
         }
        return(subtotal);
        
    }
*/
    @FXML
    private void añadirServicio(ActionEvent event) {
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

    @FXML
    private void AgregarDetalle(ActionEvent event) {
    }




    


}
