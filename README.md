# LDAP Test Server Project

This project contains a sample configuration for an LDAP (Lightweight Directory Access Protocol) test server.

## About the Project

This project includes the LDIF (LDAP Data Interchange Format) file required to set up and configure a test LDAP server. LDAP is a protocol used for directory services and is commonly used for user authentication and authorization processes.

## Configuration Details

### Basic Structure

- Root domain: `dc=example,dc=com`
- Organization: Example Inc.

### Organizational Units

1. People
   - Path: `ou=people,dc=example,dc=com`
   - Contains user information

2. Groups
   - Path: `ou=groups,dc=example,dc=com`
   - Contains group definitions

### Test User

- User ID (uid): test1
- Full Name (cn): Test User1
- Surname (sn): User1
- Password: test123
- DN: `uid=test1,ou=people,dc=example,dc=com`

### Groups

- Users Group
  - DN: `cn=users,ou=groups,dc=example,dc=com`
  - Members: test1 user

## System Test Screenshots

### phpLDAPadmin Interface
You can add a screenshot of the phpLDAPadmin interface here.
