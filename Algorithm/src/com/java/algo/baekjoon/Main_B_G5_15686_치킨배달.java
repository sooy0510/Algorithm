package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 15686_치킨배달
 * 메모리 : 15044KB
 * 시간 : 152ms
 * 길이 : 2260B
 * 풀이
 * 1. 조합
 */

public class Main_B_G5_15686_치킨배달 {

	static class Point{
		int i,j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
	
	private static int N,M;
	private static int[][] map;
	private static List<Point> chickenList;
	private static List<Point> houseList;
	private static Point[] posList;
	private static int MIN;

	public static void main(String[] args) throws Exception{
		//1: 집, 2: 치킨집
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());	//최대 치킨집 개수
		
		map = new int[N][N];
		MIN = Integer.MAX_VALUE;
		
		int temp;
		int chicken=0;
		chickenList = new ArrayList<Point>();
		houseList = new ArrayList<Point>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				temp = st.nextToken().charAt(0)-'0';
				map[i][j] = temp;
				if(temp == 2) {
					chickenList.add(new Point(i,j));
					chicken++;
				}else if(temp == 1) {
					houseList.add(new Point(i,j));
				}
			}
		}
		
		posList = new Point[M];
		if(chicken == M) {	//존재하는 치킨집 수와 최대 치킨집 수가 같으면 치킨 거리 구하기
			for(int i=0; i<M; i++) {
				posList[i] = chickenList.get(i);
			}
			
			MIN = Math.min(MIN, getDistance(posList));
		}else {
			//치킨집 조합
			combination(0,0);
		}
		
		System.out.println(MIN);
	}

	private static int getDistance(Point[] list) {
		//각 집과, 각 치킨집의 거리를 구함
		int sum = 0;
		
		for (Point house : houseList) {
			int min = Integer.MAX_VALUE;
			for (Point chicken : list) {
				int dis = Math.abs(house.i-chicken.i) + Math.abs(house.j-chicken.j);
				min = Math.min(min, dis);
			}
			sum += min;
		}
		
		return sum;
	}

	private static void combination(int idx, int count) {
		
		if(count == M) {
			MIN = Math.min(MIN, getDistance(posList));
			return;
		}
		
		for(int i=idx; i<chickenList.size(); i++) {
			posList[count] = chickenList.get(i);
			combination(i+1, count+1);
		}
	}

}
