package com.practice.atoms;

//import org.flowable.engine.task.Task;

import org.flowable.engine.RuntimeService;
//import org.flowable.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.flowable.engine.task.Task;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Transactional
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

}