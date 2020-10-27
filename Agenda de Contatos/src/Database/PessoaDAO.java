/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import javax.swing.JOptionPane;
import Controllers.TelaCadastroController;
import Database.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.Usuario;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexsander
 */
public class PessoaDAO {
    
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    
    ModuloConexao connection = new ModuloConexao();
    private final TelaCadastroController controller = new TelaCadastroController();
 
    
    public ObservableList<Usuario> listarUser() throws SQLException{
        conexao = ModuloConexao.conn();
        String sql = "SELECT * FROM td_user";
        Usuario user = new Usuario();
        ObservableList<Usuario> lista = FXCollections.observableArrayList();
     
        try {
            pst = conexao.prepareStatement(sql);
            result = pst.executeQuery();
            while(result.next()){
                user = new Usuario();
                user.setCodigo(result.getInt("id"));
                user.setNome(result.getString("nome"));
                user.setEndereco(result.getString("Endereco"));
                user.setTelefone(result.getString("telefone"));
                user.setCpf(result.getString("CPF"));
                
                lista.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conexao.close();
        }
            
            return lista;
    }
    
    public boolean cadastraUser(Usuario usuario) throws SQLException{
        
        String sql = "INSERT INTO td_user (nome, Endereco, telefone, CPF)"
                + "VALUES (?,?,?,?)";
        conexao = ModuloConexao.conn();
        try {
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEndereco());
            pst.setString(3, usuario.getTelefone());
            pst.setString(4, usuario.getCpf());
            
            pst.execute();
            return true;
              
           
            
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }finally{
            conexao.close();
        }
    }
    
    
    
    public boolean alteraUser(Usuario usuario) throws SQLException{
        String sql = "UPDATE td_user SET nome = ?, Endereco = ?, telefone = ?, CPF = ? WHERE id = ?";
        conexao = ModuloConexao.conn();
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEndereco());
            pst.setString(3, usuario.getTelefone());
            pst.setString(4, usuario.getCpf());
            pst.setInt(5, usuario.getCodigo());
            pst.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }finally{
            conexao.close();
        }
        
    
    }
    
    public boolean deletaUser(Usuario usuario) throws SQLException{
        String sql = "DELETE FROM td_user WHERE id = ?";
        conexao = ModuloConexao.conn();
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, usuario.getCodigo());
            pst.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }finally{
            conexao.close();
        }
    }
    
    public String convertId(Usuario usuario){
        String codigoUser = "";
        codigoUser = Integer.toString(usuario.getCodigo());
      
        return codigoUser;
    }
    
}
