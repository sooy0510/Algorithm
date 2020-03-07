package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_1808_지희의고장난계산기 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			
		}
	}

}

//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.StringTokenizer;
// 
//public class Solution {
// 
//    static final int MAX = Integer.MAX_VALUE;
//    static boolean[] btns = new boolean[10];    // 버튼 유효 배열
//    static int X, answer;
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = null;
//        StringBuilder sb = new StringBuilder();
//         
//        int T = Integer.parseInt(br.readLine());
//         
//        for (int tc = 1; tc <= T; tc++) {
//            answer = MAX;   // 초기값은 MAX로 지정
//             
//            // 입력
//            st = new StringTokenizer(br.readLine());
//            for(int i = 0; i < 10; i++) {
//                btns[i] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
//            }
//            X = Integer.parseInt(br.readLine());
//             
//            // 주어진 버튼으로 만들 수 있는 모든 조합을 미리 구하지 않고
//            // 재귀를 돌리면서 약수를 구하고 그때마다 주어진 버튼으로 만들 수 있는 수인지 확인한다.
//            if (X == 0 && btns[0]) answer = 3;
//            else if (X == 0 && !btns[0]) answer = -1;
//            else {
//                find(X, 1); // Equal(=) 버튼은 항상 누르니까 1부터 시작
//                answer = answer == MAX? -1 : answer;
//            }
//            sb.append("#").append(tc).append(" ").append(answer).append("\n");
//        }
//         
//        System.out.print(sb.toString());
//    }
//     
//    private static int find(int x, int cnt) {
//        // 1) x의 각 자릿수가 주어진 버튼으로 구성할 수 있는지 확인
//        if (isMake(x+"")) {
//            // 만약 구성할 수 있다면 DFS는 종료되어야 한다. (끝)
//            if (cnt == 1) { // 한번에 바로 구하는 경우 
//                answer = cnt + len(x);
//            }
//            return cnt + len(x);    // x의 자릿수를 리턴.
//        }
//         
//        int res = -1;
//        // 2) 2부터 1씩 증가하면서 x의 약수를 찾고 그 약수가 현재 주어진 버튼으로 조합 가능한지 확인
//        for (int i = 2, end = (int) Math.sqrt(x) + 1; i < end; i++) {
//            if (x % i == 0 && isMake(i+"")) {   // 나눌 수 있는 경우
//                int len1 = len(i);  // 약수의 길이(누르는 횟수)를 구하고
//                int len2 = find(x/i, cnt+1);    // 약수로 나눈 몫의 길이를 구한다. (곱하기 버튼을 누르니까 cnt+1을 넘김)
//                if (len2 == -1) continue;       // len2가 -1인 경우 몫이 주어진버튼으로 조합이 안되고 더 나눌수도 없는 경우 (skip)
//                res = len1 + len2;
//                if (res < answer && x == X) answer = res;
//            }
//        }
//        return res;
//    }
//     
//    /* 숫자 num을 String 매개변수로 받아서 각 자리의 수를
//     * 현재 주어진 버튼들로 구성할 수 있는지 여부를 T/F로 리턴한다.
//     */
//    private static boolean isMake(String num) {
//        for (char c : num.toCharArray()) {
//            if (!btns[c-'0']) return false;
//        }
//        return true;
//    }
//     
//    /* 숫자 num의 자릿수를 로그를 취하여 리턴한다. */
//    private static int len(int num) {
//        return (int) Math.log10(num) + 1;
//    }
//}