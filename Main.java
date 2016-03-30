import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
	public static int N_STUDENT=100;
	public static int N_WASHER=3;
	public static int N_RINSER=4;
	
	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> toWash=new ArrayBlockingQueue<Integer>(N_STUDENT);
		ArrayBlockingQueue<Integer> toRinse=new ArrayBlockingQueue<Integer>(N_STUDENT);
		int i;
		ExecutorService s = Executors.newFixedThreadPool(5);
		ArrayList<Future<Integer>> tasks = new ArrayList<Future<Integer>>();
		for(i=0;i<N_STUDENT;i++)
			tasks.add(s.submit(new Student(i,toWash)));

		for(i=0;i<N_WASHER;i++){
			Washer w=new Washer(toWash,toRinse);
			Thread t = new Thread(w);
			t.start();
		}
		for(i=0;i<N_RINSER;i++){
			Rinser r=new Rinser(toRinse);
			Thread t = new Thread(r);
			t.start();
		}
		s.shutdown();
	}
}