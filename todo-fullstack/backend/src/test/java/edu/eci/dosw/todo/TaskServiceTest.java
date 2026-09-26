package edu.eci.dosw.todo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.eci.dosw.todo.dto.TaskCreateRequest;
import edu.eci.dosw.todo.dto.TaskResponse;
import edu.eci.dosw.todo.dto.TaskUpdateRequest;
import edu.eci.dosw.todo.entity.TaskEntity;
import edu.eci.dosw.todo.entity.TaskPriority;
import edu.eci.dosw.todo.entity.TaskStatus;
import edu.eci.dosw.todo.exception.TaskNotFoundException;
import edu.eci.dosw.todo.repository.TaskRepository;
import edu.eci.dosw.todo.service.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)

public class TaskServiceTest {
    
    @Mock
    private TaskRepository repository;

    @InjectMocks 
    private TaskServiceImpl service;

    @Test 
    void findAll_shouldReturnTasks(){
        // Arrange
        TaskEntity entity = new TaskEntity();
        entity.setId(1L);
        entity.setTitle("Study");
        entity.setStatus(TaskStatus.PENDING);
        entity.setPriority(TaskPriority.MEDIUM);
        when(repository.findAll()).thenReturn(List.of(entity));
        
        // Act
        List<TaskResponse> responses = service.findAll();

        // Assert
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getId()).isEqualTo(1L);
        assertThat(responses.get(0).getTitle()).isEqualTo("Study");

    }

    @Test 
    void findById_shouldReturnTaskWhenExists(){

    }

    @Test
    void findById_shouldThrowExceptionWhen(){

    }

    @Test 
    void create_shouldCreateTask(){

    }

    @Test 
    void create_shouldAssignDefaultStatus(){

    }

    @Test 
    void update_shouldUpdateExistingTask(){

    }

    @Test 
    void update_shouldThrowExceptionWhenTaskDoesNotExist(){

    }

    @Test 
    void delete_shouldDeleteExistingTask(){

    }

    @Test 
    void delete_shouldThrowExceptionWhenTaskDoesNotExist(){

    }

}
