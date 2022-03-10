package nischal.bhatt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import nischal.bhatt.request.JobParamsRequest;

@Service
public class JobService {
    
	@Autowired
	JobLauncher jobLauncher;
	
	@Qualifier("firstJob")
	@Autowired
	Job firstJob;
	
	
	@Qualifier("secondJob")
	@Autowired
	Job secondJob;
	
	@Async
	public void startJob(String jobName, List<JobParamsRequest> jobParamsRequestList) throws InterruptedException
	{
		Thread.sleep(10000);
		Map<String, JobParameter> params 
		 = new HashMap<>();
		 params.put("currentTime", new JobParameter(System.currentTimeMillis()));
		 
		 jobParamsRequestList.stream().forEach(jobParamReq->{
			 params.put(jobParamReq.getParamKey(), new JobParameter(jobParamReq.getParamValue()));
		 });
		 
		 
		 JobParameters jobParameters = new JobParameters(params);
		 
		 try {
			 JobExecution jobExecution = null;
		 if (jobName.equals("second-job"))
		 {
		 jobExecution = this.jobLauncher.run(secondJob, jobParameters);
		 }else if (jobName.equals("first-job"))
		 {
		    jobExecution=	 this.jobLauncher.run(firstJob, jobParameters);
		 }
		 System.out.println("job execution id =" + " " + jobExecution.getId() );
		 }catch(Exception e)
		 {
			 System.out.println("exception encountered while starting job");
		 }
	}
}
