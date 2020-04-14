package com.java.algo.expert;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D4_1251_하나로_Prim {

	/* Prim : 하나의 정점(임의의 정점)에서 연결된 간선들 중에 하나씩 선택하면서 MST를 만들어 가는 방식
	 * 선택한 정점과 인접하는 정점들 중의 최소 비용의 간선이 존재하는 정점을 선택
	 * 정점을 하나씩 선택할 때마다 간선을 추가하면서 트리 확장
	 * => 두 종류의 상호배타집합 정보를 유지
	 * 	  - 트리 정점들 : MST를 만들기 위해 선택된 정점들
	 * 	  - 비트리 정점들 : 선택 되지 않은 정점들
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			//각 정점별로 인접리스트 참조변수
			//인접행렬
			long[][] adj = new long[N][N];
			 int[][] arr = new int[N][2];
	         for (int i = 0; i < N; i++)
	             arr[i][0] = sc.nextInt();
	         for (int i = 0; i < N; i++)
	             arr[i][1] = sc.nextInt();
	         double E = sc.nextDouble();
			//입력되어지는 간선(가중치) 배열을 인접리스트에 저장
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					long c = distance(arr[i][0], arr[j][0], arr[i][1], arr[j][1]);
					adj[i][j] = c;
					adj[j][i] = c;
				}
			}
			boolean[] visited = new boolean[N];
			long[] dist = new long[N];
			Arrays.fill(dist, Long.MAX_VALUE);
			long result = 0, min = 0;
			dist[0] = 0;
			int index;
			//시작정점을 뺀 나머지 정점 수 만큼 반복
			for(int i = 0; i < N-1; i++) {
				min = Long.MAX_VALUE;
				index = -1;
				//아직 안고른 친구중에 젤 거리값이 작은 친구
				for(int j = 0; j < N; j++) {
					if( !visited[j] && dist[j] < min ) {
						min = dist[j];
						index = j;	//가중치가 가장 작은 정점
					}
				}
				for(int j = 0; j <N; j++) {
					//기존에 있던 가중치의 함(dist[j])가  지금 새 정점까지의 가중치보다 크다면
					if( !visited[j] && adj[index][j]!=0 && dist[j] > adj[index][j] ) {
						dist[j] = adj[index][j];
					}
				}
				visited[index] = true;
			}
			for(int i = 0; i < N; i++)
				result += dist[i];
			System.out.println("#" + tc + " " + Math.round(result*E));
		}
	}
	static long distance(int x1, int x2, int y1, int y2) {
        long d = (long)((Math.pow(x1-x2,2) + Math.pow(y1-y2,2)));
        return d;
    }
}
