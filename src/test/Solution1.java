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

//    public int missingNumber (int[] nums) {
//
//    }
}
