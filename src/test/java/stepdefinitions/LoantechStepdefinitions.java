package stepdefinitions;

import io.cucumber.java.en.*;
import manageQueries.LoantechQueries;
import org.testng.Assert;

import java.sql.*;

public class LoantechStepdefinitions {

    Connection connection;
    Statement statement;
    ResultSet resultset;

    @Given("kullanici veri tabanına bağlanir")
    public void kullanici_veri_tabanına_bağlanir() throws SQLException {
        String url = "jdbc:mysql://195.35.59.18/u201212290_qaloantec";
        String username = "u201212290_qaloanuser";
        String password = "HPo?+7r$";

        connection = DriverManager.getConnection(url,username,password);
    }

    @When("deposits tablosundaki amount degerlerini sorulayip kaydeder")
    public void deposits_tablosundaki_amount_degerlerini_sorulayip_kaydeder() throws SQLException {
        String query = LoantechQueries.depositsTablosundakiAmaountSorgusu;

        // 3.Adim: SQL sorgusunu çalıştırma ve sonuç setini kaydetme
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(query);
    }
    @Then("{int}$ ile {int}$ arasinda {int} kayit bulundugunu test eder")
    public void $_ile_$_arasinda_kayit_bulundugunu_test_eder(Integer minDeger, Integer maxDeger, Integer kayitSayisi) throws SQLException {

        int sayac = 0;

        while (resultset.next()){
            Integer satirdakiAmount = resultset.getInt("amount");
            if (satirdakiAmount>=minDeger && satirdakiAmount<=maxDeger){
                sayac++;
            }
        }

        Assert.assertEquals(sayac,kayitSayisi);

    }
    @Then("database baglantisini kapatir")
    public void database_baglantisini_kapatir() throws SQLException {
        resultset.close();
        statement.close();
        connection.close();

    }


}
