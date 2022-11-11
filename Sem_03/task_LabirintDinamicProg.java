import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class task_LabirintDinamicProg {

  public static void main(String[] args) {

    // Печать исходной игровой карты
    // Point2D a = new Point2D(1, 2); // System.out.println(a);
    var mg = new MapGenerator();
    System.out.println(
            new MapPrinter().rawData(
              mg.getMap()));
              // new MapPrinter().mapColor(
              //   mg.getMap()));
            
    // Выполнение волнового алгоритма по заполнению маршрутов в игровом поле
    var lee = new WaveAlgorithm(mg.getMap());
    lee.Colorize(new Point2D(5, 1)); // стартовая точка (5; 1)
    System.out.println(
        new MapPrinter().rawData(
            mg.getMap()));
    // lee.getRoad(new Point2D(5, 21));   // точка выхода (2; 8)
  }
}

// Сущность "точка" - структура данных для хранения координат 
// (в программе будет использваться массив из точек)
class Point2D {
  // Поля (т.е. переменные) - координаты точки в игровом поле
  private int x, y;
  // Констуктор - по умолчанию
  public Point2D() {
    this.x = 0;
    this.y = 0;
  }
  // Констуктор - с присваиванием значения
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }
  // Метод для получения координаты Х (копия)
  public int getX() {
    return x;
  }
  // Метод для получения координаты Y (копия)
  public int getY() {
    return y;
  }
  // Метод для присваивания значения координате Х (записать)
  public void setX(int valX) {
    this.x = valX;
}
  // Метод для присваивания значения координате Y (записать)
  public void setY(int valY) {
    this.y = valY;
}
  // Перегруженный метод преобразования в строку (без stastic не видны поля класса)
  @Override
  public String toString() {
    return String.format("x: %d  y: %d", x, y);
  }
}

// Сущность "карта" - Стуктура данных для работы с игровым полем
class MapGenerator {
  int[][] map;
  // Конструктор - создание игрового поля в виде 2хмерного массива
  public MapGenerator() {
    // абстрактное ограничение игрового поля через "-1" для игнорирования выхода за границы массива
    // при его обходе (заполнение/раскраска и поиск маршрута)
    int[][] map = { 
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
        { -1, 00, 00, 00, 00, 00, 00, 00, 00, -1},
        { -1, 00, 00, 00, 00, 00, -1, 00, -3, -1},
        { -1, 00, -1, 00, -1, 00, -1, 00, 00, -1},
        { -1, 00, -1, -1, -1, -1, -1, 00, 00, -1},
        { -1, -2, -1, 00, -1, -1, -1, 00, 00, -1},  
        { -1, 00, -1, 00, -1, 00, -1, -1, 00, -1},
        { -1, 00, -1, 00, 00, 00, -1, 00, 00, -1},
        { -1, 00, 00, 00, 00, 00, 00, 00, 00, -1},
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
    };

    this.map = map;
  }
  // Метод для обращения к игровому полю (получить копию массива с игорвым полем)
  public int[][] getMap() {
    return map;
  }
  // Метод установки точки входа
  public void setCat(Point2D pos) {
    map[pos.getX()][pos.getY()] = -2;
  }
  // Метод установки точки выхода
  public void setExit(Point2D pos) {
    map[pos.getX()][pos.getY()] = -3;
  }
}

// Сущность "карта - отобращение" -  для печати карты
class MapPrinter {
  // Конструктор - пустой
  public MapPrinter() {
  }
  // Печать карты в цифровом виде
  public String rawData(int[][] map) {
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[row].length; col++) {
        sb.append(String.format("%5d", map[row][col]));
      }
      sb.append("\n");
    }
    for (int i = 0; i < 3; i++) {
      sb.append("\n");
    }

    return sb.toString();
  }
  // Печать карты с маской - псевдокод
  public String mapColor(int[][] map) {
    StringBuilder sb = new StringBuilder();

    for (int row = 0; row < map.length; row++) {
      for (int col = 0; col < map[row].length; col++) {
        switch (map[row][col]) {
          case 0:
            sb.append("▓");
            break;
          case -1:
            sb.append("░"); 
            break;
          case -2:
            sb.append("K");
            break;
          case -3:
            sb.append("E");
            break;
          default:
            break;
        }
      }
      sb.append("\n");
    }
    for (int i = 0; i < 3; i++) {
      sb.append("\n");
    }
    return sb.toString();
  }
}

// Сущность "волновой алгоритм"
class WaveAlgorithm {
  int[][] map;

  // Конструктор - инициализация игрового поля через копирование массива 2хметрного (поле класса MapGenerator)
  public WaveAlgorithm(int[][] map) {
    this.map = map;
  }
  // Метод - реализация обхода поля (волнового алгоритма) для заполнения количеством ходов
  public void Colorize(Point2D startPoint) {
    Queue<Point2D> queue = new LinkedList<Point2D>();
    queue.add(startPoint);                          // первый элемент в очереди стартовая позиция
    map[startPoint.getX()][startPoint.getY()] = 1;  // количество путей в стартовую позицию 1 (стоять на месте - действие или выйти и вернуться) 
    /* Приоритет обхода: 
     *   x - в строке i-й матрицы map  (i=1,m); 
     *   y - в столбце j-м матрицы map (j=1,n);
     * 1) вверх:  x-1       y=const
     * 2) вправо: x=const   у+1
     * 3) влево:  x=const   у-1
     * 4) вниз:   x+1       y=const
    */
    while (queue.size() != 0) {
      Point2D p = queue.remove();                   // извлечение точки, добавленной первой (первый элемент очереди)
      // 1) точка сверху (просмотр на 1 строку выше)  
      if (map[p.getX() - 1][p.getY()] == 0) {
        queue.add(new Point2D(p.getX() - 1, p.getY()));
        map[p.getX() - 1][p.getY()] = map[p.getX()][p.getY()] + 1;
      }      
      // 3) точка слева (просмотр на 1 столбец влево) 
      if (map[p.getX()][p.getY() - 1] == 0) {
        queue.add(new Point2D(p.getX(), p.getY() - 1));
        map[p.getX()][p.getY() - 1] = map[p.getX()][p.getY()] + 1;
      }      
      // 4) точка снизу (просмотр на 1 строку вниз) 
      if (map[p.getX() + 1][p.getY()] == 0) {
        queue.add(new Point2D(p.getX() + 1, p.getY()));
        map[p.getX() + 1][p.getY()] = map[p.getX()][p.getY()] + 1;
      }
      // 2) точка справа (просмотр на 1 столбец вправо)
      if (map[p.getX()][p.getY() + 1] == 0) {
        queue.add(new Point2D(p.getX(), p.getY() + 1));
        map[p.getX()][p.getY() + 1] = map[p.getX()][p.getY()] + 1;
      }
    }
  }
  // Построение маршрута
  public ArrayList<Point2D> getRoad(Point2D exit) {
    ArrayList<Point2D> road = new ArrayList<>();
    //

    Queue<Point2D> queue = new LinkedList<Point2D>();
    queue.add(exit);                          // первый элемент в очереди позиция выхода из лабиринта
    int temp = map[exit.getX()][exit.getY()];         
    // while (queue.size() != 0) {
    //   Point2D p = queue.remove();                   
    //   // 1) точка сверху (просмотр на 1 строку выше)  
    //   if (map[p.getX() - 1][p.getY()] == 0) {
    //     queue.add(new Point2D(p.getX() - 1, p.getY()));
    //     map[p.getX() - 1][p.getY()] = map[p.getX()][p.getY()] + 1;
    //   }      
    //   // 3) точка слева (просмотр на 1 столбец влево) 
    //   if (map[p.getX()][p.getY() - 1] == 0) {
    //     queue.add(new Point2D(p.getX(), p.getY() - 1));
    //     map[p.getX()][p.getY() - 1] = map[p.getX()][p.getY()] + 1;
    //   }      
    //   // 4) точка снизу (просмотр на 1 строку вниз) 
    //   if (map[p.getX() + 1][p.getY()] == 0) {
    //     queue.add(new Point2D(p.getX() + 1, p.getY()));
    //     map[p.getX() + 1][p.getY()] = map[p.getX()][p.getY()] + 1;
    //   }
    //   // 2) точка справа (просмотр на 1 столбец вправо)
    //   if (map[p.getX()][p.getY() + 1] == 0) {
    //     queue.add(new Point2D(p.getX(), p.getY() + 1));
    //     map[p.getX()][p.getY() + 1] = map[p.getX()][p.getY()] + 1;
    //   }
    // }

    return road;
  }
}