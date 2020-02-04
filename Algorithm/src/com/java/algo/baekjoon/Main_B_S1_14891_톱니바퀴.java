package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 이수연
 * 메모리 : 13036KB
 * 시간 : 76ms
 * 코드길이 : 2062B
 * 소요시간 : 2H50M
 * 풀이 :  재귀이용하여 작성. 스택오버플로 나서 외부코드 도움받아 로직수정
 */

public class Main_B_S1_14891_톱니바퀴 {

	
	static int[][] topni = new int[4][8];
	static int result=0;
	
	static int stoi(String s) { return Integer.parseInt(s);}
	public static void main(String[] args) throws Exception {
		
		//br.readLine()저장하는 임시 String
		String tmpString = null;
		
		//톱니바퀴 배열 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; i++) {
			tmpString = br.readLine();
			for(int j=0; j<8; j++) {
				topni[i][j] = Character.getNumericValue(tmpString.charAt(j));
			}
		}

		
		//회전 횟수 K(1<=K<=100)
		int K = stoi(br.readLine());
		int idx = 0;	//회전시킨 톱니바퀴 번호
		int dir = 0;		//방향 1: 시계방향, 2: 반시계
		
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			idx = stoi(st.nextToken())-1;
			dir = stoi(st.nextToken());
			
			//탐색
			left(idx-1, -dir);
			right(idx+1, -dir);
			rotation(idx, dir);
			
		}
		
		if(topni[0][0] == 1)result += 1;
		if(topni[1][0] == 1)result += 2;
		if(topni[2][0] == 1)result += 4;
		if(topni[3][0] == 1)result += 8;

		System.out.println(result);
		
		
		
	}
	private static void rotation(int idx, int dir) {
		
		if(dir == 1) {	//시계
			int temp = topni[idx][7];
			for(int i=7; i>=1; i--) {
				topni[idx][i] = topni[idx][i-1];
			}
			topni[idx][0] = temp;	
		}else {		//반시계
			int temp = topni[idx][0];
			for(int i=1; i<=7; i++) {
				topni[idx][i-1] = topni[idx][i];
			}
			topni[idx][7] = temp;		
		}
	}
	
	private static void right(int idx, int dir) {
		//오른쪽에 있던 톱니바퀴 회전 여부 결정
		if(idx > 3)return;
		
		// 돌려도 되면
		if(topni[idx][6] != topni[idx-1][2]) {
			right(idx+1, -dir);
			rotation(idx, dir);
		}	
	}
	
	private static void left(int idx, int dir) {
		//왼쪽에 있던 톱니바퀴 회전 여부 결정
		if(idx < 0)return;
		
		//돌려도 되면
		if(topni[idx][2] != topni[idx+1][6]) {
			left(idx-1, -dir);
			rotation(idx, dir);
		}
	}

}
