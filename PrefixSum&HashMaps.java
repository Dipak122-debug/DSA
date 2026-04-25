1. Subarray Sum Equals K

link - https://leetcode.com/problems/subarray-sum-equals-k/description/

Solution

class Solution {
    public int subarraySum(int[] nums, int k) {
        int res=0,prefSum=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            prefSum+=nums[i];
            int target = prefSum-k;
            if(map.containsKey(target)){
                res+=map.get(target);
            }
            map.put(prefSum,map.getOrDefault(prefSum,0)+1);
        }
        return res;
    }
}

2. Find Pivot Index
link - https://leetcode.com/problems/find-pivot-index/description/
Solution
class Solution {
    public int pivotIndex(int[] nums) {
        
        int prefSum=0;
        int total = Arrays.stream(nums).sum();

        for(int i=0;i<nums.length;i++){
            prefSum+=nums[i];
            if(total-prefSum==prefSum-nums[i]) return i;
        }

        return -1;
    }
}

3. shortest-subarray-with-sum-at-least-k

link -  https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/

Solution 1
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int minLen=Integer.MAX_VALUE;
        int n=nums.length;
        int[] prefSum = new int[n+1];

        for(int i=1;i<=n;i++){
            prefSum[i]=nums[i-1]+prefSum[i-1];

            //calculate min len for each prefSum
            int j=i-1;
            while(j>=0){
                if(prefSum[i]-prefSum[j]>=k){
                    minLen=Math.min(i-j,minLen);
                    break;
                }
                j--;
            }
        }

        
        return minLen!=Integer.MAX_VALUE?minLen:-1;
    }
}
TC - O(n^2)
SC - O(n)


OPTIMIZED
Solution 2
//TODO - add explanation, need to visit the problem again to understand the solution better
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int minLen=Integer.MAX_VALUE;
        int n=nums.length;
        long[] prefSum = new long[n+1];

        Deque<Integer> deque = new ArrayDeque<>();
         for(int i=0;i<n;i++){
            prefSum[i+1]=nums[i]+prefSum[i];
         }

        for(int i=0;i<=n;i++){
            //calculate min len for each prefSum
            while(!deque.isEmpty() && prefSum[i]-prefSum[deque.peekFirst()]>=k){
                minLen=Math.min(minLen,i-deque.pollFirst());
            }

            /*
            we want to maintain the deque in increasing order of prefSum, 
            so that we can easily find the minimum length subarray with sum at least k. 
            If the current prefSum is less than or equal to the prefSum at the back of the deque, 
            we can remove the back of the deque because it will never be part of a valid subarray in the future.
            */
            while(!deque.isEmpty() && prefSum[i]<=prefSum[deque.peekLast()]){
                deque.pollLast();
            }


            deque.addLast(i);
           
        }

        
        return minLen!=Integer.MAX_VALUE?minLen:-1;
    }
}
Explanation of the optimized solution
The optimized solution uses a deque to maintain the indices of the prefix sums in increasing order.


tc - O(n)
sc - O(n)

4. Maximum Product Subarray
link - https://leetcode.com/problems/maximum-product-subarray

Solution
//The idea is to calculate the prefix and suffix product of the array and update the maximum product at each step.
class Solution {
    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int prefPdct=1,suffPdct=1;
        int n=nums.length;
        for(int i=0; i<n;i++){
            //calculate prefix and suffix product
            prefPdct*=nums[i];
            suffPdct*=nums[n-i-1];
            //update max product
            maxProduct=Math.max(maxProduct,Math.max(prefPdct,suffPdct));

             if(prefPdct==0) prefPdct=1; //if the product becomes 0, we reset it to 1 because we want to start a new subarray from the next element.
                
            if( suffPdct==0)  suffPdct=1; //if the product becomes 0, we reset it to 1 because we want to start a new subarray from the next element.
           
        }

        return maxProduct;
    }
}
/*
why we need prefix and suffix product?
The reason we need both prefix and suffix products is to handle cases where the maximum product subarray may be located at the end of the array. 
For example, consider the array [-5,-2,0,-4,-10]. The maximum product subarray is [-4,-10], which is located at the end of the array. 
If we only calculate the prefix product, we would miss this case and return 0 instead of -1. By calculating both prefix and suffix products, 
we can ensure that we consider all possible subarrays and find the correct maximum product.    
*/


5. count range sum - //todo - need to revisit the problem to understand the solution better
link - https://leetcode.com/problems/count-of-range-sum/description/

Solution 1  
Explanation: 
prefSum[i] gives us the sum of the subarray from index 0 to index i-1. Therefore,
prefSum[j] - prefSum[i] gives us the sum of the subarray from index j to index i-1. If this sum is between lower and upper, we increment our count.

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        int count=0;
        long[] prefSum = new long[n+1];

        for(int i=0;i<n;i++){
            prefSum[i+1]=prefSum[i]+nums[i];
        }

        for(int i=n;i>0;i--){
            for(int j=0;j<i;j++){
                long rangeSum=prefSum[i]-prefSum[j];
                if(rangeSum >=lower && rangeSum<=upper){
                    count+=1;
                }
            }
        }

        return count;
    }
}
TC - O(n^2)
SC - O(n)



6. Subarray Sums Divisible by K
link - https://leetcode.com/problems/subarray-sums-divisible-by-k/description/  

Solution
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        
        int count = 0, n=nums.length, prefSum=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=0;i<n;i++){
            prefSum+=nums[i];
            //rem is the remainder when prefSum is divided by k. We take the modulus to handle cases where prefSum is negative, ensuring that rem is always non-negative.
            int rem=prefSum%k;
            if(rem<0) rem+=k;
            //If the same remainder has been seen before, it means that the sum of the elements between the previous index and the current index is divisible by k. 
            // Therefore, we can add the count of that remainder to our result.
            if(map.containsKey(rem)) count+=map.get(rem);

            map.put(rem,map.getOrDefault(rem,0)+1) ;
        }
        return count;
    }
}
TC - O(n) SC - O(k) because the maximum number of unique remainders is k.

NOTE:
- for subarray sum == k, we can use the same approach but instead of using the remainder, we can use the prefix sum 
- and check if the prefix sum - k is present in the map. If it is present, it means that there is a subarray with sum k between the previous index and the current index.




7. Shortest Unsorted Continuous Subarray
link - https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/

Solution
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n=nums.length;
        int[] sorted = Arrays.copyOf(nums,n);
        Arrays.sort(sorted);
        int start=0,end=0;
        for(int i=0;i<n;i++){
            if(nums[i]!=sorted[i]){
                start=i;
                break;
            }
        }

        for(int i=n-1;i>=0;i--){
            if(nums[i]!=sorted[i]){
                end=i;
                break;
            }
        }

        return end-start>=0?end-start+1:0;
    }
}

optimized solution
class Solution {        
    public int findUnsortedSubarray(int[] nums) {
        int n=nums.length;
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        int start=-1,end=-1;

        for(int i=0;i<n;i++){
            if(nums[i]<max){
                end=i;
            }else{
                max=nums[i];
            }
        }

        for(int i=n-1;i>=0;i--){
            if(nums[i]>min){
                start=i;
            }else{
                min=nums[i];
            }
        }

        return end-start>=0?end-start+1:0;
    }
}

Explanation of the optimized solution
The optimized solution uses two passes to find the start and end indices of the unsorted subarray. 
In the first pass, we iterate from left to right and keep track of the maximum value seen so far. 
If we encounter a value that is less than the maximum, it means that the current index is part of the unsorted subarray, 
and we update the end index. In the second pass, we iterate from right to left and keep track of the minimum value seen so far.
If we encounter a value that is greater than the minimum, it means that the current index is part of the unsorted subarray, and we update the start index. 
Finally, we return the length of the unsorted subarray if it exists, or 0 if the array is already sorted.