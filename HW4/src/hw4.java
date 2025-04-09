import java.util.Scanner;

public class hw4 {
  public static void main(String[] args) {
    int[][][] datasets = new int[10][][];
    int[][] dataset = new int[1000][2];
    int c = 0;
    int case_num = 0;
    Scanner s = new Scanner(System.in);
    while (true) {
      String s_line;
      try {
        s_line = s.nextLine();
        if (s_line == "") {
          datasets[case_num] = copy_dataset(dataset, c);
          case_num++;
          c = 0;
          continue;
        }
      } catch (Exception e) {
        datasets[case_num] = copy_dataset(dataset, c);
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
      // print_dataset(datasets[i]);
      System.out.println(get_Ans(datasets[i]));
    }
  }

  static int[][] copy_dataset(int[][] dataset, int count) {
    int[][] result = new int[count][];
    for (int i = 0; i < count; i++) {
      result[i] = new int[dataset[i].length];
      result[i] = dataset[i];
    }
    return result;
  }

  static int get_Ans(int[][] dataset) {
    // System.out.println(dataset);
    int result = 1000;
    boolean yes_or_no = true;
    int num = dataset[0][0];

    for (int i = 1; i < dataset.length; i++) {
      if (i + 1 >= dataset.length) {
        if (dataset[i][1] != dataset[1][1]) {
          yes_or_no = false;
        }
        if (dataset[1][1] < result) {
          result = dataset[1][1];
        }
      } else {
        if (dataset[i][1] != dataset[i + 1][0]) {
          yes_or_no = false;
        }
        if (dataset[i + 1][0] < result) {
          result = dataset[i + 1][0];
        }
      }
    }

    if (yes_or_no == true) {
      return result;
    } else {
      return -1;
    }
  }

  static void print_dataset(int[][] dataset) {
    for (int i = 0; i < dataset.length; i++) {
      for (int j = 0; j < dataset[i].length; j++) {
        System.out.print(dataset[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
}