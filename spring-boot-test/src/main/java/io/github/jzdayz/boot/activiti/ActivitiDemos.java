package io.github.jzdayz.boot.activiti;


import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiDemos {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiDemos.class, args);
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return strings -> {
            ProcessInstance process_1 = runtimeService.startProcessInstanceByKey("Process_1");
            List<Task> accountancy = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
            System.out.println();
        };

    }
}
