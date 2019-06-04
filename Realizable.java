/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realizable;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;



class Node
{
    int val;
    Node left;
    Node right;
    Node prev;
}



public class Realizable {

    static ArrayList<Node> leaves = new ArrayList();
    
    public static boolean realizable(int[] A, int T)
    {
        int S = 0;
        for(int i = 1; i < A.length; i++)
            S += A[i];
        //System.out.println(S);
        boolean[][] P = new boolean[A.length][2*S+1];
        P[0][S] = true; 
        
        for(int i = 1; i < P.length; i++)
        {
            for(int j = -S; j <= S; j++)
            {
                if((j - A[i] <= S) && (j - A[i] >= (-1*S)) && P[i-1][j - A[i] + S])
                    P[i][j + S] = true;
                if((j + A[i] <= S) && (j + A[i] >= (-1*S)) && P[i-1][j + A[i] + S])
                    P[i][j+S] = true;
            }
        }
            
        return P[A.length-1][T+S];
    }
    
    public static void showone(int[] A, int T)
    {
        int S = 0;
        for(int i = 1; i < A.length; i++)
            S += A[i];
        //System.out.println(S);
        boolean[][] P = new boolean[A.length][2*S+1];
        P[0][S] = true; 
        
        for(int i = 1; i < P.length; i++)
        {
            for(int j = -S; j <= S; j++)
            {
                if((j - A[i] <= S) && (j - A[i] >= (-1*S)) && P[i-1][j - A[i] + S])
                    P[i][j + S] = true;
                if((j + A[i] <= S) && (j + A[i] >= (-1*S)) && P[i-1][j + A[i] + S])
                    P[i][j+S] = true;
            }
        }
        
        
        if(P[A.length-1][T+S])
        {
            Stack s = new Stack();
            int j = T;
            for(int i = A.length-1; i > 0; i--)
            {
                if((j - A[i] <= S) && (j - A[i] >= (-1*S)) && P[i-1][j - A[i] + S])
                {
                    s.push(new String("-" + A[i]));
                    j += A[i];
                }
                else
                {
                    s.push(new String("+" + A[i]));
                    j -= A[i];
                }
            }
            String str = "Solution: ";
            while (!s.isEmpty())
                str += s.pop();
            System.out.println(str += " = " + T);
        }
        else
            System.out.printf("The value %d is not realizable",T);
        
    }
    
    
    public static void showall(int[] A, int T)
    {
        int S = 0;
        for(int i = 1; i < A.length; i++)
            S += A[i];
        //System.out.println(S);
        boolean[][] P = new boolean[A.length][2*S+1];
        P[0][S] = true; 
        
        for(int i = 1; i < P.length; i++)
        {
            for(int j = -S; j <= S; j++)
            {
                if((j - A[i] <= S) && (j - A[i] >= (-1*S)) && P[i-1][j - A[i] + S])
                    P[i][j + S] = true;
                if((j + A[i] <= S) && (j + A[i] >= (-1*S)) && P[i-1][j + A[i] + S])
                    P[i][j+S] = true;
            }
        }
        
        
        if(P[A.length-1][T+S])
        {
            Node root = new Node();
            root.val = T;
            buildTree(A,A.length-1,P,T,S,root);
            printSolutions(T);
        }
        else
            System.out.println("Number of solutions = 0");
        
    }
    
    public static void buildTree(int[] A,int i,boolean[][] P, int j, int S, Node root)
    {
        //System.out.println(root.val + " " + i);
        if(i == 0)
        {
            leaves.add(root);
            return;
        }
        if((j + A[i] <= S) && (j + A[i] >= (-1*S)) && P[i-1][j + A[i] + S])
                {
                    Node n = new Node();
                    root.right = n;
                    //j += A[i];
                    n.val = j + A[i];
                    n.prev = root;
                    buildTree(A,i-1,P,n.val,S,n);
                }
        if((j - A[i] <= S) && (j - A[i] >= (-1*S)) && P[i-1][j - A[i] + S])
                {
                    Node n = new Node();
                    root.left = n;
                    //j -= A[i];
                    n.val = j - A[i];
                    n.prev = root;
                    buildTree(A,i-1,P,n.val,S,n);
                }
    
    }
    
    public static void printSolutions(int T)
    {
        System.out.printf("Number of solutions = %d\n",leaves.size());
        
        for(int i = 0; i < leaves.size(); i++)
        {
            Node n = leaves.get(i);
            String str = "";
            while(n.prev != null)
            {
                str += n.prev.val > n.val?"+" + (n.prev.val-n.val):(n.prev.val-n.val);
                n = n.prev;
            }
            System.out.printf("Sol   %d : %s = %d\n",i+1,str,T);
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = scan.nextInt();
        int[]  A = new int[n+1];
        //int sum = 0;
        System.out.println("Input array: ");
        for(int i = 1; i < n+1; i++)
        {
            A[i] = scan.nextInt();
            //sum += A[i];
        }
        //System.out.println(sum);
        System.out.println("Enter T: ");
        int T = scan.nextInt();
        System.out.println("n = " + n);
        System.out.println("The input array:");
        for(int i = 1; i < n+1; i++)
        {
            System.out.print(A[i] + " ");
        }
        System.out.println("");
        System.out.println("T = " + T);
        System.out.println("");
        
        System.out.println("Part 1: Realizability check");
        if(realizable(A,T))
            System.out.printf("The value %d is realizable\n\n",T);
        else
            System.out.printf("The value %d is not realizable\n\n",T);
       
        System.out.println("Part 2: One solution");
        showone(A,T);
        
        System.out.println("\n\n");
        System.out.println("Part 3: All solutions");
        showall(A,T);
    }
    
}
