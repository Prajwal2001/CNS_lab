import java.util.Scanner;

public class BellmanFord {
    private int dist[], nov;
    public static final int MAX_VALUE = 999;

    public void evaluation(int numofv, int src, int dest, int adm[][]) {
        nov = numofv;
        dist = new int[nov + 1];
        for (int node = 1; node <= nov; node++)
            dist[node] = MAX_VALUE;
        dist[src] = 0;
        for (int node = 1; node <= nov - 1; node++)
            for (int snode = 1; snode <= nov; snode++)
                for (int dnode = 1; dnode <= nov; dnode++)
                    if (adm[snode][dnode] != MAX_VALUE)
                        if (dist[dnode] > dist[snode] + adm[snode][dnode])
                            dist[dnode] = dist[snode] + adm[snode][dnode];
        System.out.println("Distance of source " + src + " to " + dest + " is " + dist[dest]);
    }

    public static void main(String[] args) {
        int nov = 0, src, dest;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        nov = in.nextInt();
        int adm[][] = new int[nov + 1][nov + 1];
        System.out.println("Enter the adjacency matrix");
        for (int snode = 1; snode <= nov; snode++)
            for (int dnode = 1; dnode <= nov; dnode++)
                adm[snode][dnode] = in.nextInt();
        System.out.println("Enter the source vertex");
        src = in.nextInt();
        System.out.println("Enter the destination vertex: ");
        dest = in.nextInt();
        new BellmanFord().evaluation(nov, src, dest, adm);
        in.close();
    }
}
