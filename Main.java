package com.example.store;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        UsersTable ut = new UsersTable();
        ArrayList<Integer> cartItemsId=new ArrayList<>();
        cartItemsId.add(1);

        ut.insertToUsersTable(2, "mahsa81", "45567@d", "Mahsa", "094565565657", 5464.0, "1,2" );
        LoginFrame loginFrame= new LoginFrame();
        loginFrame.setVisible(true);
    }
}
