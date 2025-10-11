
  Feature: US1001 kullanici deposits tablosunda sorgu yapar

    Scenario: TC01 deposits tablosunda istenen araliktaki kayit sayisi testi

      Given kullanici veri tabanına bağlanir
      When deposits tablosundaki amount degerlerini sorulayip kaydeder
      Then 100$ ile 500$ arasinda 10 kayit bulundugunu test eder
      And database baglantisini kapatir
