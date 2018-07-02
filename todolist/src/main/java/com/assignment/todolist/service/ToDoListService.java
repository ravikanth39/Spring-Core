package com.assignment.todolist.service;

import java.io.IOException;

import java.util.List;
import java.util.Map;

public interface ToDoListService {

    List findAllLists() throws IOException;

    Map addNewList(Map list);
}
