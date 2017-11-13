import java.util.*;
import java.io.*;


public class Main{
	
	public static void main(String[] args) throws java.io.IOException {
	
		// Define variables: N,M,D;
		int n = 0;
		int m = 0;
		long d = 0;
		LinkedList<Pipe> pipes = new LinkedList<>();  //Store active pipes info.
		BufferedReader scan = new BufferedReader( new InputStreamReader(System.in));
		String[] tokens = scan.readLine().split(" ");
		n=Integer.parseInt(tokens[0]);
		m=Integer.parseInt(tokens[1]);
		d=Integer.parseInt(tokens[2]);
		//Read pipes data;
		for (int i=0; i<m; i++){
			tokens = scan.readLine().split(" ");
			pipes.add(new Pipe(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]), (i<n-1)));
		}

		//Define jointed points sets and initialize it;
		int[] blds = new int[n+1];
		for (int i=1; i<=n; i++){
			blds[i]=i;
		}

		//MST algorithm;
		PipeCmp_cost c = new PipeCmp_cost();
		Collections.sort(pipes, c);
		LinkedList<Step> steps = new LinkedList<>();
		long largest_cost;

//		LinkedList<Pipe> plan = new LinkedList<>();
		int[] result = mst(0, m, 0, n, pipes, blds, steps);

		
		int p = 0;
		int p_i = result[2];
		Pipe buffer;
		largest_cost = pipes.get(steps.get(0).pipe_index).cost;
		LinkedList<Step> step1 = new LinkedList<>();
		if (steps.size()>0 && largest_cost<d && pipes.get(p_i).cost<=d){
			for (int i=steps.size()-1; i>=0;i--){
				blds[steps.get(i).bld]=steps.get(i).bld;
			}
			while (pipes.get(p_i).cost<d){
				if (!pipes.get(p_i).original) {
					p_i++;
					continue;
				}
				if (!connected(p_i, pipes, blds)) {
					result[0]--;
					break;
				}
				else {
					p_i++;
				}
			}
		}
		
	
		System.out.println(result[0]);
		
	}
	
	static int[] mst(int p_i, int p_end, int j, int n, LinkedList<Pipe> pipes, int[] blds, LinkedList<Step> step){
		// j: Plan's added pipes count;
		int s1=-1; //bld set to which 'from' belongs;
		int s2=-1; //bld set to which 'to' belongs;
		int a = 0; //for temp use;
		int b = 0; //for temp use;
		long largest_cost=-1;
		boolean flag;
		int count = 0;
		while (j<n-1 && p_i<p_end){
			a=pipes.get(p_i).from;
			b=blds[pipes.get(p_i).from];
			while (a!=b){
				a=blds[a];
				b=blds[a];
			}
			s1=a;
			a=pipes.get(p_i).to;
			b=blds[pipes.get(p_i).to];
			while (a!=b){
				a=blds[a];
				b=blds[a];
			}
			s2=a;
			if (s1!=s2){
				blds[s2]=s1;
				if (pipes.get(p_i).cost>largest_cost && !pipes.get(p_i).original){
					step.clear();
					largest_cost=pipes.get(p_i).cost;
				}
				step.add(new Step(s2,p_i));
				if (!pipes.get(p_i).original) count++; //count non-original pipes;
				j++;
//				System.out.println(j);
			}
			p_i++;
		}
		return new int[]{count, j, p_i};
	}
	
	static boolean connected(int p_i, LinkedList<Pipe> pipes, int[] blds){
			int a = pipes.get(p_i).from;
			int b =blds[pipes.get(p_i).from];
			while (a!=b){
				a=blds[a];
				b=blds[a];
			}
			int s1=a;
			a=pipes.get(p_i).to;
			b=blds[pipes.get(p_i).to];
			while (a!=b){
				a=blds[a];
				b=blds[a];
			}
			int s2=a;
			return s1==s2;
	}
/*	
	static boolean connected(int from, int current, int to, LinkedList<Pipe> pipes){
		int nextNode=-1;
		for (Pipe pipe: pipes){
			if (current==pipe.from && pipe.to!=from){
				nextNode=pipe.to;
			}else if (current==pipe.to && pipe.from!=from){
				nextNode=pipe.from;
			}else{
				continue;
			}
			if (nextNode==to) {
				return true;
			}else if (nextNode!=-1){
				if (connected(current,nextNode,to,pipes)) return true;
			}
		}
		return false;
	}
*/
/*	
	static LinkedList<Pipe> findPath(int from, int current, int to, LinkedList<Pipe> pipes){
		int nextNode=-1;
		LinkedList<Pipe> path = new LinkedList<>();
		for (Pipe pipe: pipes){
			if (current==pipe.from && pipe.to!=from){
				nextNode=pipe.to;
			}else if (current==pipe.to && pipe.from!=from){
				nextNode=pipe.from;
			}else{
				continue;
			}
			if (nextNode==to) {
				path.add(new Pipe(current,to,pipe.cost,pipe.original));
				return path;
			}else if (nextNode!=-1){
				path=findPath(current,nextNode,to,pipes);
				if (path.size()!=0) {
					path.add(new Pipe(current,nextNode,pipe.cost,pipe.original));
					return path;
				}
			}
		}
		return path;

	}
*/	
}


class Step{
	public int bld;
	public int pipe_index;
	Step(int b, int pi){
		this.bld=b;
		this.pipe_index=pi;
	}
}

class Pipe{
	public int from;
	public int to;
	public long cost;
	public boolean original;
	Pipe(int f, int t, long c, boolean o){
		this.from = f;
		this.to = t;
		this.cost = c;
		this.original = o;
	}
	@Override
	public String toString(){
		return Integer.toString(this.from) + '-' +Integer.toString(this.to) + '-'+ Long.toString(this.cost) + '-' + Boolean.toString(this.original);
	}
}

class PipeCmp_cost implements Comparator<Pipe> {
	public int compare(Pipe p1, Pipe p2){
		if (p1.cost!=p2.cost){
			return (int)(p1.cost-p2.cost);
		}else{
			if (p1.original && !p2.original) return -1;
			else if (!p1.original && p2.original) return 1;
			else return 0;
		}
	}
}