package com.java.algo.jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1828_냉장고
 * 메모리 : 7MB
 * 실행시간 : 149ms
 * 풀이 
 * 1차시도
 * - 화학물질의 보관온도 범위가 각기 다른데, 입력받는대로 이전것과 비교하고 냉장고를 추가했음 => 제대로 비교안돼서 실패
 * --------------------------------------------------------------------------------------
 * 2차시도
 * 1. 모든 화학물질의 최저보관온도와 최고보관온도를 2차원 배열 arr에 저장
 * 2. 보관온도 비교하기 쉽게끔 정렬해준다(정렬을 잘못해서 테케 계속 실패;)
 * 3. 첫번째 화학물질은 무조건 냉장고 추가해준다
 * 4. 그다음 화학물질이 들어오면 기존 냉장고들을 for문으로 돌면서 같은 냉장고를 사용할 수 있나 체크한다
 * - 새로운 화학물질의 최저온도 >= 기존 냉장고 최저온도 && 새로운 화학물질의 최저온도 <= 기존 냉장고 최고온도 이면 isSame = true;
 * 5. isSame = true면 새로운 화학물질을 포함해 냉장고 보관온도 범위를 수정해준다
 * 6. isSame = false면 새로운 냉장고를 추가한다
 *
 */

public class Main_J_1828_냉장고 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		ArrayList<Point> result = new ArrayList<Point>();
		
		boolean isSame = false;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int min = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			arr[i][0] = min;
			arr[i][1] = max;
		}
		
		// sorting
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(arr[j][0] < arr[i][0]) {
					int[] temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;					
				}
			}
			
		}
		
		for(int i=0; i<N; i++) {
			if(i==0) {
				//처음에 일단 냉장고 한대 등록
				result.add(new Point(arr[0][0], arr[0][1]));
			}
			
			int a1= arr[i][0];
			int b1= arr[i][1];
			
			for(int j=0; j<result.size(); j++) {
				int a2 = result.get(j).x;
				int b2 = result.get(j).y;
				
				if(a1 >= a2 && a1 <= b2) {
					//같은 냉장고 사용가능
					isSame = true;
					
					int[] ss = {a1, a2, b1, b2};
					Arrays.sort(ss);
					
					result.get(j).x = ss[1];
					result.get(j).y = ss[2];
					
					break;
				}
			}
			
			if(isSame) {
				isSame=false;
				continue;
			}else {
				isSame=false;
				result.add(new Point(a1,b1));
			}
		}
		
		System.out.println(result.size());
		
	}

}

class Point{
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}