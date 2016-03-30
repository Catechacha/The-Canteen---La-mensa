import java.util.concurrent.*;

public class Student implements Callable<Integer>{
	private int index;
	static ArrayBlockingQueue<Integer> toWash;
	
	public Student(int i, ArrayBlockingQueue<Integer>a){
		this.index=i;
		toWash=a;
	}
	
	@Override
	public Integer call(){
		int eatTime = (int)(Math.random()*5);//eat
		try {
			Thread.sleep(eatTime*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			toWash.put(this.index);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("I'm the student "+this.index+", bye bye!");
		return index;
	}
}