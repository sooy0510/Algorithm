package com.java.algo.programmers;

public class Solution_43162_네트워크 {

	//visited 양방향으로 방문처리
	static int answer;
	static boolean visited[][];
	public static void main(String[] args) {
		int n = 3;
		//int[][] computers = {{1,1,0},{1,1,0},{0,0,1}};
		//int[][] computers = {{1,1,0},{1,1,1},{0,1,1}};
		int[][] computers = {{1,0,0},{0,1,0},{0,0,1}};
		System.out.println(solution(n, computers));
	}

    static public int solution(int n, int[][] computers) {
        answer = 0;
        visited = new boolean[n][n];
        for(int i=0; i<n; i++) {
    		if(!visited[i][i]) {
    			dfs(n, computers,i);
    			answer++;
    		}
        }
        return answer;
    }

	private static void dfs(int n, int[][] computers, int start) {
		for(int i=0; i<n; i++) {
			if(computers[start][i] == 1 && !visited[start][i]) {
				visited[start][i] = true;
				visited[i][start] = true;
				dfs(n, computers, i);
			}
		}
	}
}
