/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Alexsander
 */
public class ConexaoConfig {
    private String enderecoBanco = "";
    private String usuarioBanco = "";
    
    
    
    public ConexaoConfig(){
        
    }

    public String getEnderecoBanco() {
        return enderecoBanco;
    }

    public void setEnderecoBanco(String enderecoBanco) {
        this.enderecoBanco = enderecoBanco;
    }

    public String getUsuarioBanco() {
        return usuarioBanco;
    }

    public void setUsuarioBanco(String usuarioBanco) {
        this.usuarioBanco = usuarioBanco;
    }
    
   
}
