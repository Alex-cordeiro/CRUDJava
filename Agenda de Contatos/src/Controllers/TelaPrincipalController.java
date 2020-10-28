/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Database.ConnectionModule;
import java.sql.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;

/**
 * FXML Controller class
 *
 * @author Luzia de Souza 
 */
public class TelaPrincipalController implements Initializable {
    
    //Variaveis para conexão ao banco de dados
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @FXML
    private Button btnRealizaCadastro;
    
    @FXML
    private Button btnLogOff;
 
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void RealizaNovoCadastro(ActionEvent event) throws IOException {
        abrirFormulario();
    }


    @FXML
    private void Deslogar(ActionEvent event) {
    }
    
    public void abrirFormulario() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Cenas/TelaCadastro.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Formulário de Cadastro de Produtos");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("/icones/book.png"));
        stage.show();
    }
    
 

   

   
  

  
}
