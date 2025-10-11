
  Feature: US1003 sauce demo tablosu username sorgusu

    Scenario: TC03 kullanici id numarasindan username sorgusu yapar

      Given kullanici veri tabanına bağlanir
      When saucedemo tablosundan id dgeri 1006 olan kaydin ismini sorgular
      Then username bilgisinin visual_user oldugunu test eder
      And database baglantisini kapatir

