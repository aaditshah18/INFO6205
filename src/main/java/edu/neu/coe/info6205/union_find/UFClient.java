import java.util.Random;
import edu.neu.coe.info6205.union_find.UF_HWQUPC;

public class UFClient {

    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);

        int connections = 0;
        Random random = new Random();

        while (uf.components() > 1) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                connections++;
            }
        }

        return connections;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java UFClient <number_of_sites>");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);
        int connections = count(n);

        System.out.println("Number of connections generated: " + connections);
    }
}
