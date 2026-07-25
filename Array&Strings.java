1. Best time to buy and sell stock
link - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
solution
class Solution {
    public int maxProfit(int[] prices) {
        
        int minPrice=Integer.MAX_VALUE;
        int maxProfit=0;

        for(int i=0;i<prices.length;i++){

            // Update the minimum price if the current price is lower
            if(prices[i]<minPrice){
                minPrice=prices[i];
            }
            // Update the maximum profit if the current price minus the minimum price is greater than the current maximum profit
            else if(prices[i]-minPrice>maxProfit){
                maxProfit=prices[i]-minPrice;
            }

        }

        return maxProfit;

    }
}

TC - O(n) - We traverse the prices array once, so the time complexity is O(n), where n is the length of the prices array.
SC - O(1) - We use a constant amount of extra space for the minPrice

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2. Group of Anagrams
link - https://leetcode.com/problems/group-anagrams/description/    

solution
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        
        HashMap<String,List<String>> map = new HashMap<>();

        for(String str : strs){

            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String sortedStr = new String(ch);

            if(!map.containsKey(sortedStr)){
                map.put(sortedStr,new ArrayList<>());
            }

            map.get(sortedStr).add(str);

        }

        return new ArrayList<>(map.values());

    }
}

TC - O(n*klogk) - where n is the number of strings in the input array and k is the maximum length of a string. 
For each string, we sort it which takes O(klogk) time.
SC - O(n*k) - We use a HashMap to store the grouped anagrams, where the keys are the sorted strings and the values are lists of anagrams. 
In the worst case, all strings could be anagrams of each other, leading to a space complexity of O(n*k).