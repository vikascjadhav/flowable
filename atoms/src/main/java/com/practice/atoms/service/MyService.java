package com.practice.atoms.service;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.practice.atoms.entity.Person;
import com.practice.atoms.jpa.PersonRepository;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    PersonRepository personRepository;

    @Transactional
    public void  startProcess(String assignee) {
        System.out.println("Assigne "+assignee);
        Person person = personRepository.findByUsername(assignee);
       // person.getId();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess",String.valueOf(person.getId()) , variables);
        System.out.print("startProcess for Person = "+person);
    }

    @Transactional
    public List<Task> getTasks(String assignee) {
        System.out.print("Pending task for Assignee = "+assignee);
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Transactional
    public Task getTask(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    @Transactional
    public List<Task> getAllTask() {
        return taskService.createTaskQuery().active().list();
    }
    @Transactional
    public void complete(String taskId) {
        taskService.complete(taskId);
    }

    public void createDemoUsers() {
        if (personRepository.findAll().isEmpty()) {
            personRepository.save(new Person(1L,"jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person(2L, "trademakers", "Tijs", "Rademakers", new Date()));
        }
    }

}