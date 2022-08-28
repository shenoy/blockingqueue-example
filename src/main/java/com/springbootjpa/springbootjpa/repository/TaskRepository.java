package com.springbootjpa.springbootjpa.repository;
import com.springbootjpa.springbootjpa.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    void delete(Task deleted);
    List<Task> findAll();
    Task save(Task persisted);



}