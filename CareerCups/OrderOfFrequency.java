/**
	Author	: Tom Choi
	Date	: 09/10/2016
	
	You are given an array of integers. With a given integer n,
	print out n most frequency elements in the array.
	
	Strategy
		1. Read the array and map (number, frequency) pairs			O(n)
		2. Create a heap and insert each pair based on frequency	O(nlogn)
		3. Remove top n items from the heap							O(n)
		Overall runtime = O(nlogn)
*/

import java.util.*;

public class OrderOfFrequency{
	public static void main(String[] args){
		int[] arr = {0, 0, 100, 3, 5, 4, 6, 4, 2, 100, 2, 100};
		int n = 2;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < arr.length; i++){
			if(map.containsKey(arr[i])){
				map.put(arr[i], map.get(arr[i])+1);
			}else{
				map.put(arr[i], 1);
			}
		}
		EntryHeap heap = new EntryHeap();
		for(int key : map.keySet()){
			Entry entry = new Entry(key, map.get(key));
			heap.insert(entry);
		}
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < n; i++){
			Entry removed = heap.remove();
			if(removed == null){
				break;
			}else{
				result.add(removed.data);
			}			
		}
		System.out.println(result);
	}
}

class EntryHeap{
	Entry[] heap;
	int DEFAULT_SIZE = 100;
	int size;
	
	EntryHeap(){
		heap  = new Entry[DEFAULT_SIZE];
		size = 0;
	}
	
	Entry remove(){
		if(size == 0){
			return null;
		}
		Entry removed = heap[0];
		swap(0, --size);
		heapDown();
		return removed;
	}
	
	void heapDown(){
		int parent = 0;
		while(true){
			int left = 2*parent+1;
			if(left >= size){
				break;
			}
			int max = left;
			int right = left+1;
			if(right < size){
				if(heap[right].freq > heap[left].freq){
					max = right;
				}
			}
			if(heap[parent].freq < heap[max].freq){
				swap(parent, max);
				parent = max;
			}else{
				break;
			}
		}
	}
	
	void insert(Entry e){
		if(size >= heap.length){
			ensureCapacity();
		}
		if(size == 0){
			heap[0] = e;
		}else{
			heap[size] = e;
		}
		heapUp();
		size++;
		
	}
	
	void heapUp(){
		int child = size;
		int parent = (child-1)/2;
		while(parent >= 0 && heap[child].freq > heap[parent].freq){
			swap(child, parent);
			child = parent;
			parent = (child-1)/2;
		}
	}
	
	/**
	* double the size of the heap
	*/
	void ensureCapacity(){
		Entry[] old = heap;
		heap = new Entry[old.length * 2 + 1];
		for(int i = 0; i < old.length; i++){
			heap[i] = old[i];
		}
	}
	
	/**
	* print the heap
	*/
	void print(){
		for(int i = 0; i < size; i++){
			System.out.print("[" + heap[i].data + " - " + heap[i].freq + "] ");
		}System.out.println();
	}
	
	/**
	*  swap two entries
	*/
	void swap(int i, int j){
		Entry temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
}

class Entry{
	int data;
	int freq;
	Entry(int data, int freq){
		this.data = data;
		this.freq = freq;
	}
}