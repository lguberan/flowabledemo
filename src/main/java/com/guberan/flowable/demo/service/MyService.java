package com.guberan.flowable.demo.service;

import com.guberan.flowable.demo.domain.Person;
import com.guberan.flowable.demo.domain.PersonRepository;
import io.micrometer.common.util.StringUtils;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PersonRepository personRepository;

    public void startOneTaskProcess(String assignee) {

        Person person = personRepository.findByUsername(assignee);

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("person", person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    }

    public String startDemandeRente(String demandeId, String documents) {

        var variables = new java.util.HashMap<String, Object>();
        variables.put("demandeId", demandeId);
        variables.put("documents", documents);
        runtimeService.startProcessInstanceByKey("demandeRenteProcess", variables);
        return "Processus démarré pour la demande : " + demandeId + " (documents: " + documents + ")";
    }

    public List<Task> getTasks(String assignee) {
        return StringUtils.isNotEmpty(assignee) ? taskService.createTaskQuery().taskAssignee(assignee).list() :
                taskService.createTaskQuery().list();
    }

    public void completeTask(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }


    public void createDemoUsers() {
        if (personRepository.findAll().size() == 0) {
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }
    }

}