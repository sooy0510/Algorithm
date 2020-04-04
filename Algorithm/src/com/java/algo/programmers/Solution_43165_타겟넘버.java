package com.java.algo.programmers;

public class Solution_43165_타겟넘버 {

	static int answer; 
	public static void main(String[] args) {
		int[] numbers = {1,1,1,1,1};
		int target = 3;
		
		System.out.println(solution(numbers, target));
	}
	
    public static int solution(int[] numbers, int target) {
    	//DFS
    	answer = 0;
    	dfs(numbers, target,0,0);
    	
        return answer;
    }

	private static void dfs(int[] numbers, int target, int index, int sum) {
		if(index == numbers.length) {
			if(sum == target) {
				answer++;
			}
			return;
		}
		
		for(int i=0; i<2; i++) {
			switch (i) {
			case 0:	//+
				dfs(numbers, target, index+1, sum+numbers[index]);
				break;
			case 1:	//+
				dfs(numbers, target, index+1, sum-numbers[index]);
				break;	
			}
		}
		
	}

}
