package com.java.algo.baekjoon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * 13913_숨바꼭질4(3차)
 * 메모리 : 34276KB
 * 시간 : 524ms
 *
 */

public class Main_B_G4_13913_숨바꼭질4_3 {

	private static int N,K;
	private static boolean[] visited;
	private static int[] path;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		visited = new boolean[100001];	//0~100000
		path = new int[100001];
		Arrays.fill(path, -1); 	//그 전에 거쳐온것이 없다는 뜻
		
		//동생이 수빈이보다 앞에있을떄는 x-1밖에 못함
		if(K < N) {
			System.out.println(N-K);
			for(int i=N; i>=K; i--) {
				System.out.print(i+" ");
			}
			return;
		}
		
		//동생이랑 위치가 똑같을때
		if(N == K) {
			System.out.println(0);
			System.out.println(K);
			return;
		}
		
		System.out.println(bfs());
		Stack<Integer> stack = new Stack<Integer>();
		int temp = K;
		do {
			stack.push(temp);
			temp = path[temp];
		}while(temp != N);
		
		StringBuilder sb = new StringBuilder();
		sb.append(N+" ");
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb);
	}
	
	private static int bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[N] = true;	//수빈이 위치
		queue.offer(new int[] {N,0});
		
		int nx;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(cur[0] == K) return cur[1];
			
			//걷는 경우
			//위
			nx = cur[0]-1;	//cur[0]은 현재 위치
			if(nx >= 0 && !visited[nx]) {//나보다 먼저 그 위치에 다녀간 적이 있는지
				visited[nx] = true;
				path[nx] = cur[0];
				queue.offer(new int[] {nx, cur[1]+1});
			}
			//아래
			nx = cur[0]+1;	//cur[0]은 현재 위치
			if(nx <= 100000 && !visited[nx]) {//나보다 먼저 그 위치에 다녀간 적이 있는지
				visited[nx] = true;
				path[nx] = cur[0];
				queue.offer(new int[] {nx, cur[1]+1});
			}
			
			//순간이동 하는 경우
			nx = cur[0]<<1;
			if(nx <= 100000 && !visited[nx]) {//나보다 먼저 그 위치에 다녀간 적이 있는지
				visited[nx] = true;
				path[nx] = cur[0];
				queue.offer(new int[] {nx, cur[1]+1});
			}
		}
		
		//못만났으면 -1리턴
		return -1;
	}
}
