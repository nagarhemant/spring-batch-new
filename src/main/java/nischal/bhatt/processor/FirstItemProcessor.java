package nischal.bhatt.processor;



import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer,Long>{

	@Override
	public Long process(Integer item) throws Exception {
		System.out.println("inside item processor");
		// TODO Auto-generated method stub
		return Long.valueOf(item + 20);
	}

}
