package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 1247_최적경로
 * 메모리 : 25792KB
 * 시간 : 259ms(배열)
 * 풀이
 * 1. 비트마스킹
 *
 */

public class Solution_D5_1247_최적경로_2{
	 
    private static int N;
    static int MIN, CX, CY, HX, HY;
    static int[][] customers;
     
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
 
        for(int test=1; test<=T; test++) {
            MIN = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
             
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            customers = new int[N][2];
            
            CX = Integer.parseInt(st.nextToken());
            CY = Integer.parseInt(st.nextToken());
            HX = Integer.parseInt(st.nextToken());
            HY = Integer.parseInt(st.nextToken());
            
            for(int i=0; i<N; i++) {
                customers[i][0] = Integer.parseInt(st.nextToken());
                customers[i][1] = Integer.parseInt(st.nextToken());
            }
             
            go(0,0,CX,CY,0);
            sb.append("#"+test+" "+MIN+"\n");
        }
         
        System.out.println(sb);
    }
 
    private static void go(int count, int visited, int bx, int by, int result) {
    	
    	if(result >= MIN)return;	
    	
    	if(count == N) {
    		result += Math.abs(bx-HX)+Math.abs(by-HY);
    		if(MIN > result) {
    			MIN = result;
    		}
    		return;
    	}
    	
    	for(int i=0; i<N; i++) {
    		if((visited & 1<<i) == 0) {
    			//visited & 1 << i : i고객집이 기존 순열에 처리되었는지 확인:
    			//0 -> 처리 안됨, 0 아님 -> 처리되었음
    			//visited | (1<<i) : 기존 순열상태에 i고객집 추가
    			go(count+1, visited | (1<<i), customers[i][0], customers[i][1],
    					result+Math.abs(bx-customers[i][0])+Math.abs(by-customers[i][1]));
    		}
    	}
    }
 
}