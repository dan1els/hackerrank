package name.ryaboff;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.System.*;
import static java.nio.file.Paths.get;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {

    // Prefix Sum algorithm here.
    static long arrayManipulation(int n, int[][] queries) {
        int[] prefixSums = new int[n+2];

        for (int i = 0; i < queries.length; ++i) {
            prefixSums[queries[i][0]] += queries[i][2];
            prefixSums[queries[i][1] + 1] -= queries[i][2];
        }

        long maxValue = 0;
        long currValue = 0;
        for (int i = 0; i < prefixSums.length - 1; ++i) {
            currValue += prefixSums[i];
            if (maxValue < currValue)
                maxValue = currValue;
        }
        return maxValue;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Scanner scanner = new Scanner(get(getSystemResource("input").toURI()).toFile());
        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);
        out.println(result);

        scanner.close();
    }
}
