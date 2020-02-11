package com.java.algo.jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


/**
 * 
 * 1828_냉장고(2차)
 * 메모리 : 8,580KB
 * 실행시간 : 136ms
 * 풀이 
 * 1. Compartor로 배열 정렬
 * 
 */

public class Main_J_1828_냉장고_2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		int[] dp = new int[N];	//몇번째 냉장고인지 담는 배열
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// sorting(comparator 이용 - 객체 정렬) - 오름차순
		// Comparator의 인자는 int[]
		
		// 1. 익명 Comparator 클래스
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
			
		});
		
		// 2. 람다형
		//Arrays.sort(arr, (a,b) -> a[0] - b[0]);
		
		
		int Low = arr[0][0];
		int High = arr[0][1];
		dp[0] = 1;
		
		// 화학물질을 모두 돌면서 기존냉장고를 사용할지, 냉장고를 추가할지 생각한다
		for(int i=1; i<N; i++) {
			
			//포함될 떄
			if(arr[i][0] >= Low && arr[i][1] <= High) {
				Low = arr[i][0];
				High = arr[i][1];
				dp[i] = dp[i-1];
			}
			
			//최저기온이 범위에 들어갈때
			else if(arr[i][0] >= Low && arr[i][0] <= High) {
				//Low변경
				Low = arr[i][0];
				//냉장고는 그대로
				dp[i] = dp[i-1];
			}
			
			//최고기온이 범위에 들어갈떄
			else if(arr[i][1] >= Low && arr[i][1] <= High) {
				//High변경
				High = arr[i][1];
				//냉장고 그대로
				dp[i] = dp[i-1];
			}
			
			//범위 밖(냉장고 추가)
			else {
				Low = arr[i][0];
				High = arr[i][1];
				//냉장고 추가
				dp[i] = dp[i-1]+1;
			}
		}
		
		System.out.println(dp[N-1]);
		
	}

}