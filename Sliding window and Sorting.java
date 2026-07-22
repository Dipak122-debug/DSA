1. Merge Intervals
link - https://leetcode.com/problems/merge-intervals/description/

solution

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a, b) -> Integer.compare(a[0], b[0])); // Sort the intervals based on the start time

        int[] newInterval = intervals[0];

        List<int[]> res = new ArrayList<>();


        for(int[] interval : intervals){
            
            // If the current interval overlaps with the newInterval, we merge them by updating the end time of newInterval to be the maximum of the two end times.
            if(interval[0]<=newInterval[1]){
                newInterval[1]=Math.max(newInterval[1],interval[1]); 
            }
            else{

                res.add(newInterval);
                newInterval=interval;

            }
        }

        // Add the last interval to the result list
        res.add(newInterval);

        return res.toArray(new int[res.size()][2]);

    }
}

explanation of the solution
1st we sort the intervals based on the start time of each interval. This is done using the Arrays.sort() method with a custom comparator that compares the first element of each interval.
We initialize a newInterval variable to the first interval in the sorted list. This will be used to keep track of the current interval that we are merging.
We create an empty list res to store the merged intervals.  


