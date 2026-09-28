package edu.eci.dosw.todo.controller;

import static org.mockito.Answers.values;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import edu.eci.dosw.todo.controller.TaskController;
import edu.eci.dosw.todo.dto.TaskCreateRequest;
import edu.eci.dosw.todo.dto.TaskResponse;
import edu.eci.dosw.todo.dto.TaskUpdateRequest;
import edu.eci.dosw.todo.entity.TaskPriority;
import edu.eci.dosw.todo.entity.TaskStatus;
import edu.eci.dosw.todo.exception.TaskNotFoundException;
import edu.eci.dosw.todo.service.TaskService;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService service;

    private TaskResponse sampleResponse;

    @BeforeEach
    void setUp() {
        sampleResponse = new TaskResponse(1L, "Study", "Chapter 3", TaskStatus.PENDING,
                TaskPriority.HIGH, LocalDate.of(2026, 9, 30), LocalDateTime.of(2026, 9, 27, 10, 0));
    }

    @Test
    void findAll_shouldReturn200() throws Exception {
        // Arrange
        when(service.findAll()).thenReturn(List.of(sampleResponse));

        // Act & Assert
        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Study"));
    }

    @Test
    void findById_shouldReturn200WhenTaskExists() throws Exception {
        // Arrange
        when(service.findById(1L)).thenReturn(sampleResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/tasks/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Study"))
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void findById_shouldReturn404WhenTaskDoesNotExist() throws Exception {
        // Arrange
        when(service.findById(99L)).thenThrow(new TaskNotFoundException(99L));

        // Act & Assert
        mockMvc.perform(get("/api/v1/tasks/99"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").value("Task with id 99 was not found"));
    }

    @Test
    void create_shouldReturn201WhenRequestIsValid() throws Exception {
        // Arrange
        TaskCreateRequest request = new TaskCreateRequest("Study", "Chapter 3", TaskPriority.HIGH, LocalDate.of(2026, 9, 30));
        when(service.create(any(TaskCreateRequest.class))).thenReturn(sampleResponse);

        // Act & Assert
        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Study"))
                .andExpect(jsonPath("$.id").value(1));

    }

}