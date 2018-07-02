package com.assignment.todolist.service;

import com.assignment.todolist.domain.ToDoList;
import com.assignment.todolist.repository.ToDoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Service
public class ToDoListServiceImpl implements ToDoListService {

    @Autowired
    ToDoListRepository toDoListRepository;

    /**
     * Service to get all Lists and parse the data from String to map as
     * H2 database cannot store JSON data
     * @return list containing all the to do list and data as a map
     * @throws IOException
     */
    @Override
    public List findAllLists() throws IOException {
        List<ToDoList> allLists = toDoListRepository.findAll();
        List result = new ArrayList();
        for(ToDoList toDoList:allLists){
            Map listAsMap = new HashMap();
            listAsMap.put("title",toDoList.getTitle());
            listAsMap.put("id",toDoList.getId());
            //mapping the Json string data into a Map
            listAsMap.put("data",new ObjectMapper().readValue(toDoList.getData(), HashMap.class));
            result.add(listAsMap);
        }
        return result;
    }

    /**
     * Service to add new ToDoList
     * @param map map containing the ToDoList to save
     * @return a map containing the saved ToDoList along with id
     */
    @Override
    @Transactional
    public Map addNewList(Map map){
        ToDoList newList = new ToDoList();
        Map resultMap = new HashMap();
        newList.setTitle(String.valueOf(map.get("title")));
        //converting the data Map to a Json String as H2 Database cannot store JSon objects
        newList.setData(new JSONObject((Map)map.get("data")).toString());
        newList=toDoListRepository.save(newList);
        resultMap.put("title",newList.getTitle());
        resultMap.put("id",newList.getId());
        resultMap.put("data",newList.getData());
        return resultMap;
    }

}
