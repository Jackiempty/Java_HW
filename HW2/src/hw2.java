import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class hw2 {
  public static void main(String[] args) {
    int[][][] datasets = new int[10][3][];
    int[][] dataset = new int[3][];
    int c = 0;
    int case_num = 0;
    Scanner s = new Scanner(System.in);
    while (true) {
      String s_line;
      try {
        s_line = s.nextLine();
        if (s_line == "") {
          datasets[case_num] = copy_dataset(dataset);
          case_num++;
          c = 0;
          continue;
        }
      } catch (Exception e) {
        datasets[case_num] = copy_dataset(dataset);
        case_num++;
        c = 0;
        break;
      }

      // System.out.println(s_line.toCharArray());
      String[] numbers = s_line.split("\\s+");
      // char[] line = s_line.toCharArray();
      int[] data_line = new int[numbers.length];
      dataset[c] = data_line;
      for (int j = 0; j < numbers.length; j++) {
        dataset[c][j] = Integer.parseInt(numbers[j]); // Integer.getValue turned into ascii code
      }
      c++;
    }
    s.close();

    for (int i = 0; i < case_num; i++) {
      System.out.println("Case " + (i + 1) + ": " + get_Ans(datasets[i]));
    }
  }

  static void print_dataset(int[][] dataset) {
    System.out.println("target: " + dataset[0][0]);
    System.out.print("position: ");
    for (int i = 0; i < dataset[1].length; i++) {
      System.out.print(dataset[1][i] + " ");
    }
    System.out.println();
    System.out.print("velocity: ");
    for (int i = 0; i < dataset[2].length; i++) {
      System.out.print(dataset[2][i] + " ");
    }
    System.out.println();
  }

  static int[][] copy_dataset(int[][] dataset) {
    int[][] result = new int[dataset.length][];
    for (int i = 0; i < dataset.length; i++) {
      result[i] = new int[dataset[i].length];
      result[i] = dataset[i];
    }

    return result;
  }

  static int get_Ans(int[][] dataset) {
    // print_dataset(dataset);
    int target = dataset[0][0];
    int[] position = dataset[1];
    int[] velocity = dataset[2];
    int result = 0;

    float[] time = new float[position.length];
    for (int i = 0; i < position.length; i++) {
      time[i] = (float) (target - position[i]) / (float) velocity[i];
      // System.out.println("time[" + (i + 1) + "]: " + time[i]);
    }

    Map<Float, Float> map = new HashMap<Float, Float>();
    for (int i = 0; i < position.length; i++) {
      map.put(time[i], (float) position[i]);
    }

    Arrays.sort(time);
    
    // for (int i = 0; i < position.length; i++) {
    //   System.out.print(time[i] + ": ");
    //   System.out.println(map.get(time[i]));
    // }

    for (int i = position.length - 1; i >= 0; i--) {
      if (map.get(time[i]) == null) {
        continue;
      }
      float current_position = map.get(time[i]);
      float current_time = time[i];
      for (int j = 0; j < position.length; j++) {
        if (time[j] < current_time && map.get(time[j]) < current_position) {
          map.remove(time[j]);
        }
        map.remove(current_time);
      }
      result++;
    }
    return result;
  }
}
