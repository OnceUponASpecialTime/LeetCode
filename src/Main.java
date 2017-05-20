import java.util.*;

/**
 * Created by Foutas on 2017/4/11.
 */
public class Main {

//    public static void main(String[] args) {
//        int[] a = {2,4,6,5,7,3,8,9,10};
//        int n = 0;
//        n = findUnsortedSubarray(a);
//        System.out.println(n);
//    }
//
//    public static void main(String[] args) {
//        int[] a = {3,5,7,4,6,9,1};
//        System.out.println(findDisappearedNumbers(a));
//    }
//
//    private static int findUnsortedSubarray(int[] A) {
//        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
//        for (int i=1;i<n;i++) {
//            max = Math.max(max, A[i]);
//            min = Math.min(min, A[n-1-i]);
//            if (A[i] < max) end = i;
//            if (A[n-1-i] > min) beg = n-1-i;
//        }
//        return end - beg + 1;
//
//    }
//
//    private static List<Integer> findDisappearedNumbers(int[] nums) {
//        List<Integer> ret = new ArrayList<Integer>();
//
//        for(int i = 0; i < nums.length; i++) {
//            int val = Math.abs(nums[i]) - 1;
//            if(nums[val] > 0) {
//                nums[val] = -nums[val];
//            }
//        }
//
//        for(int i = 0; i < nums.length; i++) {
//            if(nums[i] > 0) {
//                ret.add(i+1);
//            }
//        }
//        return ret;
//    }

    public static void main(String[] args) {
        int[] nums = {3,1,6,9,12};
        int k = 4;
        boolean ans = containsNearbyDuplicate(nums, k);
//        int ans = missingNumber(nums);
        System.out.println(ans);
    }

    private static int thridMax(int[] nums) {
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

//    private static int missingNumber(int[] nums) { //binary search
//        Arrays.sort(nums);
//        int left = 0, right = nums.length, mid= (left + right)/2;
//        while(left<right){
//            mid = (left + right)/2;
//            if(nums[mid]>mid) right = mid;
//            else left = mid+1;
//        }
//        return left;
//    }

    private static int missingNumber(int[] nums) { //xor
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    private static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k)
                    return true;
            }
        }
        return false;
    }
}
