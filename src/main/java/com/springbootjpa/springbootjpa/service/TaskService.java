package com.springbootjpa.springbootjpa.service;
import com.springbootjpa.springbootjpa.entity.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TaskService {
    List<Task> list();
    void save(Task todo);
    void deleteAll();
}