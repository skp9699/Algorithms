import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Collections;
class sum2{
	
	static String fileName = "2sum.txt";
	static int leftBound = -10000;
	static int rightBound = 10000;
	
	public static void main(String args[])throws IOException{
	
	File file = new File(fileName);
	BufferedReader br = new BufferedReader(new FileReader(file));

	ArrayList<Long> arr = new ArrayList<Long>();
	
	String str = br.readLine();
	while(str != null){
		//if(!arr.contains(Long.parseLong(str)))
		arr.add(Long.parseLong(str));
		str = br.readLine();
		}	
	
	
	Collections.sort(arr);
		
	int siz = arr.size()-2;
	long temp = arr.get(siz+1);
	
	//removing duplicates
	for(int i = siz; i>= 0; i--){
		if(temp == arr.get(i))
			arr.remove(i);
		else
			temp = arr.get(i);
	}
	int left = 0, right = arr.size()-1, count = 0;
	
	ArrayList<Long> indices = new ArrayList<Long>();
	
	while(left<=right){
		//System.out.println(arr);
			temp = arr.get(left) + arr.get(right);
			if(temp < leftBound){
				left++;
				}
			else if(temp > rightBound)
				right--;
			else{
				
				for(int q = right; q > left; q--){
					temp = arr.get(q) + arr.get(left);
					if(temp >= leftBound && temp <= rightBound){
						if(!indices.contains(temp))
							indices.add(temp);
					}
					else
						break;
				}
				left++;
			}
		}
	
	System.out.println("nums = "+indices.size());
	
	}//main

}//class