/**
 * Created by kjh on 16. 11. 9.
 */
public class Dijkstra {

    private final int INF = Integer.MAX_VALUE;
    int[] distance;
    boolean[] visit;
    PriorityQueue priorityQueue;


    public Dijkstra(int[][] weight, int start){
        int len = weight.length;
        visit = new boolean[len];
        distance = new int[len];
        priorityQueue = new PriorityQueue();

        weight = init(weight, start);

        shortest_path_heap(weight);
        print(start);
    }

    private int[][] init(int[][] weight, int start) {
        for(int i=0 ; i<weight.length ; i++){
            for(int j=0 ; j<weight.length ; j++){
                if(i != j && weight[i][j] == 0)
                    weight[i][j] = INF;
            }
            if(i != start)
                distance[i] = INF;
            visit[i] = false;
            priorityQueue.arrayList.add(new Node(i, distance[i], -1));
            priorityQueue.build_min_heap();
        }
        return weight;
    }

    private void print(int start) {
        for(int i=0 ; i<distance.length ; i++){
            if(i != start)
                System.out.println(start + "->" + i + ", cost = " + distance[i]);
        }
    }

    private void shortest_path_heap(int[][] weight){
        int len = weight.length;
        while(!priorityQueue.isEmpty()){
            Node start = priorityQueue.extract_min();
            visit[start.index] = true;
            for(int i=0 ; i<len ; i++){
                if(weight[start.index][i] != INF && visit[i] == false){
                    priorityQueue.setNode(i, Math.min(distance[i], distance[start.index] + weight[start.index][i]), start.index);
                    distance[i] = Math.min(distance[i], distance[start.index] + weight[start.index][i]);
                }
            }
        }
    }


}
