package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution_D4_7701_염라대왕의이름정렬 {

	private static int N;
	private static Set<String> set;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			set = new HashSet<String>();
 			
			N = Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++) {
				set.add(br.readLine());
			}
			
			ArrayList<String> list = new ArrayList<String>(set);
			
			Collections.sort(list, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					//길이가 같으면
					if(o1.length() == o2.length()) {
						//사전순
						int len = o1.length();
						for(int i=0; i<len; i++) {
							if(o1.charAt(i) == o2.charAt(i))continue;
							return o1.charAt(i)-o2.charAt(i);
						}
					}
					return o1.length()-o2.length();
				}
			});
			
			sb.append("#"+test+"\n");
			for (Object str : list) {
				sb.append(str+"\n");
			}
			
		}
		System.out.println(sb);
		
	}

}
