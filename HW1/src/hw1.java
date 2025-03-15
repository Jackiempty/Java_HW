import java.util.Scanner;

public class hw1 {
  public static void main(String[] args) {
    int[][] sub_sudoku = new int[9][9];
    int[][][] sudoku = new int[5][9][9];
    int case_num = 0;
    Scanner s = new Scanner(System.in);
    int c = 0;
    while (true) {
      // System.out.println("row number " + (c + 1));
      String s_line;
      try {
        s_line = s.nextLine();
        if (s_line == "") {
          sudoku[case_num] = copy_dataset(sub_sudoku);
          // print_dataset(sudoku[case_num]);
          case_num++;
          c = 0;
          continue;
        }
        // System.out.println("aaa: " + s_line);
      } catch (Exception e) {
        sudoku[case_num] = copy_dataset(sub_sudoku);
        // print_dataset(sudoku[case_num]);
        case_num++;
        c = 0;
        // System.out.println("bbb");
        break;
      }

      // System.out.println(s_line.toCharArray());
      char[] line = s_line.toCharArray();
      for (int j = 0; j < 9; j++) {
        sub_sudoku[c][j] = Character.getNumericValue(
            line[j]); // Integer.getValue turned into ascii code
      }
      c++;
    }

    s.close();

    for (int i = 0; i < case_num; i++) {
      // print_dataset(sudoku[i]);
      if (validate_dataset(sudoku[i])) {
        System.out.println("Case " + (i + 1) + ": "
                           + "True.");
      } else {
        System.out.println("Case " + (i + 1) + ": "
                           + "False.");
      }
    }
  }

  static boolean validate_dataset(int[][] sudoku) {
    // initialization
    int[][][] mask = new int[9][3][3];
    boolean[] result = {true, true, true};
    boolean Final = true;
    int[][] column = new int[9][9];

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        column[i][j] = sudoku[j][i];
      }
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          mask[i][j][k] = sudoku[3 * (i / 3) + j][3 * (i % 3) + k];
        }
      }
    }

    // validate row
    boolean[] list_1 = new boolean[9];
    for (int i = 0; i < 9; i++) {
      list_1[i] = validate_array(sudoku[i]);
    }

    for (int i = 0; i < 9; i++) {
      result[0] &= list_1[i];
    }

    // validate column
    boolean[] list_2 = new boolean[9];
    ;
    for (int i = 0; i < 9; i++) {
      list_2[i] = validate_array(column[i]);
    }

    for (int i = 0; i < 9; i++) {
      result[1] &= list_2[i];
    }

    // validate 3x3 subgrid
    boolean[] list_3 = new boolean[9];
    ;
    for (int i = 0; i < 9; i++) {
      list_3[i] = validate_3x3_grid(mask[i]);
    }

    for (int i = 0; i < 9; i++) {
      result[2] &= list_3[i];
    }

    // sum up
    for (int i = 0; i < 3; i++) {
      Final &= result[i];
    }

    return Final;
  }

  static boolean validate_array(int[] array) {
    boolean result = true;
    boolean[] list = new boolean[9];
    for (int i = 0; i < 9; i++) {
      list[i] = false;
    }

    for (int i = 0; i < 9; i++) {
      list[array[i] - 1] = true;
    }

    for (int i = 0; i < 9; i++) {
      result &= list[i];
    }

    return result;
  }

  static boolean validate_3x3_grid(int[][] grid) {
    boolean result = true;
    boolean[] list = new boolean[9];
    for (int i = 0; i < 9; i++) {
      list[i] = false;
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        list[grid[i][j] - 1] = true;
      }
    }

    for (int i = 0; i < 9; i++) {
      result &= list[i];
    }

    return result;
  }

  static void print_dataset(int[][] dataset) {
    for (int i = 0; i < 9; i++) {
      System.out.print("row " + (i + 1) + ": ");
      for (int j = 0; j < 9; j++) {
        System.out.print(dataset[i][j] + " ");
      }
      System.out.println();
    }
  }

  static int[][] copy_dataset(int[][] dataset) {
    int[][] result = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        result[i][j] = dataset[i][j];
      }
    }
    return result;
  }
}
