package com.springbootjpa.springbootjpa;
import com.springbootjpa.springbootjpa.entity.Task;
import com.springbootjpa.springbootjpa.repository.TaskRepository;
import com.springbootjpa.springbootjpa.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringbootjpaApplication implements CommandLineRunner {

	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepository taskRepository;

	@Bean
	ScheduledExecutorService getExecutor(){
		return Executors.newSingleThreadScheduledExecutor();
	}

	BlockingQueue<ScheduledFuture> queue = new ArrayBlockingQueue(1024);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ScheduledExecutorService executorService = getExecutor();
		Runnable runnable1 = () -> { System.out.println("Task #1 is running"); };
		Runnable runnable2 = () -> { System.out.println("Task #2 is running"); };
		Runnable runnable3 = () -> { System.out.println("Task #3 is running"); };
		ScheduledFuture future1 = executorService.scheduleAtFixedRate(runnable1,0,5, TimeUnit.SECONDS);
		ScheduledFuture future2 = executorService.scheduleAtFixedRate(runnable2,0,5, TimeUnit.SECONDS);
		ScheduledFuture future3 = executorService.scheduleAtFixedRate(runnable3,0,5, TimeUnit.SECONDS);
		queue.add(future1);
		queue.add(future2);
		queue.add(future3);
		System.out.println(queue.contains(future1));
		String nameOfFuture = future1.toString();
		taskService.save(new Task("Task1",nameOfFuture));
		taskService.save(new Task("Task2",future2.toString()));
		taskService.save(new Task("Task3",future3.toString()));
		queue.remove(future1);
		future1.cancel(true);
		List<ScheduledFuture> temp = new ArrayList(queue);
		List<String>qlist = temp.stream().map(x->x.toString()).collect(Collectors.toList());
		taskService.deleteAll();
		qlist.forEach(x->taskService.save(new Task(LocalDateTime.now().toString(),x)));
	}

}
