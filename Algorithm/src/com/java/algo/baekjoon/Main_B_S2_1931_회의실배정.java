package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1931_회의실배정
 * 메모리 : 44216KB
 * 시간 : 592ms
 * 코드길이 : 1487B
 * 풀이
 * 1. Greedy
 *
 */

public class Main_B_S2_1931_회의실배정 {

	
	static class Meeting implements Comparable<Meeting>{
		int start, end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			int res = this.end - o.end;
			if(res==0) {	//종료시간이 같으면, 시작시간이 더 빠른순으로
				res = this.start - o.start;
			}
			return res;
		}
	}
	
	
	static int N;	//회의의 수
	static Meeting[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new Meeting[N];
		
		StringTokenizer st = null;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(list);
		
		//선택된 회의 저장
		ArrayList<Meeting> schedule = new ArrayList<Meeting>();
		schedule.add(list[0]);	//선택을 되돌리지 않음
		for(int i=1; i<N; i++) {
			if(schedule.get(schedule.size()-1).end <= list[i].start) {	//다음 회의시작시간이 이전 회의시간과 같거나 클대
				schedule.add(list[i]);
			}
		}
		System.out.println(schedule.size());
		
	}

}
