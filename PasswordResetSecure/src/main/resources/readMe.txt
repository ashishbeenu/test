############################# App URL: ############################# 	
GET		http://localhost:444/api/v1/test

GET:	http://localhost:444/api/v1/user/profile/ayadav32


POST:	http://localhost:444/api/v1/user/profile
		Request Body:
		{
		    "userId": "ayadav32",
		    "secretQuestion": [
		        {
		            "count": "1",
		            "answer": "beenu"
		        },
		        {
		            "count": "2",
		            "answer": "jhon"
		        },
		        {
		            "count": "3",
		            "answer": "9880711544"
		        }
		    ]
		}
		Response Body:
		true		
		
############################# DB Details ############################# 
#dbDetails:
	jdbc:mysql://localhost:3306/dxc-labs-store?useSSL=false
#username:XXXX
#password:XXXX

################################# EC2 Configuration START ###############################

#How to create mongoDB database:
	1. create mongod.config file in the any location 
	c:\Program Files\MongoDB\Server\3.6\bin>mongod.exe --config "C:\Program Files\MongoDB\Server\3.6\config\mongod.config"
	
	2. create directory in the location as below
	D:\mongodb\project\aims
#How to start mongoDB: ##################
	c:\Program Files\MongoDB\Server\3.6\bin>mongod --port 27017 --dbpath "D:\mongodb\project\dxc-labs-store"
	c:\Program Files\MongoDB\Server\3.6\bin>mongod --port 27017 --dbpath "C:\Program Files\MongoDB\Server\3.6\data\dxc-labs-store"
	
################################# EC2 Configuration END  ###############################



#How to create mongoDB database:
	1. create mongod.config file in the any location 
	c:\Program Files\MongoDB\Server\3.6\bin>mongod.exe --config "C:\Program Files\MongoDB\Server\3.6\config\mongod.config"
	
	2. create directory in the location as below
	D:\mongodb\project\aims

#How to start mongoDB:
	D:\mongodb\Server\3.6\bin>mongod --port 27017 --dbpath "D:\mongodb\project\<PUT Project name>"
	c:\Program Files\MongoDB\Server\3.6\bin>mongod --port 27017 --dbpath "C:\Program Files\MongoDB\Server\3.6\data\dxc-labs-store"
	
	
	
	
********************************* Creating SSL **********************************	
https://howtodoinjava.com/spring-boot/spring-boot-ssl-https-example/

D:\Software\workspace\workspace-sts-3.8.4\PasswordResetSecure>keytool -genkey -alias selfsigned_localhost_sslserver -keyalg RSA -keysize 2048 -validity 700 -keypass changeit -storepass changeit -keystore ssl-server.jks
What is your first and last name?
  [Unknown]:  Ashish Yadav
What is the name of your organizational unit?
  [Unknown]:  CI
What is the name of your organization?
  [Unknown]:  DXC
What is the name of your City or Locality?
  [Unknown]:  Bangalore
What is the name of your State or Province?
  [Unknown]:  KA
What is the two-letter country code for this unit?
  [Unknown]:  IN
Is CN=Ashish Yadav, OU=CI, O=DXC, L=Bangalore, ST=KA, C=IN correct?
  [no]:  Y


D:\Software\workspace\workspace-sts-3.8.4\PasswordResetSecure>
To view what is inside this keystore we can again use the keytool -list command as bellow.
>keytool -list -keystore ssl-server.jks
>D:\Software\workspace\workspace-sts-3.8.4\PasswordResetSecure\src\main\resources\config>keytool -list -keystore ssl-server.jks
Enter keystore password:

Keystore type: JKS
Keystore provider: SUN

Your keystore contains 1 entry

selfsigned_localhost_sslserver, Oct 10, 2018, PrivateKeyEntry,
Certificate fingerprint (SHA1): D2:08:84:21:C5:38:3E:3A:C7:30:9A:CF:D7:E3:AD:36:6C:C2:7F:52

D:\Software\workspace\workspace-sts-3.8.4\PasswordResetSecure\src\main\resources\config>

