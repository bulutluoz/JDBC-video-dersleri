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

    @When("cron schedules tablosundaki isimleri sogular")
    public void cron_schedules_tablosundaki_isimleri_sogular() throws SQLException {

        String query = LoantechQueries.cronSchedulesTablosuNameSorgusu;
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(query);
    }
    @Then("ilk iki ismin {int} minutes ve {int} minutes oldugunu test eder")
    public void ilk_iki_ismin_minutes_ve_minutes_oldugunu_test_eder(Integer int1, Integer int2) throws SQLException {

        String expectedIlkName = "5 Minutes";

        resultset.absolute(1);
        String actualIlkName = resultset.getString("name");

        Assert.assertEquals(actualIlkName,expectedIlkName);

        String expectedIkinciIsim = "10 Minutes";
        resultset.absolute(2);
        String actualIkinciName = resultset.getString("name");

        Assert.assertEquals(actualIkinciName,expectedIkinciIsim);



    }

    @When("saucedemo tablosundan id dgeri {int} olan kaydin ismini sorgular")
    public void saucedemo_tablosundan_id_dgeri_olan_kaydin_ismini_sorgular(Integer int1) throws SQLException {
        String query = LoantechQueries.saucedemo1006NoluKaydinUsernameSorgusu;
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(query);

    }
    @Then("username bilgisinin visual_user oldugunu test eder")
    public void username_bilgisinin_visual_user_oldugunu_test_eder() throws SQLException {

        resultset.first();
        String expectedUsername = "visual_user";
        String actualUsername = resultset.getString("username");
        Assert.assertEquals(actualUsername,expectedUsername);

    }

    @When("subscribers tablosunda id si {int} olan kaydin email bilgisini sorgular")
    public void subscribers_tablosunda_id_si_olan_kaydin_email_bilgisini_sorgular(Integer int1) throws SQLException {
       String query = LoantechQueries.subscribersTablosundaki20numaraliKaydinEmailSorgusu;

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(query);
    }
    @Then("email'in raisa.price@gmail.com oldugunu test eder")
    public void email_in_raisa_price_gmail_com_oldugunu_test_eder() throws SQLException {

        String expectedEmail = "raisa.price@gmail.com";

        resultset.first();
        String actualEmail = resultset.getString("email");

        Assert.assertEquals(actualEmail,expectedEmail);

    }

}
