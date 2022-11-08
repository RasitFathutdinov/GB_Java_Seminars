// Урок 2. Почему вы не можете не использовать API
// Решаемое задание. Реализовать алгоритм сортировки вставками

// Теоритическая часть
// Сортировка вставками удобна для сортировки коротких последоваельностей.
// в левой части упорядоченная подпоследовательность, с правой части берётся
// очередной элемент и сравнивается с имеющимися в левой подпоследовательности,
// проходя подпоследовательность слева направо
// Последовательность сортируется без дополнительной памяти: 
// помимо массива используется только фиксированное число ячеек памяти
// 1 - в первую позицию помещается наименьший элемент массива
// 2 - во внутреннем цикле (while по i) выполняет лишь одну операцию присваивания, а не обмена
// 3 - прекращает выполнение внутреннего цикла, когда вставляемый элемент уже на нужной позиции
/*  Демонстрация работы алгоритма (сортировка по возрастанию)
    0   1   2   3   4   5 
        х
    5   2   4   6   1   3 до
    2   5   4   6   1   3 после
            х
    2   5   4   6   1   3 до
    2   4   5   6   1   3 после
                х
    2   5   4   6   1   3 до
    2   4   5   6   1   3 после
                    х
    2   5   4   6   1   3 до
    1   2   4   5   6   3 после
                        х
    2   5   4   6   1   3 до
    1   2   3   4   5   6 после
*/

package Seminars.Sem_02;

public class task_sortInsertion {
    // Поля - вместо конструктора: выделение памяти
    private static int myLength = 2;
    private static int[] myArray = new int[myLength];

    public static void main(String[] args) {
        myLength = 6 ;     // 0   1   2   3   4   5
        myArray = new int[] { 5,  2,  4,  6,  1,  3};
        printArray("исходный");
        insertSort();
        printArray("отсортированный");
    }
    // Метод сортировки вставками
    public static void insertSort(){
        int key = 0;   // ключ - врем. переменная для добавления элемента массива в левую отсортиров. часть
        for(int j = 1; j < myLength; j++){
            key = myArray[j];
            // добаление ключа в нужную позицию левой отсортированной подполедовательности
            int i = j - 1;
            while(i >= 0 && myArray[i] > key){ // сдвиг левой подпоследовательности: обход с наибольших с правого края 
                myArray[i+1] = myArray[i];
                --i;                           //пошаговый сдвиг к левой границе, при нахождении меньшего, чем ключ
            }
            myArray[i + 1] = key; // на своём месте при отсутсвии меньших в левой подпоследоваетльности   
            // String strOut = "- подпоследовательность длины " + (j+1) + " -> ";
            // printArray(strOut);
        }
    }
    // Печать массива
    public static void printArray(String someStr){
        StringBuffer strBuffer = new StringBuffer("Массив " + someStr + " = [");
        for(int j = 0; j < myLength-1; ++j){
            strBuffer.append(" " + myArray[j] + ",");
        }
        strBuffer.append(" " + myArray[myLength-1] + " ]");
        System.out.println(strBuffer);
    }
}