#!/bin/bash

echo "Waiting for LDAP server to start..."
for i in {1..30}; do
    if ldapsearch -x -H ldap://localhost:389 -b dc=example,dc=com -D "cn=admin,dc=example,dc=com" -w admin >/dev/null 2>&1; then
        echo "LDAP server is ready!"
        exit 0
    fi
    echo "Attempt $i: LDAP server not ready yet..."
    sleep 2
done
echo "LDAP server failed to start properly"
exit 1 