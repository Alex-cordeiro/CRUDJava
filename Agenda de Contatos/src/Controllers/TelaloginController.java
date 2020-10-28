/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.sun.javaws.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Database.ConnectionModule;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author alexsander
 */
public class TelaloginController implements Initializable {
    
    //declaração de variveis para conexão ao banco de dados
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    
    //variaveis de itens da classe
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnLogin;

    /**
     * Initializes the controller class.
     * @param url
     */
    
     public static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        conexao = ConnectionModule.conn();
    }    

    @FXML
    private void logar(MouseEvent event) throws IOException {
      
        loginAdmin();
        /*String sql = "SELECT * FROM td_user WHERE nome =? AND str_Senha = ?";
        
        
        try {
        //receber os valores dos campos de input de texto
        pst = conexao.prepareStatement(sql);
        pst.setString(1,txtUser.getText());
        pst.setString(2,txtSenha.getText());
        result = pst.executeQuery();
        
        if (result.next()){
            
            abrirTelaPrincipal();
            
        }else{
           JOptionPane.showMessageDialog(null, "Usuário ou senha inválida!!"); 
        }
    } catch (Exception FalhaLogin) {
        FalhaLogin.printStackTrace();
        JOptionPane.showMessageDialog(null,FalhaLogin);
        
    }*/
        
    }

    @FXML
    private void Logar(ActionEvent event) {
    }
    
    private void loginAdmin() throws IOException{
        
        
        String usuarioPadrao = txtUser.getText();
        String verificaSenha= txtSenha.getText();
        
        System.out.println(usuarioPadrao);
        System.out.println(verificaSenha);
        
        if(usuarioPadrao.equals("admin") && verificaSenha.equals("admin")){
            abrirTelaPrincipal();
        }else{
             JOptionPane.showMessageDialog(null, "Usuário ou senha inválida!!"); 
        }
        
    }
    
  
    public void abrirTelaPrincipal() throws IOException{
        
        Parent root= FXMLLoader.load(getClass().getResource("/Cenas/TelaPrincipal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Tela Principal");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/icones/book.png"));
        stage.show();
       
        
        
    }
    
   
    
}
