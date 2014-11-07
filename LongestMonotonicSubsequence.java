import java.util.*;

/**
 * Project made by:
 * Sunish Sheth(sss140830) and Kanav Kaul(kxk140730)
 */
public class LongestMonotonicSubsequence {

    public static void main(String args[]) throws Exception {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int a[] = new int[n + 1];
        int w[] = new int[n + 1];
        int result[] = new int[n + 1];
        int aux[] = new int[n + 1];
        result[0] = 0;
        a[0] = 0;
        w[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            a[i] = s.nextInt();
            w[i] = s.nextInt();
        }



        // PART A.
        for (int i = 1; i < n + 1; i++) {
            result[i] = w[i];
            for (int j = i - 1; j >= 0; j--) {
                if (a[i] > a[j] && result[i] < result[j] + w[i]) {
                    result[i] = result[j] + w[i];
                    aux[i] = j;
                }
            }
        }
        int max = 0;
        int index = 0;
        for (int i = 1; i < n + 1; i++) {
            //System.out.println(result[i]);
            if (result[i] > max) {
                max = result[i];
                index = i;
            }
        }
        System.out.println(max);
        int c = index;
        String print = "";
        while (index > 0) {
            print =  index+ " " + print;
            index = aux[index];
        }
        System.out.println(print);
        print = "";
        String print1 = "";
        while (c > 0) {
            print = a[c] + " " + print;
            c = aux[c];
        }
        System.out.println(print);


        // PART B.
        int res[][] = new int[n + 1][k + 1];
        int aux2[][] = new int[n + 1][k + 1];
        res[0][0] = 0;

        for (int k1 = 1; k1 < k + 1; k1++) {
            res[0][k1] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int k1 = 0; k1 < k + 1; k1++) {
                res[i][k1] = w[i];
                for (int j = i - 1; j >= 0; j--) {
                    if (a[i] > a[j] && res[i][k1] < res[j][k1] + w[i]) {
                        res[i][k1] = res[j][k1] + w[i];
                        aux2[i][k1] = j;
                    }
                    if (k1 > 0) {
                        if (a[j] >= a[i] && res[i][k1] < res[j][k1 - 1] + w[i] - (a[j] - a[i]) * w[i]) {
                            res[i][k1] = res[j][k1 - 1] + w[i] - (a[j] - a[i]) * w[i];
                            aux2[i][k1] = j;
                        }
                    }
                }
            }
        }

        max = 0;
        index = 0;
        int violate = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int k1 = 0; k1 < k + 1; k1++) {
                if (res[i][k1] > max) {
                    max = res[i][k1];
                    index = i;
                    violate = k1;
                }
            }
        }
        System.out.println(max);

        c = index;
        print = "";
        while (index > 0) {
            print = index + " " + print;
            index = aux2[index][violate];
        }
        System.out.println(print);
        print = "";
        while (c > 0) {
            print = a[c] + " " + print;
            c = aux2[c][violate];
        }
        System.out.println(print);

    }
}
