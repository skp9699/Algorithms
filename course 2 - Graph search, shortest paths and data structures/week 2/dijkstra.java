import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class dijkstra{

/*N entered should be +1 from the original elements
to test on other cases, one must check whether the starting
node is 0 or 1. This is implemented as per 1. if incase 
0 start is to be tested for loops indexing must be changed*/

private static String fileName = "dijkstraData.txt";
	private static ArrayList<Integer> totals,vertices;
	private static int N = 201;
	
	private static void dataParser(Map<Integer, ArrayList<Integer>> node, Map<Integer, ArrayList<Integer>> weight) throws IOException{
		
	File file = new File(fileName);
	BufferedReader br = new BufferedReader(new FileReader(file));

			String str = br.readLine();
		    while (str != null){
		    
			String[] arr = str.split("\t");
		    int vertex = Integer.parseInt(arr[0]);
			int len = arr.length;
			
			for(int i = 1; i<len; i++){
				String arr1[] = arr[i].split(",");
				node.get(vertex).add(Integer.parseInt(arr1[0]));
				weight.get(vertex).add(Integer.parseInt(arr1[1]));
			}
			
			str = br.readLine();
			
			} // while
				
			br.close();
		}
		
	public static void main(String args[])throws IOException{
		Map<Integer, ArrayList<Integer>> node = new HashMap<Integer, ArrayList<Integer>>();
		Map<Integer, ArrayList<Integer>> weight = new HashMap<Integer, ArrayList<Integer>>();
		
		for(int i=1; i<N; i++){
			node.put(i, new ArrayList<Integer>());
			weight.put(i, new ArrayList<Integer>());
		}
		
		dataParser(node, weight);
		
		dijkstra(node,weight);
	}
	
	private static void dijkstra(Map<Integer, ArrayList<Integer>> node, Map<Integer, ArrayList<Integer>> weight){
		int arr[] = new int[N];
		Arrays.fill(arr, Integer.MAX_VALUE);
		
		/*the index in the following element indicates the starting node*/
		arr[1] = 0;
		
		
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		for(int i=1; i<N; i++)
			vertices.add(i);
		
		int count = 0;
		while(++count < N){
			int i = findMin(arr,vertices);
			//System.out.println("minimum = "+i);
			vertices.remove(vertices.indexOf(i));
			
			int siz = node.get(i).size();
			
			for(int j=0; j<siz; j++){
				int temp = arr[i] + weight.get(i).get(j);
				if(temp < arr[node.get(i).get(j)])
					arr[node.get(i).get(j)] = temp;
			}
		}//from 1 to 200
	
		System.out.println(arr[7]+","+arr[37]+","+arr[59]+","+arr[82]+","+arr[99]+","+arr[115]+","+arr[133]+","+arr[165]+","+arr[188]+","+arr[197]);	
	
	}
	
	/*the function returns the element which has minimum value at 
	that instance. */
	
	private static int findMin(int arr[], ArrayList<Integer> vertices){
		int min = Integer.MAX_VALUE;
		int len = vertices.size();
		int v = 0;
		
		for(int i=0; i<len; i++)
			if(min > arr[vertices.get(i)]){
				min = arr[vertices.get(i)];
				v = vertices.get(i);
			}
			
		return v;
	}
		
}