import java.util.*;
import java.io.*;

public class Main{
  public static void main(String[] args) throws java.io.IOException{
    int n = 0;
    int bh = 0;
    int min = 2000;
    int max = 1;
	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
	String[] tokens=scan.readLine().split(" ");
    n = Integer.parseInt(tokens[0]);
    tokens=scan.readLine().split(" ");
    int[] woods = new int[4001];
    for (int i=0; i<n; i++) {
      bh=Integer.parseInt(tokens[i]);
      if (bh>max) max=bh;
      if (bh<min) min=bh;
      woods[bh]++;
    }
//    for (Map.Entry entry: woods.entrySet()){
//      System.out.println((Integer)entry.getKey() +":"+ (Integer)entry.getValue());
//    }
    int[] pairedHeights = new int[4001];
    for (int ch=2*min; ch<=2*max; ch++){
      for (int i=Math.max(1,ch-2000); i<=ch/2; i++){
      	  if (ch==2*i){
      	  	  pairedHeights[ch]+=woods[i]/2;
      	  }else{
      	  	  pairedHeights[ch]+=Math.min(woods[i],woods[ch-i]);
      	  }
      }
    }
    int heightsCount=0;
    int fenceWidth=0;
/*
    for (Map.Entry entry: pairedHeights.entrySet()){
      int newVal = (int)entry.getValue()/2;
      pairedHeights.replace((Integer)entry.getKey(),(Integer)newVal);
    }
*/
//    for (Map.Entry entry: pairedHeights.entrySet()){
//      System.out.println((int)entry.getKey() +"-"+ (int)entry.getValue());
//    }

    for (int i=2; i<=4000; i++){
//      System.out.println((int)entry.getValue()+"vs"+(int)fenceWidth+((int)entry.getValue()==(int)fenceWidth));
      if (pairedHeights[i]>fenceWidth){
        fenceWidth=pairedHeights[i];
        heightsCount=1;
//        System.out.println("New high:"+fenceWidth);
      }else if (pairedHeights[i]==fenceWidth){
        heightsCount=heightsCount+1;
//        System.out.println("Count++:"+heightsCount);
      }else{
      }
    }
    System.out.println(fenceWidth+" "+heightsCount);
  }
}