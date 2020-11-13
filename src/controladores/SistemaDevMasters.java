/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.conexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author José
 */
public class SistemaDevMasters extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        
        try {
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(SistemaDevMasters.class.getResource("/vistas/vistaProductos.fxml"));
            Pane ventana = (Pane) loader.load();
            
            
            Scene scene= new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SistemaDevMasters.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        conexion cn= new conexion();
        cn.openConnection();
    }
    
    
}
