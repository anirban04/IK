package Sorting;


public class App {
	
	public static void main(String[] args)	{
		//int [] arr = { 1, 2, 3, 4, 7, 8, 9, 10, 14, 16 };
		int [] arr = {8,4,6,5,7};
		System.out.println("Before:");
		printArr(arr);
		
		
		//insertionSort(arr);
		//selectionSort(arr);
		//arr = mergeSort(arr);
		//quickSort(arr);
		
	
		//System.out.println();
		//System.out.println("After:");
		//printArr(arr);
		
		
		HeapSort hs = new HeapSort(); 
		
		hs.sort(arr);
		System.out.println("\nAfter:");
		printArr(arr);
		
		
	}
	
	private static void printArr(int[] arr) {
		System.out.printf("[");
		for(int i=0; i<arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.printf("]");
	}
	
	private static void swapElements(int[] arr, int p1, int p2) {
		int temp;
		if(p1 == p2)
			return;
		temp = arr[p2];
		arr[p2] = arr[p1];
		arr[p1] = temp;
	}
	
	private static void insertionSort(int[] arr) {
		/* Iterate through the array starting from the first position */
		for (int pos=1; pos<arr.length; pos++) {
			int currIndex = pos;
			/* At every element, check if that element is lesser than the element just before it */
			while ((currIndex > 0) && (arr[currIndex] < arr[currIndex - 1])) {
				/* If it is,swap them */
				swapElements(arr, currIndex, currIndex - 1);
				/* Do this for every pair of elements till the start of the array */
				currIndex = currIndex - 1;
			}
		}
	}
	
	private static void selectionSort(int[] arr) {
		int minIndex;
		
		/* Iterate over the entire array */
		for(int i=0; i<arr.length; i++) {
			minIndex = i;
			/* Compare element at i against all elements after it */
			for(int j=i+1; j<arr.length; j++) {
				/* find the index of the smallest of the remaining elements */
				if(arr[i] > arr[j])
					minIndex = j;
			}
			/* Swap if smaller found */
			if (minIndex != i)
			swapElements(arr, i, minIndex);
		}
	}
	
	/* Recursive selectionSort */
	static void selectionSort(int[] arr, int index) {
		/* Base Case */
		if (index == arr.length - 1)
			return;
		
		int minIndex = index;
		/* Compare element at index against all elements after it */
		for (int j=index+1; j<arr.length; j++) {
			if (arr[index] > arr[j])
				/* find the index of the smallest of the remaining elements */
				minIndex = j;
		}
		/* Swap if smaller found */
		if (minIndex != index)
			swapElements(arr, minIndex, index);
		
		/* Call selection sort on remaining elements */
		selectionSort(arr, index+1);
	}
	private static int[] mergeSorted(int[] left, int[] right) {
		int[] res = new int[left.length + right.length];
		int leftIdx = 0;
		int rightIdx = 0;
		int resIdx = 0;
		/* Merge the sorted arrays */
		while ((leftIdx < left.length ) || (rightIdx < right.length)) {
			while ((leftIdx < left.length) && (rightIdx < right.length)) {
				if(left[leftIdx] <= right[rightIdx])
					res[resIdx++] = left[leftIdx++];
				else
					res[resIdx++] = right[rightIdx++]; 
			}
			if (leftIdx < left.length)
				res[resIdx++] = left[leftIdx++];
			if (rightIdx < right.length)
				res[resIdx++] = right[rightIdx++];
		}
		return res;
	}
	
	private static int[] mergeSort(int[] arr) {
		if(arr.length <= 1)
			return arr;

		int mid = arr.length/2;
		int[] left = new int[mid];
		int[] right = new int[arr.length - mid];
		/* Split relatively around the kid and copy elements into 2 arrays */
		for(int i=0; i<left.length; i++)
			left[i] = arr[i];
		for(int i=0; i<right.length; i++)
			right[i] = arr[mid+i];

		return (mergeSorted(mergeSort(left), mergeSort(right)));
	}
	
	private static void qs(int[] arr, int low, int high) {
		int i = low;
		int j = high;
		
		if ((arr == null ) || (arr.length == 0))
			return;
		
		if (low>=high)
			return;
		
		/* Compute the mid */
		int mid= low + (high - low) / 2;
		int pivot = arr[mid];

		/* We will be moving i and j towards each other.
		 * So check if i has reached j.
		 */
		while (i<=j) {
			/* Check if all elements at a lower
			 * index than mid are less than mid. 
			 */
			while (arr[i] < pivot)
				i++;
			/* Check if all elements at a higher
			 * index than mid are greater than mid. 
			 */
			while (pivot < arr[j])
				j--;
			
			/* Swap any element at a lower index than mid,
			 * but greater in value than mid, with any 
			 * element at a higher index than mid but less
			 * in value than mid.
			 */
			if (i<=j) {
				swapElements(arr, i, j);
				i++;
				j--;
			}
		}
		
		/* if j has not reached the start then call 
		 * qs recursively on that remaining part. 
		 */
		if (low < j)
			qs(arr, low, j);
		
		/* if i has not reached the end then call 
		 * qs recursively on that remaining part. 
		 */
		if(i < high)
			qs(arr, i, high);
	}
	
	private static void quickSort(int[] arr) {
		qs(arr, 0, arr.length -1);
	}
}

class HeapSort {
	
	private static int N;
	
	/* Method to sort */
	public void sort(int[] arr) {
		/* Heapify the array */
		buildHeap(arr);
		for (int i = N; i > 0; i--) {
			/* Swap the max element at index 0 with the last element and  */
			swap(arr, 0, N);
			N = N - 1;
			maxHeapify(arr, 0);
		}
	}
	
	/* Function to create the heap */
	private static void buildHeap(int[] arr) {
		/* We do this because we are considering array starting from index
		 * 1, which is of course not true. Therefore, we need to compensate 
		 * and this is where we do that. 
		 */
		N = arr.length - 1;
		
		/* Half of the elements in a complete tree are leaf nodes. 
		 * Therefore we heapify only the  remaining half.
		 */
		for (int i = N/2; i >= 0; i--)
			maxHeapify(arr, i);
	}
	
	/* Function to create a heap starting at i */
	private static void maxHeapify(int[] arr, int i) {
		/* Compute indices for the 2 children */
		int left = 2 * i;
		int right = 2 * i+ 1;
		/* Set max to i */
		int max =i;
		
		/* Find the max out of the parent and 2 children */
		if (left <= N && arr[left] > arr[max])
			max = left;
		if (right <= N && arr[right] > arr[max])
			max = right;
		/* Check if max is still i, if its changed, swap */
		if (max != i) {
			swap (arr, max, i);
			/* Since we have swapped, need to maxHeapify the changed index again */
			maxHeapify(arr, max);
		}
	}

	/* Helper function to swap elements of the array */
	private static void swap(int[] arr, int i1, int i2) {
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
}
