
package application;

import utilconnext.DBConnection;
import views.frm_Banhang;
import views.frm_Dashboard;
import views.frm_Login;

public class main {

    public static void main(String[] args) {
        new frm_Login().setVisible(true);
        System.out.println("" + DBConnection.openDbConnection());
    }
}
