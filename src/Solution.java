import java.util.*;

/**
 * Created by Foutas on 2017/4/14.
 */
public class Solution {
//    public int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[j] = target - nums[i]) {
//                    return new int[] {i, j};
//                }
//            }
//        }
//        throw new IllegalArgumentException();
//    }

//    public int[] twoSum(int[] nums, int target) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i], i);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            int complement = target - nums[i];
//            if (map.containsKey(complement) && map.get(complement) != i) {
//                return new int[] {i, map.get(complement)};
//            }
//        }
//        throw new IllegalArgumentException();
//    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
        }
        throw new IllegalArgumentException();
    }

//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode dummyHead = new ListNode(0);
//        ListNode p = l1, q = l2, curr = dummyHead;
//        int carry = 0;
//        while (p != null && q != null) {
//            int x = (p != null) ? p.val : 0;
//            int y = (q != null) ? q.val : 0;
//            int sum = carry + x + y;
//            carry = sum / 10;
//            curr.next = new ListNode(sum % 10);
//            curr = curr.next;
//            if (p != null) p = p.next;
//            if (p != null) q = q.next;
//        }
//        if (carry > 0) {
//            curr.next = new ListNode(carry);
//        }
//        return dummyHead.next;
//    }

    public String reverseWords(String s) {
        char[] s1 = s.toCharArray();
        int i = 0;
        for(int j = 0; j < s1.length; j++) {
            if (s1[j] == ' ') {
                reverse(s1, i, j-1);
                i = j + 1;
            }
        }
        reverse(s1, i, s1.length-1);
        return new String(s1);
    }

    private void reverse(char[] s1, int i, int i1) {
        while (i < i1) {
            char temp = s1[i];
            s1[i] = s1[i1];
            s1[i1] = temp;
            i++;
            i1--;
        }
    }

    public double findMedianSortedArray(int[] nums1, int[] nums2) {
        int[] nums = null;
        double median;
        int i = 0, j = 0, count = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                nums[count] = nums2[j];
                j++;
                count++;
            } else if (nums1[i] < nums2[j]) {
                nums[count] = nums1[i];
                i++;
                count++;
            } else if (nums1[i] == nums2[j]) {
                nums[count] = nums1[i];
                i++;
                j++;
                count = count + 2;
            }
        }
        if (count % 2 == 0) {
            median =(double) nums[count/2];
        } else {
            median = (double) (nums[count/2 - 1] + nums[count/2])/2;
        }
        return median;
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        String res = null;
        boolean[][] dp = new boolean[n][n];
        for(int i = n - 1; i > 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i+1][j-1]);
                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder("");
        }
        int incre = 1;
        int index = 0;
        for (int i = 0; i < sb.length; i++) {
            sb[index].append(s.charAt(i));
            if (index == 0) {
                incre = 1;
            }
            if (index == numRows - 1) {
                incre = -1;
            }
            index += incre;
        }
        String re = "";
        for (int i = 0; i < sb.length; i++) {
            re += sb[i];
        }
        return re.toString();
    }

    public int myApoi(String str) {
        int[] a = null;
        char[] cc = str.toCharArray();
        for (int i = 0; i < cc.length; i++) {
            a[i] = (int)cc[i];
        }
        int result = 0;
        for (int i = 0; i < cc.length; i++) {
            int newresult = result + a[i];
            result = newresult * 100;
        }
        return result;
    }

    public boolean isPalindrome (int x) {
        int result = 0;
        int tail;
        while (x != 0) {
            tail = x % 10;
            int newResult = result + tail;
            result = newResult;
            x = x / 10;
        }
        if (x == result)
            return true;
        return true;
    }

    public int romanToInt(String s) {
        int nums[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M' :
                    nums[i] = 1000;
                    break;
                case 'D' :
                    nums[i] = 500;
                    break;
                case 'C' :
                    nums[i] = 100;
                    break;
                case 'L' :
                    nums[i] = 50;
                    break;
                case 'X' :
                    nums[i] = 10;
                    break;
                case 'V' :
                    nums[i] = 5;
                    break;
                case 'I' :
                    nums[i] = 1;
                    break;
            }
        }

        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (nums[i] < nums[i+1]) {
                sum -= nums[i];
            } else {
                sum += nums[i];
            }
        }
        return sum + nums[nums.length - 1];
    }

    public String longestCommonPrefix (String[] str) {
        if (str == null || str.length == 0)
            return  "";
        String pre = str[0];
        int i = 1;
        while (i < str.length) {
            //如果查找的字符串不是从第一位开始，就换
            while (str[i].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length()-1);
            }
            i++;
        }
        return pre;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i + 1])){
                int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == sum) {
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < sum) low++;
                    else  high--;
                }
            }
        }
        return res;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i + 1])) {
                int low = i + 1, high = nums.length - 1, min = target - nums[i];
                while (low < high) {
                    if (nums[low] + nums[high] == min) {
                        sum = target;
                    } else {
                        min = nums[low] + nums[high] - target;
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        if (Math.abs(nums[low] + nums[--high] -target) < Math.abs(min)) {
                            min = nums[low] + nums[high] - target;
                            sum = target + min;
                        } else {
                            high--;
                        }
                        low++;
                    }
                }
            }
        }
        return sum;
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        int[] keys = {2,3,4,5,6,7,8,9};
        String[] values = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i],values[i]);
        }
        char[] num = digits.toCharArray();
        for (int i = 0; i < num.length; i++) {
            if (!map.containsKey(num[i])) {
                return null;
            }else {
                char[] m = map.get(num[i]).toCharArray();

            }
        }
        return list;
    }

    public List<List<Integer>> fourSum(int[] num, int target) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if(num.length < 4) return ans;
        Arrays.sort(num);
        for (int i = 0; i < num.length - 3; i++) {
            if (i>0 && num[i] == num[i+1]) continue;
            for (int j = i + 1; j < num.length - 2; j++) {
                if (j > i + 1 && num[j] == num[j+1]) continue;
                int low = j+1, high = num.length - 1;
                while (low < high) {
                    int sum = num[i] + num[j] + num[low] + num[high];
                    if (sum == target) {
                        ans.add(Arrays.asList(num[i],num[j],num[low],num[high]));
                        while (low < high && num[low] == num[low+1]) low++;
                        while (low < high && num[high] == num[high - 1]) high--;
                        low++;
                        high--;
                    }
                    else if (sum < target) low++;
                    else high--;
                }
            }
        }
        return ans;
    }

    public ListNode removeNthFromEnd (ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        for (int i = 1; i < n+1; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return start.next;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            }
            else if (c == '{') {
                stack.push('}');
            }
            else if (c == '[') {
                stack.push(']');
            }
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public ListNode MergeTwoLists(ListNode l1, ListNode l2) {
        ListNode list = new ListNode(0);
        ListNode p = new ListNode(0);
        ListNode q = new ListNode(0);
        p.next = l1;
        q.next = l2;
        while (p.next != null && q.next != null) {
            if (p.next.val < q.next.val) {
                list.next = p.next;
                list = list.next;
                p = p.next;
            } else if (p.next.val > q.next.val) {
                list.next = q.next;
                list = list.next;
                q = q.next;
            } else {
                list.next = p.next;
                list = list.next;
                p = p.next;
                q = q.next;
            }
        }
       if(p.next == null && q.next != null) {
            list.next = q.next;
            list = list.next;
            q = q.next;
       } else if (p.next != null && q.next == null) {
            list.next = p.next;
            list = list.next;
            p = p.next;
       }
        return list;
    }

    public ListNode SwapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode n = head.next;
        head.next = SwapPairs(head.next.next);
        n.next = head;
        return n;
    }

    public int removeDuplicates(int[] nums) {
        int res = 0;
        for (int n : nums) {
            if (res == 0 || n >nums[res - 1]) {
                nums[res++] = n;
            }
        }
        return res;
    }

    public int removeElement (int[] nums, int val) {
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                count++;
            }
        }
        return count;
    }

    public int strStr(String haystack, String needle) {
        int l1 = haystack.length();
        int l2 = needle.length();
        if (l1 < l2) {
            return -1;
        } else if (l2 == 0) {
            return 0;
        }
        int k = l2 - l1;
        for (int i = 0; i < k; i++) {
            if (haystack.substring(i, i+l2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public void nextPermutation (int[] nums) {
        int n = nums.length;
        if(n < 2) {
            return;
        }
        int index = n - 1;
        while (index > 0) {
            if (nums[index - 1] < nums[index]) {
                break;
            }
            index--;
        }
        if (index == 0) {
            reverseSort(nums,0,n-1);
            return;
        }
        else {
            int val = nums[index - 1];
            int j = n - 1;
            while (j > index) {
                if(nums[j] > val)
                    break;
                j--;
            }
            swap(nums,j,index-1);
            reverseSort(nums, index, n-1);
            return;
        }

    }

    private void reverseSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        for (int i = 0; i < (start + end)/2; i++) {
            swap(nums,i,(start+end-i));
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = 0;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int findComplement (int num) {
        return ~num&(Integer.highestOneBit(num) - 1);
    }

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start+end)/2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[start])
                    end = mid - 1;
                else
                    start = mid + 1;
            }

            if(nums[end] >= nums[mid]) {
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return  -1;
    }

    public int[] serchRange(int[] A, int target) {
        int start = Solution.firstGreaterEqual(A, target);
        if (start == A.length || A[start] != target) {
            return new int[]{-1,-1};
        }
        return new int[] {start, Solution.firstGreaterEqual(A,target+1) - 1};
    }

    private static int firstGreaterEqual(int[] a, int target) {
        int low = 0, high = a.length;
        while (low < high) {
            int mid = low + ((high - low )>>1);
            if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int mid = 0;
        if (target < nums[start]) {
            mid = 0;
        }
        if (target > nums[end]) {
            mid = nums.length;
        }
        while (start < end) {
            mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return mid;
    }

    public String countAndSay (int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        for (int i = 0; i < n; i++) {
            prev = curr;
            curr = new StringBuilder();
            count = 1;
            say = prev.charAt(0);
            for (int j = 1, len = prev.length();j < len; j++) {
                if (prev.charAt(j) != say) {
                    curr.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                }
                else
                    count++;
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    private void getResult(List<List<Integer>> result, List<Integer> curr, int[] candidates, int target, int start) {
        if (target > 0) {
            for (int i = 0; i < candidates.length && target > candidates[i]; i++) {
                curr.add(candidates[i]);
                getResult(result, curr, candidates, target - candidates[i], i);
                curr.remove(curr.size() - 1);
            }
        }
        else if (target == 0) {
            result.add(new ArrayList<Integer>(curr));
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(candidates, 0, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int cur, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList(path));
            return;
        }

        if (target < 0) return;
        for (int i = cur; i < candidates.length; i++) {
            if (i > cur && candidates[i] < target) continue;;
            path.add(path.size(), candidates[i]);
            dfs(candidates, cur, target - candidates[i], path, res);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> templist, int[] nums) {
        if (templist.size() == nums.length) {
            list.add(new ArrayList<>(templist));
        }
        else {
            for (int i = 0; i < nums.length; i++) {
                if (templist.contains(nums[i])) continue;
                templist.add(nums[i]);
                backtrack(list, templist, nums);
                templist.remove(templist.size() - 1);
            }
        }
    }

    public void rotate(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][matrix.length - 1 -i];
                matrix[j][matrix.length - 1-i] = temp;
            }
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0 || strs == null) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Arrays.sort(strs);
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (map.containsKey(keyStr)) map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public double myPow(double x, int n) {
        double ans = 0;
        int k = n;
        for (int i = 0; i < n; i++) {
            double pow = x / k;
            ans = ans + pow;
            k = k - 1;
        }
        return ans;
    }

    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar,maxEndingHere);
        }
        return maxSoFar;
    }

    public List<Integer> spiralOrder (int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        if (matrix.length == 0) return list;
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int columnBegin = 0;
        int columnEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && columnBegin <= columnEnd) {
            for (int j = columnBegin; j < columnEnd; j++){
                list.add(matrix[rowBegin][j]);
            }
            rowBegin++;

            for (int j = rowBegin; j < rowEnd; j++) {
                list.add(matrix[j][columnEnd]);
            }
            columnEnd--;

            if (rowBegin <= rowEnd) {
                for (int j = columnEnd; j >= columnBegin; j--){
                    list.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;

            if (columnBegin <= columnEnd) {
                for (int j = rowEnd; j >= rowBegin; j--) {
                    list.add(matrix[j][columnBegin]);
                }
            }
            columnBegin++;
        }
        return list;
    }

    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > max) return false;
            max = Math.max(nums[i]+i, max);
        }
        return true;
    }

    public int lengthOfLastWord (String s) {
        s = s.trim();
        int lastIndex = s.lastIndexOf(" ") + 1;
        return s.length() - lastIndex;
    }
}
