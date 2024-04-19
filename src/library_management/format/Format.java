package library_management.format;

public class Format implements Color {

  public enum Align {
    LEFT, CENTER, RIGHT
  }

  public static String centerString(String s, int width, String color) {
    return String.format("%" + width + "s", color + s + ANSI_RESET);
  }

  public static String padStringLeftAndRight(String s, int width, String color) {
    return padStringLeftAndRight(s, width, color, ANSI_RESET);
  }

  public static String padStringLeftAndRight(String s, int width, String color, String bgColor) {
    int remainingWidth = width - s.length();
    int leftPad = remainingWidth / 2;
    int rightPad = remainingWidth - leftPad;
    // System.out.println(leftPad + " " + rightPad);
    return bgColor + String.format("%" + leftPad + "s", "")
        + String.format("%s", color + s + bgColor)
        + String.format("%" + rightPad + "s", "") + ANSI_RESET;
  }

  public static String leftString(String s, int width, String color, String bgColor) {
    return bgColor + String.format("%-" + ((bgColor != ANSI_RESET ? 1 : 0) + width) + "s", color + s + bgColor)
        + ANSI_RESET;
  }

  public static String leftString(String s, int width, String color) {
    return leftString(s, width, color, ANSI_RESET);
  }

  public static String rightString(String s, int width, String color, String bgColor) {
    return bgColor + String.format("%" + ((bgColor != ANSI_RESET ? 1 : 0) + width) + "s", color + s + bgColor)
        + ANSI_RESET;
  }

  public static String rightString(String s, int width, String color) {
    return rightString(s, width, color, ANSI_RESET);
  }

  public static String colorString(String s, String color) {
    return color + s + ANSI_RESET;
  }

  public static String surroundStringWithBox(String s, String color) {
    int width = s.length() + 4;
    String box = colorString("+" + "-".repeat(width - 2) + "+", color);
    return box + "\n" + colorString("| " + s + " |", color) + "\n" + box + ANSI_RESET;
  }

  public static String surroundStringWithBox(String s, int pad, String color, String boxColor) {
    return getBoxBorderHorizontal(s, pad, color, boxColor) + getBoxBorderVertical(s, pad, color, boxColor)
        + getBoxBorderHorizontal(s, pad, color, boxColor);
  }

  public static String surroundStringWithBox(String s, int pad, String color, String boxColor, String bgColor) {
    return getBoxBorderHorizontal(s, pad, color, boxColor) + getBoxBorderVertical(s, pad, color, boxColor, bgColor)
        + getBoxBorderHorizontal(s, pad, color, boxColor);
  }

  public static String getBoxBorderHorizontal(String s, int pad, String color, String boxColor) {
    int width = 4 + pad * 2;
    String box = colorString(String.format("%" + width + "s", "+" + "-".repeat(width - 2) + "+"), boxColor);
    return box + "\n" + ANSI_RESET;
  }

  public static String getBoxBorderHorizontalNoNextLine(String s, int pad, String color, String boxColor) {
    int width = 4 + pad * 2;
    String box = colorString(String.format("%" + width + "s", "+" + "-".repeat(width - 2) + "+"), boxColor);
    return box;
  }

  public static String getBoxBorderVerticalNoNextLine(String s, int pad, String color, String boxColor, String bgColor,
      Enum<Align> align) {
    int width = pad * 2;
    String str = padStringLeftAndRight(s, width, color, bgColor);
    if (align == Align.LEFT) {
      str = leftString(s, width + 9, color, bgColor);
    } else if (align == Align.RIGHT) {
      str = rightString(s, width + 9, color, bgColor);
    }
    return colorString("| " + str + boxColor + " |", boxColor);
  }

  public static String getBoxBorderVertical(String s, int pad, String color, String boxColor, String bgColor,
      Enum<Align> align) {
    int width = pad * 2;
    String str = padStringLeftAndRight(s, width, color, bgColor);
    if (align == Align.LEFT) {
      str = leftString(s, width + 9, color, bgColor);
    } else if (align == Align.RIGHT) {
      str = rightString(s, width + 9, color, bgColor);
    }

    return colorString("| " + str + boxColor + " |", boxColor) + "\n"
        + ANSI_RESET;
  }

  public static String getBoxBorderVertical(String s, int pad, String color, String boxColor) {
    return getBoxBorderVertical(s, pad, color, boxColor, ANSI_RESET, Align.CENTER);
  }

  public static String getBoxBorderVertical(String s, int pad, String color, String boxColor, Enum<Align> alignT) {
    return getBoxBorderVertical(s, pad, color, boxColor, ANSI_RESET, alignT);
  }

  public static String getBoxBorderVertical(String s, int pad, String color, String boxColor, String bgColor) {
    return getBoxBorderVertical(s, pad, color, boxColor, bgColor, Align.CENTER);
  }

  public static void displayMenu(String menuName, String[] options) {
    displayMenu(menuName, options, ANSI_YELLOW, ANSI_BOLD_BLUE + ANSI_UNDERLINE_BLUE, ANSI_RESET, ANSI_PURPLE,
        ANSI_RESET);
  }

  public static void displayMenu(String menuName, String[] options, String menuNameColor, String optionsColor) {
    displayMenu(menuName, options, ANSI_YELLOW, menuNameColor, ANSI_RESET, optionsColor, ANSI_RESET);
  }

  public static void displayMenu(String menuName, String[] options, String boxColor, String menuNameColor,
      String menuNameBgColor,
      String optionsColor, String optionsBgColor) {
    System.out.print(Format.getBoxBorderHorizontal(" ", 40, optionsColor,
        boxColor));
    System.out.print(
        Format.getBoxBorderVertical(menuName, 40, menuNameColor,
            boxColor, menuNameBgColor));

    for (int i = 0; i < options.length; i++) {
      System.out.print(
          Format.getBoxBorderVertical((i + 1) + ". " + options[i], 40, optionsColor,
              boxColor, optionsBgColor, Format.Align.LEFT));
    }
    System.out.print(Format.getBoxBorderHorizontal(" ", 40, optionsColor,
        boxColor));
  }

  public static void displayData(String[] data) {
    displayData(data, ANSI_BOLD_CYAN, ANSI_HIGH_INTENSITY_PURPLE, ANSI_RESET);
  }

  public static void displayData(String[] data, String boxColor) {
    displayData(data, boxColor, ANSI_HIGH_INTENSITY_PURPLE, ANSI_RESET);
  }

  public static void displayData(String[] data, String boxColor, String dataColor) {
    displayData(data, boxColor, dataColor, ANSI_RESET);
  }

  public static void displayData(String[] data, String boxColor, String dataColor, String dataBgColor) {
    System.out.print(Format.getBoxBorderHorizontal(" ", 40, dataColor,
        boxColor));
    for (int i = 0; i < data.length; i++) {
      System.out.print(
          Format.getBoxBorderVertical(data[i], 41, dataColor,
              boxColor, Format.Align.LEFT));
    }
    System.out.print(Format.getBoxBorderHorizontal(" ", 40, dataColor,
        boxColor));
  }

  public static void displayTable(String[] headers, String[][] data) {
    displayTable(headers, data, ANSI_BOLD_CYAN, ANSI_HIGH_INTENSITY_PURPLE, ANSI_RESET, ANSI_UNDERLINE_YELLOW, ANSI_RESET);
  }

  public static void displayTable(String[] headers, String[][] data, String boxColor) {
    displayTable(headers, data, boxColor, ANSI_HIGH_INTENSITY_PURPLE, ANSI_RESET, ANSI_UNDERLINE_YELLOW, ANSI_RESET);
  }

  public static void displayTable(String[] headers, String[][] data, String boxColor, String dataColor) {
    displayTable(headers, data, boxColor, dataColor, ANSI_RESET, dataColor, ANSI_RESET);
  }

  public static void displayTable(String[] headers, String[][] data, String boxColor, String dataColor,
      String dataBgColor, String headerColor, String headerBgColor) {
    for (int i = 0; i < headers.length; i++) {
      System.out.print(
          Format.getBoxBorderHorizontalNoNextLine(headers[i], 10, headerColor,
              boxColor));
    }
    System.out.println();
    for (int i = 0; i < headers.length; i++) {
      System.out.print(
          Format.getBoxBorderVerticalNoNextLine(headers[i], 11, headerColor,
              boxColor, headerBgColor, Format.Align.LEFT));
    }
    System.out.println();
    for (int i = 0; i < headers.length; i++) {
      System.out.print(
          Format.getBoxBorderHorizontalNoNextLine(headers[i], 10, headerColor,
              boxColor));
    }
    System.out.println();
    for (int i = 0; i < data.length; i++) {
      // for (int j = 0; j < data[i].length; j++) {
      //   System.out.print(
      //       Format.getBoxBorderHorizontalNoNextLine(" ", 10, dataColor,
      //           boxColor));
      // }
      // System.out.println();
      for (int j = 0; j < data[i].length; j++) {
        if (data[i][j] == null) {
          data[i][j] = "null";
        }
        int len = data[i][j].length();
        if (len <= 20) {
          System.out.print(
              Format.getBoxBorderVerticalNoNextLine(data[i][j], 11, dataColor,
                  boxColor, ANSI_RESET, Format.Align.LEFT));
          continue;
        }
        System.out.print(
            Format.getBoxBorderVerticalNoNextLine(data[i][j].substring(0, 17)+"...", 11, dataColor,
                boxColor, ANSI_RESET, Format.Align.LEFT));
      }
      System.out.println();
      for (int j = 0; j < data[i].length; j++) {
        System.out.print(
            Format.getBoxBorderHorizontalNoNextLine(" ", 10, dataColor,
                boxColor));
      }
      System.out.println();
    }
  }

  public static void exitWithAnimate(String s) {
    System.out.print(Format.colorString(s, Color.ANSI_HIGH_INTENSITY_BLACK));
    Animate.animateTextWithColor("...\n", Color.ANSI_HIGH_INTENSITY_BLACK, 300);
  }

  public static void main(String[] args) {
    System.out.println(centerString("Hello World", 20, ANSI_RED));
    System.out.println(leftString("Hello World", 20, ANSI_GREEN));
    System.out.println(rightString("Hello World", 20, ANSI_BLUE));
    System.out.println(colorString("Hello World", ANSI_PURPLE));
    System.out.println(surroundStringWithBox("Hello world", 40, ANSI_RED, ANSI_YELLOW));

    System.out.print(getBoxBorderHorizontal("Hello world", 40, ANSI_RED, ANSI_YELLOW));
    System.out.print(getBoxBorderVertical("Hello world", 40, ANSI_RED, ANSI_YELLOW));
    System.out.print(getBoxBorderVertical("1. Hello world zxc sd sdfsdf", 40, ANSI_CYAN, ANSI_YELLOW, Align.LEFT));
    System.out.print(getBoxBorderVertical("3. sdfsv Hello world", 40, ANSI_RED, ANSI_YELLOW, Align.RIGHT));
    System.out.print(getBoxBorderHorizontal("Hello world", 40, ANSI_RED, ANSI_YELLOW));

    String options[] = { "Option 1 asda", "Option 2", "Option 3", "Option 4" };
    displayMenu("Main Menu", options);
    System.out.println(Format.colorString("Invalid choice", Color.ANSI_HIGH_INTENSITY_RED));
    // System.out.print(Format.colorString("Exiting to main menu",
    // Color.ANSI_HIGH_INTENSITY_BLACK));
    // Animate.animateTextWithColor("...\n", Color.ANSI_HIGH_INTENSITY_BLACK, 300);
    displayData(options);

    String headers[] = { "Header 1", "Header 2", "Header 3", "Header 4" };
    String data[][] = { { "Data 1", "Data 2", "Data 3", "Data 4" }, { "Data 5", "Data 6", "Data 7", "Data 8" },
        { "Data 9", "Data 10", "Data 11", "Data 12" } };
    displayTable(headers, data);

  }

}
