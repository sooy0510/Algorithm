package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.temporal.IsoFields;
import java.util.StringTokenizer;

public class Solution_D3_1220_Magnetic {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		int[][] table;
		int count=0;
		
		for(int test=1; test<=T; test++) {
			count=0;
			table = new int[100][100];
			br.readLine();
			
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int col=0; col<100; col++) {
				int cnt = 0;
				int now = 0;
				for(int row=0; row<100; row++) {
					if(table[row][col] == 1 && now != 1) {
						now = 1;
					}
					
					if(now == 1 && table[row][col] == 2) {
						cnt++;
						now = 2;
					}
				}
				
				count += cnt;
				
			}
			
			sb.append("#"+test+" "+count+"\n");
		}

		System.out.println(sb);
	}

}
