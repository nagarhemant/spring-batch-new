package nischal.bhatt.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nischal.bhatt.service.SecondTasklet;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private SecondTasklet secondTasklet;

	@Bean
	public Job firstJob()
	{
		return this.jobBuilderFactory.get("firstJob")
		.incrementer(new RunIdIncrementer())		
		.start(firstStep())
		.next(secondStep())
		.build();
	}

	private Step firstStep()
	{
		return this.stepBuilderFactory.get("first-step")
		.tasklet(firstTask())
		.build();
	}
	
	private Step secondStep()
	{
		return this.stepBuilderFactory.get("second-step")
		.tasklet(secondTasklet)
		.build();
	}

	private Tasklet firstTask()
	{
		return new Tasklet()
				{

					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
							throws Exception {
						System.out.println("this is first tasklet step");
						return RepeatStatus.FINISHED;
					}
			
				};
	}
	
	
	

}
