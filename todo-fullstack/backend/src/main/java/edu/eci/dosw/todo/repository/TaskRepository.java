package edu.eci.dosw.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.dosw.todo.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long>{
    
}
