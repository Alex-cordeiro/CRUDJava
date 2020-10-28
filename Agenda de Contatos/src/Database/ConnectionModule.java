package Database;

import java.sql.*;
/**
 *
 * @author Luzia de Souza
 */
public class ConnectionModule {
    
    public static Connection conn(){
        
        java.sql.Connection connection = null;
        
        String driver = "com.mysql.jdbc.Driver";
            
        //informações do banco de dados
         String url = "jdbc:mysql://localhost:3306/Cad_Prod?autoReconect=true&useSSL=false";
        String user = "root";
        String password = "alexsander";
        //Estabelecendo a conexão com o banco
        
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password); //acessa o driver e passa os parametros do banco para conectar
            return connection;                                                          
        }catch(Exception NotConected){
            return null;
        }
    }
}
