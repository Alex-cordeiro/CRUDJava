/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Database.ModuloConexao;
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
import Models.Usuario;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.Usuario;
import Database.PessoaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Alexsander
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
    private TextField txtEndereco;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCpf;
    @FXML
    private TableView<Usuario> tbUser;
    @FXML
    private TableColumn<Usuario, Integer> clnCodigo;
    @FXML
    private TableColumn<Usuario, String> clnNome;
    @FXML
    private TableColumn<Usuario, String> clnEndereco;
    @FXML
    private TableColumn<Usuario, String> clnTelefone;
    @FXML
    private TableColumn<Usuario, String> clnCPF;
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
        conexao = ModuloConexao.conn();
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
        PessoaDAO pessoaDAO = new PessoaDAO();
        Usuario usuarioSelecionado = tbUser.getSelectionModel().getSelectedItem();
        pessoaDAO.deletaUser(usuarioSelecionado);
        JOptionPane.showMessageDialog(null, "Usuario Apagado com Sucesso!");
        limparCampos();
        PreencheTabela();
    }

    @FXML
    private void editaUsuario(MouseEvent event) throws SQLException {
        Usuario usuarioSelecionado = tbUser.getSelectionModel().getSelectedItem();
        PessoaDAO pessoaDAO = new PessoaDAO();
        /*if(txtNome.getText().isEmpty() ||
            txtEndereco.getText().isEmpty()||
                txtTelefone.getText().isEmpty()||
                txtCpf.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Existem campos sem informações!!");
        }else{*/
        usuarioSelecionado.setCpf(txtCpf.getText());
        usuarioSelecionado.setEndereco(txtEndereco.getText());
        usuarioSelecionado.setNome(txtNome.getText());
        usuarioSelecionado.setTelefone(txtTelefone.getText());
        pessoaDAO.alteraUser(usuarioSelecionado);
        JOptionPane.showMessageDialog(null, "Usuario Editado com Sucesso!");
        limparCampos();
        PreencheTabela();
        /*}*/

    }

    @FXML
    private void cadastraUsuario(MouseEvent event) throws SQLException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        if (txtNome.getText().isEmpty()
                || txtEndereco.getText().isEmpty()
                || txtTelefone.getText().isEmpty()
                || txtCpf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Existem campos sem informações!!");

        } else {
            pessoaDAO.cadastraUser(new Usuario(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText(), txtCpf.getText()));
            System.out.println(txtNome.getText() + "," + txtEndereco.getText() + "," + txtTelefone.getText() + "," + txtCpf.getText());
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

        ObservableList<Usuario> lista = getLista();

        clnCodigo.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("codigo"));
        clnNome.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nome"));
        clnEndereco.setCellValueFactory(new PropertyValueFactory<Usuario, String>("endereco"));
        clnTelefone.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefone"));
        clnCPF.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cpf"));

        tbUser.setItems(lista);

    }

    private ObservableList<Usuario> getLista() throws SQLException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.listarUser();
    }

    // exemplo de metodo para buscar o usuario e printar no console
    public void selecionaItemTableView(Usuario usuario) {
        // System.out.println("Usuario selecionado:"+usuario.getNome());
        if (usuario != null) {
            txtNome.setText(usuario.getNome());
            txtEndereco.setText(usuario.getEndereco());
            txtTelefone.setText(usuario.getTelefone());
            txtCpf.setText(usuario.getCpf());
        }
    }

    
    private void limparCampos() {
        txtNome.clear();
        txtEndereco.clear();
        txtEndereco.clear();
        txtTelefone.clear();
        txtCpf.clear();
    }

  

}
