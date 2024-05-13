import java.util.*;
import java.io.*;
public class FizzBuzz{
	private int length;
	private volatile int currentLength = 1;//to denote the current length of the string printed
	//volatile is used to ensure the same visilbility across all threads
	//if it wasn't used all threads would see cached version,which may not be correct
	public static final String lock=new String("lock");
	public FizzBuzz(int n){
	    this.length=n;
	}
	//constructor called to set the length
	public void printFizz(){
	    System.out.print("fizz ");
	    return;
	}
	public void printBuzz(){
	    System.out.print("buzz ");
	    return;
	}
	public void printFizzBuzz(){
	    System.out.print("fizzbuzz ");
	    return;
	}
	public void printNumber(){
	    System.out.print(currentLength+" ");//here before calling this method we must have obtained a lock on currentLength
	    return;
	}
	//synchronized fizz method to ensure only one thread uses the currentLength
	public void fizz(int m,int n){
	    synchronized(lock){
	        while(currentLength<=length){
	                 
	            if(currentLength%m==0&&currentLength%n!=0){
	                printFizz();
	                currentLength++;
	                lock.notifyAll();//as soon as current thread finishes it notifies other threads waiting for the lock
	            }
	            else{
	                           
	                try{
	                    lock.wait();//if the condition is not met it waits for the lock
	                }catch(InterruptedException e){
	                    e.printStackTrace();
	                }
	            }    
	        }
	        lock.notifyAll();
	    }
	    return;
    }
    //buzz method also is similar to fizz method
    public void buzz(int m,int n){
	    synchronized(lock){
	        while(currentLength<=length){
	            if(currentLength%n==0&&currentLength%m!=0){
	                printBuzz();
	                currentLength++;
	                lock.notifyAll();
	            }
	            else{
	                           
	                try{
	                    lock.wait();
	                }catch(InterruptedException e){
	                    e.printStackTrace();
	                }
	            }
	        }
	        lock.notifyAll();   
	    }
	    return;
    }
    //fizzBuzz method is also similar to fizz method
    public void fizzBuzz(int m,int n){
        synchronized(lock){
	    	while(currentLength<=length){
	                 
	            if(currentLength%m==0&&currentLength%n==0){
	                printFizzBuzz();
	                currentLength++;
	                lock.notifyAll();
	            }
	            else{
	                           
	                try{
	                    lock.wait();
	                }catch(InterruptedException e){
	                    e.printStackTrace();
	                }
	            }
	        }
	        lock.notifyAll();
	    }
	}
	//number method is also similar to fizz method
	public void number(int m,int n){
	    synchronized(lock){
	        while(currentLength<=length){
	                
	            if(currentLength%m!=0&&currentLength%n!=0){
	                printNumber();
	                currentLength++;
	                lock.notifyAll();
	            }
	            else{
	                           
	                try{
	                    lock.wait();
	                }catch(InterruptedException e){
	                    e.printStackTrace();
	                }
	            }
	        }
	        lock.notifyAll();
	    }
	}
	 
	public static void main(String[] args){
	    int lengthRequired;
	    Scanner scn = new Scanner(System.in);
        System.out.print("Enter desired length(only enter positive integer):");
        lengthRequired=scn.nextInt();
        System.out.println("Only enter different numbers below");
        System.out.print("Enter when fizz should occur:");
        final int m=scn.nextInt();
        System.out.print("Enter when buzz should occur:");
        final int n=scn.nextInt();
        scn.close();
	    //required length is taken as input
	    FizzBuzz fizzBuzzObj = new FizzBuzz(lengthRequired);
	    Thread fizzThread = new Thread(() -> {
			fizzBuzzObj.fizz(m,n);
		},"fizzThread");//runnable interface object is created by lambda function whose run function is fizz method
	    Thread buzzThread = new Thread(() -> {
            fizzBuzzObj.buzz(m,n);
        },"buzzThread");//similar to the fizz thread
        Thread fizzBuzzThread = new Thread(() -> {
            fizzBuzzObj.fizzBuzz(m,n);
        },"fizzBuzzThread");//similar to the fizz thread
        Thread numberThread = new Thread(() -> {
            fizzBuzzObj.number(m,n);
        },"numberThread");//similar to fizz thread
        System.out.println("Required sequence:");
        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();//all threads are started
        try {
            fizzThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            buzzThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            fizzBuzzThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            numberThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//wait for all threads to join,and check for interrupted exceptions in all threads
        //which may be caused if the threads are interrupted
        System.out.print("\n");
    }
}

	           
	
	                   
	
