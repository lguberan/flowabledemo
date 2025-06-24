package com.guberan.flowable.demo.controller;

import com.guberan.flowable.demo.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProcessController {
    private final RuntimeService runtimeService;
    private final MyService myService;
    private final TaskService taskService;


    @PostMapping(value = "/process")
    public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
        myService.startOneTaskProcess(startProcessRepresentation.getAssignee());
    }

    @PostMapping("/start")
    public String startProcess(@RequestParam String demandeId,
                               @RequestParam(defaultValue = "complets") String documents) {
        return myService.startDemandeRente(demandeId, documents);
    }

    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam(required = false) String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

    @PostMapping("/complete-task")
    public String completeTask(@RequestParam String taskId) {
        taskService.complete(taskId);
        return "Tâche %s complétée".formatted(taskId);
    }

    @GetMapping("/process/variables/{id}")
    public Map<String, Object> getVariables(@PathVariable String id) {
        return runtimeService.getVariables(id);
    }

    static class StartProcessRepresentation {

        private String assignee;

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }
    }

    static class TaskRepresentation {

        private String id;
        private String name;

        public TaskRepresentation(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}