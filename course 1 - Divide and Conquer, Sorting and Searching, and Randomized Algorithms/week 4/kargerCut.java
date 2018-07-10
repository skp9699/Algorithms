import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class kargerCut {
	
	/*to test any file, update N as the number of nodes and filename"*/
	
	private static ArrayList<Integer> left,right;
	private static int N=200,node=N;
	private static String fileName = "kargerMinCut.txt";
	
	private static int randomCall(Map<Integer, ArrayList<Integer>> dummy, ArrayList<Integer> vertices)throws IOException
	{
		dataParser(dummy,vertices);
		createEdges(dummy,vertices);
		node = N;
		
		Random ran = new Random();
		while(vertices.size() > 2){
			
		int randomNo = ran.nextInt(left.size());
		
		int v1 = left.get(randomNo);
		int v2 = right.get(randomNo);
			
		ArrayList<Integer> temp2 = dummy.get(v2);
		ArrayList<Integer> temp1 = dummy.get(v1);
	
		while(temp2.contains(v1))
			temp2.remove(temp2.indexOf(v1));
		
		while(temp1.contains(v2))
			temp1.remove(temp1.indexOf(v2));
		
		temp1.addAll(temp2);
	
		dummy.remove(v1);
		dummy.remove(v2);
		
		vertices.remove(vertices.indexOf(v1));
		vertices.remove(vertices.indexOf(v2));
		
		dummy.put(++node,temp1);
		
		vertices.add(node);
	
		/* removing self nodes creating a new node and updating it
		in the map*/
		
		for (int key: vertices){
		    
			ArrayList<Integer> value = dummy.get(key);
			int nos = value.size();
			
			for(int i=0; i<nos; i++){
				if(value.get(i) == key)
					value.remove(i);
				else if(value.get(i) == v1 || value.get(i) == v2)
					value.set(i,node);
			}
		
			dummy.remove(key);
			dummy.put(key,value);
		}
		
		createEdges(dummy,vertices);
		
		}
		return dummy.get(node).size();
	}
	
	
	private static void dataParser(Map<Integer, ArrayList<Integer>> dummy, ArrayList<Integer> vertices) throws IOException{
	
	left = new ArrayList<Integer>();
	right = new ArrayList<Integer>();
	vertices.clear();
	dummy.clear();
	
	File file = new File(fileName);
	BufferedReader br = new BufferedReader(new FileReader(file));
		
			String str = br.readLine();
			
		    while (str != null){
		    	String[] arr = str.split("\t");
		    
				int vertex = Integer.parseInt(arr[0]);
		    	vertices.add(vertex);
		    	
				ArrayList<Integer> temp = new ArrayList<Integer>();

				int len = arr.length;
				
		    	for(int i=1; i < arr.length; i++)
		    		if(Integer.parseInt(arr[i]) != vertex)
		    			temp.add(Integer.parseInt(arr[i]));
		    	
				dummy.put(vertex, temp);
		    	str = br.readLine();
				
		    } // while
			br.close();
		
	}
		
	private static void createEdges(Map<Integer, ArrayList<Integer>> dummy, ArrayList<Integer> vertices){
		left.clear();
		right.clear();
		
		for (Map.Entry<Integer, ArrayList<Integer>> entry : dummy.entrySet()){
		    int key = entry.getKey();
			ArrayList<Integer> value = entry.getValue();

			int nos = value.size();
			for(int i=0; i<nos; i++){
				if(value.get(i) != key){
					left.add(key);
					right.add(value.get(i));
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Map<Integer, ArrayList<Integer>> dummy = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		
		dataParser(dummy, vertices);
		
		int min = Integer.MAX_VALUE;
		int len = vertices.size();
		
		//for(int i=0; i<len; i++)
		//System.out.println(dummy.get(vertices.get(i)));
		
		/*Actual calls should be N*N-1/2.
		discussions say that N-1 calls usually gets the correct answer*/
		
		System.out.println("minimum from all cases");
		
		for(int i=0; i<N; i++){
			int t = randomCall(dummy,vertices);
			if(t<min)
				min = t;
			System.out.print(t+" ");
		}
		System.out.println();
		
		System.out.println("minimum cuts = " +min);
	} // main
		
} // MinCuts
