1. Problem - 3Sum

question - https://leetcode.com/problems/3sum/


solution 
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> ans = new ArrayList<>();
        int j=0,k=0;
        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){
            if(i>0 && nums[i]==nums[i-1]) continue; // skipping duplicates
            j=i+1;
            k=nums.length-1;

            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];    
                if(sum==0){
                    ans.add(Arrays.asList(nums[i],nums[j],nums[k])); // adding the triplet to the answer list
                    j++;
                    k--;
                    // skipping duplicates
                    while(j<k && nums[j]==nums[j-1]) j++; //j<k is important to avoid out of bound exception
                    while(j<k && nums[k]==nums[k+1]) k--;
                }
                else if(sum>0) k--;
                else j++;

            }

        }

        return ans;
    }
}


2. 3Sum Closest
question - https://leetcode.com/problems/3sum-closest/

solution
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        
        Arrays.sort(nums);

        int res = nums[0]+nums[1]+nums[2];
        int l=0,r=0;
        for(int i=0; i<nums.length-2;i++){
            if(i>0 && nums[i]==nums[i-1])continue;
            l=i+1;
            r=nums.length-1;

            while(l<r){
                int sum = nums[i]+nums[l]+nums[r];
                if(sum==target) return target;

                if(Math.abs(target-sum)<Math.abs(target-res)){
                    res=sum;

                }
                if (sum < target) {
                    l++;
                     while(l<r && nums[l]==nums[l-1]) l++;
                } else if (sum > target) {
                    r--;
                     while(l<r && nums[r]==nums[r+1]) r--;
                } else {
                    return target; 
                }
            }

        }

        return res;
    }
}

3. Longest Mountain in Array
Question - https://leetcode.com/problems/longest-mountain-in-array/description/

Approach 
1.Traverse the array and check if the current index is a peak.
2.If it is:
    -Expand to the left while the sequence is strictly increasing.
    -Expand to the right while the sequence is strictly decreasing.
    -Calculate the mountain length.
    -Skip processed elements to avoid redundant work.




solution - 1st try
class Solution {
    public int longestMountain(int[] arr) {

       int maxLen=0, n = arr.length, left=0,right=0;

       for(int i=1;i<n-1;i++){

        if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){

            left=i-1;
            right=i+1;

            while(left>0 && arr[left]>arr[left-1]) left--;
            while(right<n-1 && arr[right]>arr[right+1]) right++;

            maxLen = Math.max(maxLen,right-left+1);

        }
       }

       return maxLen; 
    }
}

solution - more optimized
class Solution {
    public int longestMountain(int[] arr) {

       int maxLen=0, n = arr.length, left=0,right=0;
       int i=1; 
       while(i<n-1){

        if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){

            left=i-1;
            right=i+1;

            while(left>0 && arr[left]>arr[left-1]) left--;
            while(right<n-1 && arr[right]>arr[right+1]) right++;

            maxLen = Math.max(maxLen,right-left+1);
            i=right+1;
        }
        else{
            i++;
        }
       }

       return maxLen; 
    }
}

TC-O(N)  SC-O(1)
Even though there are nested loops, each index is visited at most once due to pointer skipping, so overall complexity is linear O(n).

