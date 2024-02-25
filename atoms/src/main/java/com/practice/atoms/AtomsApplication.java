package com.practice.atoms;

import com.practice.atoms.service.MyService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flowable.engine.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
//@EnableWebMvc
@EnableAutoConfiguration
public class AtomsApplication {
	@Autowired
	MyService myService;

	public static void main(String[] args) {
		SpringApplication.run(AtomsApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(final RepositoryService repositoryService,
								  final RuntimeService runtimeService,
								  final TaskService taskService) {

		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				myService.createDemoUsers();
				myService.startProcess("jbarrez");
				myService.startProcess("trademakers");

				System.out.println("Number of process definitions : "
						+ repositoryService.createProcessDefinitionQuery().count());
				System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
				//runtimeService.startProcessInstanceByKey("oneTaskProcess");
				System.out.println("Number of tasks after process start: "
						+ taskService.createTaskQuery().count());


			}
		};
	}
}
