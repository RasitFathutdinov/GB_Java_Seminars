
// Урок 2. Почему вы не можете не использовать API
// Решаемое задание. Написать программу, 
// показывающую последовательность действий для игры “Ханойская башня”

// Теоритическая часть
/*
Имеется:
    3 стержня
    N дисков, ктр помещаются на 3х стержнях (№ 1, № 2, № 3)
Диски 
    - различаются размерами
    - в начале размещаются на одном из стержней 
        от самого большого (диск N) внизу до самого маленького (диск 1) наверху
Нужно:
    Переместить диски на соседнюю позицию (стержень) при соблюдении следующих правил:
    1. Одновременно можно переложить только один диск
    2. Ни один диск нельзя положить на диск меньшего размера
 "-"" сдвиг диска влево
 "+"" сдвиг диска вправо 
*/
//#region пример с 5-ю дисками
// Пример решения c 5 дисками 
/*         стержень.1  стержень.2  стержень.3
 *         1-2-3-4-5   0-0-0-0-0   0-0-0-0-0
 *         Перемещение N-1 дисков влево
 * + д.1   0-2-3-4-5   0-0-0-0-1   0-0-0-0-0  
 * - д.2   0-0-3-4-5   0-0-0-0-1   0-0-0-0-2
 * + д.1   0-0-3-4-5   0-0-0-0-0   0-0-0-1-2
 * + д.3   0-0-0-4-5   0-0-0-0-3   0-0-0-1-2
 * + д.1   0-0-1-4-5   0-0-0-0-3   0-0-0-0-2
 * - д.2   0-0-1-4-5   0-0-0-2-3   0-0-0-0-0
 * + д.1   0-0-0-4-5   0-0-1-2-3   0-0-0-0-0 
 * - д.4   0-0-0-0-5   0-0-1-2-3   0-0-0-0-4  
 * + д.1   0-0-0-0-5   0-0-0-2-3   0-0-0-1-4  
 * - д.2   0-0-0-2-5   0-0-0-0-3   0-0-0-1-4 
 * + д.1   0-0-1-2-5   0-0-0-0-3   0-0-0-0-4 
 * + д.3   0-0-1-2-5   0-0-0-0-0   0-0-0-3-4
 * + д.1   0-0-0-2-5   0-0-0-0-1   0-0-0-3-4
 * - д.2   0-0-0-0-5   0-0-0-0-1   0-0-2-3-4
 * + д.1   0-0-0-0-5   0-0-0-0-0   0-1-2-3-4   <- Готово "влево"
 *  *      Перекладываем N-й нижний диск вправо
 * + д.5   0-0-0-0-0   0-0-0-0-5   0-1-2-3-4
 *         Перемещаем башню поверх N-го нижнего диска
 * + д.1   0-0-0-0-1   0-0-0-0-5   0-0-2-3-4
 * - д.2   0-0-0-0-1   0-0-0-2-5   0-0-0-3-4
 * + д.1   0-0-0-0-0   0-0-1-2-5   0-0-0-3-4
 * + д.3   0-0-0-0-3   0-0-1-2-5   0-0-0-0-4
 * + д.1   0-0-0-0-3   0-0-0-2-5   0-0-0-1-4
 * - д.2   0-0-0-2-3   0-0-0-0-5   0-0-0-1-4
 * + д.1   0-0-1-2-3   0-0-0-0-5   0-0-0-0-4
 * - д.4   0-0-1-2-3   0-0-0-4-5   0-0-0-0-0
 * + д.1   0-0-0-2-3   0-0-1-4-5   0-0-0-0-0
 * - д.2   0-0-0-0-3   0-0-1-4-5   0-0-0-0-2
 * + д.1   0-0-0-0-3   0-0-0-4-5   0-0-0-1-2
 * + д.3   0-0-0-0-0   0-0-3-4-5   0-0-0-1-2
 * + д.1   0-0-0-0-1   0-0-3-4-5   0-0-0-0-2
 * - д.2   0-0-0-0-1   0-2-3-4-5   0-0-0-0-0
 * + д.1   0-0-0-0-0   1-2-3-4-5   0-0-0-0-0   <- Готово
 */
//#endregion
//#region пример с 3-мя дисками
// Пример решения c 3 дисками
 /*       № 1       № 2       № 3
 *       1-2-3     0-0-0     0-0-0
 * Перемещение N-1 дисков влево
 * + 1   0-2-3     0-0-1     0-0-0  
 * - 2   0-0-3     0-0-1     0-0-2
 * + 1   0-0-3     0-0-0     0-1-2  <- Готово "влево"
 * Перекладываем N-й нижний диск вправо 
 * + 3   0-0-0     0-0-3     0-1-2
 * Перемещаем башню поверх N-го нижнего диска
 * + 1   0-0-1     0-0-3     0-0-2
 * - 2   0-0-1     0-2-3     0-0-0
 * + 1   0-0-0     1-2-3     0-0-0  <- Готово
 * 
 *         i    +    k   +   temp  =  6
 *  i - столбец, с которого нужно переставить
 *  k - столбец, на который нужно переставить
 */
//#endregion
 
package Seminars.Sem_02;
import java.util.Scanner;

public class task_hanojTowers {
    // Поля
    private static int n = 3, i = 1, k = 2;

    public static void main(String[] args) {
        n = getNumberFromConsole();     //количество дисков с консоли
        hanoj(n, i, k);                 //рекурсивное решение задачи
    }
    // Рекурсивный метод с выводом ходов решения
    // диск, перекладывемый (n)  - откуда перекладываем (i)  - куда перекладываем (k)     
    public static void  hanoj(int n, int i, int k){
        StringBuffer strBuffer = new StringBuffer("");
        if (n == 1){                // если 1 диск - условие выхода из рекурсии
            strBuffer.append("Перемещение диска " + 1 + " с стержня № " + i + " на № " + k );
            System.out.println(strBuffer);
        }
        else
        {                           // рекурсия
            int temp = 6 - i - k;   // подсчёт диска, куда можем переложить на следующем ходу
            hanoj(n-1, i, temp);    
            strBuffer.append("Перемещение диска " + n + " с стержня № " + i + " на № " + k );
            System.out.println(strBuffer);
            hanoj(n-1, temp, k);
        }
    }
    // Метод для чтения данных с консоли
    public static int getNumberFromConsole(){
        //Получение данных из терминала 
        Scanner iScanner = new Scanner(System.in);
        boolean flag = false;
        int number = 0;
        while (!flag){   
            System.out.printf("\nВведите количество дисков на стержне и нажмите enter: ");
            flag = iScanner.hasNextInt(); // проверка данных с консоли на принадлежность целому числу
            if (flag){
                number = iScanner.nextInt();
                if (number <= 0){   flag = false;   } // если ввели "0" или отрицательное - меняем флаг
            }
            if(!flag){  System.out.println("Некорректный вод. Нужно ввести натуральное число (больше 0)!");  }
        }
        iScanner.close();
        return number;
    }
}