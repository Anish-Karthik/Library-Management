package library_management;

import library_management.menu.AdminMainMenu;

public class Main {
	public static void main(String[] args) throws Exception {
		DB db = DB.getInstance();
		AdminMainMenu adminMainMenu = new AdminMainMenu(db.con);
		adminMainMenu.processMenu();
		db.con.close();
	}
}
