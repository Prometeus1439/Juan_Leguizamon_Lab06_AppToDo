package edu.eci.dosw.todo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")

public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 120)
        private String title;

        // TODO: description
        // TODO: status (pista: @Enumerated)
        // TODO: priority
        // TODO: dueDate (pista: name = "due_date")
        // TODO: createdAt

        public TaskEntity() {
        }

        // TODO: getters y setters
        
}