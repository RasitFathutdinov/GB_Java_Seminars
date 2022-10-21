package Seminars.Sem_01;

//#regionregion
/*
Задание семинара № 1.

Дано:
    + На вход некоторому исполнителю подаётся два числа ("a", "b"). У исполнителя есть "две команды"
    - "команда 1" (к1): увеличить "а" в "с" раз, т.е. "а" умножается на "c"
    - "команда 2" (к2): увеличить "а" на "d", к "a" прибавляется "d"

Нужно:
    Написать программу, которая "выдаёт набор команд", позволяющий число "a" превратить в число "b" ( а-> b)
                        или сообщить, что это невозможно

    Пример 1: а = 1, b = 7, c = 2, d = 1
    ответ: к2, к2, к2, к2, к2, к2, k2 или к1, к1, к2, к2, к2 
    Примечание. Оптимальным должно быть то решение, которое по максимуму использует возможность той операции, 
                которая даёт наибольшее приближение к числу "b"
                value * d или value + c, т.е. ищем до того момента, пок не превышаем. 
                Превысили, откат на 1 шаг назад и анализируем разницу, которая даст желаемое число

Можно начать с более простого – просто подсчёта общего количества вариантов 

    Пример 2: а = 11, b = 7, c = 2, d = 1
    ответ: нет решения. 
    Примечание. а > b => невозможно a->b без операций "вычетание", "деление"

*Подумать над тем, как сделать минимальное количество команд


        путь 1  2   3  4 
        рез  0  1   0  1
        опер 2  4   2  6  
*/
//#endregion
public class exam_01 {
    // Переменные (поля) основные
    static int a = 1, b = 7;
    static int c = 2,  d = 1;
    // Переменные (поля) для вычислений
    static boolean way_is_good = false, flag_to_continue = true;
    static int count_operation = 0;
    static String commands_of_way = "";

    public static void main(String[] args) {

        //Путь 1 - команда умножения
        int temp_val = a;
        System.out.println("\nПуть 1 - команда умножения"); 
        count_operation = 0;
        commands_of_way = "";
        temp_val = call_command("k1", a, -1);
        print_result(temp_val);
        // Можно решить через геометрическую прогрессию 
        // bn = b1*q^(n-1) или b = a*c^(n-1)  или logс(b) = logс(a)+(n-1)  или n = logc(b/a)+1
        // если n целое - есть решение

        //Путь 2 - команда сложения
        System.out.println("\nПуть 2 - команда сложения");
        count_operation = 0;
        commands_of_way = ""; 
        temp_val = call_command("k2", a, -1);
        print_result(temp_val);
        // Можно решить через арифметическую прогрессию - 
        // an = a1+(n-1)*d   или  b = a+(n-1)*d   =>   n = (b+d-a)/d
        // если n целое - есть решение

        /*
        Путь 3
        Ищем крайнее количество произведений потом дополняем сложением: комбинация геометрической и арифметической прогрессии
        */
        System.out.println("\nПуть 3 - комбинация прогрессий: геометрическая, затем арифметическая"); 
        double log_data  = b/a;
        double n =  Math.log10(log_data)/Math.log10(c);
        int int_part =  (int)(Math.round(n));
        double residual = n % 1;
        if (residual > 0.0000001){
            count_operation = 0;
            commands_of_way = "";
            temp_val = call_command("k1", a, int_part);
            temp_val = call_command("k2",  temp_val, -1);
            print_result(temp_val);
        }
        System.out.println("\n"); 

        /*
        Путь 4
        Применяем сложение пока дальше не решится умножением: комбинацию арифметической и геометрической прогрессии
        */

 
    }
    // Метод для команды - к1
    public static int command_mult(int value, int addition){
        return value * addition;

    }
    // Метод для команды - к2
    public static int command_add(int value, int addition){
        return value + addition;
    }
    // Метод для вычисления через цикл, применяя одну команду - не используется
    public static int call_command(String command){
        way_is_good = false; 
        flag_to_continue = true;
        count_operation = 0;
        commands_of_way = "";
        int temp_val = a;
        while (flag_to_continue){
            //применение команды
            if (command == "k1"){
                temp_val = command_mult(temp_val, c);
                commands_of_way += "k1 ";
            }
            else{
                temp_val = command_add(temp_val, d);
                commands_of_way += "k2 ";
            }
                ++count_operation; 
            //проверка результата применения команды
            if (temp_val > b){
                flag_to_continue = false;
            }
            else if (temp_val == b){
                way_is_good = true;
                flag_to_continue = false;
            }
        }
        return temp_val;
    }
    // Перегруженный метод для вычисления через цикл, применяя одну команду,
    // с реализацией цикла со счётчиком и условного цикла. Условный нужен для формирокания ряда к1 и к2
    // без формирования л1 и к2 можно было бы применить формулы прогрессий и вывести n*Kх команд 
    public static int call_command(String command, int temp_val, int iteration){
        if (iteration > 0){
            for (int i = 1; i < iteration; ++i){
                //применение команды
                if (command == "k1"){
                    temp_val = command_mult(temp_val, c);
                    commands_of_way += "k1 ";
                }
                else{
                    temp_val = command_add(temp_val, d);
                    commands_of_way += "k2 ";
                }
                    ++count_operation; 
                //проверка результата применения команды
                if (temp_val == b){way_is_good = true;}
             }
        }
        else{
            flag_to_continue = true;
            while (flag_to_continue){
                //применение команды
                if (command == "k1"){
                    temp_val = command_mult(temp_val, c);
                    commands_of_way += "k1 ";
                }
                else{
                    temp_val = command_add(temp_val, d);
                    commands_of_way += "k2 ";
                }
                    ++count_operation; 
                //проверка результата применения команды
                if (temp_val > b){
                    flag_to_continue = false;
                }
                else if (temp_val == b){
                    way_is_good = true;
                    flag_to_continue = false;
                }
            }
        }
        return temp_val;
    }
    // Метод форматированного вывода  консоль
    public static void print_result(int temp_val){
        String res = String.format("a: %d -> %d;  b = %d;  количество операций = %d; eсть решение -> %b",
        a, temp_val, b, count_operation, way_is_good);
        System.out.println(res);
        System.out.println("выполнены команды: " + commands_of_way);
    }
}