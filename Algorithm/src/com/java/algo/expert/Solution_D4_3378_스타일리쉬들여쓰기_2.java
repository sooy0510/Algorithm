package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 3378_스타일리쉬들여쓰기
 * 메모리 : 26436KB
 * 시간 : 123ms
 * 길이 : 3057B
 * 풀이
 * 1. 교수님 설명듣고 풀었음
 * 2. 마스터의 코드를 분석하고 가능한 r,c,s의 순열을 구한뒤
 * 3. 내 코드에 대입
 * => 각 순열마다 들여쓰기의 개수가 일정하지 않다는것은 유일한 해가 아니라는 것 => -1출력
 *
 */

public class Solution_D4_3378_스타일리쉬들여쓰기_2 {

	private static int[][] m;
	private static int[][] dap;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			//마스터 코드
			m = new int[p][4];
			for(int i=0; i<p; i++) {
				String line = br.readLine();
				
				//들여쓰기 개수(.의 개수)세주기
				int index = 0;
				while(line.charAt(index) == '.') {
					index++;
				}
				
				m[i][0] = index;
				
				//괄호개수를 누적처리
				if(i > 0) {	//이전값으로 초기화
					m[i][1] = m[i-1][1];
					m[i][2] = m[i-1][2];
					m[i][3] = m[i-1][3];
				}
				
				//괄호 개수 세주기
				for(int j=index; j<line.length(); j++) {
					switch (line.charAt(j)) {
						case '(': m[i][1]++; break;
						case ')': m[i][1]--; break;
						case '{': m[i][2]++; break;
						case '}': m[i][2]--; break;
						case '[': m[i][3]++; break;
						case ']': m[i][3]--; break;
					}
				}
			}//마스터 코드 끝
			
			//내 코드
			dap = new int[q][4];
			for(int i=0; i<q; i++) {
				String line = br.readLine();
				
				//내 코드에는 들여쓰기 없으므로 [q][0]을 안쓰는 값-2로 초기화
				dap[i][0] = -2;
				
				//괄호 개수는 누적
				if(i > 0) {	//이전값으로 초기화
					dap[i][1] = dap[i-1][1];
					dap[i][2] = dap[i-1][2];
					dap[i][3] = dap[i-1][3];
				}
				
				//괄호 개수 세주기
				for(int j=0; j<line.length(); j++) {
					switch (line.charAt(j)) {
						case '(': dap[i][1]++; break;
						case ')': dap[i][1]--; break;
						case '{': dap[i][2]++; break;
						case '}': dap[i][2]--; break;
						case '[': dap[i][3]++; break;
						case ']': dap[i][3]--; break;
					}
				}
			}
			
			//r,c,s가능한 순열
			for(int r=1; r<=20; r++) {
				for(int c=1; c<=20; c++) {
					for(int s=1; s<=20; s++) {
						if(check(r,c,s)) {//마스터코드에서 해가 될수 있는지 체크, 해가 되면 내 코드에 적용
							cal(r,c,s);
						}
					}
				}
				
			}
			
			sb.append("#").append(test).append(" 0 "); //첫번째 줄의 들여쓰기는 0개
			
			for(int i=1; i<q; i++) {
				sb.append(dap[i][0]+" ");
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void cal(int r, int c, int s) {
		for(int i=1; i<dap.length; i++) {
			int ans = dap[i-1][1]*r + dap[i-1][2]*c + dap[i-1][3]*s;
			if(dap[i][0] == -2) {	//답을 구한적이 없으면
				dap[i][0] = ans;
			}else if(dap[i][0] != ans){	//기존값과 다른값이 생기면 => 몇 번 들여쓰기를 해야하는지 유일하게 결정되지 않은것
				dap[i][0] = -1;
			}
		}
	}

	private static boolean check(int r, int c, int s) {
		for(int i=1; i<m.length; i++) {
			if(m[i][0] != m[i-1][1]*r + m[i-1][2]*c + m[i-1][3]*s) {
				return false;	//해가 될수없음
			}
		}
		return true;
	}
}
	

