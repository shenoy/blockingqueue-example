package com.springbootjpa.springbootjpa.entity;


import lombok.*;

import javax.persistence.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledFuture;

@Entity
@Table(name = "task_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Lob
    private String future;

    public Task(String name, String future){
        this.name=name;
        this.future=future;
    }
}