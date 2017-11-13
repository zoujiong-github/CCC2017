import java.util.*;

public class Main{

  private static int countHits(int n){
    int hour = 12;
    int minute = 0;
    int count = 0;
    for (int i = 1; i <= n; i++ )
    {
      hour = (hour + Math.floorDiv((minute + 1), 60)-1)%12+1;
      minute = (minute + 1) % 60;
      if (hour==11 && minute==11) {
         count=count+1;
//         System.out.println(hour+":"+minute);
      } else if (hour==12 && minute==34) {
         count=count+1; 
//         System.out.println(hour+":"+minute); 
      } else if (hour==10){
      } else {
         if (hour+(minute % 10) == 2 * Math.floorDiv(minute, 10)) {
           count=count+1;
//           System.out.println(hour+":"+minute);
         }
      }
    }
//    System.out.println("Hits:"+hits);
    
    return(count);
    
  }

  public static void main(String[] args){
    int n = 0;
    Scanner scan = new Scanner(System.in);
    System.out.println("Input number of duration minutes:");
    n = scan.nextInt();
    int totalCount = 0;
    totalCount=Math.floorDiv(n,720)*countHits(720)+countHits(n%720);
    System.out.println(totalCount);
  }

}