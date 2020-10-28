package Models;

import java.sql.*;


public class Products {
    private int code;
    private String name;
    private String description;
    private String value;
    private String category;

  
    
    public Products(){
        
    }
    
    
    
    public  Products(int codigo, String name, String description, String value, String category){
        this.code = code;
        this.name = name;
        this.description = description;
        this.value = value;
        this.category = category;
    }
    
    public  Products(String name, String description, String value, String category){
        
        this.name = name;
        this.description = description;
        this.value = value;
        this.category = category;
    }
  


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    

}
