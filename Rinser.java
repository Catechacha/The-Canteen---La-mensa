import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Rinser implements Runnable{
	static ArrayBlockingQueue<Integer> toRinse;
	static AtomicInteger count= new AtomicInteger(0);
	
	public Rinser(ArrayBlockingQueue<Integer> a){
		toRinse=a;
	}
	
	@Override
	public void run(){
		Integer x;
		while(true){
			if(count.get()>=99)
				break;
			x=toRinse.poll();
			if(x!=null){
				int rinseTime = (int)(Math.random()*5);//wash
				try {
					Thread.sleep(rinseTime*1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				count.incrementAndGet();
			}
		}
		System.out.println(Thread.currentThread()+" Finish rinse");
	}
}
