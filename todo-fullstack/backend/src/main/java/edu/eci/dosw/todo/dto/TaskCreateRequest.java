package edu.eci.dosw.todo.dto;

import java.time.LocalDate;
import edu.eci.dosw.todo.entity.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class TaskCreateRequest {

    @NotBlank
    @Size(max = 120)
    private String title;

    @Size(max = 500)
    private String description;

    private TaskPriority priority;
    private LocalDate dueDate;

    public TaskCreateRequest() {
    }

    public TaskCreateRequest(String title, String description, TaskPriority priority, LocalDate dueDate){
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public TaskPriority getPriority(){
        return priority;
    }
    public void setPriority(TaskPriority priority){
        this.priority = priority;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
    
}
