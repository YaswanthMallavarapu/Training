package com.taskmanagement.service;

import com.taskmanagement.dto.TaskReqDto;
import com.taskmanagement.dto.TaskResDto;
import com.taskmanagement.dto.TaskUpdateDto;
import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;
import com.taskmanagement.exceptions.ResourceNotFoundException;
import com.taskmanagement.model.Task;
import com.taskmanagement.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private  TaskService taskService;
    @Mock
    private  TaskRepository taskRepository;



    @Test
    public void getAllTasksTest(){

        Task task1 = new Task();
        task1.setTaskId(1L);
        task1.setTitle("Deploy");
        task1.setDescription("Deploy app");
        task1.setDueDate(LocalDate.of(2026, 4, 8));
        task1.setPriority(Priority.LOW);
        task1.setStatus(Status.PENDING);

        Task task2 = new Task();
        task2.setTaskId(2L);
        task2.setTitle("Testing");
        task2.setDescription("Write unit tests");
        task2.setDueDate(LocalDate.of(2026, 4, 9));
        task2.setPriority(Priority.HIGH);
        task2.setStatus(Status.IN_PROGRESS);

        List<Task> list = List.of(task1, task2);

        Page<Task> pageTask = new PageImpl<>(list);
        int page = 0;
        int size = 2;
        Pageable pageable = PageRequest.of(page, size);

        Mockito.when(taskRepository.findAll(pageable)).thenReturn(pageTask);

        Assertions.assertEquals(2, taskService.getAllTasks(0, 2).size());

        pageTask = new PageImpl<>(list.subList(0, 1));
        page = 0;
        size = 1;
        pageable = PageRequest.of(page, size);

        Mockito.when(taskRepository.findAll(pageable)).thenReturn(pageTask);

        Assertions.assertEquals(1, taskService.getAllTasks(0, 1).size());

        pageTask = new PageImpl<>(list.subList(0, 2));
        page = 0;
        size = 3;
        pageable = PageRequest.of(page, size);

        Mockito.when(taskRepository.findAll(pageable)).thenReturn(pageTask);

        Assertions.assertEquals(2, taskService.getAllTasks(0, 3).size());


    }

    @Test
    public void getTaskByIdWhenExistTest(){
        Task task = new Task();

        task.setTaskId(1L);
        task.setTitle("Deploy Application");
        task.setDescription("Deployment should be done tomorrow");
        task.setDueDate(LocalDate.of(2026, 4, 8));
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        task.setPriority(Priority.HIGH);
        task.setStatus(Status.PENDING);

        TaskResDto dto=new TaskResDto(
                1L,
                "Deploy Application",
                "Deployment should be done tomorrow",
                LocalDate.of(2026, 4, 8),
                Priority.HIGH,
                Status.PENDING
        );

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Assertions.assertEquals(dto,taskService.getTaskById(1L));

        verify(taskRepository,times(1)).findById(1L);

    }


    @Test
    public void getTaskByIdWhenDoesNotExistTest(){

        when(taskRepository.findById(11L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException e = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.getTaskById(11L)
        );

        Assertions.assertEquals("Invalid Task Id.", e.getMessage());
    }

    @Test
    public void updateTaskTest(){
        Task task = new Task();

        task.setTaskId(1L);
        task.setTitle("Deploy Application");
        task.setDescription("Deployment should be done tomorrow");
        task.setDueDate(LocalDate.of(2026, 4, 8));
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        task.setPriority(Priority.HIGH);
        task.setStatus(Status.PENDING);

        TaskUpdateDto dto=new TaskUpdateDto("IN_PROGRESS");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        taskService.updateTask(1L,dto);
        ArgumentCaptor<Task>taskCaptor=ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(taskCaptor.capture());
        Task updatedTask=taskCaptor.getValue();
        Assertions.assertEquals(Status.IN_PROGRESS,updatedTask.getStatus());
        verify(taskRepository,times(1)).save(updatedTask);
    }

    @Test
    public void deleteTaskTest(){
        Task task = new Task();

        task.setTaskId(1L);
        task.setTitle("Deploy Application");
        task.setDescription("Deployment should be done tomorrow");
        task.setDueDate(LocalDate.of(2026, 4, 8));
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        task.setPriority(Priority.HIGH);
        task.setStatus(Status.PENDING);
        
        taskService.deleteTask(1L);
        verify(taskRepository,times(1)).deleteById(1L);
    }
}
