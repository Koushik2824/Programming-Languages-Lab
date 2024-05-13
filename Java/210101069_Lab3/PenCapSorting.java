import java.util.*;
import java.io.*;
import java.util.concurrent.Semaphore;
//these are created for pen and cap objects creation
class PenClass{
    String penColour;
    PenClass(String colour){
        this.penColour=colour;
    }
}
class CapClass{
    String capColour;
    CapClass(String colour){
        this.capColour=colour;
    }
}
//there is only one matching machine which must be acquired by semaphore
class MatchingMachine {
    //function to check colours
    public boolean match(PenClass pen, CapClass cap,int robotArmNumber){
        if (pen.penColour.equals(cap.capColour)) {
            System.out.println("Matching pair found: " + pen.penColour +" ,given by robotic arm whose number is "+robotArmNumber);
            return true;
        }
        return false;
    }
}
//the shelf manager which places the acquired pairs
class ShelfManagerRobot {
    public void placeOnShelf(String colour,int robotArmNumber) {
        System.out.println("Shelf Manager Robot placing "+colour +" pair on the shelf"+" ,given by robotic arm whose number is "+robotArmNumber);
    }
}
//robotic arm class which defines the run function
class RoboticArm extends Thread{
    private Semaphore penCapSemaphore;
    private Semaphore matchingMachineSemaphore;
    private Semaphore shelfManagerSemaphore;
    private MatchingMachine matchMachine;
    private ShelfManagerRobot shelfManager;
    private List<PenClass>  pens;
    private List<CapClass> caps;
    private int robotArmNumber;
    //pens list,caps list and semaphores are acquired here
    RoboticArm(List<PenClass> pens,List<CapClass> caps,Semaphore pen,Semaphore matchs,Semaphore shelf,MatchingMachine match,ShelfManagerRobot shelfManager,int number){
        this.penCapSemaphore=pen;
        this.matchingMachineSemaphore=matchs;
        this.shelfManagerSemaphore=shelf;
        this.matchMachine=match;
        this.shelfManager=shelfManager;
        this.caps=caps;
        this.pens=pens;
        this.robotArmNumber=number;
    }
    @Override
    public void run(){
    	//to choose randomly from both the lists
        Random random=new Random();
        while(true){
            try{
                penCapSemaphore.acquire();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            //after acquiring semaphores check if the lists are empty or not
            if(pens.isEmpty()){
                penCapSemaphore.release();
                break;
            }
            PenClass pen=pens.remove(random.nextInt(pens.size()));
            CapClass cap=caps.remove(random.nextInt(caps.size()));
            penCapSemaphore.release();
	    //release the semaphore as required work is done
            try{
                matchingMachineSemaphore.acquire();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            //matchmachine is called here to perform required task after acquiring the lock
            boolean check=matchMachine.match(pen,cap,robotArmNumber);
            matchingMachineSemaphore.release();
            if(check){
                try{
                    shelfManagerSemaphore.acquire();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                shelfManager.placeOnShelf(pen.penColour,robotArmNumber);
                shelfManagerSemaphore.release();
            }else{
                try{
                    penCapSemaphore.acquire();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                pens.add(pen);
                caps.add(cap);//keep back as their colours are not equal
                penCapSemaphore.release();
            }
        }
    }
}
public class PenCapSorting{
    private static String getRandomColour(){
        String[] colours={"red","blue","green","black"};
        Random random=new Random();
        return colours[random.nextInt(colours.length)];
    }//to generate a random colour
    public static void main(String [] args){
        Semaphore matchingMachineSemaphore = new Semaphore(1);
        Semaphore shelfManagerSemaphore = new Semaphore(1);
        Semaphore penCapSemaphore=new Semaphore(1);
        int numberOfRoboticArms;
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter number of pens and caps(positive number):");
        int numberOfItems;
        numberOfItems=scn.nextInt();
        if(numberOfItems<=0){
            System.out.println("Invalid number of items");
            return;
        }
        System.out.print("Enter number of Robotic arms(positive number):");
        //number of robotic arms taken as input
        numberOfRoboticArms=scn.nextInt();
        if(numberOfRoboticArms<=0){
            System.out.println("Invalid number of Robotic arms");
            return;
        }
        List<PenClass> pens=new ArrayList<>();
        List<CapClass> caps=new ArrayList<>();
        System.out.print("The array of colors generated for pens and caps is:");
        for(int i=0;i<numberOfItems;i++){
            String colour=getRandomColour();
            pens.add(new PenClass(colour));
            caps.add(new CapClass(colour));
            System.out.print(colour+" ");
        }//adding colours to all the pens and caps
        System.out.println("\n");
        ShelfManagerRobot shelf=new ShelfManagerRobot();
        MatchingMachine match=new MatchingMachine();
        RoboticArm [] roboticArm=new RoboticArm[numberOfRoboticArms];
        //all threads are instantiated here
        for(int i=0;i<numberOfRoboticArms;i++){
            roboticArm[i]=new RoboticArm(pens,caps,penCapSemaphore,matchingMachineSemaphore,shelfManagerSemaphore,match,shelf,i+1);
        }

        // Start robotic arms
        for(int i=0;i<numberOfRoboticArms;i++){
            roboticArm[i].start();
        }
        // Wait for robotic arms to finish
        try {
            for(int i=0;i<numberOfRoboticArms;i++){
                roboticArm[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work is completed");
    }
}
