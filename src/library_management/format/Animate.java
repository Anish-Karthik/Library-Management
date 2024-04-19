package library_management.format;

public class Animate extends Format {
  public static void animateText(String text) {
    animateText(text, 70);
  }
  public static void animateText(String text, int timeInMs) {
    for (int i = 0; i < text.length(); i++) {
      System.out.print(text.charAt(i));
      try {
        Thread.sleep(timeInMs);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println();
  }
  public static void animateTextWithColor(String text, String color) {
    animateTextWithColor(text, color, 70);
  }

  public static void animateTextWithColor(String text, String color, int timInMs) {
    for (int i = 0; i < text.length(); i++) {
      System.out.print(colorString(text.charAt(i) + "", color));
      try {
        Thread.sleep(timInMs);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // animateText("Hello World");
    // animateTextWithColor("Hello World", ANSI_RED);
    animateText(surroundStringWithBox("Hello World", 40, ANSI_GREEN, ANSI_BLUE ), 10);
  }
}
