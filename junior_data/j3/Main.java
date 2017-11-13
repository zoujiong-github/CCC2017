import java.util.*;
import java.io.*;
public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int sa = 0;
    int sb = 0;
    int da = 0;
    int db = 0;
    int chrg = 0;
    String line = "";
    line = sc.nextLine().trim();
    sa = Integer.parseInt(line.substring(0,line.indexOf(" ")));
    sb = Integer.parseInt(line.substring(line.indexOf(" ")+1));
    line = sc.nextLine().trim();
    da = Integer.parseInt(line.substring(0,line.indexOf(" ")));
    db = Integer.parseInt(line.substring(line.indexOf(" ")+1));
    chrg = sc.nextInt();
    if (((Math.abs(da-sa)+Math.abs(db-sb)-chrg) <= 0) && ((Math.abs(da-sa)+Math.abs(db-sb)-chrg) % 2 == 0)) 
      System.out.println("Y");
    else 
      System.out.println("N");
    
  }
}