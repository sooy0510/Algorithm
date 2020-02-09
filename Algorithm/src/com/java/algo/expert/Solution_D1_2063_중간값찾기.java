package com.java.algo.expert;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
 

class Solution_D1_2063_중간값찾기 {
    public static void main(String args[]) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
 
        int[] arr = new int[T];
        int idx = 0;
         
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         
        while(st.hasMoreTokens()) {
            arr[idx++] = Integer.parseInt(st.nextToken());
        }
         
        for(int i=0; i<T-1; i++) {
            for(int j=i+1; j<T; j++) {
                if(arr[j] < arr[i]) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
         
        System.out.println(arr[arr.length/2]);
         
    }
     
}