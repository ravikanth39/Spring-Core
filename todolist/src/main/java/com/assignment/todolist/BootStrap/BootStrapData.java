package com.assignment.todolist.BootStrap;

import com.assignment.todolist.domain.ToDoList;
import com.assignment.todolist.repository.ToDoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BootStrapData implements CommandLineRunner {

    @Autowired
    ToDoListRepository toDoListRepository;

    /**
     * Method to input a sample List into database
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        ToDoList sampleList = new ToDoList();
        Map<String,String> dataMap = new HashMap<>();
        sampleList.setTitle("Example");
        dataMap.put("1","task1");
        dataMap.put("2","task2");
        sampleList.setData(new ObjectMapper().writeValueAsString(dataMap));
        toDoListRepository.save(sampleList);
    }
}
