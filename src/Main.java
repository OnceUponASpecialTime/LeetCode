import java.util.*;

/**
 * Created by Foutas on 2017/4/11.
 */
public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Main m = new Main();
//        while (sc.hasNextInt()) {
//         //   int n = sc.nextInt();
//            int n = 15;
//            System.out.println(m.Fenjie(n));
//        }
//    }
//public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    String line1 = sc.nextLine();
//    String line2 = sc.nextLine();
//    if (line1 != null && !line1.isEmpty() && line2 != null && !line2.isEmpty()) {
//        int res = Match(line1.trim(), line2.trim());
//        System.out.printf(String.valueOf(res));
//    }
//}
//
//    private static int Match(String s1, String s2) {
//        int res = 0;
//        char[] c1 = s1.toCharArray();
//        char[] c2 = s2.toCharArray();
//
//        return 0;
//    }
//
//    private int Fenjie(int n) {
//        int m = 1;
//        while (n >= 0 && n <= 50) {
//            if (n == 0 || n == 1 || n == 2 || n == 3|| n == 4) {
//                m = n;
//            }  else {
//                while ((n - 3) >= 4) {
//                    n = n - 3;
//                    m = m*3;
//                }
//                if (n == 6) {
//                    m = m*3*3;
//                } else if(n == 5) {
//                    m = m*3*2;
//                } else if(n == 4) {
//                    m = m*2*2;
//                }
//            }
//        }
//        return m;
//    }
//
//    public static int resolve(String expr) {
//        String s = expr.split(" ").toString();
//        int res = 0;
//        Stack<Character> stack = new Stack<Character>();
//        if (stack.isEmpty()){
//            res = -1;
//        }
//        if (stack.size() > 16) {
//            res = -2;
//        }
//        for(char c : s.toCharArray()) {
//            if (c == '+') {
//                char x = stack.pop();
//                char y = stack.pop();
//                res = x + y;
//                stack.push((char)res);
//            }
//            else if (c == '*') {
//                char x = stack.pop();
//                char y = stack.pop();
//                res = x * y;
//                stack.push((char)res);
//            }
//            else if (c == '^') {
//                char x = stack.pop();
//                res = x * x + 1;
//                stack.push((char)res);
//            }
//            else {
//                stack.push(c);
//            }
//        }
//        return res;
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Main m = new Main();
//        int n = sc.nextInt();
//        int[] a = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = sc.nextInt();
//        }
//        System.out.println(m.Price(a));
//    }

    private int Price(int[] a) {
        int ans = 0;
        Arrays.sort(a);
        int[] b = null;
        if (a.length < 3) {
            ans = -1;
        } else {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] != a[i + 1]) {
                    b[i] = a[i];
                }
                else
                    b[i] = a[i + 1];
            }
            if (b.length < 3) {
                ans = -1;
            } else {
                ans = b[2];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(m.Inlaw(n, k));
        }
    }

    private int Inlaw(int n, int k) {
        int res = 0;

        return 0;
    }
}
