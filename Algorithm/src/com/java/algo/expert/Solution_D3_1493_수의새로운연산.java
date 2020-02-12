package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1493_수의새로운연산
 * 메모리 : 25912KB
 * 실행시간 : 209ms
 * 풀이 : 배열만들어서 했음
 * 다른풀이로 해보기
 *
 */

public class Solution_D3_1493_수의새로운연산 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[][] map = new int[400][400];
		int T = Integer.parseInt(br.readLine());
		int p,q;
		
		//map에 수 할당(행 단위로 값 넣기)
		//1. 각 행의 첫번째 값을 구한다
		//2. +(행값+1)씩 더해준다
		int first = 1;
		int jump;
		for(int row=1; row<400; row++) {
			first += row-1;
			jump = row+1;
			for(int col=1; col<400; col++) {
				if(col == 1) {
					map[row][col] = first;
				}
				else {
					map[row][col] = map[row][col-1] + jump;
					jump += 1;
				}
			}
		}
		
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			
			int[][] vertex = new int[2][2];
			//p값의 좌표, q값의 좌표 구하기
			int out = 0;
			for(int i=1; i<400; i++) {
				for(int j=1; j<400; j++) {
					if(map[i][j] == p) {
						vertex[0][0] = i;
						vertex[0][1] = j;
						out++;
					}
					if(map[i][j] == q) {
						vertex[1][0] = i;
						vertex[1][1] = j;
						out++;
					}
					if(out == 2) {
						break;
					}
				}
			}
			
			int new_x, new_y;
			new_y = vertex[0][0] + vertex[1][0];
			new_x = vertex[0][1] + vertex[1][1];
			
			sb.append("#"+test+" "+map[new_y][new_x]+"\n");
			
		}
		System.out.println(sb);
	}

}
