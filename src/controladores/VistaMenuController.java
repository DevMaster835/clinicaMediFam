/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnPacientes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaPacientes.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
    /*
        @FXML
    private void btnPacientes(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaPacientes.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
*/
    @FXML
    private void btnEmpleados(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaEmpleados.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
   /* 
    @FXML
    private void btnEmpleados(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaEmpleados.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
  */  
    @FXML
    private void btnMedicos(MouseEvent event) {
    }

    @FXML
    private void btnEnfermeras(ActionEvent event) {
    }
  /*  
    @FXML
    private void btnEnfermeras(MouseEvent event) {
    }
*/
    @FXML
    private void btnProveedores(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaProveedores.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
   /* 
    @FXML
    private void btnProveedores(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaProveedores.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
*/
    @FXML
    private void btnProductos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaProductos.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
   /* 
     @FXML
    private void btnProductos(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaProductos.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
*/
    @FXML
    private void btnConsultas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaConsultas.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
  /*  
    @FXML
    private void btnConsultas(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaConsultas.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
*/
    @FXML
    private void btnHistorial(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaHistorial.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
  /*  
     @FXML
    private void btnHistorial(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaHistorial.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
  */ 
    
}
