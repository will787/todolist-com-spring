package br.com.williamvieira.todolist.user;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator= "UUID")
    private UUID id;    
    
    //@Column(name = "usuario") aqui seria username mas no banco de dados seria usuario
    @Column(unique = true) // lá no banco de dados vai ser um atributo único, se tiver um valor já existen ele vai dar um erro 
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDate createdAt;
    //getters mostra 
    //setters atualizar, definir um atributo;

}

