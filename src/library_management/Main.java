package library_management;

import library_management.menu.UserMainMenu;

public class Main {
	public static void main(String[] args) throws Exception {
		DB db = DB.getInstance();
		UserMainMenu mainMenu = new UserMainMenu(db.con);
		mainMenu.processMenu();
		db.con.close();
	}
}
