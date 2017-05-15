import java.util.Scanner;
public class Glorin{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int numbers; //输入长度
        numbers = s.nextInt();
        int h = 0; //行
//在距阵中，1在第一行正中
        int l = numbers / 2; //列
        int[][] a = new int[numbers][numbers];
        for (int i = 1; i <= numbers * numbers; i++)
        {
            a[h][l] = i; //运行提示溢出
//随后的数字应放到上一个数字的右上方方格中
            h--;
            l++;
//3.如果都不行，就放到上一个数字的正下方
            if (h < 0 && l >= numbers)
            {
//先返回上一个数字
                h++;
                l--;
//再下移一行
                h++;
            }
//1.如果向上不行，就放到该列的最下方格子
            else if (h < 0)
            {
                h = numbers - 1;
            }
//2.如果向右不行，就放到该行的最左边
            else if (l >= numbers)
            {
                l = 0;
            }
//4.如果目标格子中已经有数字，也放到上一个数字的正下方
            else if (a[h][l] > 0)
            {
//先返回上一个数字
                h++;
                l--;
//再下移一行
                h++;
            }
        }
//打印九宫格
        for (int j = 0; j < numbers; j++)
        {
            for (int k = 0; k < numbers; k++)
            {
                System.out.print(a[j][k] + " ");
            }
//换行
            System.out.println();
        }
    }
}
