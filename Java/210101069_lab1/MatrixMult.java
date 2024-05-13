import java.util.*;
import java.io.*;
public class MatrixMult{
    public static int matsize;
    public static int [][] mat1;
    public static int [][] mat2;
    public static int [][] res;
    static class EachThread implements Runnable {
        public int row;
        EachThread(int row) {
            this.row = row;
        }
 
        @Override
        public void run() {
            for (int j = 0; j < matsize; j++) {
                for (int k = 0; k < matsize; k++) {
                    res[row][j] += mat1[row][k] * mat2[k][j];
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        System.out.println("Only enter integers for all the below inputs");
        System.out.print("Enter matrix size(square matrices are taken):");
        matsize = scn.nextInt();
        mat1=new int[matsize][matsize];
        mat2=new int[matsize][matsize];
        res=new int[matsize][matsize];
        System.out.println("Enter first matrix in row wise manner:");
        for(int i=0;i<matsize;i++)
        {
            for(int j=0;j<matsize;j++)
            {
                mat1[i][j]=scn.nextInt();
                res[i][j]=0;
            }
        }
        System.out.println("Enter second matrix in row wise manner:");
        for(int i=0;i<matsize;i++)
        {
            for(int j=0;j<matsize;j++)
            {
                mat2[i][j]=scn.nextInt();
            }
        }
        System.out.println("First Matrix:");
        for (int i = 0; i < matsize; i++) {
            for (int j = 0; j < matsize; j++) {
                System.out.print(mat1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("No explicit lock is required as each thread deals with different rows");
        System.out.println("Second Matrix:");
        for (int i = 0; i < matsize; i++) {
            for (int j = 0; j < matsize; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Created "+matsize+" number of threads");
        //number of threads created is same as number of rows in each matrix
        Thread[] threads = new Thread[matsize];
        int row_number=0;
        for (int i = 0; i < matsize; i++) {
            threads[i] = new Thread(new EachThread(row_number++));
        }
        for (int i = 0; i < matsize; i++) {
            threads[i].start();
        }
        for (int i = 0; i < matsize; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Result of matrix multiplication is:");
        for (int i = 0; i < matsize; i++) {
            for (int j = 0; j < matsize; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
        return;
    }
}