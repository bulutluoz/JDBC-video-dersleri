package ders05_dinamikQueryOlusturma;

import manageQueries.LoantechQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class C03_kayitSayisiTesti {

    public static void main(String[] args) throws SQLException {
        // Loantech uygulamasındaki subscribers tablosunda
        // toplam 21 kaydın var olduğunu doğrulayın.

        // 1.Adim: Veri tabanına bağlanma ve bağlantıyı kaydetme
        String url = "jdbc:mysql://195.35.59.18/u201212290_qaloantec";
        String username = "u201212290_qaloanuser";
        String password = "HPo?+7r$";

        Connection connection = DriverManager.getConnection(url,username,password);


        // 2.Adim : SQL sorgusunu hazırlama
        String query = LoantechQueries.subscribersEmailSorgusu;

        // 3.Adim: SQL sorgusunu çalıştırma ve sonuç setini kaydetme
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultset = statement.executeQuery(query);

        // 4.Adim: resultSet objesini iterator gibi kullanarak istenen işlemi yapma
        List<String> emailList = new ArrayList<>();

        while (resultset.next()){

            emailList.add(resultset.getString("email"));
        }

        if (emailList.size()==21){
            System.out.println("Test PASSED");
        } else System.out.println("Test FAILED");


        // 5.Adim: Database bağlantısını kapatma
        resultset.close();
        statement.close();
        connection.close();


    }
}
