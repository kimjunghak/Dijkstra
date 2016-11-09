
/**
 * Created by kjh on 16. 11. 9.
 */
public class Dijkstra {

    private final int INF = Integer.MAX_VALUE;
    int[] distance;
    boolean[] visit;

    public Dijkstra(int[][] weight, int start){
        distance = new int[weight.length];
        visit = new boolean[weight.length];

        weight = init(weight, start);

        shortest_path(weight, start);
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
        }
        return weight;
    }

    private void print(int start) {
        for(int i=0 ; i<distance.length ; i++){
            if(i != start)
                System.out.println(start + "->" + i + ", cost = " + distance[i] + "\n");
        }
    }

    private void shortest_path(int[][] weight, int start){
        int len = weight.length;

        for(int i=0 ; i<len ; i++){
        }
    }
}
