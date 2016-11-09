import java.util.Scanner;

/**
 * Created by kjh on 16. 11. 9.
 */
public class Test {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        /*int[][] weight = {
                {0, 7, 0, 0, 3, 10, 0},
                {7, 0, 4, 10, 2, 6, 0},
                {0, 4, 0, 2, 0, 0, 0},
                {0, 10, 2, 0, 11, 9, 4},
                {3, 2, 0, 11, 0, 0, 5},
                {10, 6, 0, 9, 0, 0, 0},
                {0, 0, 0, 4, 5, 0, 0}
        };*/

        int[][] weight;

        System.out.println("Enter the number of nodes : ");
        int node_num = scan.nextInt();
        weight = new int[node_num][node_num];

        scan.nextLine();

        int index = 0;
        while(index != node_num){
            if(index == 0)
                System.out.println("Enter the cost matrix : \n(ex>0,7,0,0)");
            String cost = scan.nextLine();
            String[] split = cost.split(",");
            if(split.length > node_num) {
                System.out.print("Node count differs from the input value.");
                System.exit(0);
            }
            else{
                for(int i=0 ; i<node_num ; i++)
                    weight[index][i] = Integer.parseInt(split[i]);
                index++;
            }
        }

        System.out.println("Enter the source matrix :");
        int start = scan.nextInt();

        new Dijkstra(weight, start);
    }
}
