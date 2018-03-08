package dataStruckture.heap.problem_11286_절대값힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11286 {

	public static void main(String[] args) throws NumberFormatException, IOException{
		int data[] = new InputDevice().getInitialData();

		AbsoluteHeapFactory ahf = new AbsoluteHeapFactory(data.length);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<data.length; i++){
			if(data[i]==0){
				sb.append(ahf.popHeapData()+"\n");
			}else{
				ahf.addHeapData(data[i]);
			}
		}
		System.out.println(sb.toString());
	}
}


class AbsoluteHeapFactory{
	
	int[] heap;
	int remain;
	
	public AbsoluteHeapFactory(int leng){
		heap = new int[leng];
		remain = 0;
	}
	
	public void addHeapData(int data){
		heap[remain++] = data;
		int sIndex = remain - 1;//
		while(sIndex>0){
			int nIndex = (sIndex - 1) / 2;
			if(Math.abs(heap[nIndex])>Math.abs(heap[sIndex])){
				swap(nIndex,sIndex);
			}else if(Math.abs(heap[nIndex])==Math.abs(heap[sIndex])){
				if(heap[nIndex]>heap[sIndex]){
					swap(nIndex,sIndex);
				}else{
					break;
				}
			}else{
				break;	
			}
			sIndex = (sIndex - 1) / 2;
		}
	}
	
	public int popHeapData(){
		int result = heap[0];
		if(remain==0)
			return result;
		int lastIndex = remain - 1;
		heap[0] = heap[lastIndex];
		heap[lastIndex] = 0;//마지막 index는 비운다.
		remain--;
		int sIndex = 0;
		while(true){
			int nextIndex = (sIndex+1) * 2 - 1;
			if(nextIndex<remain){
				if(nextIndex+1<remain){
					if(Math.abs(heap[nextIndex])<Math.abs(heap[nextIndex+1])){
						if(Math.abs(heap[nextIndex])<Math.abs(heap[sIndex])){
							swap(nextIndex,sIndex);
							sIndex = nextIndex;
						}else if(Math.abs(heap[nextIndex])==Math.abs(heap[sIndex])){
							if(heap[nextIndex]<heap[sIndex]){
								swap(nextIndex,sIndex);
								sIndex = nextIndex;
							}else break;
						}else{
							break;
						}
					}else if(Math.abs(heap[nextIndex])==Math.abs(heap[nextIndex+1])){
						if(heap[nextIndex]<heap[nextIndex+1]){
							if(Math.abs(heap[nextIndex])<Math.abs(heap[sIndex])){
								swap(nextIndex,sIndex);
								sIndex = nextIndex;
							}else if(Math.abs(heap[nextIndex])==Math.abs(heap[sIndex])){
								if(heap[nextIndex]<heap[sIndex]){
									swap(nextIndex,sIndex);
									sIndex = nextIndex;
								}else{
									break;
								}
							}else{
								break;
							}
						}else{
							if(Math.abs(heap[nextIndex+1])<Math.abs(heap[sIndex])){
								swap(nextIndex+1,sIndex);
								sIndex = nextIndex+1;
							}else if(Math.abs(heap[nextIndex+1])==Math.abs(heap[sIndex])){
								if(heap[nextIndex+1]<heap[sIndex]){
									swap(nextIndex+1,sIndex);
									sIndex = nextIndex+1;
								}else{
									break;
								}
							}else{
								break;
							}
						}
					}else{
						if(Math.abs(heap[nextIndex+1])<Math.abs(heap[sIndex])){
							swap(nextIndex+1,sIndex);
							sIndex = nextIndex+1;
						}else if(Math.abs(heap[nextIndex+1])==Math.abs(heap[sIndex])){
							if(heap[nextIndex+1]<heap[sIndex]){
								swap(nextIndex+1,sIndex);
								sIndex = nextIndex+1;
							}else{
								break;
							}
						}else{
							break;
						}
					}
				}else{
					if(Math.abs(heap[nextIndex])<Math.abs(heap[sIndex])){
						swap(nextIndex,sIndex);
						sIndex = nextIndex;
					}else if(Math.abs(heap[nextIndex]) == Math.abs(heap[sIndex])){
						if(heap[nextIndex]<heap[sIndex]){
							swap(nextIndex,sIndex);
							sIndex = nextIndex;
						}else{
							break;
						}
					}else{
						break;
					}
				}
			}else{
				break;
			}
		}
		return result;
		
	}
	
	private void swap(int sIndex, int eIndex){
		int temp = heap[sIndex];
		heap[sIndex] = heap[eIndex];
		heap[eIndex] = temp;
	}
	
	
}

class InputDevice{
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public int[] getInitialData() throws NumberFormatException, IOException{
		int[] data = new int[Integer.valueOf(br.readLine())];
		
		for(int i=0; i<data.length; i++){
			data[i] = Integer.valueOf(br.readLine());
		}
		
		return data;
	}
}