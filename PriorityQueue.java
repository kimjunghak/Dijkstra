import java.util.ArrayList;

/**
 * Created by KJH on 2016-11-10.
 */

public class PriorityQueue {

    ArrayList<Node> arrayList = new ArrayList<>();

    public void init(int[] distance, int index){
        arrayList.add(new Node(index, distance[index], -1));
        build_min_heap();
    }

    private void min_heapify(int index){
        int leftChildIndex = index*2+1;
        int rightChildIndex = index*2+2;
        int smallest = index;

        if(leftChildIndex <= arrayList.size()-1 && arrayList.get(leftChildIndex).distance < arrayList.get(index).distance)
            smallest = leftChildIndex;

        if(rightChildIndex <= arrayList.size()-1 && arrayList.get(rightChildIndex).distance < arrayList.get(index).distance)
            smallest = rightChildIndex;

        if(smallest != index) {
            swapNode(index, smallest);
            min_heapify(smallest);
        }
    }

    public void build_min_heap(){
        for(int i = (arrayList.size()-1)/2; i>=0 ; i--)
            min_heapify(i);
    }

    private void swapNode(int index, int smallest) {
        Node temp = arrayList.get(index);
        arrayList.set(index, arrayList.get(smallest));
        arrayList.set(smallest, temp);
    }

    public void setNode(int index, int distance, int prev){
        for(int i=0 ; i<arrayList.size() ; i++){
            if(arrayList.get(i).index == index){
                arrayList.set(i, new Node(index, distance, prev));
            }
        }
        build_min_heap();
    }

    public Node extract_min(){
        Node ret = arrayList.remove(0);
        build_min_heap();
        return ret;
    }

    public boolean isEmpty(){
        return arrayList.size() == 0;
    }
}