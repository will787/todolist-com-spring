package br.com.williamvieira.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "tb_task")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 50) //deixando 50 caracteres para o título
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    
    private UUID idUser;
    
    @CreationTimestamp
    private LocalDateTime createAt;


    public void SetTitle(String title) throws Exception {
        if(title.length() > 50){
            throw new Exception("O campo title deve conter no máximo 50 caracteres"); 
        }
        this.title = title;
    }
}
