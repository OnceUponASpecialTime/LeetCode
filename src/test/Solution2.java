package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Foutas on 2017/7/1.
 */
public class Solution2 {
    public int singleNumber (int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    public boolean isHappy (int n) {
        Set<Integer> num = new HashSet<>();
        int remain, sum;
        while (num.add(n)) {
            sum = 0;
            while (n > 0) {
                remain = n % 10;
                sum += remain * remain;
                n /= 10;
            }
            if (sum == 1) {
                return true;
            } else {
                n = sum;
            }
        }
        return false;
    }

    public int countPrimes (int n) {
        boolean[] notPrimes = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrimes[i] == false) {
                count++;
                for (int j = 2; i * j <n; j++) {
                    notPrimes[i * j] = true;
                }
            }
        }
        return count;
    }

    public boolean isIsomorphic (String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        if (s == null || s.length() <= 1) return true;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if (map.get(a).equals(b))
                    continue;
                else
                    return false;
            } else {
                if (!map.containsKey(b)) {
                    map.put(a, b);
                } else
                    return false;
            }
        }
        return false;
    }

    public String convertToTitle (int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n > 0) {
            n--;
            stringBuilder.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return stringBuilder.toString();
    }
    public int titleToNumber (String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result * 26 + (s.charAt(i) - 'A' + 1);
        }
        return result;
    }

    public int trailingZeroes (int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }

    public boolean isPowerOfTwo (int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }

    public int addDigits (int num) {
        return num == 0 ? 0 : (num % 9 == 0 ? 9 : (num % 9));
    }

    public int findNthDigit (int n) {
        int count = 9;
        int start = 1;
        int length = 1;

        while (n > length * count) {
            n -= length * count;
            length++;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) * length;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % length));
    }

    public String addStrings (String nums1, String nums2) {
        StringBuilder stringBuilder = new StringBuilder();
        int carry = 0;
        for (int i = nums1.length() - 1, j = nums2.length() - 1; i > 0 || j > 0 || carry == 1; i--, j--) {
            int x = i < 0 ? 0 : nums1.charAt(i) - '0';
            int y = i < 0 ? 0 : nums2.charAt(j) - '0';
            stringBuilder.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return stringBuilder.reverse().toString();
    }

    public int minMoves (int[] nums) {
        if (nums.length == 0) return 0;
        int min = nums[0];
        for (int n : nums) min = Math.min(min, n);
        int res = 0;
        for (int n : nums) res += n - min;
        return res;
    }
}
