package com.taskmanagement.service;

import com.taskmanagement.dto.TaskReqDto;
import com.taskmanagement.dto.TaskResDto;
import com.taskmanagement.dto.TaskUpdateDto;
import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;
import com.taskmanagement.exceptions.ResourceNotFoundException;
import com.taskmanagement.mapper.TaskMapper;
import com.taskmanagement.model.Task;
import com.taskmanagement.repository.TaskRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void addTask(TaskReqDto taskReqDto) {
        //map to task
        Task task= TaskMapper.mapToEntity(taskReqDto);
        Priority priority=Priority.valueOf(taskReqDto.priority());
        task.setStatus(Status.PENDING);
        task.setPriority(priority);
        taskRepository.save(task);

    }

    public List<TaskResDto> getAllTasks(int page,int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<Task>pageTask=taskRepository.findAll(pageable);
        return pageTask
                .toList()
                .stream()
                .map(TaskMapper::mapToDto)
                .toList();
    }

    public TaskResDto getTaskById(long taskId) {
        Task task= taskRepository.findById(taskId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Task Id."));
        return TaskMapper.mapToDto(task);
    }

    public void updateTask(long taskId, @Valid TaskUpdateDto taskUpdateDto) {
        Task task=taskRepository.findById(taskId)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Task Id."));
        Status status=Status.valueOf(taskUpdateDto.status());
        task.setStatus(status);
        taskRepository.save(task);
    }

    public void deleteTask(long taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<TaskResDto> filterTasks(String priority, String status
    ,int page,int size) {

        //  validation of status and priority
        Priority taskPriority= priority.isEmpty() ?null:Priority.valueOf(priority);
        Status taskStatus=status.isEmpty()?null:Status.valueOf(status);

        if(taskPriority==null && taskStatus==null)
            return List.of();

        //  create pagination
        Pageable pageable=PageRequest.of(page,size);


        Page<Task>pageTask=taskRepository.getByStatusAndPriority(taskStatus,taskPriority,pageable);

        // converting page of tasks to list of task res dto

        return pageTask
                .toList()
                .stream()
                .map(TaskMapper::mapToDto)
                .toList();
    }
}
