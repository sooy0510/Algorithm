package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_G4_17135_캐슬디펜스 {

	static int[][] map;
	static boolean [] visited;
	static int[] gungsu;	//궁수
	static int N,M,D;
	static int MAX = 0;
	static int attackCnt=0;
	//static int[][] result;
	static Stack<int[]> stack;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M];	//N행 바로 아래의 모든 칸에 성이 있음
		visited = new boolean[M];
		gungsu = new int[3];
		stack = new Stack<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		//궁수가 있을 수 있는 성의 위치 조합을 구한다
		combination(0,M,3,0);	//M개 중에서 3개 뽑는 조합
		System.out.println(MAX);
	}

	private static void combination(int index, int n, int r, int target) {
		
		if(r == 0) {
			//3개 뽑는 조합 완성된 상태
			//공격
			System.out.println("================================");
			System.out.println(Arrays.toString(gungsu));
			int[][] copyMap = deepCopy(map);
			attack(copyMap,0);
			return;
		}
		else if(target == n)return;
		else {
			gungsu[index] = target;
			combination(index+1, n, r-1, target+1);
			combination(index, n, r, target+1);
		}
	}

	private static int[][] deepCopy(int[][] original) {
		if(original == null)return null;
		int[][] result = new int[original.length][original[0].length];
		
		for(int i=0; i<original.length; i++) {	//행단위로 copy
			System.arraycopy(original[i], 0, result[i], 0, original[0].length);
		}
		return result;
	}

	private static void attack(int[][] copymap, int attackcnt) {	//무찌른 적의수
		boolean kill;
		Queue<int[]> targetEnemies = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				System.out.println("o1_d : "+ o1[2]+" / o2_d : "+o2[2]);
				if(o1[2] == o2[2]) {	//거리가 같다면
					//더 왼쪽순
					return o1[1] - o2[1];
				}else {//더 가까운순
					return o1[2] - o2[2];
				}
			}
		});
		
		//모든 적이 격자판에서 제외되면 게임이 끝남
		int enemies = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(copymap[i][j] == 1) {
					enemies++;
				}
			}
		}
		
		if(enemies == 0) {
			MAX = Math.max(MAX, attackcnt);
			return;
		}
		//////////////////////////////////////////
		
		for(int g=0; g<3; g++) {	//궁수마다 돌면서	
			//가까운거 먼저 죽이기
			L:for(int i=N-1; i>=0; i--) {	
				for(int j=0; j<M; j++) {	//적이 나타나면 공격
					if(copymap[i][j] == 1) {
						int d = (int)(Math.abs(N-i) + Math.abs(gungsu[g]-j));
						//System.out.println(d);
						if(d <= D) {	//거리가 D이하면, 더 가까운 거리에 있는 적을 죽여야함
							//일단 우선순위 큐에 다 담아놓음
							System.out.println("enemy_x : "+ i+" / enemy_y : "+j+" / distance : "+d);
							int[] enemy = {i,j,d};
							targetEnemies.offer(enemy);
							
							
//							if(copymap[i][j] == 2) { //이미 무찌른적
//								//큐를 확인한다
//								break L;
//							}else {
//								copymap[i][j] = 2;	// 적을 무찔럿다
//								attackcnt++;
//								break L;
//							}
						}
					}
				}
			}
		
			
			for (int[] is : targetEnemies) {
				System.out.println(is[0] + " " + is[1]);  
			}
			
			
			//그런데 다른 궁수가 똑같은 적 죽일 수 잇따,,! => result배열에 좌표 넣어주기
			if(!targetEnemies.isEmpty()) {	
				int[] pol = targetEnemies.poll();
				int row = pol[0];
				int col = pol[1];
				//result[g][0] = row;
				//result[g][1] = col;
				int[] one = {row, col};
				stack.add(one);
//				copymap[row][col] = 0;
//				attackcnt++;
			}
			
			//큐 초기화
			targetEnemies.clear();
		
		}
		
		for(int i=0; i<stack.size(); i++) {
			System.out.println(Arrays.toString(stack.get(i)));
		}

		
		int x = 0,y=0;
//		for(int i=0; i<stack.size(); i++) {
//			//중복 제거하고 attackCnt 올려주기
//			if(i==0) {
//				attackcnt++;
//			}else {
//				if((stack.get(i)[0] == stack.get(i-1)[0]) && (stack.get(i)[1] == stack.get(i-1)[1]))
//					continue;
//				else {
//					copymap[stack.get(i)[0]][stack.get(i)[1]] = 0;
//					attackcnt++;
//				}
//			}
//		}
		
		while(!stack.isEmpty()) {
			int[] ss = stack.pop();
			
			if(x == ss[0] && y == ss[1]) {
				continue;
			}
			else {
				x = ss[0];
				y = ss[1];
				attackcnt++;
			}
		}
		
		//result 배열 초기화
//		for(int i=0; i<3; i++) {
//			Arrays.fill(result[i], 0);
//		}
		//stack.clear();
		
		System.out.println(attackcnt);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(copymap[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		// 한 턴 끝낫으면 적이 한 줄 씩 내려옴
		//int[] tmp = copymap[N-1];
 		for(int i=N-1; i>=1; i--) {
 			copymap[i] = Arrays.copyOfRange(copymap[i-1], 0, M);
		}
 		Arrays.fill(copymap[0], 0);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(copymap[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		//System.out.println("================================");
		attack(copymap, attackcnt);
		
	}

}
