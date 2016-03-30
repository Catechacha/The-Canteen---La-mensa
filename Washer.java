import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Washer implements Runnable{
	static ArrayBlockingQueue<Integer> toWash;
	static ArrayBlockingQueue<Integer> toRinse;
	static AtomicInteger count= new AtomicInteger(0);
	
	public Washer(ArrayBlockingQueue<Integer> a, ArrayBlockingQueue<Integer> b){
		toWash=a;
		toRinse=b;
	}
	
	@Override
	public void run(){
		Integer x;
		while(true){
			if(count.get()>=99)
				break;
			x=toWash.poll();
			if(x!=null){
				int washTime = (int)(Math.random()*5);//wash
				try {
					Thread.sleep(washTime*1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					toRinse.put(x);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count.incrementAndGet();
			}
		}
		System.out.println(Thread.currentThread()+" Finish wash");
	}
}