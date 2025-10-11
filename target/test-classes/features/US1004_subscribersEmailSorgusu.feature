
  Feature: US1004 Subscribers tablosu email sorgusu

    @wip
    Scenario: TC04 id numarasi ile email bilgisi sorgulama

      Given kullanici veri tabanına bağlanir
      When subscribers tablosunda id si 20 olan kaydin email bilgisini sorgular
      Then email'in raisa.price@gmail.com oldugunu test eder
      And database baglantisini kapatir