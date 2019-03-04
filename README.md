# test

#####################################################################################
#				Configuration for local desktop
#####################################################################################

server.port=446
################## SSL Configuration #################
server.ssl.key-alias=selfsigned_localhost_sslserver
server.ssl.key-password=changeit
server.ssl.key-store=classpath:ssl-server.jks
server.ssl.key-store-provider=SUN
server.ssl.key-store-type=JKS
################## SSL Configuration #################

#server.servlet.context-path=/api
spring.application.name="PasswordResetSecure"

spring.main.banner-mode=LOG

#logging path configuration
logging.path=D:/Software/workspace/workspace-sts-3.8.4/PasswordResetSecure/logs
#logging.path=C:/Users/Administrator/Project/PasswordReset/logs
logging.file=${logging.path}/PasswordResetSecure.application.log
#logging.level.root=info
#logging.pattern.console=%d{dd-MM-yyyy HH:mm:ss.SSS} %magenta([%thread]) %highlight(%-5level) %logger.%M - %msg%n



#logging.level.root=WARN,info,fatal
################ Start MongoDB SQL################ 
# Set here configurations for the database connection
spring.data.mongodb.database=dxc-labs-store
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017

