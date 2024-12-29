# LDAP Test Sunucusu Projesi

Bu proje, LDAP (Lightweight Directory Access Protocol) test sunucusu için örnek bir yapılandırma içermektedir.

## Proje Hakkında

Bu proje, test amaçlı bir LDAP sunucusu kurmak ve yapılandırmak için gerekli LDIF (LDAP Data Interchange Format) dosyasını içerir. LDAP, dizin hizmetleri için kullanılan bir protokoldür ve genellikle kullanıcı kimlik doğrulama ve yetkilendirme işlemleri için kullanılır.

## Yapılandırma Detayları

### Temel Yapı

- Kök domain: `dc=example,dc=com`
- Organizasyon: Example Inc.

### Organizasyon Birimleri

1. People (Kişiler)
   - Yolu: `ou=people,dc=example,dc=com`
   - Kullanıcı bilgilerini içerir

2. Groups (Gruplar)
   - Yolu: `ou=groups,dc=example,dc=com`
   - Grup tanımlamalarını içerir

### Test Kullanıcısı

- Kullanıcı ID (uid): test1
- Tam Ad (cn): Test User1
- Soyad (sn): User1
- Şifre: test123
- DN: `uid=test1,ou=people,dc=example,dc=com`

### Gruplar

- Users Grubu
  - DN: `cn=users,ou=groups,dc=example,dc=com`
  - Üyeler: test1 kullanıcısı

## Sistem Test Görüntüleri

### phpLDAPadmin Arayüzü
Burada phpLDAPadmin arayüzünden bir ekran görüntüsü ekleyebilirsiniz.

