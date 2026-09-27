package edu.eci.dosw.todo;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

    private TaskEntity existingTask;

    @BeforeEach
    void setUp() {
        existingTask = new TaskEntity();
        existingTask.setId(1L);
        existingTask.setTitle("Study");
        existingTask.setStatus(TaskStatus.PENDING);
        existingTask.setPriority(TaskPriority.MEDIUM);
    }

    @Test 
    void findAll_shouldReturnTasks(){
        // Arrange
        when(repository.findAll()).thenReturn(List.of(existingTask));
        
        // Act
        List<TaskResponse> responses = service.findAll();

        // Assert
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).getId()).isEqualTo(1L);
        assertThat(responses.get(0).getTitle()).isEqualTo("Study");

    }

    @Test 
    void findById_shouldReturnTaskWhenExists(){
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(existingTask));

        // Act 
        TaskResponse response = service.findById(1L);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Study");
    }

    @Test
    void findById_shouldThrowExceptionWhenTaskDoesNotExist(){
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.findById(99L))
            .isInstanceOf(TaskNotFoundException.class);

    }

    @Test 
    void create_shouldCreateTask(){
        // Arrange
        TaskCreateRequest request = new TaskCreateRequest("Study", "Class study", TaskPriority.HIGH, LocalDate.of(2026, 9, 30));

        when(repository.save(any(TaskEntity.class))).thenAnswer(invocation -> {
            TaskEntity entity = invocation.getArgument(0);
            entity.setId(1L);
            return entity;
        });

        // Act
        TaskResponse response = service.create(request);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Study");
        assertThat(response.getDescription()).isEqualTo("Class study");
        assertThat(response.getPriority()).isEqualTo(TaskPriority.HIGH);
        assertThat(response.getDueDate()).isEqualTo(LocalDate.of(2026, 9, 30));

    }

    @Test 
    void create_shouldAssignDefaultStatus(){
        // Arrange
        TaskCreateRequest request = new TaskCreateRequest("Study", "Class study", null, null);

        when(repository.save(any(TaskEntity.class))).thenAnswer(invocation -> {
            TaskEntity entity = invocation.getArgument(0);
            entity.setId(1L);
            return entity;
        });

        // Act
        TaskResponse response = service.create(request);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getPriority()).isEqualTo(TaskPriority.MEDIUM);
        assertThat(response.getStatus()).isEqualTo(TaskStatus.PENDING);
        assertThat(response.getCreatedAt()).isNotNull();
    }

    @Test 
    void update_shouldUpdateExistingTask(){
        // Arrange
        TaskUpdateRequest request = new TaskUpdateRequest("Study hard", "Chapter 4", TaskStatus.IN_PROGRESS, TaskPriority.HIGH, LocalDate.of(2026, 10, 5));
        when(repository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(repository.save(any(TaskEntity.class))).thenAnswer(invocation ->
            invocation.getArgument(0) 
        );

        // Act
        TaskResponse response = service.update(1L, request);

        // Assert
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo("Study hard");
        assertThat(response.getDescription()).isEqualTo("Chapter 4");
        assertThat(response.getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
        assertThat(response.getPriority()).isEqualTo(TaskPriority.HIGH);

    }

    @Test 
    void update_shouldThrowExceptionWhenTaskDoesNotExist(){
        // Arrange
        TaskUpdateRequest request = new TaskUpdateRequest("Study hard", "Chapter 4", TaskStatus.IN_PROGRESS, TaskPriority.HIGH, LocalDate.of(2026, 10, 5));
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.update(99L, request))
            .isInstanceOf(TaskNotFoundException.class);
    }

    @Test 
    void delete_shouldDeleteExistingTask(){

    }

    @Test 
    void delete_shouldThrowExceptionWhenTaskDoesNotExist(){

    }

}
