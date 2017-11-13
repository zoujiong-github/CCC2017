// stts: Array index - station, value - passengers' number, Previous stt;
// lines: index - line number, value - number of stts in this line, first stt, last stt;
import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws java.io.IOException {
		long startTime=System.currentTimeMillis();
		int N=0;
		int M=0;
		int Q=0;
		int temp = 0;
		int cstt = 0;
		int n=0;
		BufferedReader scan = new BufferedReader(new InputStreamReader(new FileInputStream(java.io.FileDescriptor.in), "ASCII"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out)), 5120);
		String[] tokens=scan.readLine().split(" ");
		N=Integer.parseInt(tokens[0]);
		M=Integer.parseInt(tokens[1]);
		Q=Integer.parseInt(tokens[2]);
		int[][] stts= new int[N+1][2];
		int[][] lines = new int[M+1][3];
		tokens=scan.readLine().split(" ");
		for (int i=1; i<=N; i++){
			if (lines[Integer.parseInt(tokens[i-1])][0]==0){
				lines[Integer.parseInt(tokens[i-1])][1]=i;
			}else{
				stts[i][1]=lines[Integer.parseInt(tokens[i-1])][2];
			}
			lines[Integer.parseInt(tokens[i-1])][0]++;
			lines[Integer.parseInt(tokens[i-1])][2]=i;
			
		}
		tokens=scan.readLine().split(" ");
		for (int i=1; i<=N; i++){
			stts[i][0]=Integer.parseInt(tokens[i-1]);
		}
		for (int i=1; i<=Q; i++){
			tokens=scan.readLine().split(" ");
			if (Integer.parseInt(tokens[0])==1){
				temp=0;
				for (int j=Integer.parseInt(tokens[1]); j<=Integer.parseInt(tokens[2]); j++){
					temp+=stts[j][0];
				}
				out.write(Integer.toString(temp)+"\r\n");
//				out.newLine();
				
			}else{
				n=lines[Integer.parseInt(tokens[1])][0];
				cstt=lines[Integer.parseInt(tokens[1])][2];
				temp=stts[cstt][0];
				for (int j=1; j<n; j++){
					stts[cstt][0]=stts[stts[cstt][1]][0];
					cstt=stts[cstt][1];
				}
				stts[lines[Integer.parseInt(tokens[1])][1]][0]=temp;
			}
		}
//		out.write("Run time:" + (System.currentTimeMillis()-startTime)/1000);
		out.flush();
	}
}