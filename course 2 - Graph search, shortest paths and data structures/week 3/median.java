import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;

class median{
	
	/*N should be 1 more*/
	
	static String fileName = "Median.txt";
	static ArrayList<Integer> less,more;
	static int lefSiz = 1,rigSiz = 1, N = 10001;

	public static void main(String args[])throws IOException{
	less = new ArrayList<Integer>();
	more = new ArrayList<Integer>();
	
	File file = new File(fileName);
	BufferedReader br = new BufferedReader(new FileReader(file));

	int[] arr = new int[N];
	
	/*first two elements are directly added so as to 
	make the further loop able to compare it to something*/
	
	less.add(Integer.parseInt(br.readLine()));
	arr[1] = less.get(0);
	
	int temp = Integer.parseInt(br.readLine());
	if(temp > less.get(0))
		more.add(temp);
	else{
		more.add(less.get(0));
		less.set(0,temp);
	}
	arr[2] = less.get(0);
	for(int i=3; i<N; i++){
		int num = Integer.parseInt(br.readLine());
		if(num < less.get(lefSiz-1)){
			addIn(less,num);
			lefSiz++;
		}
		else if(num > more.get(0)){
			addIn(more,num);
			rigSiz++;
		}
		else{
			less.add(num);
			lefSiz++;
		}
		balance();
		arr[i] = less.get(lefSiz-1);
	}
	
	Long sum = 0L;
	for(int i=1; i<N; i++)
		sum += arr[i];
	System.out.println(sum % 10000);
	
	}//main
	
	private static void addIn(ArrayList<Integer> tem, int number){
		int siz = tem.size()-1;
		if(number < tem.get(0))
			tem.add(0,number);
		else{
			int l = 0, r=siz, m = (l+r)/2;
			binSea(tem, l, r, m, number);
		}
	}
	
	private static void binSea(ArrayList<Integer> tem,int l, int r, int m, int number){
		if(r - l < 4){
			for(int j=r; j>=0; j--){
				if(number > tem.get(j)){
				tem.add(j+1, number);
				break;}
				}
		}
		else{
			if(number < tem.get(m))
				binSea(tem, l, m, (l+m)/2, number);
			else if(number > tem.get(m))
				binSea(tem, m, r, (m+r)/2, number);
			else if(number == tem.get(m))
				tem.add(m, number);
		}
	}
	
	private static void balance(){
	if( lefSiz != rigSiz){
		if(lefSiz < rigSiz){
			less.add(more.get(0));
			more.remove(0);
			lefSiz++;
			rigSiz--;
		}
		
		/*the condition in the following loop is to maintain consistency.
		if there are odd number of elements , i would like to keep it in less
		array. It can be done either ways, however if that condition is not 
		put, it may transfer one number to the right array */
		
	else if(lefSiz - rigSiz != 1){
			more.add(0,less.get(--lefSiz));
			less.remove(lefSiz);
			rigSiz++;
		}
	}	
	}//balance
	
	
}//class