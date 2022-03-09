package nischal.bhatt;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"nischal.bhatt.config","nischal.bhatt.service"})
public class SpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

}
/*
 * Job - job instance (1 march job run is 1 job instance)
 *     - job execution - what time this instance started, what time ended 
 *     - if job execution completed successfully --> then job instance cannot restart
 *     - if job execution failed --> then u can restart 
 *     - spring batch knows stuff because of metadata in the database 
 *     - job instance is unique 
 *     - job execution context --> stored information in key-value form that all steps can access 
 *     - steps can pass info from step to step via job execution context 
 *     - 
 * 
 */


/*
 *  step execution          - when the step started, completed etc
 *  step execution context  - step level - within step if u want to pass some info - then use step execution context 
 *  
*/