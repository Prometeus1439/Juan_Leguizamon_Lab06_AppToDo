package edu.eci.dosw.todo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;


import edu.eci.dosw.todo.entity.TaskPriority;
import edu.eci.dosw.todo.entity.TaskStatus;

public class TaskResponse {
    
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;
    private LocalDateTime createdAt;

    
    public TaskResponse() {
    }

    public TaskResponse(Long id, String title, String description, TaskStatus status, TaskPriority priority, LocalDate dueDate, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    

}
