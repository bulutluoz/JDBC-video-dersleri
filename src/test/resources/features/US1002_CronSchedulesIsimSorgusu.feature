
  Feature: US1002 Crone Schedules tablosu isim sorgusu

    Scenario: TC02 kullanici ilk iki ismin verilen degerler oldugunu dogrular

      Given kullanici veri tabanına bağlanir
      When cron schedules tablosundaki isimleri sogular
      Then ilk iki ismin 5 minutes ve 10 minutes oldugunu test eder
      And database baglantisini kapatir
