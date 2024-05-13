import java.util.*;
import java.io.*;
public class Brackets{
    public static int length;
    public static String result;
    public static int tempLength;
    public static final String lock=new String("lock");
    static class EachThread implements Runnable {
        public char bracketType;
        public EachThread(char bracketType)
        {
            this.bracketType=bracketType;
        }
        @Override
        public void run() {
            while(tempLength<length){
                synchronized(lock)
                {
                    if(result.length()==(2*length))
                    {
                        lock.notifyAll();
                        break;
                    }
                    if(bracketType=='{')
                    result=result+'}';
                    else if(bracketType=='[')
                    result=result+']';
                    else result=result+')';
                    result=bracketType+result;
                    tempLength++;
                    lock.notifyAll();
                }
            }
            }
        }
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter desired length/2(only enter positive integer):");
        length=scn.nextInt();
        result=new String("");
        tempLength=0;
        Thread[] threads = new Thread[3];
        threads[0] = new Thread(new EachThread('{'));
        threads[1] = new Thread(new EachThread('['));
        threads[2] = new Thread(new EachThread('('));
        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 3; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Resultant string is: "+result);
    }
}