/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.ConnectionModule;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import Models.Products;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.Products;
import Database.ProductDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Luzia de Souza
 */
public class TelaCadastroController implements Initializable {

    //variaveis de conexão ao banco de dados
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    //variaveis da tela
 
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtValor;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TableView<Products> tbUser;
    @FXML
    private TableColumn<Products, Integer> clnCodigo;
    @FXML
    private TableColumn<Products, String> clnNome;
    @FXML
    private TableColumn<Products, String> clnDescricao;
    @FXML
    private TableColumn<Products, String> clnValor;
    @FXML
    private TableColumn<Products, String> clnCategoria;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //adiciona a variavel de conexão ao banco de dados
        conexao = ConnectionModule.conn();
        //tenta preencher a tabela
        try {
            PreencheTabela();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //listener para selecionar item da tabela que vai ser clicado
        tbUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionaItemTableView(newValue));
        

    }

    //metodos dos botoes
    @FXML
    private void deletarUsuario(MouseEvent event) throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        Products productchosen = tbUser.getSelectionModel().getSelectedItem();
        productDAO.deleteProducts(productchosen);
        JOptionPane.showMessageDialog(null, "Produto Apagado com Sucesso!");
        limparCampos();
        PreencheTabela();
    }

    @FXML
    private void editaUsuario(MouseEvent event) throws SQLException {
        Products productchosen = tbUser.getSelectionModel().getSelectedItem();
        ProductDAO productDAO = new ProductDAO();
        /*if(txtNome.getText().isEmpty() ||
            txtDescricao.getText().isEmpty()||
                txtValor.getText().isEmpty()||
                txtCategoria.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Existem campos sem informações!!");
        }else{*/
        productchosen.setCategory(txtCategoria.getText());
        productchosen.setDescription(txtDescricao.getText());
        productchosen.setName(txtNome.getText());
        productchosen.setValue(txtValor.getText());
        productDAO.alterProductsChangeProducts(productchosen);
        JOptionPane.showMessageDialog(null, "Produto Editado com Sucesso!");
        limparCampos();
        PreencheTabela();
        /*}*/

    }

    @FXML
    private void cadastraUsuario(MouseEvent event) throws SQLException {
        ProductDAO productDAO = new ProductDAO();
        if (txtNome.getText().isEmpty()
                || txtDescricao.getText().isEmpty()
                || txtValor.getText().isEmpty()
                || txtCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Existem campos sem informações!!");

        } else {
            productDAO.registerProducts(new Products(txtNome.getText(), txtDescricao.getText(), txtValor.getText(),txtCategoria.getText()));
  /*linha de teste*/          System.out.println(txtNome.getText() + "," + txtDescricao.getText() + "," + txtValor.getText() + "," + txtCategoria.getText());
            limparCampos();
            PreencheTabela();
            JOptionPane.showMessageDialog(null, "Usuario inserido com sucesso!");
        }
    }
    
      @FXML
    private void sair(MouseEvent event) {
        System.exit(0);
    }

    public void PreencheTabela() throws SQLException {

        ObservableList<Products> lista = getList();

        clnCodigo.setCellValueFactory(new PropertyValueFactory<Products, Integer>("code"));
        clnNome.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        clnDescricao.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        clnValor.setCellValueFactory(new PropertyValueFactory<Products, String>("value"));
        clnCategoria.setCellValueFactory(new PropertyValueFactory<Products,String>("category"));

        tbUser.setItems(lista);

    }

    private ObservableList<Products> getList() throws SQLException {
        ProductDAO product = new ProductDAO();
        return product.listProducts();
    }

    // exemplo de metodo para buscar o usuario e printar no console
    public void selecionaItemTableView(Products product) {
        // System.out.println("Usuario selecionado:"+usuario.getNome());
        if (product != null) {
            txtNome.setText(product.getName());
            txtDescricao.setText(product.getDescription());
            txtValor.setText(product.getValue());
            txtCategoria.setText(product.getCategory());
        }
    }

    
    private void limparCampos() {
        txtNome.clear();
        txtDescricao.clear();
        txtDescricao.clear();
        txtValor.clear();
        txtCategoria.clear();
    }

  

}
