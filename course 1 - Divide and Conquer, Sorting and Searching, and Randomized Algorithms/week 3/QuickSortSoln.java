/*commenting needed to be verified from line 38 - 44
as per question*/

import java.io.*;

class QuickSortSoln
{
	static long comparisons = 0L;
    static int[] arr;
	
    public static void main(String args[])throws IOException
    {
        //File file = new File("dummy.txt");    
		//int i=0 ,n=6;
		
		File file = new File("QuickSort.txt");
		int i=0 ,n=10000;
		
		BufferedReader br = new BufferedReader(new FileReader(file));
				
		arr = new int[n];
		
		String st;
		while ((st = br.readLine()) != null)
		arr[i++] = Integer.parseInt(st);
	
		br.close();
 
		//System.out.println("Original Array");
        //printArray();
 
        quicksort(0, n-1);
 
       	System.out.println("comparisons ="+comparisons);
    }
	
	private static void quicksort(int left, int right){
		if(left < right){
		comparisons += (long)(right-left);
		
		/*following two lines used only for Q3*/
		int mid = (left + right)/2;
		mid = findMid(left,mid,right);
	
		swap(left,mid); // used this for Q3
		//swap(left,right); // swap this for Q2
		
		int piv = arr[left];
	
		int loc = left + 1;
		for(int i=left + 1; i<=right; i++){
		if(piv > arr[i])
				swap(loc++,i);
		}//for
		
		//here loc will give the next highest value in the array
		//compared to pivot, hence swapping will be one less and the piv
		//else pivot wont be in correct position
		
		swap(--loc,left);
	
		quicksort(left,loc-1);
		quicksort(loc+1,right);
		}
		
		}
		
	private static int findMid(int left, int mid, int right){
		if (arr[left] > arr[mid]) {
			if (arr[mid] > arr[right]) {
				//do nothing
			}
			else if (arr[left] > arr[right])
				mid = right;
			else 
				mid = left;
		} 
		
		else {
		if (arr[left] > arr[right]) 
			mid = left;
		else if (arr[mid] > arr[right])
			mid = right;
		else{//do nothing. mid is at correct pos
			}
		}
	return mid;
	}

	
	private static void printArray(){
		int len = arr.length;
		for(int i=0; i<len; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
	}
	
	private static void swap(int pos1, int pos2){
		int temp = arr[pos1];
		arr[pos1] = arr[pos2];
		arr[pos2] = temp;
	}
	
	
}