import java.util.*;
import java.io.*;

public class Main{

  public static void main(String[] args){
    int n = 0;
    int k = 0;
    int sum = 0;
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the number (N):");
    n = sc.nextInt();
    System.out.println("Enter the shift times (k):");
    k = sc.nextInt();
    for (int i=0; i<=k; i++){
      sum = sum + n*(int)Math.pow(10,i);
    }
    System.out.println(sum);
  }
}