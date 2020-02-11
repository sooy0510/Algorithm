package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1220_Magnetic {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//빨간색은 아래로 한칸씩 for문
		//파란색은 위로 한칸씩 for문
		//int T = 10;
		int T = 1;
		int[][] table;
		
		for(int test=1; test<=T; test++) {
			table = new int[100][100];
			br.readLine();
			
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					System.out.print(table[i][j]);
				}
				System.out.println();
			}
			
			//하나의 열에 하나의 자성체만 있으면, 교착상태 없으니까 pass
			//1은 밑으로만 가고, 2는 위로만간다
			//교착상태 아닌 1(N극)이 밑으로 내려가다 2(S극)만나면 교착
			//1이 내려가다 1만나면 합쳐짐
			
			for(int col=0; col<100; col++) {
				int now = 0;
				for(int row=0; row<100; row++) {
					if(table[col][row] == 0) continue;
					else {
						now = table[col][row];
					}
				}
			}
			
		}
		
	}

}
