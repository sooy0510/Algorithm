package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3378_스타일리쉬들여쓰기 {

	private static int[][] m;
	private static int[][] dap;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int p = Integer.parseInt(st.nextToken());	//마스터의 코드 줄수
			int q = Integer.parseInt(st.nextToken());	//나의 코드 줄수
			
			
			//마스터 코드 분석
			m = new int[p][4];	// . 소 중 대
			for(int i=0; i<p; i++) {
				String line = br.readLine();// 한줄을 입력받아서
				// 앞부분에 나온 .의 개수
				int index = 0;
				while(line.charAt(index) == '.') {
					index++;
				}
				m[i][0] = index;
				// 괄호의 개수는 누적처리
				if(i > 0) {	//이전 값으로 초기화
					m[i][1] = m[i-1][1];	//소괄호의 열린 개수
					m[i][2] = m[i-1][2];	//중괄호
					m[i][3] = m[i-1][3];	//대괄호
				}
				
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
				
			}//마스터의 스타일리쉬 코드 분석 for
			
			
			//내 코드 분석
			dap = new int[q][4];	// . 소 중 대
			for(int i=0; i<q; i++) {
				String line = br.readLine();// 한줄을 입력받아서
				// 앞부분에 나온 .의 개수
				int index = 0;
				
				//내 코드는 .이 없으므로
//				while(line.charAt(index) == '.') {
//					index++;
//				}
				dap[i][0] = index;
				// 괄호의 개수는 누적처리
				if(i > 0) {	//이전 값으로 초기화
					dap[i][1] = dap[i-1][1];	//소괄호의 열린 개수
					dap[i][2] = dap[i-1][2];	//중괄호
					dap[i][3] = dap[i-1][3];	//대괄호
				}
				
				for(int j=index; j<line.length(); j++) {
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
				
			//dap[i][0] : 초기값 -2, .의 개수를 몇개
			for(int i=0; i<q; i++) {
				dap[i][0] = -2;	//안쓰는 값으로 초기화
			}
			
			//중복 순열
			for(int R=1; R<=20; R++) {
				for(int C=1; C<=20; C++) {
					for(int S=1; S<=20; S++) {
						if(check(R,C,S)) {	//마스터 코드에서 해가 되는가?
							cal(R,C,S);
						}
					}
				}
			}
			
			sb.append("#").append(test).append(" 0");	//첫번째 줄의 들여쓰기는 0으로 일정
			for(int i=1; i<dap.length; i++) {
				sb.append(" ").append(dap[i][0]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	//내 코드에서 들여쓰기를 각 라인에 몇개씩 해야하는지 구해서 dap 배열에 저장
	private static void cal(int R, int C, int S) {
		for(int i=1; i<dap.length; i++) {
			int x = dap[i-1][1]*R + dap[i-1][2]*C + dap[i-1][3]*S;
			if(dap[i][0] == -2) {	//답을 구한적이 없으면
				dap[i][0] = x;
			}else if(dap[i][0] != x) {	//기존값과 다른 들여쓰기 값이 생긴다면
				dap[i][0] = -1;
			}
		}
	}
	
	//마스터 코드에서 해가 되는지 체크해서 리턴
	private static boolean check(int R, int C, int S) {
		for(int i=1; i<m.length; i++) {
			if(m[i][0] != m[i-1][1]*R + m[i-1][2]*C + m[i-1][3]*S) {
				return false;
			}
		}
		return true;
	}
}
	

