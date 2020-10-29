/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadastroPessoa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author alexs
 */
public class Main extends Application {
public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
       
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/Cenas/TelaCadastro.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static Stage getStage(){
        return stage;
    }
    
    public static void setStage(Stage stage){
        Main.stage = stage;
    }
    public static void main(String[] args){
        launch(args);
    }

   
    
}
