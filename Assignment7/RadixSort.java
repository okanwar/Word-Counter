/**
 * Name: RadixSort
 * 
 * @author Chris Eardensohn (ceardensohn@sandiego.edu)
 * @author Om Kanwar (okanwar@sandiego.edu)
 *
 * Date: 12/17/2017
 * 
 * Description: This file contains code to generate an 
 * array of one million random integers using Math.random. 
 * The array is sorted by using radix sort, a form of sort
 * by the digit's place. 
 */
public class RadixSort {
	
	//main function to generate one million integers and then sort it
	public static void main(String[] args) {
		int million = 1000000;
		int maxSize = Integer.MAX_VALUE;
		int[] arr = new int[million];
		for (int i = 0; i < million; i++){
			arr[i] = (int)(Math.random()*maxSize + 1);
		}
		
		radixSort(arr);
		
		//loop through array to check if any integers are out of order
		for(int j = 0; j < arr.length -1; j++) {
			//System.out.println(arr[j]);
			if (arr[j] > arr[j+1]) {
				System.out.println("Error at index: " + j);
			}
		}
	}
	
	//function to radix sort
	//first it finds the digit to sort starting with least significant digit.
	//each iteration increases its digit place by one.
	public static void radixSort(int[] array){
		for(int digit = 1; digit <= 1000000000; digit *=10) {
			sortByDigit(array, digit);
		}
	}
	//function to sort value in buckets based on its digit value 0-9
	public static void sortByDigit(int[] array, int digit){
		//tmp array to help sort, bucket array identify digit place
		int[] tmp = new int[array.length];
		int[] bucket = new int[10];
		
		//how many times 0-9 occurs
		for(int i = 0; i < array.length; i++){
			int digitValue = (array[i]/digit) % 10;
			bucket[digitValue] ++;
		}
		
		//swap to correct positions
		for(int i = 1; i < 10; i++) {
			bucket[i] += bucket[i-1];
		}
		//fill tmp array to sort
		for(int i = array.length - 1; i >=0; i--) {
			int digitValue = (array[i]/digit) % 10;
			tmp[bucket[digitValue] - 1] = array [i];
			bucket[digitValue]--;
		}
		//store back into main arr
		for(int i = 0; i < array.length; i++) {
			array[i] = tmp[i];
		}
	}
}
