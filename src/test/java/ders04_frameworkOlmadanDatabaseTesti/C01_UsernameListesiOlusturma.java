package ders04_frameworkOlmadanDatabaseTesti;

import java.sql.*;

public class C01_UsernameListesiOlusturma {

    public static void main(String[] args) throws SQLException {
        // Loantech uygulamasini kullanan kullanicilarin
        // username listesini yazdirin.

        // 1.Adim: Veri tabanına bağlanma ve bağlantıyı kaydetme

        String url = "jdbc:mysql://195.35.59.18/u201212290_qaloantec";
        String username = "u201212290_qaloanuser";
        String password = "HPo?+7r$";

        Connection connection = DriverManager.getConnection(url,username,password);

        // 2.Adim : SQL sorgusunu hazırlama
        String selectUsername = "SELECT * FROM users";

        // 3.Adim: SQL sorgusunu çalıştırma ve sonuç setini kaydetme
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet usernameResultSet = statement.executeQuery(selectUsername);

        // 4.Adim: resultSet objesini iterator gibi kullanarak istenen işlemi yapma
        System.out.println(usernameResultSet);
        // resultSet direkt yazdirilamaz
        // Iterator gibi kullanarak, satirlari elden gecirmeli
        // ve satirlarda istedigimiz islemi yapmaliyiz

        while (usernameResultSet.next()){

            System.out.println(usernameResultSet.getString("username"));
        }

        // 5.Adim: Database bağlantısını kapatma
        usernameResultSet.close();
        statement.close();
        connection.close();
    }

}
