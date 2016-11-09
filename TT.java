/**
 * Created by kjh on 16. 11. 9.
 */
import java.io.*;
import java.util.*;

public class TT {

    private Scanner scan;
    int size;
    String start;
    String[] vertices;
    Node[] a;
    boolean[] visited;
    String[] prev;
    int[] dis;

    public TT() throws IOException{
        FileInputStream stream = new FileInputStream("D:/Users/KJH/workspace/DSP02/src/DSP02");
        InputStreamReader reader = new InputStreamReader(stream);
        StreamTokenizer token = new StreamTokenizer(reader);

        while((token.nextToken() != StreamTokenizer.TT_EOF)){

            switch(token.ttype){
                case StreamTokenizer.TT_NUMBER:
                    if(token.lineno() == 1){
                        size = (int)token.nval;
                        a = new Node[size];
                        visited = new boolean[size];
                        prev = new String[size];
                        dis = new int[size];
                        vertices = new String[size];
                    }
                    break;
                case StreamTokenizer.TT_WORD:
                    if(token.lineno() < size+2 && token.lineno() > 1){
                        vertices[token.lineno()-2] = token.sval;
                        a[token.lineno()-2] = new Node(index(token.sval), 0, null);
                    }
                    else{
                        String edge0 = token.sval;
                        token.nextToken();
                        String edge1 = token.sval;
                        token.nextToken();
                        int i = (int)token.nval;
                        add(edge0, edge1, i);
                    }
                    break;
            }
        }
        stream.close();

    }
    public void add(String v, String w, int distance){
        Node p1 = a[index(v)];
        Node p2 = a[index(w)];
        while(p1.next != null)
            p1 = p1.next;
        p1.next = new Node(index(w), distance, null);
        while(p2.next != null)
            p2 = p2.next;
        p2.next = new Node(index(v), distance, null);
    }

    public String toString(){
        if(size == 0) return "{";
        StringBuffer buf = new StringBuffer("{" + vertices[0]);
        for(int i=1 ; i<size ; i++)
            buf.append("," + vertices[i]);
        return buf + "}";
    }

    private int index(String v){
        for(int i=0 ; i<size ; i++)
            if(vertices[i].equals(v)) return i;
        return a.length;
    }

    public void Dijkstra(){
        int sIndex=0;
        int tempDis;
        for(int i=0 ; i<size ; i++){
            visited[i] = false;
            dis[i] = Integer.MAX_VALUE;
        }
        scan = new Scanner(System.in);
        System.out.print(vertices[0]);
        for(int i=1 ; i<size ; i++)
            System.out.print("," + vertices[i]);
        System.out.println(" ÃÑ "+ size + "°³ÀÇ Á€Á¡(Vertex)°¡ ÀÖœÀŽÏŽÙ. Ãâ¹ßÁ¡À» ÀÔ·ÂÇØÁÖŒŒ¿ä :");
        start = scan.nextLine();

        for(int i=0 ; i<size ; i++){
            if(start.equals(vertices[i])){
                dis[i] = 0;
                sIndex = i;
                visited[i] = true;
                break;
            }
        }
        int k=0;
        while(k!=size){
            for(Node p=a[sIndex].next ; p!=null ; p=p.next){
                tempDis = dis[sIndex] + p.distance;
                if(tempDis < dis[p.to]){
                    dis[p.to] = tempDis;
                    prev[p.to] = vertices[sIndex];
                }
                else
                    visited[p.to] = true;
            }
            Node tempNode = findShortestPath(sIndex);
            if(tempNode !=null)
                sIndex = tempNode.to;
            else
                break;
            visited[sIndex] = true;
            k++;
        }
        for(int i=0 ; i<size ; i++){
            if(dis[i] == 0)
                System.out.println(start + " : °Åž® " + dis[i] + " / " + "Ãâ¹ßÁ¡");
            else{
                System.out.print(vertices[i] + " : °Åž® " + dis[i] + " / ");
                printPath(vertices[i]);
                System.out.println();
            }
        }
    }

    public Node findShortestPath(int sIndex){
        int minimumDis = Integer.MAX_VALUE;
        Node nextNode = null;
        Node p = a[sIndex].next;
        while(p!=null){
            if(minimumDis>p.distance && visited[p.to] == false){
                minimumDis = p.distance;
                nextNode = p;
            }
            p=p.next;
        }
        if(nextNode != null)
            return nextNode;
        else
            return null;
    }

    public void printPath(String vertex){
        Stack<String> S = new Stack<String>();
        S.push(vertex);
        for(int i=index(vertex) ; !prev[i].equals(start) ; i=index(prev[i])){
            S.push(prev[i]);
        }
        System.out.print(start);
        while(!S.isEmpty())
            System.out.print(" -> " + S.pop());
    }

    public void GLDFS() {
        boolean visit[] = new boolean[size];
        Stack<String> S = new Stack<String>();
        String[] L = new String[size];
        int k = 0;
        for(int i=0 ; i<size ; i++)
            visit[i] = false;
        visit[0] = true;
        S.push(vertices[0]);
        while(!S.isEmpty()){
            L[k++] = S.peek();
            int j = index(S.pop());
            Node p = a[j];
            while(p.next != null){
                p = p.next;
                if(visit[p.to] == false){
                    visit[p.to] = true;
                    S.push(vertices[p.to]);
                }
            }
        }
        System.out.println("±íÀÌ ¿ìŒ± Åœ»öÀÇ °á°ú ÀÔŽÏŽÙ.");
        System.out.print("{" + L[0]);
        for(int i=1;i<size;i++){
            System.out.print(" -> " + L[i]);
        }
        System.out.println("}");
    }

    private class Node{
        int to;
        int distance;
        Node next;
        public Node(int to, int distance, Node next){
            this.to = to;
            this.distance = distance;
            this.next = next;
        }
    }
}

