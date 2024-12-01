import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        //вводим значения для n,m с клавиатуры//
        int n = in.nextInt();
        int m = in.nextInt();
        //сосдаю матрицу(двумерный массив)//
        int[][] matrix = new int[n][m];
        //инициализирую каждый элемент в матрице//
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        //просто вывожу матрицу чтоб посмотреть//
        out.println("Изначальная матрица");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
        //ввожу переменную для поиска максимальной суммы подматрицы 3х3//
        int mx = -111111000;
        //поиск максимальной суммы элементов подматрицы 3х3//
        for (int i = 0; i < (n - 2); i++) {
            for (int j = 0; j < (m - 2); j++) {
                int mx1 = matrix[i][j] + matrix[i + 1][j] + matrix[i + 2][j] + matrix[i][j + 1] + matrix[i][j + 2] + matrix[i + 1][j + 1] + matrix[i + 2][j + 1] + matrix[i + 1][j + 2] + matrix[i + 2][j + 2];
                if (mx1 > mx) {
                    mx = mx1;
                }
            }
        }
        //создаю новую матрицу 3х3 для подматрицы с максимальной суммой(mx)//
        int[][] matrix1 = new int[3][3];
        for (int i = 0; i < (n - 2); i++) {
            for (int j = 0; j < (m - 2); j++) {
                int mx1 = matrix[i][j] + matrix[i + 1][j] + matrix[i + 2][j] + matrix[i][j + 1] + matrix[i][j + 2] + matrix[i + 1][j + 1] + matrix[i + 2][j + 1] + matrix[i + 1][j + 2] + matrix[i + 2][j + 2]; //вспомогательная переменная для суммы текущей строки
                if (mx1 == mx) { //если текущая сумма равна максимальной, то начинаю инициализировать подматрицу 3х3
                    //вручную инициализирую все элементы подматрицы//
                    matrix1[0][0] = matrix[i][j];
                    matrix1[0][1] = matrix[i][j + 1];
                    matrix1[0][2] = matrix[i][j + 2];
                    matrix1[1][0] = matrix[i + 1][j];
                    matrix1[2][0] = matrix[i + 2][j];
                    matrix1[1][1] = matrix[i + 1][j + 1];
                    matrix1[2][2] = matrix[i + 2][j + 2];
                    matrix1[1][2] = matrix[i + 1][j + 2];
                    matrix1[2][1] = matrix[i + 2][j + 1];
                }
            }
        }
        //вывод подматрицы 3х3 с максимальной суммой элементов//
        out.println("Подматрица 3х3 с максимальной суммой элементов");
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                out.print(matrix1[i][j] + " ");
            }
            out.println();
        }
        //сортировка исходной матрицы по возрастанию суммы элементов в строках методом пузырька//
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) { //создаём дополнительный цикл для сортировки
                int sum1 = 0;//создаю 2 новые переменные для подсчета сумм элементов строк
                int sum2 = 0;
                for (int k = 0; k < matrix[j].length; k++) {
                    sum1 += matrix[j][k];
                }
                for (int ss = 0; ss < matrix[j + 1].length; ss++) {
                    sum2 += matrix[j + 1][ss];
                }
                if (sum1 > sum2) { //меняем местами,если условие выполняется
                    int[] tmp = matrix[j + 1];
                    matrix[j + 1] = matrix[j];
                    matrix[j] = tmp;
                }
                //если сумма равна, сортирую элементы каждой строки по убыванию тоже методом пузырька//
                else if (sum1 == sum2) {
                    int[] tmp1 = matrix[j + 1];
                    for (int ii = 0; ii < tmp1.length - 1; ii++) {
                        for (int jj = 0; jj < tmp1.length - ii - 1; jj++) {
                            if (tmp1[jj] > tmp1[jj + 1]) {
                                int temp = tmp1[jj];
                                tmp1[jj] = tmp1[jj + 1];
                                tmp1[jj + 1] = temp;
                            }
                        }
                    }
                    matrix[j + 1] = tmp1;
                    int[] tmp2 = matrix[j];
                    for (int ii = 0; ii < tmp2.length - 1; ii++) {
                        for (int jj = 0; jj < tmp2.length - ii - 1; jj++) {
                            if (tmp2[jj] > tmp2[jj + 1]) {
                                int temp = tmp2[jj];
                                tmp2[jj] = tmp2[jj + 1];
                                tmp2[jj + 1] = temp;
                            }
                        }
                    }
                    matrix[j] = tmp2;
                }
            }
        }
        //вывод матрицы просто посмотреть//
        out.println("Отсортированная матрица");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
        //ввожу вспомогательные переменные для дальнейшего вывода элементов "змейкой"//
        int indexn = 0;
        int indexm = 0;
        int s = 0;
        //вывод "змейкой"//
        out.println("Вывод элементов матрицы змейкой ");
        if (n <= m) {//1 случай - если столбцов больше или равно строк
            while (indexn < n && indexm < m) {
                if (s % 2 == 0) {
                    out.print(matrix[indexn][indexm] + " ");
                    indexm++;

                }
                if (s % 2 == 1) {
                    out.print(matrix[indexn][indexm] + " ");
                    indexn++;

                }
                s++;
            }
        }
        else {//второй случай - если строк больше столбцов
            while (indexn < m && indexm < m) {
                if (s % 2 == 0) {
                    out.print(matrix[indexn][indexm] + " ");
                    indexm++;
                }
                if (s % 2 == 1) {
                    out.print(matrix[indexn][indexm] + " ");
                    indexn++;
                }
                s++;
            }

        }
        //замена отрицательных элементов на их квадраты//
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < 0) {
                    matrix[i][j] = matrix[i][j] * matrix[i][j];
                }
            }
        }
        out.println(" ");
        //вывод обновленного массива//
        out.println("Обновленный массив");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                out.print(matrix[i][j] + " ");
            }
            out.println();
        }
    }
}


