package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1812_수정이의타일자르기(2차)
 * 메모리 : 84652KB
 * 시간 : 227ms
 * 길이 : 2177B
 * 풀이
 * 1. Comparable - 사이즈가 큰 순서대로
 *
 */

public class Solution_D5_1812_수정이의타일자르기_2 {

	static class Rectangle implements Comparable<Rectangle>{
		int min, max;

		//생성자
		public Rectangle(int width, int height) {
			if(width > height) {
				min = height;
				max = width;
			}else {
				min = width;
				max = height;
			}
		}
		
		@Override
		public int compareTo(Rectangle o) {
			return o.min - this.min; 	//직사각형들을 내림차순으로 정렬
		}
		
		
	}

	private static int N,M;
	private static int[] size;
	static PriorityQueue<Rectangle> queue;
	static int cnt;	//필요한 타일 개수
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			cnt = 0;
			//변의 크기를 담을 size배열
			size = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				size[i] = Integer.parseInt(st.nextToken());
			}
			
			cut();
			sb.append("#"+test+" "+cnt+"\n");
		}
		System.out.println(sb);
	}

	private static void cut() {
		Arrays.sort(size);
		
		//큐에 맨처음 타일 넣어주기
		queue = new PriorityQueue<Rectangle>();
		queue.offer(new Rectangle(M, M));
		++cnt;
		
		for(int i=N-1; i>=0; i--) {
			go(1<<size[i]);	//변의 길이는 2의 제곱만큼
		}
	}

	private static void go(int size) {
		Rectangle r = queue.poll();
		
		//자를수 있다면
		if(r.min >= size) {
			//자르고 나서 다시 넣어주기
			queue.offer(new Rectangle(r.min-size, size));
			queue.offer(new Rectangle(r.min, r.max-size));
		}else {
			//자를 수 없으니까 뺏던 타일 다시 넣기
			queue.offer(r);
			//새로운 타일 사기
			queue.offer(new Rectangle(M-size, size));
			queue.offer(new Rectangle(M, M-size));
			++cnt;
		}
	}

}
