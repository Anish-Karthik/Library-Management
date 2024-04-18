package library_management;

import library_management.book.menu.admin.AdminBookMenu;

public class Main {
	public static void main(String[] args) throws Exception {
		DB db = DB.getInstance();
		AdminBookMenu adminBookMenu = new AdminBookMenu(db.con);
		adminBookMenu.processMenu();
		db.con.close();
	}
}
