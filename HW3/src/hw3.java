import java.util.Scanner;

public class hw3 {
  public static void main(String[] args) {
    char[] sub_data = new char[1000];
    char[][] dataset = new char[10][1000];
    int case_num = 0;
    Scanner s = new Scanner(System.in);
    while (true) {
      String s_line;
      try {
        s_line = s.nextLine();
        if (s_line == "") {
          dataset[case_num] = copy_dataset(sub_data);
          case_num++;
          continue;
        }
      } catch (Exception e) {
        dataset[case_num] = copy_dataset(sub_data);
        case_num++;
        break;
      }

      sub_data = s_line.toCharArray();
    }

    s.close();

    for (int i = 0; i < case_num; i++) {
      System.out.println(get_Ans(dataset[i]));
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
}
