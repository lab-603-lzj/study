package designPattern.establish;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) throws IOException {
        double[] car = {28.22,29.38,28.52,26.35,28.98,30.42};
        double[] sum = {547.26,584.74,543.96,258.77,374.66};
        Arrays.asList(test(car,sum)).forEach((tmp)->{
            Arrays.asList(tmp).forEach(System.out::print);
            System.out.println();
        });
        System.out.println("结束");
    }

    static int[][] test(double[] car,double[] sum){
        int[][] result = new int[5][24];
        double sumi = 0d; // 标记i的和
        for (int i=0;i<5;i++){
            int k=0,count=1; // 标记k的位置
            while (k<24 && count>0){
                int j=0;
                while (j<6 && count>0){
                    sumi+=car[j];
                    if (sumi<sum[i]){
                        result[i][k] = j;
                        k++;
                        continue;
                    }else if (sumi==sum[i]){
                        result[i][k] = j;
                        break;
                    }else if (count==1){
                        result[i][k] = -1;
                        break;
                    }else if (j>=5){
                        count--;
                        k--;
                        sumi-=car[j];
                        sumi-=car[result[i][k-1]];
                        result[i][k]=0;
                        break;
                    }else {
                        count = k;
                        sumi-=car[j];
                        j++;
                    }
                }
            }
        }
        return result;
    }
}
