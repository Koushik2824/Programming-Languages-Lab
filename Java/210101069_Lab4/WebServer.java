/*implement a busy web server(for e-commerce website) that handles the requests from multiple clients that arrive indefinitely at a rate of 3T requests per unit time with different priority and random processing time of 0(>0) to 3 units time, concurrently using T threads. Use a waiting queue in case of thread unavailability. Give each request’s details(entry till exit) after it is processed. Assume only one server.

·         Implement a ‘reqGen’ class to generate requests randomly with different priorities and add them to queues.

·         Implement the ‘Q2Server’ class to send requests from the queue to the server.

·         implement a ‘webServer’ class that manages the server’s request handling.
*/
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
//request class describes all aspects of request
class Request{
    int priority;
    int processingTime;
    long time;//this denotes the time generated
    public Request(int priority,int processingTime,long time){
        this.priority=priority;
        this.processingTime=processingTime;
        this.time=time;
    }
}
//this generates the requests
class ReqGen{
	static Random random =new Random();
	static int numPriorities;
	public static Request generateRequest(){
		int priority=random.nextInt(numPriorities);
		int processingTime=random.nextInt(3)+1;
		long time=System.currentTimeMillis();//current time when generated is stored
		return new Request(priority,processingTime,time);
	}
}
//for priority queue waiting queue first sort by time generated and then by priorities to prevent starvation
class TimeStampPriorityComparator implements Comparator<Request>{
	@Override
	public int compare(Request r1,Request r2){
		if(r1.time<r2.time){
			return -1;
		}else if(r1.time>r2.time){
			return 1;
		}else{
			return Integer.compare(r1.priority,r2.priority);
		}
	}
}
//handles queue ,locks the queue when one server uses it
class Q2Server{
	private PriorityQueue<Request> waitingQueue = new PriorityQueue<>(new TimeStampPriorityComparator());
	public void addRequestToQueue(Request r){
		synchronized(waitingQueue){
			waitingQueue.add(r);
			waitingQueue.notifyAll();//after adding waiting threads will continue
		}
	}
	public Request getRequestFromQueue(){
		synchronized(waitingQueue){
			while(waitingQueue.isEmpty()){
				try{
					waitingQueue.wait();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
			return waitingQueue.poll();//give request when queue is non empty
		}
	}
}
//each thread function
class ProcessRequests extends Thread{
	private int num;//denotes thread number
	private Q2Server server;
	private Request req;
	ProcessRequests(int num,Q2Server server){
		this.num=num;
		this.server=server;
		this.req=null;
	}
	public void getRequestDirectly(Request req){
		this.req=req;//if thread is not active then directly give to thread
	}
	@Override
	public void run(){
		while(true){
			if(req==null){
    			Request request=server.getRequestFromQueue();
    			if(request!=null){
    				processRequest(request,num);
    			}
    		}
    		else{
    			processRequest(req,num);
    			req=null;
    		}
    	}
    }
    private static void processRequest(Request request,int num){//prints the essential details to terminal
    	SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
    	String timeReceived = dateFormat.format(new Date(request.time));
    	String timeStarted=dateFormat.format(new Date(System.currentTimeMillis()));
    	try{
    		Thread.sleep((int)request.processingTime*1000);//to simulate the processing time
    	} catch(InterruptedException e){
    		e.printStackTrace();
    	}
    	String timeFinished = dateFormat.format(new Date(System.currentTimeMillis()));
    	System.out.println("Processing Request -Priority:"+request.priority+",Processing Time:"+request.processingTime+",Time Generated:"+timeReceived+",Time Started:"+timeStarted +",Time Finished:"+timeFinished+" by thread:"+num);
    }
}
public class WebServer{//implements WebServer functions
	public static void main(String[] args){
		Scanner scn=new Scanner(System.in);
		System.out.print("Enter the number of priority levels: ");
        int numPriorities = scn.nextInt();
        if(numPriorities==0){
        	System.out.println("Wrong input");
        	return;
        }
        ReqGen.numPriorities=numPriorities;
        System.out.print("Enter the number of threads (T): ");
        int T = scn.nextInt();
        if(T==0){
        	System.out.println("Wrong input");
        	return;
        }
    	Q2Server server=new Q2Server();
    	scn.close();
    	ProcessRequests [] threads=new ProcessRequests[T];
    	for(int i=0;i<T;i++){
    		threads[i]=new ProcessRequests(i+1,server);//all threads are created here
    	}
    	for(int i=0;i<T;i++){
    		threads[i].start();//threads start working
    	}
    	// Start generating requests
        while (true) {
            for (int i = 0; i < 3 * T; i++) {
                Request request = ReqGen.generateRequest();
                boolean sent=false;
                for(int j=0;j<T;j++){
                	if(!threads[j].isAlive()){
                		threads[j].getRequestDirectly(request);
                		sent=true;
                		break;
                	}
                }
                if(!sent){//if the generated request was not sent to any thread then only send to queue
                	server.addRequestToQueue(request);
                }
                int wait=1000/(3*T);
                try {
                	Thread.sleep(wait); // Sleep for 1 second to generate requests at a rate of 3T per second
            	} catch (InterruptedException e) {
                	e.printStackTrace();
            	}		
            }
			//System.out.println("One Time unit of request generations is completed-------------------");
        }
    }
}
    	
