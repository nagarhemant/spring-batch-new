package nischal.bhatt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Qualifier("firstJob")
	@Autowired
	Job firstJob;
	
	
	@Qualifier("secondJob")
	@Autowired
	Job secondJob;
	
	 @GetMapping("/start/{jobName}")
	 public String startJob(@PathVariable String jobName) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException
	 {
		 
		 Map<String, JobParameter> params 
		 = new HashMap<>();
		 params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		 
		 JobParameters jobParameters = new JobParameters(params);
		 
		 
		 if (jobName.equals("second-job"))
		 {
		 this.jobLauncher.run(secondJob, jobParameters);
		 }else if (jobName.equals("first-job"))
		 {
			 this.jobLauncher.run(firstJob, jobParameters);
		 }
		 return "Job Started";
		 		
	 }
}
