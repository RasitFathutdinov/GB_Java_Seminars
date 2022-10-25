// Написать программу вычисления n-ого треугольного числа. url
//#region
/*
Треугольное число — один из классов фигурных многоугольных чисел, 
определяемый как число точек, которые могут быть расставлены в форме правильного треугольника. 
Как видно из рисунка, n-е треугольное число T(n) — это сумма n-первых натуральных чисел. 
    T(1) = 1              = 1
    (2) =  1 + 2          = 2 
    T(3) = 1 + 2 + 3      = 6
    T(4) = 1 + 2 + 3 + 4  = 10
Арифметическая прогрессия:
S(n) = n * (2*a(1) +d*(n-1)) / 2) = {a(1) = 1, d = 1} = n * (2 + n - 1)/2 = (2*n + n*n - n)/2 = n (n+1) / 2
    T(n) = 1 + 2 +...+ n  =  n*(n+1) / 2
  */ 
//#endregion

package Seminars.Sem_01;
import java.util.Scanner;

public class task_TriangleNumbers {
    //Поля: вводимое и вычисляемое значение
    private static int n = 0, triangleNumber = 0;
    // Основной метод - вызов выполнения программы
    public static void main(String[] args) {
        //Получение наурального числа из терминала (далее проверку n > 0 не провожу )
        n = getNumberFromConsole();
        // упращённая формула подсчёта суммы первых n членов арифмет. прогрессии (при d1 = 1; d = 1)
        if (n == 1)
            triangleNumber = n;
        else
            triangleNumber = n * (n + 1) / 2; 
        // вывод данных
        printTriangleNumberToConsole();
        // вывод в консоль триангулярных чисел
        // printTriangleNumbersToConsole(n);
    }
    // Метод чтения натурального числа из консоли
    public static int getNumberFromConsole(){
        //Получение данных из терминала 
        Scanner iScanner = new Scanner(System.in);
        boolean flag = false;
        int number = 0;
        while (!flag){   
            System.out.printf("\nВведите порядковый номер \"n вычисляемого треуголного числа \n(n натуральное) и нажмите enter: ");
            flag = iScanner.hasNextInt(); // проверка данных с консоли на принадлежность целому числу
            if (flag){
                number = iScanner.nextInt();
                if (number <= 0){   flag = false;   } // если ввели "0" или отрицательное - меняем флаг
            }
            if(!flag){  System.out.println("Некорректный вод. Нужно ввести натуральное число!");  }
        }
        iScanner.close();
        return number;
    }
    // Метод для вывода результатов расчётов в консоль (терминал) 
    public static void printTriangleNumberToConsole(){
        String res = String.format("Вычисленное треугольное число T(%d) = %d\n", n, triangleNumber);
        System.out.println(res);
    }
    // Метод печати триангулярных чисел от 1 до n
    public static void printTriangleNumbersToConsole(int number){
        if (n > 1){
            for(int i = 1; i <= number; ++i){
                StringBuilder sOut = new StringBuilder("");
                for(int j = 1; j <= i; ++j){
                    sOut.append(' ');
                    sOut.append(j);
                }
                System.out.println(sOut);
            }
        }
        else{
            System.out.println("Для вывода триангулярных чисел нужно натуральное число!");
        }
    }
}
