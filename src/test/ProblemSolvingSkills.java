package test;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.impl.orbutil.graph.Node;

import java.util.*;

/**
 * Created by Foutas on 2017/7/13.
 */
public class ProblemSolvingSkills {

    Solution1 solution1 = null;

    public void replaceSpaces (char[] str, int length) {
        int spaceCount = 0, newLength, i;
        for (i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        newLength = length + spaceCount * 2;
        str[newLength] = '\0';
        for (i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[newLength - 1] = '0';
                str[newLength - 2] = '2';
                str[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                str[newLength - 1] = str[i];
                newLength = newLength - 1;
            }
        }
    }

    public String compressBad (String str) {
        int size = countCompression(str);
        if (size >= str.length()) {
            return str;
        }

        StringBuilder mystr = new StringBuilder();
        char last = str.charAt(0);
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                mystr.append(last);
                mystr.append(count);
                last = str.charAt(i);
                count = 1;
            }
        }
        mystr.append(last);
        mystr.append(count);
        return mystr.toString();
    }

    private int countCompression(String str) {
        if (str == null || str.length() == 0) return 0;
        char last = str.charAt(0);
        int count = 1;
        int size = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                last = str.charAt(i);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        size += 1 + String.valueOf(count).length();
        return size;
    }

    public void rotate (int[][] matrix, int n) {
        for (int layer = 0; layer < n/2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
    }

    public void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                row[i] = true;
                column[j] = true;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; i++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public boolean isRotation(String s1, String s2) {
        int len = s1.length();
        if (len == s2.length() && len > 0) {
            String s1s1 = s1 + s1;
            return true;
        }
        return false;
    }

    public static void deleteDups(LinkedListNode node) {
//        Hashtable table = new Hashtable();
//        LinkedListNode previous = null;
//        while (node != null) {
//            if (table.containsKey(node.data)) {
//                previous.next = node.next;
//            } else {
//                table.put(node.data, true);
//                previous = node;
//            }
//            node = node.next;
//        }

        if (node == null) return;
        LinkedListNode current = node;
        while (current != null) {
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    private static class LinkedListNode {
        LinkedListNode next = null;
        int data;
    }

    public LinkedListNode nthToLast(LinkedListNode head, int k) {
        if (k < 0) return null;
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        for (int i = 0; i < k - 1; i++) {
            if (p2 == null) return null;
            p2 = p2.next;
        }

        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static boolean deleteNode(LinkedListNode n) {
        if (n == null || n.next == null) {
            return false;
        }
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    public LinkedListNode partition(LinkedListNode node, int x) {
        LinkedListNode beforeStart = null;
        LinkedListNode afterStart = null;
        while (node != null) {
            LinkedListNode next = node.next;
            if (node.data < x) {
                node.next = beforeStart;
                beforeStart = node;
            } else {
                node.next = afterStart;
                afterStart = node;
            }
            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        }

        LinkedListNode head = beforeStart;
        while (beforeStart.next != null) {
            beforeStart = beforeStart.next;
        }
        beforeStart.next = afterStart;
        return head;
    }

    public LinkedListNode addLists (LinkedListNode l1, LinkedListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) return null;

        LinkedListNode result = new LinkedListNode();

        int value = carry;
        if (l1 != null) {
            value += l1.data;
        }
        if (l2 != null) {
            value += l2.data;
        }
        result.data = value % 10;
        LinkedListNode more = addLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0 );
        return result;
    }

    public LinkedListNode FindBegining (LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public boolean isPalindrome(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack<Integer>();

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop().intValue();
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public class NodeWithMin {
        public int value;
        public int min;
        public NodeWithMin(int value, int min) {
            this.min = min;
            this.value = value;
        }
    }

    public class StackWithMin1 extends Stack<NodeWithMin> {
        public void push (int value) {
            int newMin = Math.min(value, min());
            super.push(new NodeWithMin(value, newMin));
        }

        public int min() {
            if (this.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return peek().min;
            }
        }
    }

    public class MyQueue<T> {
        Stack<T> stackNewest, stackOldest;

        public MyQueue() {
            stackNewest = new Stack<T>();
            stackOldest = new Stack<T>();
        }

        public int size() {
            return stackOldest.size() + stackNewest.size();
        }

        public void add (T value) {
            stackNewest.push(value);
        }

        private void shiftStacks() {
            if (stackOldest.isEmpty()) {
                while (!stackNewest.isEmpty()) {
                    stackOldest.push(stackNewest.pop());
                }
            }
        }

        public T peek() {
            shiftStacks();
            return stackOldest.peek();
        }

        public T remove() {
            shiftStacks();
            return stackOldest.pop();
        }
    }

    public static Stack<Integer> sort(Stack<Integer> s) {
        Stack<Integer> r = new Stack<Integer>();
        while (!s.isEmpty()) {
            int tmp = s.pop();
            while (!r.isEmpty() && r.peek() > tmp) {
                s.push(r.pop());
            }
            r.push(tmp);
        }
        return r;
    }

    public static int getHeight(Solution1.TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)+1);
    }

    public static boolean isBalanced(Solution1.TreeNode root) {
        if (root == null) return true;
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public static int checkHeight(Solution1.TreeNode root) {
        if (root == null) return 0;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight,rightHeight)+1;
        }
    }

    public static boolean balanced (Solution1.TreeNode root) {
        if (checkHeight(root) == -1) {
            return false;
        } else {
            return true;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    public TreeNode createMinimalBST (int arr[], int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid]);
        n.left = createMinimalBST(arr, start, mid - 1);
        n.right = createMinimalBST(arr, mid + 1, end);
        return n;
    }

    public TreeNode createMinimalBST(int arr[]) {
        return createMinimalBST(arr, 0 , arr.length - 1);
    }

    public void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
        if (root == null) return;

        LinkedList<TreeNode> list = null;
        if (lists.size() == level) {
            list = new LinkedList<TreeNode>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    public ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    public ArrayList<LinkedList<TreeNode>> createLevel (TreeNode root) {
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
        LinkedList<TreeNode> current = new LinkedList<>();
        if (root != null) {
            current.add(root);
        }

        while (current.size() > 0) {
            result.add(current);
            LinkedList<TreeNode> parents = current;
            current = new LinkedList<TreeNode>();
            for (TreeNode parent : parents) {
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
    }
}
