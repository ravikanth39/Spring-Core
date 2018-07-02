package com.assignment.todolist.repository;

import com.assignment.todolist.domain.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList,Long> {

}
