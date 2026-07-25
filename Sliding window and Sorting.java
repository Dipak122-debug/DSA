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


2. Top K Frequent Elements
link - https://leetcode.com/problems/top-k-frequent-elements/description/
solution
approach 1: Using HashMap and PriorityQueue
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        int[] res = new int[k];

        HashMap<Integer,Integer> freqMap = new HashMap<>();

        for(int i : nums){

            freqMap.put(i,freqMap.getOrDefault(i,0)+1);
           
        }


        // {[num,freq]........}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b)->(a[1]-b[1]));

        for(int key : freqMap.keySet()){
            minHeap.offer(new int[]{key,freqMap.get(key)});

            if(minHeap.size()>k){
                minHeap.poll();
            }
        }
        int idx=0;

        while(!minHeap.isEmpty()){

            res[idx++]=minHeap.poll()[0];

        }


        return res;


    }
}


TC - O(nlogk) for creating the frequency map and adding elements to the min-heap. Overall, the time complexity is O(nlogk).
why logk --> Because we are adding elements to the min-heap and removing the smallest element when the size exceeds k, which takes O(logk) time.

For a binary heap:

offer() → O(log H)
poll() → O(log H)

where H is the current heap size. In this case, the heap size is limited to k, so the time complexity for each offer and poll operation is O(log k). 
Since we perform these operations for each of the n elements in the frequency map, the overall time complexity is O(n log k).
SC - O(n) for the frequency map and the min-heap.

explanation of the solution
We first create a frequency map using a HashMap to count the occurrences of each number in the input array. We iterate through the nums array and update the frequency count for each number in the map.
Next, we create a min-heap (PriorityQueue) to keep track of the top k   frequent elements. We iterate through the keys in the frequency map and add each key along with its frequency to the min-heap. 
If the size of the min-heap exceeds k, we remove the element with the lowest frequency (the root of the min-heap).
Finally, we extract the top k frequent elements from the min-heap and store them in the result array res, which is then returned.

approach 2: Using HashMap and Bucket Sort
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        int[] res = new int[k];

        HashMap<Integer,Integer> freqMap = new HashMap<>();

        for(int i : nums){

            freqMap.put(i,freqMap.getOrDefault(i,0)+1);
           
        }

        // Create an array of lists to represent the buckets
        List<Integer>[] buckets = new List[nums.length + 1];

        // Populate the buckets based on frequency
        for(int key : freqMap.keySet()){
            int freq = freqMap.get(key);
            if(buckets[freq] == null){
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        // Collect the top k frequent elements from the buckets
        int idx = 0;
        for(int i = buckets.length - 1; i >= 0 && idx < k; i--){
            if(buckets[i] != null){
                for(int num : buckets[i]){
                    res[idx++] = num;
                    if(idx == k) break;
                }
            }
        }

        return res;
    }
}

TC - O(n) for creating the frequency map, O(n) for populating the buckets, and O(n) for collecting the top k elements from the buckets. Overall, the time complexity is O(n).
SC - O(n) for the frequency map and the buckets array.

explanation of the solution
We first create a frequency map using a HashMap to count the occurrences of each number in the input array. We iterate through the nums array and update the frequency count for each number in the map.
Next, we create an array of lists called buckets, where the index represents the frequency of the numbers. We populate the buckets based on the frequency of each number in the frequency map.
Finally, we iterate through the buckets in reverse order (from highest frequency to lowest) and collect the top k frequent elements into the result array res, which is then returned.