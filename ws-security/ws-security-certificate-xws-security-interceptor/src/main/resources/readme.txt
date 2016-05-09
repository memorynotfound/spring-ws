# http://ruchirawageesha.blogspot.in/2010/07/how-to-create-clientserver-keystores.html

# create keystore for server and client.
keytool -genkey -alias server -keyalg RSA -keystore server.jks
keytool -genkey -alias client -keyalg RSA -keystore client.jks

# view content of keystore
keytool -list -v -keystore server.jks -storepass changeit
keytool -list -v -keystore client.jks -storepass changeit

# getting the server public key and store it in the client
keytool -export -file server.cert -keystore server.jks -storepass changeit -alias server

# getting client public key and store in server
keytool -export -file client.cert -keystore client.jks -storepass changeit -alias client

# view the content of the certificate
keytool -printcert -v -file server.cert
keytool -printcert -v -file client.cert

# import client.cert public key into keystore server
keytool -import -file client.cert -keystore server.jks -storepass changeit -alias client

# import server.cert public key into keystore client
keytool -import -file server.cert -keystore client.jks -storepass changeit -alias server

# view content of keystore again
keytool -list -v -keystore server.jks -storepass changeit
keytool -list -v -keystore client.jks -storepass changeit