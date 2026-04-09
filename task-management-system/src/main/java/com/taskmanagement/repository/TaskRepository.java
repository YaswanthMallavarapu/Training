package com.taskmanagement.repository;

import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;
import com.taskmanagement.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("""
    select t from Task t
    where (?1 is null or t.status=?1)
    and (?2 is null or t.priority=?2)
""")
    Page<Task> getByStatusAndPriority(Status status, Priority priority, Pageable pageable);
}
