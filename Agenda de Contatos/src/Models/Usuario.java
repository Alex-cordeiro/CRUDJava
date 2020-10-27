
package Models;

import java.sql.*;


public class Usuario {
    private int codigo;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;

  
    
    public Usuario(){
        
    }
    
    
    
    public  Usuario(int codigo, String nome, String endereco, String telefone, String cpf){
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
    }
    
    public  Usuario(String nome, String endereco, String telefone, String cpf){
        
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
    }
  


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    

}
