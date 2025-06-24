package com.guberan.flowable.demo;

import com.guberan.flowable.demo.service.MyService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class FlowableDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowableDemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner init1(final MyService myService) {

        return new CommandLineRunner() {
            public void run(String... strings) throws Exception {
                myService.createDemoUsers();
            }
        };
    }

    @Bean
    public CommandLineRunner init2(final MyService myService) {

        return new CommandLineRunner() {
            public void run(String... strings) throws Exception {
                myService.startDemandeRente("MDZ24567", "complets");
                myService.startOneTaskProcess("jbarrez");
            }
        };
    }

    @Bean
    public CommandLineRunner init3(final RepositoryService repositoryService,
                                   final RuntimeService runtimeService,
                                   final TaskService taskService,
                                   final org.flowable.dmn.api.DmnRepositoryService dmnRepositoryService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                System.out.println("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());

                List<org.flowable.dmn.api.DmnDecision> tables = dmnRepositoryService.createDecisionQuery().list();
                for (var table : tables) {
                    System.out.println("DMN key = " + table.getKey() + ", name = " + table.getName());
                }
            }
        };
    }
    
}