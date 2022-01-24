package com.example.poi.pdf.itext.util;

import java.util.Arrays;

/**
 * @Description:
 * @author: lzj
 * @date: 2021年12月20日 14:10
 */
public class Test {
    public static String convert1(String s, int numRows){
        char[] chars = s.toCharArray();
        char[][] result = new char[numRows][s.length()];
        int p = 0,k = 0; // p用来确定在哪一行;k用来确定该值在那一列
        for (int i = 0; i < chars.length; i++) {
            // 用来确定是奇数次还是偶数次循环，偶数次往下排，奇数次往右排
            k = i/numRows;
            p = p%numRows;
            if (((i/numRows)%2) == 0){
                result[p][k] = chars[i];
            }else {
                // 如果p等于numRows-k,那么在该处赋值，否则其余的赋值为空。
                int i1 = numRows - 1 - (i % numRows) - 1;
                if (p == i1){
                    result[p][k] = chars[i];
                }else {
                    result[p][k] = ' ';
                }
                if ((p+1) == numRows){
                    continue;
                }else {
                    i--;
                }
            }
            p++;
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(convert1("PAYPALISHIRING",3));
    }
}
