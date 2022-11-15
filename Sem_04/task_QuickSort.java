/* Быстрая сортировка (Тони Хоара)
 * Рекурсивный (рекуррентный) алгоритм сортировки
 * 
 * Идея:
 *  исходный массив делится на 3 части относительно "барьерного" элемента:
 *  - левая часть - все элементы, меньше "бартерного";
 *  - правая часть - все элементы, больше "барьерного";
 *  - срединная часть - сам "барьерный" элемент и равные ему.
 * 
 * Алгоритм:
 * 1. Выбрать элемент из массива "барьерный".
 * 2. Разбиение: перераспределение элементов в массиве таким образом, что элементы, 
 *               меньшие "барьерного", помещаются перед ним, а большие или равные - после.
 * 3. Рекурсивно применить первые два шага к двум подмассивам слева и справа от "барьерного" элемента. 
 *               Рекурсия не применяется к массиву, в котором только один элемент или отсутствуют элементы.
 *
 * Затраты на вычисление 
 *  - W(n log2(n)) на средней выборке
 * Сортирующие действия выполняются на прямом ходу рекурсии
 * 
 * Без дополнительной памяти.
 * 
 * Относится к алгоритмам категории "разделяй и влавствуй"
 */
package Seminars.Sem_04;

public class task_QuickSort {
    // Поля - вместо конструктора сразу присваиваю пустое значение
    private static int myLength = 1;
    private static int[] myArray = new int[myLength];
   
    // Основной метод - вызов выполнения программы
    public static void main(String[] args) {
        myLength = 10;     // 0   1   2  3  4  5  6  7  8  9
        myArray = new int[] {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
        printArray("исходный");
        QickSort(myArray, 0, myLength-1);
        printArray("отсортированный");
    }
    // решение без коллекций, через массив и его индексы
    // рекурсивный просмотр массива относительно барьерного элемента
        //#region
        // sortedArray — сортируемый массив,
        // lowBorder и highBorder — нижняя и верхняя границы сортируемого участка этого массива
        //#endregion
    public static void QickSort(int[] sortedArray, int lowBoundary, int highBoundary){
        // крайний случай, когда 1 элемент: lowBorder == highBorder. 
        // Он же является и условием выхода из рекурсии
        if (lowBoundary < highBoundary){
            int barrier = partition(sortedArray, lowBoundary, highBoundary);
            // Сортировка левой части, относительно барьерного элемента
            QickSort(sortedArray, lowBoundary, barrier);
            // Сортировка правой части, относительно барьерного элемента
            QickSort(sortedArray, barrier + 1, highBoundary);
        }
    }
    // Метод сортровки подмассива относительно разбиения на уровень выше (с возвратом позиции для нового разбиения)
    public static int partition(int[] A, int low, int high){
        int pivot = A[(low + high) / 2]; // барьерным выбран элемент с медианой по индексу
        int i = low, j = high;
        while (true){
            while (A[i] < pivot)
                i = i + 1;
            while (A[j] > pivot)
                j = j - 1;
            if (i >= j){
                //System.out.print(j); System.out.print(" "); 
                //printArray("в процессе разбиения ");
                return j;           // крайний случай (остался условно 1 элемент)
                }
            swap(i++, j--);         // выполняются перестановки, индексы не станут равны 
        }
    }
    // Метод перестановки значений массива, элемент с правым индексом в левый
    public static void swap(int lsIndex, int rsIndex){
        int temp = myArray[lsIndex];    // переставляем максимальное из корня в конец на позицию i
        myArray[lsIndex] = myArray[rsIndex]; // в корень же значение с по одному конца при пошаговом движении в начало на каждой итерации
        myArray[rsIndex] = temp;
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