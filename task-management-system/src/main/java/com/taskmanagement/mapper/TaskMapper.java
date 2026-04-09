package com.taskmanagement.mapper;

import com.taskmanagement.dto.TaskReqDto;
import com.taskmanagement.dto.TaskResDto;
import com.taskmanagement.model.Task;

public class TaskMapper {

    public static Task mapToEntity(TaskReqDto dto){
        Task task=new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDueDate(dto.DueDate());
        return task;
    }
    public static TaskResDto mapToDto(Task task){
        return new TaskResDto(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority()
                ,task.getStatus()
        );
    }
}
