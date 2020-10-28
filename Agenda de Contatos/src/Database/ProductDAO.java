package Database;

import javax.swing.JOptionPane;
import Controllers.TelaCadastroController;
import Database.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.Products;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    
    
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet result = null;
    
    ConnectionModule connectionModule = new ConnectionModule();
    
    //trocaR!!!
    private final TelaCadastroController controller = new TelaCadastroController();

public ObservableList<Products> listProducts() throws SQLException{
        connection = ConnectionModule.conn();
        String sql = "SELECT * FROM cadastro_produto";
        Products products = new Products();
        ObservableList<Products> list = FXCollections.observableArrayList();
     
        try {
            pst = connection.prepareStatement(sql);
            result = pst.executeQuery();
            while(result.next()){
                products = new Products();
                products.setCode(result.getInt("id"));
                products.setName(result.getString("nome"));
                products.setDescription(result.getString("descricao"));
                products.setValue(result.getString("valor"));
                String categories = showCategoryAboutProduct(result.getInt("categoria"));
                products.setCategory(categories);
                
                list.add(products);
            }
  } catch (Exception e) {
            e.printStackTrace();
        }finally{
            connection.close();
        }
            
            return list;
    }
    
    public boolean registerProducts(Products products) throws SQLException{
        
        String sql = "INSERT INTO cadastro_produto (nome, descricao, valor, categoria)"
                + "VALUES (?,?,?,?)";
        connection = ConnectionModule.conn();
        try {
            pst = connection.prepareStatement(sql);
            
            pst.setString(1, products.getName());
            pst.setString(2, products.getDescription());
            pst.setString(3, products.getValue());
            pst.setString(4, products.getCategory());
            
            pst.execute();
            return true;
              
           
            
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }finally{
            connection.close();
        }
    }
    
    
    
    public boolean alterProductsChangeProducts(Products products) throws SQLException{
        String sql = "UPDATE cadastro_produto SET nome = ?, descricao = ?, valor = ?, categoria = ? WHERE id = ?";
        connection = ConnectionModule.conn();
        
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, products.getName());
            pst.setString(2, products.getDescription());
            pst.setString(3, products.getValue());
            pst.setString(4, products.getCategory());
            pst.setInt(5, products.getCode());
            pst.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }finally{
            connection.close();
        }
        
    
    }
    
    public boolean deleteProducts(Products products) throws SQLException{
        String sql = "DELETE FROM cadastro_produto WHERE id = ?";
        connection = ConnectionModule.conn();
        
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, products.getCode());
            pst.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }finally{
            connection.close();
        }
    }
    
    public String convertId(Products products){
        String codeProduct = "";
        codeProduct = Integer.toString(products.getCode());
      
        return codeProduct;
    }
    
    public String showCategoryAboutProduct(int category){
        if(category == 1){
            return "moveis";
        }else if(category == 2){
            return "decoracao";
        }else if(category == 3){
            return "celular";
        }else if(category == 4){
            return "informatica";
        }else if(category == 5){
            return "brinquedos";
        }else{
            return "Sem categoria cadastrada!";
        }
    }
    
}


