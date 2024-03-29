import java.util.*;
public class PorblemB {

	static int getmax(int Array[], int njobs) {
		int max = 1;
		for (int i = 0; i < njobs; i++) {
			if (Array[i] > max) {
				max = Array[i];
			}
		}
		return max;
	}

	static void delmax(int Array[], int njobs) {
		for (int i = 0; i < njobs; i++) {
			if (Array[i] == getmax(Array,njobs)) {
				Array[i] = 0;
				break;
			}
		}

	}
	
	public static void main(String[] args) {

		int njobs = 6;
		Queue<Integer> q = new Queue<>(njobs);
		Queue<Integer> qt = new Queue<>(njobs);
		Queue<Integer> qtf = new Queue<>(njobs);
		Queue<Integer> qf = new Queue<>(njobs);
		int[] Array = {3,2,5,4,6,1};
		
		q.enQueue(3);
		q.enQueue(2);
		q.enQueue(5);
		q.enQueue(4);
		q.enQueue(6);
		q.enQueue(1);
		
		qt.enQueue(0);
		qt.enQueue(0);
		qt.enQueue(0);
		qt.enQueue(1);
		qt.enQueue(0);
		qt.enQueue(0);
		
		
		
		
		
		while (!qf.isFull()) {
			//for (int i = 0; i < njobs; i++) {
				// System.out.println("bug");
				//qf.print();
				//System.out.println(Arrays.toString(Array));
				int j = q.deQueue();
				int k = qt.deQueue();

				if (j == getmax(Array,njobs) || q.getLength() == 0) {
					qf.enQueue(j);
					qtf.enQueue(k);
					delmax(Array,njobs);
					getmax(Array,njobs);
					continue;
				} else {
					q.enQueue(j);
					qt.enQueue(k);
				}
			}
			
		
		
		qf.print();
		qtf.print();
		
		
		
		}
	}

	
	
	
	
	
	
	
	
	
