import java.util.ArrayList;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.io.FileReader;
import java.io.File;
import java.util.Collections;
import java.util.Arrays;
import java.util.Collections;

/*value of N entered should be N+1.*/

class dfs{
	private static String fileName = "SCC.txt";
	private static Stack<Integer> stack;
	private static ArrayList<Integer> totals,vertices;
	private static int N = 875715;
	private static boolean[] explored = new boolean[N];
	
	private static void dataParser(Map<Integer, ArrayList<Integer>> data,Map<Integer, ArrayList<Integer>> reversed) throws IOException{
		
	File file = new File(fileName);
	BufferedReader br = new BufferedReader(new FileReader(file));

			String str = br.readLine();
		    while (str != null){
		    
			String[] arr = str.split(" ");
		    int tail = Integer.parseInt(arr[0]);
			int dest = Integer.parseInt(arr[1]);
			
			str = br.readLine();
			
			if(tail == dest)
				continue;
			
			if(!data.get(tail).contains(dest)) //prevents creating path same dest again
			data.get(tail).add(dest);
			
			if(!reversed.get(dest).contains(tail)) //prevents creating path same dest again
			reversed.get(dest).add(tail);
		    } // while
				
			br.close();
		
			//for(int i=1; i<10; i++)
			//System.out.println((i)+" "+data.get(i));
		}
		
	public static void main(String args[])throws IOException{
				Map<Integer, ArrayList<Integer>> data = new HashMap<Integer, ArrayList<Integer>>();
				Map<Integer, ArrayList<Integer>> reversed = new HashMap<Integer, ArrayList<Integer>>();
				
				stack = new Stack<Integer>();
				totals = new ArrayList<Integer>();
				vertices = new ArrayList<Integer>();
				
				for(int i=1; i<N; i++){
					data.put(i, new ArrayList<Integer>());
					reversed.put(i, new ArrayList<Integer>());
				}
				
				System.out.println("data parser called");
				dataParser(data,reversed);
				
				System.out.println("dfs routine 1 called");
				for(int i=1; i<N; i++)
				dfsRoutine(reversed,i);
				
				//System.out.println(stack);
				
				for(int i=1; i<N; i++)
				explored[i] = false;
			
				System.out.println("dfs routine 2 called");
			
				int siz = stack.size();
				for(int i=0; i<siz; i++){
					ArrayList<Integer> vertices = new ArrayList<Integer>();
					dfsRoutine2(data,stack.pop(),vertices);
						int k = vertices.size();
						if(k!=0)
						totals.add(k);
					}
			
				Collections.sort(totals);
				Collections.reverse(totals);
				for(int i=0; i<5; i++)
				System.out.print(totals.get(i)+" ");
	}
	
	private static void dfsRoutine(Map<Integer, ArrayList<Integer>> data,int node){
			
		if(!explored[node]){ // not explored before
		//System.out.println("exploring node number = "+node);
		explored[node] = true;
		
		/*use this if elements are not directly
		else exception occurs. however since they are added directly
		from 1 to N, this comparison can be removed.
		if(data.containsKey(node))*/
	
		for(int j: data.get(node))
			if(!explored[j])
				dfsRoutine(data,j);
		
		stack.push(node);
		
		}//if not explored before

	}//dfsRoutine
	
		
	private static void dfsRoutine2(Map<Integer, ArrayList<Integer>> data,int node,ArrayList<Integer> ver){
		
		if(!explored[node]){ // not explored before
		//System.out.println("exploring node number = "+node);
		explored[node] = true;
		
		for(int j: data.get(node))
			if(!explored[j])
				dfsRoutine2(data,j,ver);
		ver.add(node);		
		
		}//if not explored before

	}//dfsRoutine2
	
}