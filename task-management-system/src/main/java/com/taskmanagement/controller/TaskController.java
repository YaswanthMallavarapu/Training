package com.taskmanagement.controller;

import com.taskmanagement.dto.TaskReqDto;
import com.taskmanagement.dto.TaskResDto;
import com.taskmanagement.dto.TaskUpdateDto;
import com.taskmanagement.model.Task;
import com.taskmanagement.repository.TaskRepository;
import com.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Object> addTask(@Valid @RequestBody TaskReqDto taskReqDto){

        taskService.addTask(taskReqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();

    }
    @GetMapping("/get-all")
    public ResponseEntity<List<TaskResDto>> getAllTasks(@RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                                        @RequestParam(value = "size",required = false,defaultValue = "5")int size){
        List<TaskResDto>list=taskService.getAllTasks(page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<TaskResDto> getTicketById(@PathVariable long taskId){
        TaskResDto task=taskService.getTaskById(taskId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(task);
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Object> updateTaskById(@Valid @RequestBody TaskUpdateDto taskUpdateDto,
                                                 @PathVariable long taskId){
        taskService.updateTask(taskId,taskUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Object> deleteTaskById(
                                                 @PathVariable long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TaskResDto>> filterTasks(@RequestParam(value = "priority",required = false,defaultValue = "")String priority,
                                                        @RequestParam(value = "status",required = false,defaultValue = "")String status,
                                                        @RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                                        @RequestParam(value = "size",required = false,defaultValue = "5")int size){
        List<TaskResDto>list=taskService.filterTasks(priority,status,page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);

    }
}
