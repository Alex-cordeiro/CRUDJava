/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;
/**
 *
 * @author Alexsander
 */
public class ModuloConexao {
    
    public static Connection conn(){
        
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.jdbc.Driver";
            
        //informações do banco de dados
         String url = "jdbc:mysql://db4free.net:3306/cadastro_pessoa?autoReconect=true&useSSL=false";
        String user = "mickelangelo";
        String password = "G1ng1b1rr4";
        //Estabelecendo a conexão com o banco
        
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password); //acessa o driver e passa os parametros do banco para conectar
            return conexao;                                                          
        }catch(Exception NotConected){
            return null;
        }
    }
}
