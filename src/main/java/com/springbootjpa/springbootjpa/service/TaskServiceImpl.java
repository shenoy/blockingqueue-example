package com.springbootjpa.springbootjpa.service;
import com.springbootjpa.springbootjpa.entity.Task;
import com.springbootjpa.springbootjpa.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public List<Task> list(){
        List<Task> searchResults = repository.findAll();
        return searchResults;
    }

    public void save(Task task){
        repository.save(task);
    }


    @Transactional
    public void deleteAll(){
        //Delete all rows in single query
        Query query = manager.createNativeQuery("DELETE FROM task_tbl");
        query.executeUpdate();
    }
}
