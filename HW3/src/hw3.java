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
    for (int i = 1; i <= dataset.length; i++) {
      // System.out.print("i:" + i);
      char[] sub_string = new char[i];
      for (int j = 0; j <= dataset.length - i; j++) {
        // System.out.print(" j:" + j);
        for (int k = j; k < j + i; k++) {
          // System.out.println(" k:" + k);
          sub_string[k - j] = dataset[k];
        }
        // System.out.print(sub_string);
        // System.out.println(": " + palindrome(sub_string));
        if (palindrome(sub_string))
          result++;
      }
    }
    return result;
  }

  static boolean palindrome(char[] s) {
    int length = s.length;
    for (int i = 0; i < length / 2; ++i) {
      if (s[i] != s[length - 1 - i])
        return false;
    }
    return true;
  }
}
