package ders05_dinamikQueryOlusturma;

import manageQueries.LoantechQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class C01_usernameTesti {

    public static void main(String[] args) throws SQLException {
        // Loantech uygulamasındaki users tablosunda
        // username bilgisi “darkdark” ve “tester” olan
        // kayıtların var olduğunu doğrulayın.


        // 1.Adim: Veri tabanına bağlanma ve bağlantıyı kaydetme
        String url = "jdbc:mysql://195.35.59.18/u201212290_qaloantec";
        String username = "u201212290_qaloanuser";
        String password = "HPo?+7r$";

        Connection connection = DriverManager.getConnection(url,username,password);

        // 2.Adim : SQL sorgusunu hazırlama
        String query = LoantechQueries.usersTablosundakiUsernameSorgusu;

        // 3.Adim: SQL sorgusunu çalıştırma ve sonuç setini kaydetme
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultset = statement.executeQuery(query);

        // 4.Adim: resultSet objesini iterator gibi kullanarak istenen işlemi yapma
        // Her seferinde bir sutundaki tum kayitlari while loop ile gezinmek yerine
        // bir kere while loop yapip, kayitlari bir list'e ekleyelim
        // list Java kullanimina daha uygun oldugu icin
        // rahatlikla istenen islemleri yapabiliriz

        List<String> usernameList = new ArrayList<>();

        while (resultset.next()){

            usernameList.add(resultset.getString("username"));
        }

        if (usernameList.contains("darkdark") && usernameList.contains("tester")){
            System.out.println("Test PASSED");
        } else System.out.println("Test FAILED");

        // 5.Adim: Database bağlantısını kapatma
        resultset.close();
        statement.close();
        connection.close();

    }

}
