/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author José
 */
public class VistaMenuController implements Initializable {

    private double xOffset = 0; 
    private double yOffset = 0;
    
    @FXML
    private Button Close;
    @FXML
    private Button Minimize;

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
    }  
    
    //Close button
    @FXML
    private void exitButtonOnAction(ActionEvent event){
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();      
    }

    @FXML
    private void minimizeButtonOnAction(ActionEvent event){
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).setIconified(true);
    }

    @FXML
    private void btnPacientes(ActionEvent event) throws IOException {
               Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaPacientes.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root, 997, 656);
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
               ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();   
    }

    @FXML
    private void btnEmpleados(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaEmpleados.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root, 997, 656);
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
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
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
    private void btnMedicos(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaMedicos.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root, 997, 656);
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
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnEnfermeras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaEnfermeros.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root, 997, 656);
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
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
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
                Scene scene = new Scene(root, 997, 656);
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
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
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
                Scene scene = new Scene(root, 997, 656);
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
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
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
                Scene scene = new Scene(root, 1000, 700);
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
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
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
                Scene scene = new Scene(root, 997, 656);
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
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
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
