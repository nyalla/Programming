## Example for Database and spring boot 

# Data base:

 Have to add dependency for mysql and Java connectivity
 <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>

Properties:

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://localhost:3306/base
spring.datasource.username=root
spring.datasource.password=root

If we using capital letters in database table and column anmes , getting exceptions aying unknown column. TO avoid this we have to use this property.
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl		
