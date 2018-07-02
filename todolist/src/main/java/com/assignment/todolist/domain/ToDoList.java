package com.assignment.todolist.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String data;
}
