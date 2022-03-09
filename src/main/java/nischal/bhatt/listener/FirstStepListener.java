package nischal.bhatt.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener{

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("before step " + stepExecution.getStepName());
		System.out.println(stepExecution.getJobExecution().getExecutionContext());
		System.out.println(stepExecution.getExecutionContext());
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("after step " + stepExecution.getStepName());
		System.out.println(stepExecution.getJobExecution().getExecutionContext());
		System.out.println(stepExecution.getExecutionContext());
	    return null;
	}

}
