# Spring Boot LDAP Test Ortamı

Bu proje, Spring Boot uygulamaları için LDAP (Lightweight Directory Access Protocol) test ortamını içermektedir. Docker kullanarak kolayca kurulabilen bu ortam, LDAP tabanlı kimlik doğrulama ve yetkilendirme testleri için idealdir.

## Proje Hakkında

Bu proje, Docker üzerinde çalışan OpenLDAP sunucusu ve bu sunucuyu yönetmek için phpLDAPadmin arayüzünü içerir. LDAP, dizin servisleri için kullanılan bir protokoldür ve genellikle kullanıcı kimlik doğrulama ve yetkilendirme işlemleri için tercih edilir.

## Kurulum

### Ön Gereksinimler

- Docker
- Docker Compose
- Java 17 veya üzeri (Spring Boot uygulaması için)

### Kurulum Adımları

1. Projeyi klonlayın:
   ```bash
   git clone [proje-url]
   ```

2. Proje dizinine gidin:
   ```bash
   cd springboot-ldap
   ```

3. Docker container'larını başlatın:
   ```bash
   docker-compose up -d
   ```

## Sistem Mimarisi

### Docker Container'ları

1. **OpenLDAP Server (openldap)**
   - Port: 389 (LDAP), 636 (LDAPS)
   - Base DN: dc=example,dc=com
   - Admin DN: cn=admin,dc=example,dc=com
   - Admin Şifresi: admin

2. **phpLDAPadmin (Web Arayüzü)**
   - URL: http://localhost:8081
   - Giriş bilgileri:
     - Login DN: cn=admin,dc=example,dc=com
     - Şifre: admin

### LDAP Yapılandırması

#### Organizasyon Yapısı

```
dc=example,dc=com
├── ou=people
│   ├── uid=test
│   └── uid=test1
└── ou=groups
    └── cn=users
```

#### Test Kullanıcıları

1. Test Kullanıcısı 1:
   - DN: uid=test,ou=people,dc=example,dc=com
   - Kullanıcı adı: test
   - Şifre: test123

2. Test Kullanıcısı 2:
   - DN: uid=test1,ou=people,dc=example,dc=com
   - Kullanıcı adı: test1
   - Şifre: test123

#### Gruplar

- Users Grubu:
  - DN: cn=users,ou=groups,dc=example,dc=com
  - Üyeler: test ve test1 kullanıcıları

## Kullanım

### phpLDAPadmin ile Yönetim

1. Web tarayıcınızda http://localhost:8081 adresine gidin
2. Login DN: cn=admin,dc=example,dc=com
3. Şifre: admin
4. Sol menüden LDAP ağacını görüntüleyebilir ve yönetebilirsiniz

### Yeni Kullanıcı Ekleme

1. phpLDAPadmin arayüzünde "ou=people" altına gidin
2. "Create new entry here" seçeneğini tıklayın
3. "Default" şablonunu seçin
4. Gerekli bilgileri doldurun:
   - objectClass: inetOrgPerson
   - cn: Kullanıcı Adı
   - sn: Soyadı
   - uid: Benzersiz ID
   - userPassword: Şifre

### LDIF ile Toplu Veri Yükleme

1. LDIF dosyasını hazırlayın (örnek: test-server.ldif)
2. Docker Compose dosyasında volume olarak ekleyin
3. Container'ı yeniden başlatın

## Sorun Giderme

1. Container'ların Durumunu Kontrol Etme:
   ```bash
   docker-compose ps
   ```

2. OpenLDAP Loglarını Görüntüleme:
   ```bash
   docker-compose logs openldap
   ```

3. Container'ları Yeniden Başlatma:
   ```bash
   docker-compose restart
   ```

4. Tüm Verileri Sıfırlama:
   ```bash
   docker-compose down -v
   docker-compose up -d
   ```

## Sistem Test Ekran Görüntüleri
<img width="1083" alt="Ekran Resmi 2024-12-29 23 54 08" src="https://github.com/user-attachments/assets/905aa003-4ee3-44bb-a6ce-b0781bc37d5e" />
<img width="1496" alt="Ekran Resmi 2024-12-29 23 53 50" src="https://github.com/user-attachments/assets/2793dc0e-9cf8-46e3-98e6-3ebaf808096c" />

### phpLDAPadmin Arayüzü
<img width="1500" alt="Ekran Resmi 2024-12-29 23 54 18" src="https://github.com/user-attachments/assets/ea95c630-e778-4cc3-93ce-a211e2f034d7" />
