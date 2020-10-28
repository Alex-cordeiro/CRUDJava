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
       
        Parent root = FXMLLoader.load(getClass().getResource("/Cenas/TelaCadastro.fxml"));
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("/icones/book.png"));
        stage.getIcons().add(applicationIcon);
        Scene scene = new Scene(root);
        stage.setTitle("Cadastro de Produtos");
        stage.setScene(scene);
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
