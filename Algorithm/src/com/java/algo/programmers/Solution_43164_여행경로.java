package com.java.algo.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_43164_여행경로 {

	static List<String> list = new ArrayList<String>();
	static String route = "";
	static boolean[] visited;
	public static void main(String[] args) {
		//String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		String[] result;
		
		result = Solution(tickets);
		System.out.println(Arrays.toString(result));
		
	}
	
	

	public static String[] Solution(String[][] tickets) {
		
		for (int i = 0; i < tickets.length; i++) {
			for (int j = 0; j < tickets[i].length; j++) {
				visited = new boolean[tickets.length];
				String start = tickets[i][0];
				String end = tickets[i][1];
				
				if(start.equals("ICN")) {
					route = start+",";
					visited[i] = true;
					dfs(tickets, end, 1);
				}
			}
		}
		
		Collections.sort(list);
		String[] answer = list.get(0).split(",");
		
		return answer;
    }



	private static void dfs(String[][] tickets, String end, int cnt) {
		route += end+",";
		
		if(cnt == tickets.length) {
			list.add(route);
			return;
		}
		
		for(int i=0; i<tickets.length; i++) {
			String s = tickets[i][0];
			String e = tickets[i][1];
			if(s.equals(end) && !visited[i]) {
				visited[i] = true;
				dfs(tickets, e, cnt+1);
				visited[i] = false; 	//다른 경로 만들어주기 위해서
				route = route.substring(0, route.length()-4);	//종착역 하나빼주기
			}
		}
		
	}
}
