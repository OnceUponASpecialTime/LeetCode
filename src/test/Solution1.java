package test;

import java.util.*;

/**
 * Created by Foutas on 2017/5/6.
 */
public class Solution1 {
    public int uniquePath(int m, int n) {
        Integer[][] map = new Integer[m][n];
        for (int i = 0; i < m; i++) {
            map[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            map[0][j] = 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; i < n; j++) {
                if (i == 0 && j != 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0 && j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if (i == 0 && j == 0) {
                    grid[i][j] = grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
                }
            }
        }
        return grid[m-1][n-1];
    }

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                break;
            } else {
                digits[0] = 0;
            }
        }
        if (digits[0] == 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
        return digits;
    }

    public String addBinary (String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public int sqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int left = 1, right = Integer.MAX_VALUE;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/mid) {
                    return mid;
                }
                left = mid + 1;
            }
        }
    }

    public int climbStairs(int n) {
        if (n == 0 || n == 1 || n == 2) return n;
        int mem[] = new int[n];
        mem[0] = 1;
        mem[1] = 2;
        for (int i = 2; i < n; i++) {
            mem[i] = mem[i - 1] + mem[i - 2];
        }
        return mem[n - 1];
    }

    public String simplifyPath (String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".","/"));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        for (String dir : stack) res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }

    public void setZeros (int[][] matrix) {
        boolean fr = false, fc = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) fr = true;
                    if (j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (fr) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (fc) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public void sortColors(int A[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; i++) {
            if (A[i] == 0) {
                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
            } else if (A[i] == 1) {
                A[++n2] = 2; A[++n1] = 1;
            } else if (A[i] == 2) {
                A[++n2] = 2;
            }
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i+1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i > -1 && j > -1) nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        while (j > -1) nums1[k--] = nums2[j--];
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode (int x) { val = x ;}
    }

    public ListNode deleteDuplicates (ListNode head) {
        if (head == null || head.next == null)
            return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public int findUnsortedSubarray (int[] nums) {
        int n = nums.length;
        int[] temp = new int[n];

        int start = 0;
        while (start < n && nums[start] == temp[start]) start++;

        int end = 0;
        while (end > start && nums[end] == temp[end]) end--;

        return end - start + 1;

    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (r * c != m * n) return nums;
        int[][] res = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            res[i/c][i%c] = nums[i/m][i%m];
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return isSame(s.left, t) || isSame(s.right, t);
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Set<Integer> starters = new HashSet<Integer>();
        Set<Integer> uniqs = new HashSet<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (uniqs.contains(nums[i] - k))
                starters.add(nums[i] - k);
            if (uniqs.contains(nums[i] + k))
                starters.add(nums[i] + k);
            uniqs.add(nums[i]);
        }
        return starters.size();
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, maxHere = 0;
        for (int n : nums)
            max = Math.max(max, maxHere = n == 0 ? 0 : maxHere + 1);
        return max;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int m = 0, n = 0;
        Arrays.sort(nums);
        if (nums.length == 0) list = null;
        while (m < nums.length - 1) {
            if (nums[m] == nums[m + 1] || nums[m] == nums[m + 1] - 1) {
                m++;
            } else {
                n = nums[m + 1] - nums[m];
                for (int i = 1; i < n; i++) {
                    list.add(nums[m] + i);
                }
            }

        }
        return list;
    }

    public int thridMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.contains(i)) {
                pq.offer(i);
                set.add(i);
                if (pq.size() > 3) {
                    set.remove(pq.poll());
                }
            }
        }
        if (pq.size() < 3) {
            while (pq.size() > 1) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public void moveZeroes (int[] nums) {
        if (nums.length == 0 || nums == null) return;
        int postnum = 0;
        for (int num : nums) {
            if (num != 0) nums[postnum] = num;
        }
        while (postnum < nums.length) {
            nums[postnum++] = 0;
        }
    }

    public boolean containsDuplicate (int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        if (set.size() == nums.length) return true;
        else return false;
    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reserve(nums, 0, nums.length-1);
        reserve(nums, 0, k-1);
        reserve(nums, k, nums.length - 1);
    }

    private void reserve(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end++;
        }
    }

    public int majorityElement (int[] nums) {
        int major = nums[0], count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else
                count--;
        }
        return major;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > inorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart,inIndex-1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }

    public int minimumTotal (List<List<Integer>> triangle) {
        int[] A = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i > 0; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                A[j] = Math.min(A[j], A[j-1]) + triangle.get(i).get(j);
            }
        }
        return A[0];
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;

        if (headA == null || headB == null) return null;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return  a;
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode nextNode = head.next;
        ListNode nextHead = reverseList(nextNode);
        nextHead.next = head;
        head.next = null;
        return nextHead;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val) {
            return isSameTree(p.left,q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public List<List<Integer>> levelOrderBottom (TreeNode root) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        levelMaker(list, root, 0);
        return list;
    }

    private void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (level > list.size()) {
            list.add(0, new LinkedList<Integer>());
        }
        levelMaker(list, root.left, level+1);
        levelMaker(list, root.right, level+1);
        list.get(list.size()-level-1).add(root.val);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        TreeNode root = getBST(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode getBST(int[] nums, int low, int high) {
        if (low > high)
            return null;
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = getBST(nums, low, mid-1);
        node.right = getBST(nums, mid + 1, high);
        return node;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return height(root) != -1;
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lH = height(node.left);
        if (lH == -1) {
            return -1;
        }
        int rH = height(node.right);
        if (rH == -1) {
            return -1;
        }
        if ((lH - rH) < -1 || (lH - rH) > 1) {
            return -1;
        }
        return Math.max(lH, rH) + 1;
    }

    public int sumOfLeftLeaves (TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                ans += root.left.val;
            }
            else {
                ans += sumOfLeftLeaves(root.left);
            }
        }
        ans += sumOfLeftLeaves(root.right);
        return ans;
    }

    public String countAndSay(int n) {
        if (n == 1)
            return "1";
        String prev = countAndSay(n-1);

        StringBuilder sb = new StringBuilder();
        int count = 1;
        char prevNum = prev.charAt(0);
        for (int i = 0; i < prev.length(); i++) {
            char currNum = prev.charAt(i);
            if (currNum == prevNum) {
                ++count;
            }
            else {
                sb.append(count);
                sb.append(prevNum);
                count = 1;
            }
            prevNum = currNum;
        }
        sb.append(count);
        sb.append(prevNum);

        return sb.toString();
    }

    public String reverseString (String s) {
        char[] c = s.trim().toCharArray();
        int i = 0;
        int j = c.length - 1;
        while (i < j) {
            char temp = c[j];
            c[i] = temp;
            c[j] = c[i];
            i++;
            j--;
        }
        return c.toString();
    }

    public String reverseVolwes (String s) {
        if (s == null || s.length() == 0) return null;
        String vowels = "aeiouAEIOU";
        char[] c = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && !vowels.contains(c[start]+"")) {
                start++;
            }
            while (start < end && !vowels.contains(c[end]+"")) {
                end--;
            }
            char temp = c[start];
            c[start] = c[end];
            c[end] = temp;

            start++;
            end--;
        }
        return  new String(c);
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int arr[] = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[ransomNote.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length();i++) {
            if (--arr[magazine.charAt(i) - 'a'] < 0) {
                return  false;
            }
        }
        return true;
    }

    public int countSegments(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i-1) == ' ')) {
                res++;
            }
        }
        return res;
    }

    public boolean repeatedSubstringPattern (String str) {
        int length = str.length();
        for (int i = length/2; i > 0; i++) {
            if (length % i == 0) {
                int m = length/i;
                String sub = str.substring(0, i);
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    stringBuilder.append(sub);
                }
                if (stringBuilder.toString().equals(str))
                    return true;
            }
        }
        return false;
    }

    public boolean detectCapitalUse (String word) {
        return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int i = 0;
        int n = s.length();
        while (i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap (chars, i, j);
            i += 2 * k;
        }
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[j];
            chars[j] = chars[i];
            chars[i] = temp;
            i++;j--;
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[j])
                nums[++j] = nums[i];
        }
        return ++j;
    }

    public int[] twoSum (int[] nums, int target) {
        int[] ans = new int[2];
        if (nums == null || nums.length < 2) return ans;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int v = nums[left] + nums[right];
            if (v == target) {
                ans[0] = left + 1;
                ans[1] = right + 1;
                break;
            } else if (v > target) {
                right--;
            } else if (v < target) {
                left++;
            }
        }
        return ans;
    }

    public void removeZeros (int[] nums) {
        if (nums == null || nums.length == 0) return;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[count++] = nums[i];
        }
        while (count < nums.length) {
            nums[count++] = 0;
        }
    }

    public int[] interSection (int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = null;
        int count = 0;
        if (n == 0 || m == 0) return null;
        while (n > 0 && m > 0) {
            if (nums1[n] == nums2[m] && nums1[n-1] != nums1[n]) {
                ans[count++] = nums1[n];
                n--;m--;
            } else if (nums1[n] > nums2[m]) {
                n--;
            } else if (nums1[n] < nums2[m]) {
                m--;
            }
        }
        return ans;
    }
}
