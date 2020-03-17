package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1812_수정이의타일자르기
 * 메모리 : 84652KB
 * 시간 : 227ms
 * 길이 : 2177B
 * 풀이
 * 1. Comparable - 사이즈가 큰 순서대로
 *
 */

public class Solution_D5_1812_수정이의타일자르기 {

	static class Rectangle implements Comparable<Rectangle>{
		int min, max;
		public Rectangle(int width, int height) {
			if(width < height) {
				min = width;
				max = height;
			}else {
				min = height;
				max = width;
			}
		}
		@Override
		public int compareTo(Rectangle o) {
			return o.min - this.min;
		}
	}

	static int N,M,size[], cnt;
	static PriorityQueue<Rectangle> queue;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			size = new int[N];
			cnt = 0;
			
			st = new StringTokenizer(br.readLine()," ");
			for(int i=0; i<N; i++) {
				size[i] = Integer.parseInt(st.nextToken());
			}//만들고자하는 정사각형의 크기(2^k)입력(k)
			
			cut();
			sb.append("#").append(test).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void cut() {
		//가장 큰 크기의 정사각형부터 만들기
		Arrays.sort(size);
		queue = new PriorityQueue<Rectangle>();
		queue.offer(new Rectangle(M, M));//한장 산것
		++cnt;
		
		for(int i=N-1; i>=0; i--) {//가장큰 크기의 정사각형부터
			go(1<<size[i]);	//1<<2 //시프트 할때마다 제곱씩 커짐
		}
	}

	private static void go(int size) {
		//queue에서 poll() : min값이 최대인 직사각형이 뽑아짐
		//직사각형의 최소변의 길이가 size보다 같거나 크면 : 원하는 크기의 정사각형을 만들 수 있음
		Rectangle r = queue.poll();
		if(r.min >= size) {
			queue.offer(new Rectangle(r.min-size, size));
			queue.offer(new Rectangle(r.min, r.max-size));
		}else {	//아니면 : 원하는 크기의 정사각형을 만들 수 없음 =>새타일을 사야함
			queue.offer(r);	
			queue.offer(new Rectangle(M-size, size)); //자르고 남은거 넣어주기
			queue.offer(new Rectangle(M, M-size));
			++cnt;
		}
		
	}

}
