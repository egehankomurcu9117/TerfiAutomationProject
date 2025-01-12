# Gauge Web Test Automation Framework

Gauge, Selenium 4 ve Java kullanılarak oluşturulmuş, JSON tabanlı element yönetimi içeren kapsamlı bir web test otomasyon framework'ü.

## Özellikler

- Gauge BDD framework entegrasyonu
- Selenium 4 entegrasyonu
- JSON tabanlı merkezi element yönetimi
- Cross-browser test desteği (Chrome, Firefox, Safari)
- Page Object Model implementasyonu
- Gauge HTML raporlama
- Tag bazlı test çalıştırma
- Singleton pattern ile WebDriver yönetimi

## Gereksinimler

- Java 11 veya üstü
- Maven 3.6 veya üstü
- Gauge CLI
- Chrome, Firefox veya Safari tarayıcı


### Desteklenen Lokator Tipleri
- id
- css
- xpath
- name
- class
- tagname
- linktext
- partiallinktext

### Element Kullanım Örneği
```java
// JSON'dan element çağırma
click("loginButton");
sendKeys("usernameField", "username");
```

## Kurulum

1. Repository'yi klonlayın:
```bash
git clone https://github.com/yourusername/gauge-web-automation.git
```

2. Gauge'u yükleyin:
```bash
npm install -g @getgauge/cli
```

3. Java plugin'ini yükleyin:
```bash
gauge install java
```

4. Bağımlılıkları yükleyin:
```bash
mvn clean install
```

## Yeni Sayfa ve Element Ekleme

1. elements.json dosyasına yeni sayfanın elementlerini ekleyin:
```json
[
  {
    "key": "usernameField",
    "value": "//input[@placeholder='Username']",
    "type": "xpath"
  } 
]
```

2. Yeni page class'ı oluşturun:
```java
public class NewPage extends BasePage {
    public NewPage() {
        super("newPage");
    }
}
```

## Testleri Çalıştırma

### Tüm testleri çalıştırma:
```bash
mvn gauge:execute
```

### Tag'e göre test çalıştırma:
```bash
mvn gauge:execute -DspecsDir=specs -Dtags="smoke"
```

### Belirli bir tarayıcıda test çalıştırma:
```bash
mvn gauge:execute -Dbrowser=firefox
```

### Paralel test çalıştırma:
```bash
mvn gauge:execute -DinParallel=true -DspecsDir=specs
```

## HTML Rapor Oluşturma

Testler çalıştırıldıktan sonra HTML rapor otomatik olarak `reports` klasöründe oluşturulacaktır.


## İletişim

Proje Sorumlusu - [@egehankomurcu9117](https://github.com/egehankomurcu9117)

Proje Linki: [https://github.com/egehankomurcu9117/TerfiAutomationProject](https://github.com/egehankomurcu9117/TerfiAutomationProject)