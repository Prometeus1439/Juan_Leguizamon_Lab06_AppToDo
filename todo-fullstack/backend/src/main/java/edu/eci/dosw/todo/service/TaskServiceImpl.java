package edu.eci.dosw.todo.service;

import java.time.LocalDateTime;
import java.util.List;

import edu.eci.dosw.todo.dto.TaskCreateRequest;
import edu.eci.dosw.todo.dto.TaskResponse;
import edu.eci.dosw.todo.dto.TaskUpdateRequest;
import edu.eci.dosw.todo.entity.TaskEntity;
import edu.eci.dosw.todo.entity.TaskPriority;
import edu.eci.dosw.todo.entity.TaskStatus;
import edu.eci.dosw.todo.exception.TaskNotFoundException;
import edu.eci.dosw.todo.repository.TaskRepository;
import edu.eci.dosw.todo.service.TaskService;

public class TaskServiceImpl implements TaskService{

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
public List<TaskResponse> findAll() {
    return repository.findAll().stream()
            .map(this::toResponse)
            .toList();
}

    @Override
    public TaskResponse findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public TaskResponse create(TaskCreateRequest request) {
        TaskEntity entity = new TaskEntity();

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setDueDate(request.getDueDate());

        entity.setStatus(TaskStatus.PENDING);
        entity.setCreatedAt(LocalDateTime.now());
        
        if (request.getPriority() == null) {
            entity.setPriority(TaskPriority.MEDIUM);    
        }
        else{
            entity.setPriority(request.getPriority());
        }
        
        TaskEntity savedEntity = repository.save(entity);

        return toResponse(savedEntity);
    }

    @Override
    public TaskResponse update(Long id, TaskUpdateRequest request) {
        TaskEntity entity = findEntityOrThrow(id);

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setDueDate(request.getDueDate());
        entity.setStatus(request.getStatus());
        entity.setPriority(request.getPriority());

        TaskEntity savedEntity = repository.save(entity);

        return toResponse(savedEntity);

    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private TaskEntity findEntityOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    private TaskResponse toResponse(TaskEntity entity) {
        return new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getDueDate(),
                entity.getCreatedAt());
    }

}
