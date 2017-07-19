import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> _ids = new ArrayList<Integer>();
        ArrayList<Integer> _parents = new ArrayList<Integer>();
        ArrayList<Integer> _costs = new ArrayList<Integer>();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while(line != null && !line.isEmpty()) {
            if(line.trim().equals("0")) break;
            String []values = line.trim().split(" ");
            if(values.length != 3) {
                break;
            }
            _ids.add(Integer.parseInt(values[0]));
            _parents.add(Integer.parseInt(values[1]));
            _costs.add(Integer.parseInt(values[2]));
            line = in.nextLine();
        }
        int res = resolve(_ids, _parents, _costs);

        System.out.println(String.valueOf(res));
    }

    // write your code here
    public static int resolve(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> costs) {
        int max = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            if (parents.get(i) == 0) {
                map1.put(ids.get(i), costs.get(i));
                max = Math.max(max, costs.get(i));
            }
        }
        for (int i = 0; i < ids.size(); i++) {
            if (ids.contains(parents.get(i))) {
                max = Math.max(max, map1.get(ids.get(parents.get(i))) + costs.get(i));
            }
        }
        return max;
    }

    public boolean isUniqueChars(String str) {
        if (str.length() > 256) return false;
        boolean[] char_set = new boolean[256];

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public boolean permutation(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] letters = new int[256];

        char[] s_array = s.toCharArray();
        for (char c : s_array) {
            letters[c]++;
        }

        for (int i = 0; i < t.length(); i++) {
            int c = (int)t.charAt(i);
            if (--letters[c] < 0) {
                return false;
            }
        }
        return true;
    }


}