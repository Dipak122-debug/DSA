1. Search in Rotated Sorted Array

link - https://leetcode.com/problems/search-in-rotated-sorted-array/description/

solution

class Solution {
    public int search(int[] nums, int target) {

        int left = 0, mid =0;
        int right = nums.length -1;

        while(left<=right){

            mid = left+(right-left)/2;

            if(nums[mid]==target) return mid;

            //checking left half is sorted or not
            else if(nums[mid]>=nums[left]){
                // if target is present in the sorted half do the binary search there
                if(target>=nums[left] && target<nums[mid]) {
                    right = mid-1;
                }
                else left = mid+1;
            }

            else{
                if(target>nums[mid] && target<=nums[right]){
                    left = mid+1;
                }
                else{
                    right = mid-1;
                }
            }
        }

        return -1;
        
    }
}

TC - O(N)
SC- O(1)


