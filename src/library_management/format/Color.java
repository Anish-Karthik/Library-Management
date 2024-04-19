package library_management.format;

public interface Color {
  String ANSI_RESET = "\u001B[0m";
  String ANSI_CYAN = "\u001B[36m";
  String ANSI_GREEN = "\u001B[32m";
  String ANSI_YELLOW = "\u001B[33m";
  String ANSI_RED = "\u001B[31m";
  String ANSI_PURPLE = "\u001B[35m";
  String ANSI_BLUE = "\u001B[34m";
  String ANSI_BLACK = "\u001B[30m";
  String ANSI_WHITE = "\u001B[37m";



//   Regular Colors
// Value	Color
// \e[0;30m	Black
// \e[0;31m	Red
// \e[0;32m	Green
// \e[0;33m	Yellow
// \e[0;34m	Blue
// \e[0;35m	Purple
// \e[0;36m	Cyan
// \e[0;37m	White


// Bold
// Value	Color
// \e[1;30m	Black
// \e[1;31m	Red
// \e[1;32m	Green
// \e[1;33m	Yellow
// \e[1;34m	Blue
// \e[1;35m	Purple
// \e[1;36m	Cyan
// \e[1;37m	White
  String ANSI_BOLD_BLACK = "\u001B[1;30m";
  String ANSI_BOLD_RED = "\u001B[1;31m";
  String ANSI_BOLD_GREEN = "\u001B[1;32m";
  String ANSI_BOLD_YELLOW = "\u001B[1;33m";
  String ANSI_BOLD_BLUE = "\u001B[1;34m";
  String ANSI_BOLD_PURPLE = "\u001B[1;35m";
  String ANSI_BOLD_CYAN = "\u001B[1;36m";
  String ANSI_BOLD_WHITE = "\u001B[1;37m";


// Underline
// Value	Color
// \e[4;30m	Black
// \e[4;31m	Red
// \e[4;32m	Green
// \e[4;33m	Yellow
// \e[4;34m	Blue
// \e[4;35m	Purple
// \e[4;36m	Cyan
// \e[4;37m	White 
  String ANSI_UNDERLINE_BLACK = "\u001B[4;30m";
  String ANSI_UNDERLINE_RED = "\u001B[4;31m";
  String ANSI_UNDERLINE_GREEN = "\u001B[4;32m";
  String ANSI_UNDERLINE_YELLOW = "\u001B[4;33m";
  String ANSI_UNDERLINE_BLUE = "\u001B[4;34m";
  String ANSI_UNDERLINE_PURPLE = "\u001B[4;35m";
  String ANSI_UNDERLINE_CYAN = "\u001B[4;36m";
  String ANSI_UNDERLINE_WHITE = "\u001B[4;37m";

// Background
// Value	Color
// \e[40m	Black
// \e[41m	Red
// \e[42m	Green
// \e[43m	Yellow
// \e[44m	Blue
// \e[45m	Purple
// \e[46m	Cyan
// \e[47m	White

  String ANSI_BACKGROUND_BLACK = "\u001B[40m";
  String ANSI_BACKGROUND_RED = "\u001B[41m";
  String ANSI_BACKGROUND_GREEN = "\u001B[42m";
  String ANSI_BACKGROUND_YELLOW = "\u001B[43m";
  String ANSI_BACKGROUND_BLUE = "\u001B[44m";
  String ANSI_BACKGROUND_PURPLE = "\u001B[45m";
  String ANSI_BACKGROUND_CYAN = "\u001B[46m";
  String ANSI_BACKGROUND_WHITE = "\u001B[47m";
// High Intensty
// Value	Color
// \e[0;90m	Black
// \e[0;91m	Red
// \e[0;92m	Green
// \e[0;93m	Yellow
// \e[0;94m	Blue
// \e[0;95m	Purple
// \e[0;96m	Cyan
// \e[0;97m	White

  String ANSI_HIGH_INTENSITY_BLACK = "\u001B[0;90m";
  String ANSI_HIGH_INTENSITY_RED = "\u001B[0;91m";
  String ANSI_HIGH_INTENSITY_GREEN = "\u001B[0;92m";
  String ANSI_HIGH_INTENSITY_YELLOW = "\u001B[0;93m";
  String ANSI_HIGH_INTENSITY_BLUE = "\u001B[0;94m";
  String ANSI_HIGH_INTENSITY_PURPLE = "\u001B[0;95m";
  String ANSI_HIGH_INTENSITY_CYAN = "\u001B[0;96m";
  String ANSI_HIGH_INTENSITY_WHITE = "\u001B[0;97m";
// Bold High Intensty
// Value	Color
// \e[1;90m	Black
// \e[1;91m	Red
// \e[1;92m	Green
// \e[1;93m	Yellow
// \e[1;94m	Blue
// \e[1;95m	Purple
// \e[1;96m	Cyan
// \e[1;97m	White

  String ANSI_BOLD_HIGH_INTENSITY_BLACK = "\u001B[1;90m";
  String ANSI_BOLD_HIGH_INTENSITY_RED = "\u001B[1;91m";
  String ANSI_BOLD_HIGH_INTENSITY_GREEN = "\u001B[1;92m";
  String ANSI_BOLD_HIGH_INTENSITY_YELLOW = "\u001B[1;93m";
  String ANSI_BOLD_HIGH_INTENSITY_BLUE = "\u001B[1;94m";
  String ANSI_BOLD_HIGH_INTENSITY_PURPLE = "\u001B[1;95m";
  String ANSI_BOLD_HIGH_INTENSITY_CYAN = "\u001B[1;96m";
  String ANSI_BOLD_HIGH_INTENSITY_WHITE = "\u001B[1;97m";

// High Intensty backgrounds
// Value	Color
// \e[0;100m	Black
// \e[0;101m	Red
// \e[0;102m	Green
// \e[0;103m	Yellow
// \e[0;104m	Blue
// \e[0;105m	Purple
// \e[0;106m	Cyan
// \e[0;107m	White

  String ANSI_BACKGROUND_HIGH_INTENSITY_BLACK = "\u001B[0;100m";
  String ANSI_BACKGROUND_HIGH_INTENSITY_RED = "\u001B[0;101m";
  String ANSI_BACKGROUND_HIGH_INTENSITY_GREEN = "\u001B[0;102m";
  String ANSI_BACKGROUND_HIGH_INTENSITY_YELLOW = "\u001B[0;103m";
  String ANSI_BACKGROUND_HIGH_INTENSITY_BLUE = "\u001B[0;104m";
  String ANSI_BACKGROUND_HIGH_INTENSITY_PURPLE = "\u001B[0;105m";
  String ANSI_BACKGROUND_HIGH_INTENSITY_CYAN = "\u001B[0;106m";
  String ANSI_BACKGROUND_HIGH_INTENSITY_WHITE = "\u001B[0;107m";
  

  
  public static final String[] COLORS = { ANSI_CYAN, ANSI_GREEN, ANSI_YELLOW, ANSI_RED, ANSI_PURPLE, ANSI_BLUE };
}
