package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1062_가르침
 * 메모리 : 14964KB
 * 시간 : 268ms
 * 길이 : 1682B
 * 풀이
 * 1. 조합, dfs
 */

public class Main_B_G4_1062_가르침 {

	private static int N,K;
	private static char[][] words;
	private static int MAX;
	private static boolean[] letters = {	//acint 는 꼭 배워야하는 필수문자
			true, false, true,
			false, false, false,
			false, false, true,
			false, false, false,
			false, true, false,
			false, false, false,
			false, true, false,
			false, false, false,
			false, false
	};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new char[N][];
		for(int i=0; i<N; i++) {
			words[i] = br.readLine().toCharArray();
		}
		
		//조합
		combi('a',5);
		
		System.out.println(MAX);
	}

	private static void combi(int c, int cnt) {
		if(cnt == K) {
			check();
			return;
		}
		
		if(c > 'z')return;
		
		//문자(c)를 안배울 때
		combi(c+1, cnt);
		
		//문자(c)를 배울때
		int idx = c - 'a';
		if(!letters[idx]) {
			letters[idx] = true;
			combi(c+1, cnt+1);
			letters[idx] = false;
		}
	}

	private static void check() {
		int count = 0; 		//읽은 단어 수 세기
		for(int i=0; i<N; i++) {
			boolean isRead = true;
			for(int j=0, size = words[i].length; j<size; j++) {
				int letter = words[i][j] - 'a';
				if(!letters[letter]) {	//하나라도 안배운 글자 있으면 읽을수없는 단어
					isRead = false;
					break;
				}
			}
			if(isRead) {
				count++;
			}
		}
		MAX = Math.max(MAX, count);
	}

}
