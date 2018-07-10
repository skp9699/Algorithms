import java.io.*;
 
class inversions {  
	static int arrsize = 100000;
	static long count = 0L;
    public static void main(String args[])throws Exception{    
    
		File file = new File("IntegerArray.txt");   //replace here to test

		BufferedReader br = new BufferedReader(new FileReader(file));
			
		int i=0,arr[] = new int[arrsize];
		
		String st;
		while ((st = br.readLine()) != null)
		arr[i++] = Integer.parseInt(st);
	
		br.close();
	
		inversions inv = new inversions();
	
		inv.merge_sort(arr,0,99999);
	
		System.out.println(count);
	}//main
	
	private void merge(int arr[], int beg,int mid, int end){
		int i=beg, j=mid+1, index = beg, temp[] = new int[arrsize],pending = mid - i + 1;
		
		/*if elements present on both 
		the sides, depending on lower
		it switches*/
		
		while(i <= mid && j <= end){
				
				if(arr[i] < arr[j]){
					temp[index] = arr[i++];
					pending--;
				}
				
				else{
					temp[index] = arr[j++];
					count += (long)pending;
				}
				
				index++;
		}
		
		/*if the while loop breaks
		and the elements are still 
		to be merged, they ll be done
		here. Since the while loop 
		is broken, it means only one 
		part contains elements*/
		
		if(i>mid)
			while(j <= end)
				temp[index++] = arr[j++];
		
		else
			while(i <= mid)
				temp[index++] = arr[i++];
	
		for(int k=beg;k<index;k++)
			arr[k] = temp[k];
	}//merge
	
	void merge_sort(int arr[],int beg, int end){
		int mid;
		if(beg<end){
			mid = (beg+end)/2;
			merge_sort(arr,beg,mid);
			merge_sort(arr,mid+1,end);
			merge(arr,beg,mid,end);
		}
	}//merge_sort
	
}//class
