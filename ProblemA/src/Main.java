import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int getmax(int Array[], int njobs) { // Calculates highest priority value in the queue
		int max = 1;
		for (int i = 0; i < njobs; i++) {
			if (Array[i] > max) {
				max = Array[i];
			}
		}
		return max;
	}

	static void delmax(int Array[], int njobs) { // Changes highest priority value in the queue to 0
		for (int i = 0; i < njobs; i++) {
			if (Array[i] == getmax(Array,njobs)) {
				Array[i] = 0;
				break;
			}
		}

	}

	public static void main(String[] args) throws IOException {

		InputStreamReader in = new InputStreamReader(System.in); 
		BufferedReader bf = new BufferedReader(in); //Creates input buffer

		int numcases = Integer.parseInt(bf.readLine()); //Reads the number of cases 
		
		for(int c=0;c<numcases;c++) {   //Repeats the process for each case
		String strjobs = bf.readLine(); //Reads the n,m as a string
		
		String[] arjobs = strjobs.split(" "); //Makes a string array from prev string 
		                                      //(Separating by blank spaces)
		 
		int njobs = Integer.parseInt(arjobs[0]); //Stores n as an integer
		int myjpos = Integer.parseInt(arjobs[1]);//Stores m as an integer
		int time = 1; //Time is always 1 or higher
		
		 
			
			String str_job_pr = bf.readLine(); //Reads job priority values
			
			String[] ar_str_job_pr = str_job_pr.split(" "); //Turns that into a string array (Separating by blank spaces)
			
			int[] Array = new int[ar_str_job_pr.length];   //Turns that array from str to int array
			for (int i = 0; i < ar_str_job_pr.length; i++) {
			   Array[i] = Integer.parseInt(ar_str_job_pr[i]);
			   
			}
			
			//Initializes every queue                             
		Queue<Integer> q = new Queue<>(njobs); //Normal priority value Queue (For comparing)
		Queue<Integer> qt = new Queue<>(njobs);//Queue that keeps track of my job (filled by 0's and a 1)
		Queue<Integer> qtf = new Queue<>(njobs);//Organized tracking queue
		Queue<Integer> qf = new Queue<>(njobs);//Organized normal queue
		
		//int[] Array = ar_int_jobpr;
		
		for(int i=0;i<njobs;i++) {  //Fills queue with priority values
			q.enQueue(Array[i]);
		}
	
		for(int i=0;i<myjpos;i++) { //Fills tracking queue with 0's values
			qt.enQueue(0);
		}
		qt.enQueue(1);// Adds a 1 to represent my job
		
		while(!qt.isFull()) {//Fills the rest of the tracking queue with 0's
			qt.enQueue(0);
		}
		
		while (!qf.isFull()) {//Apply sorting till organized queue is full
		
			int j = q.deQueue();//De-queue value of normal queue
			int k = qt.deQueue();//De-queue value of tracking queue

			if (j == getmax(Array,njobs) || q.getLength() == 0) { //Compare value to the max value 
				qf.enQueue(j);                                    //in the queue, and if so move to 			                                                      
				qtf.enQueue(k);                                   //organized
				delmax(Array,njobs);                             
				getmax(Array,njobs);
				continue;
			} else {                     //Else send to back of queue
				q.enQueue(j);//Normal queue 
				qt.enQueue(k);//Tracking queue
			}
		}
		while(!qtf.isEmpty()) {//Count the number of 0's in front of my job and 1 minute to time
			if(qtf.deQueue()==0) {
				time++;
			}else break;
		}
		
		System.out.println(time);
		}
		
		
		bf.close();//Closes buffer reader just in case
	}
}

class Queue<T> { //Queue class implemented using a Circular array 

	T[] queue;//Queue values constructor
	int size;
	int head;
	int tail;
	int length;

	public Queue(int size) {//Queue initializer
		if (size > 0) {
			this.size = size;

			queue = (T[]) new Object[size]; // creating array of generic type
		} else
			System.out.println("Error: Invalid Size");//Must be bigger than 0
	}

	public void enQueue(T data) {
		if (!isFull()) {
			queue[tail] = data;
			tail = (tail + 1) % size;
			length++;
		} else
			System.out.println("enQueue(" + data + ")-Error: Queue is Full");
	}

	public T deQueue() {

		T data = queue[head];

		if (!isEmpty()) {
			head = (head + 1) % size;
			length--;
		} else {
			System.out.println("deQueue-Error: Queue is Empty");
		}
		return data;

	}

	public T peek() {
		if (!isEmpty()) {

			T data = queue[head];
			return data;
		} else
			return null;
	}

	public int getLength() {//Queue current length
		return length;
	}

	public boolean isEmpty() {
		return getLength() == 0;
	}

	public boolean isFull() {
		return getLength() == size;
	}

	public void print() {
		for (int i = 0; i < length; i++) {
			System.out.print(queue[(head + i) % size] + " ");
		}
		System.out.println();
	}
}
