package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 6987_월드컵
 * 메모리 : 12976KB
 * 시간 : 76ms
 * 길이 : 2826B
 * 풀이
 * 1. 완전탐색
 * 2. 무승부 계산에서 단순히 개수만 소거해주면 무승부쌍이 이루어질수없는경우에도 소거되는 경우가 발생
 * 따라서 조합으로 모든 국가쌍을 구한다음, 완전탐색으로 각 국가쌍에 대해 소거가 이루어지는지 확인해햐한다
 */

public class Main_B_S3_6987_월드컵 {

	private static boolean check;
	private static Pair[] arr;
	private static int[] win, lose, tie;
	

    static class Pair {
	      int x, y;

	      public Pair(int x, int y) {
	         super();
	         this.x = x;
	         this.y = y;
	   }
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t=0; t<4; t++) {
			int sum = 0;
			win = new int[6];
			tie = new int[6];
			lose = new int[6];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<6; i++) {	//6개 국가
				win[i] = st.nextToken().charAt(0)-'0';
				tie[i] = st.nextToken().charAt(0)-'0';
				lose[i] = st.nextToken().charAt(0)-'0';
				sum += win[i] + tie[i] + lose[i];
			}
			
			// 승패 수 같아야 하고 무승부도 짝수여야한다. 한 국가당 경기는 5개
			if(sum == 30) {
				int victory = 0;
				int defeat = 0;
				int draw = 0;
				
				for(int i=0; i<6; i++) {
					int ssum = win[i] + tie[i] + lose[i];
					victory += win[i];
					defeat += lose[i];
					draw += tie[i];
					if(ssum != 5) {
						sb.append(0+" ");
						continue;
					}
				}
				
				if(victory != defeat) {
					sb.append(0+" ");
				}else if(draw % 2 != 0) {	//무승부가 짝수가 아니면
					sb.append(0+" ");
				}else {
					check = false;
					arr = combi();
					solve(victory, defeat, draw, 0);
					if(check) {
						sb.append(1+" ");
					}else {
						sb.append(0+" ");
					}
				}
			}else {
				sb.append(0+" ");	//경기수의 합이 30이 아닐때
			}
		}
		System.out.println(sb.toString());
	}

	private static void solve(int victory, int defeat, int draw, int game) {
		if(check)
			return;
		
		if(game == 15) {
			check = true;
			return;
		}
		
		int i = arr[game].x;
		int j = arr[game].y;
		
		//승리
		if(victory > 0) { //승리 - 패배 각 쌍을 하나씩 소거
			if(lose[j] > 0 && win[i] > 0) {	
				lose[j]--;
				win[i]--;
				solve(victory-1, defeat-1, draw, game+1);
				lose[j]++;
				win[i]++;
			}
		}
		
		//패배
		if(defeat > 0) {
			if(lose[i] > 0 && win[j] > 0) {
				lose[i]--;
				win[j]--;
				solve(victory-1, defeat-1, draw, game+1);
				lose[i]++;
				win[j]++;
			}
		}
		
		//무승부
		if(draw > 0) {
			if(tie[i] > 0 && tie[j] > 0) {
				tie[i]--;
				tie[j]--;
				solve(victory, defeat, draw-2, game+1);
				tie[i]++;
				tie[j]++;
			}
		}
	}

	private static Pair[] combi() {
		Pair[] temp = new Pair[15];	//조합갯수 n*n-1 / 2
		for(int i=0, index=0; i<6; i++) {
			for(int j=i+1; j<6; j++) {
				temp[index++] = new Pair(i,j);
			}
		}
		return temp;
	}

}
