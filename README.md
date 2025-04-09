# 📱 Telefon Rehberi Yönetim Sistemi

Bu proje, bir **Telefon Rehberi Yönetim Sistemi** olarak geliştirilmiştir. Sistem, iki mikroservisli yapıya sahiptir:

* **Contact Service:** Kişi bilgilerini ekler, günceller, siler ve kişilerin iletişim bilgilerini yönetir.
* **Report Service:** Kişi bilgilerini aldıktan sonra, belirli verilerle raporlar oluşturur.

Proje, **Java 21** ve **Spring Boot** altyapısında geliştirilmiştir. **RabbitMQ** kullanılarak asenkron iletişim sağlanmış, mikroservisler arası veri aktarımı gerçekleştirilmiştir.

## ⚡ Teknolojiler

Projede aşağıdaki teknolojiler kullanılmıştır:

* **Java 21**  
* **Spring Boot 3**  
* **Spring Data JPA**  
* **RabbitMQ** (Mikroservisler arası iletişim için)  
* **JUnit 5 & Mockito** (Unit ve Integration Testler için)  
* **Swagger UI** (API dokümantasyonu için)  
* **PostgreSQL** (Veritabanı yönetimi için)  
* **Docker**  
* **Docker Compose**  

## 💡 Sistem Mimarisi

Proje, **Spring Boot mikroservis yapısı** ile geliştirilmiştir. Ana bileşenler:

* **Contact Service:** Kişi ekleme, silme ve güncelleme işlemleri.
* **Report Service:** Kişi bilgilerini alarak rapor oluşturur ve rapor verilerini saklar.
* **RabbitMQ:** Mikroservisler arası veri iletimi için kullanılır.
* **Report Builder:** Kişi bilgilerini alarak raporları oluşturur.
* **Exception Handling:** Global hata yönetimi.
* **PostgreSQL:** Veritabanı yönetimi.

## 🛠 Veritabanı Yapısı

Projede **PostgreSQL** kullanılmaktadır.

**Tablolar:**

* **Contacts:** `(id, name, surname, phone, email, location)`
* **Reports:** `(id, contactId, location, contactCount, phoneNumberCount, requestedAt)`

## 📝 API Endpointleri

### 📞 Kişi Yönetimi

* `POST /contacts/create` - Yeni kişi ekle
* `DELETE /contacts/delete/{contactId}` - Kişi sil
* `GET /contacts` - Tüm kişileri listele
* `GET /contacts/{contactId}` - Kişi detaylarını getir

### 📊 Rapor Yönetimi

* `GET /reports` - Tüm raporları listele
* `GET /reports/{reportId}` - Rapor detaylarını getir

## 💡 Unit ve Integration Testler

Testler **JUnit 5** ve **Mockito** kullanılarak yazıldı.

* **%100 Unit Test Coverage** sağlamak için her servis için testler yazıldı.
* **PostgreSQL Database** kullanılarak test ortamı oluşturuldu.

## 🐳 Docker ile Hızlı Kurulum ve Çalıştırma

Projenin bağımlılıkları olan **RabbitMQ** ve **PostgreSQL**, **Docker Compose** ile tek bir komutla kolayca ayağa kaldırılabilir.

### Docker Compose Kullanarak Çalıştırma

Projenizi başlatmak için aşağıdaki adımları takip edebilirsiniz:

1. **Docker Compose ile RabbitMQ ve PostgreSQL servisini başlatın:**
```bash
docker-compose up

3. **Uygulamayı Başlatın:**
```sh
mvn spring-boot:run
```

## 📑 Swagger UI Kullanımı
<img width="1728" alt="image" src="https://github.com/user-attachments/assets/d083796a-2d7a-41eb-9c0d-6184f2fc3195" />

## Database Ekran Görüntüsü
![image](https://github.com/user-attachments/assets/8f58ab36-7f5b-47c0-b4f1-6c33bf6b7e43)

