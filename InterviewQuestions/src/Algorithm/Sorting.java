package Algorithm;

public class Sorting {

	public void insertionSort(int[] array){
		if (array == null || array.length < 2) return;
		for(int i = 1; i < array.length; i++){
			int k = i;
			for(int j = k-1; j >= 0 ; j--){
				if(array[k] <= array[j]){
					int temp = array[k];
					array[k] = array[j];
					array[j] = temp;
					k = j;
				}
				else{
					break;
				}
			}
		}
	}

	public void selectionSort(int[] array){
		if(array == null || array.length <= 1) return;
		for(int i = 0; i < array.length; i++){
			int minIndex = i;
			for(int j = i+1; j < array.length; j++){
				if(array[j] < array[minIndex]){
					minIndex = j;
				}
			}

			int temp = array[i];
			array[i] = array[minIndex];
			array[minIndex] = temp;
		}
	}


	/*
	pseudo-code for mergeSort
	input: array, startIndex, endIndex
	1. break the array into two
	2. recursively sort the left and right subarray
	3. merge the left and right subarray
	 */
	public int[] mergeSort(int[] array){
		if(array == null || array.length <= 1) return array;
		return mergeSort(array, 0, array.length - 1);
	}

	private int[] mergeSort(int[] array, int start, int end){
		if(start > end) return array;
		if(array == null || array.length <= 1) return array;

		if(start == end){ //only one element
			int[] result = new int[1];
			result[0] = array[start];
			return result;
		}

		//more than one element
		int mid = (start+end)/2;
		int[] left = mergeSort(array, start, mid);
		int[] right = mergeSort(array, mid+1, end);

		//merge
		int lp = 0, rp = 0;
		int[] result = new int[left.length + right.length];
		while(lp < left.length && rp < right.length){
			if(left[lp] < right[rp]){
				result[lp+rp] = left[lp];
				lp++;
				if(lp >= left.length){
					while(rp < right.length){
						result[lp+rp] = right[rp];
						rp++;
					}
				}
			}
			else{
				result[lp+rp] = right[rp];
				rp++;
				if(rp >= right.length){
					while(lp < left.length){
						result[lp+rp] = left[lp];
						lp++;
					}
				}
			}
		}

		return result;
	}

	public void quickSort(int[] array){
		if(array == null || array.length <= 1) return;
		quickSort(array, 0, array.length-1);
	}

	private void quickSort(int[] array, int start, int end){
		if(array == null || array.length <= 1) return;
		if(start >= end) return;

		int pivot = partition(array, start, end);
		quickSort(array, start, pivot);
		quickSort(array, pivot + 1, end);
	}

	private int partition(int[] array, int start, int end){
		if(array == null || array.length <= 1) return 0;

		//base case
		if(start == end) return start;

		int pivot = (start + end)/2;
		int temp = 0;
		if(pivot != end){
			temp = array[pivot];
			array[pivot] = array[end];
			array[end] = temp;
			pivot = end;
		}
		
		int ptr = start;
		for(int i = start; i < end; i++){
			if(array[i] < array[pivot]){
				if(i != ptr){
					temp = array[i];
					array[i] = array[ptr];
					array[ptr] = temp;
				}
				ptr++;
			}
		}

		temp = array[pivot];
		array[pivot] = array[ptr];
		array[ptr] = temp;
		return ptr;
	}

	public static void main(String[] args){
		Sorting s = new Sorting();
		System.out.println("Insertion sort:");
		int[] array = {6, 5, 4, 3, 2, 1};
		System.out.println("Original array:");
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println("\nSorted array:");
		s.insertionSort(array);
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println();

		System.out.println("Selection sort:");
		int count = 10;
		array = new int[count];
		for(int i = 0; i < count; i++){
			array[i] = count - i;
		}
		System.out.println("Original array:");
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println("\nSorted array:");
		s.insertionSort(array);
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println();

		System.out.println("Merge sort:");
		count = 10;
		array = new int[count];
		array[0] = 5;
		array[1] = 2;
		array[2] = 15;
		array[3] = 32;
		array[4] = 56;
		array[5] = 232;
		array[6] = 85;
		array[7] = 9;
		array[8] = 92;
		array[9] = 534;
		System.out.println("Original array:");
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println("\nSorted array:");
		array = s.mergeSort(array);
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println();		

		System.out.println("Quick sort:");
		count = 10;
		array = new int[count];
		array[0] = 5;
		array[1] = 2;
		array[2] = 15;
		array[3] = 32;
		array[4] = 56;
		array[5] = 232;
		array[6] = 85;
		array[7] = 9;
		array[8] = 92;
		array[9] = 13;
		System.out.println("Original array:");
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println("\nSorted array:");
		s.quickSort(array);
		for(int i : array){
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println();
	}
}
