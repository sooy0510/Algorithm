package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 13458_시험감독
 * 메모리 : 122736KB
 * 시간 : 400ms
 * 풀이
 * 1. 각 시험장의 사람수를 입력받아 배열에 저장
 * 2. 총 감독관이 감시할수 있는 인원만큼 제외시키고
 * 3. 부족하다면 부감독관을 추가해준다
 * -> 남은 인원%부감독 > 0 이라면 나머지 인원을 감시할 한명의 부감독이 더 필요하다는 뜻이므로 몫+1을 해준다.
 */

public class Main_B_B2_13458_시험감독 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());		// N(1 ≤ N ≤ 1,000,000)
		int a[] = new int[N];
		long result = 0L;
		
		StringTokenizer st = new StringTokenizer(br.readLine());	// Ai (1 ≤ Ai ≤ 1,000,000)
		for(int i=0; i<N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());	// (1 ≤ B, C ≤ 1,000,000)
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		
		//각 반마다 필요한 감독관 수 더하기
		for(int i=0; i<N; i++) {
			//총감독관이 감시하고 남은학생수
			a[i] -= B;
			
			//총감독관 한명만으로 충분할경우 감독관수+1 해주고 나가기
			if(a[i] <= 0) {
				result++;
				continue;
			}else {
				//부감독관 수 더하기
				if(a[i]%C == 0) {	//딱 맞으면 몫이 부감독 수
					result += a[i]/C + 1;
				}else {
					result += a[i]/C + 2; 
				}
			}
		}
		
		System.out.println(result);
		
	}

}
