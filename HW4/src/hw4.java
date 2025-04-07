import java.util.Scanner;

public class hw4 {
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

      // System.out.println(get_Ans(dataset[i]));
    }
  }

  static char[] copy_dataset(char[] dataset) {
    char[] result = new char[dataset.length];
    for (int i = 0; i < dataset.length; i++) {
      result[i] = dataset[i];
    }
    return result;
  }

  static int get_Ans(char[] dataset) {
    // System.out.println(dataset);
    int result = 0;
    
    return result;
  }