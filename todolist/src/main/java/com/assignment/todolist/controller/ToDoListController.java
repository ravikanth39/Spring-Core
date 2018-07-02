package com.assignment.todolist.controller;


import com.assignment.todolist.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/list/")
public class ToDoListController {
    @Autowired
    ToDoListService toDoListService;

    /**
     * Get Method to get all ToDoLists present
     * @param httpStatus
     * @return
     */
    @RequestMapping(value = "/getall",method = RequestMethod.GET)
    public ResponseEntity<List> getAllLists(HttpStatus httpStatus){
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        List responseBody = Collections.emptyList();
        try{
            responseBody=toDoListService.findAllLists();
            httpStatus = HttpStatus.OK;
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseBody,httpStatus);
    }

    /**
     *Post method to add a new ToDoList
     * @param map
     * @param httpStatus
     * @return
     */
    @RequestMapping(value ="/add",method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public ResponseEntity<Map> addList(@RequestBody Map map, HttpStatus httpStatus){
        httpStatus = HttpStatus.FORBIDDEN;
        Map responseBody = Collections.emptyMap();
        if(null != map){
            responseBody=toDoListService.addNewList(map);
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<>(responseBody,httpStatus);
    }


}
