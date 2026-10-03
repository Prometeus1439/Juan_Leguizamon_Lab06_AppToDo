package edu.eci.dosw.todo.service;

import java.util.List;

import edu.eci.dosw.todo.dto.TaskCreateRequest;
import edu.eci.dosw.todo.dto.TaskResponse;
import edu.eci.dosw.todo.dto.TaskUpdateRequest;

public interface TaskService {
    
    List<TaskResponse> findAll();

    TaskResponse findById(Long id);

    TaskResponse create(TaskCreateRequest request);

    TaskResponse update(Long id, TaskUpdateRequest request);

    void delete(Long id);

}
