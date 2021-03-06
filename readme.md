# CatSneeze Online Movie Web Application
Full Stack Web App for selling movies
__________________________________________________________________________
1. Implemented restful services with multiple layers to support browsing, ordering, managing etc. operations using Java.
2. Scaled MVC app with MySQL master-slave replication, Apache load balancing, connection pooling and sticky session in AWS ec2 and GCP, test average query time under one/multiple threads mode.
3. Interacted with web UI and a corresponding built Android UI through https.

## Index page demo screenshot:
<img src="/resources/CatSneeze.png"  alt="main page">

## project package structure:

dao: database access object: about access database

pojo: plain ordinary java object: one class corresponds to one database

service: about business service logics

servlet: controller: control to select service and assemble Vo and forward to pages

vo: view object: include all data to be viewed in pages (to be sent to front web by ajax)

common: contains constants and C-S interaction object and Error Code

## technique detail:
### connection pooling<br>
Because we use Intellij as IDE, it doesn’t contain a default META-INF folder, we add a Tomcat Context Descriptor in the Module Setting and the folder and its corresponding context.xml come out. We did the following three modification to allow connection pooling:
1)	Add a <Resource> configuration in <Context> to indicate the url, username, password and so on.
2)	Add a <resource-ref> node in web.xml to indicate the resources name.
3)	get a connection from the pool in each jdbc operation in dao files.

Using Context.lookup to get data source, and use getConnection factory function to get each connection. Use connection.close() to return the connection to the pool.


### Prepared Statements<br>
We enabled the cache function of prepared statement, and used a ‘?’ style of prepare statement to put variables into sql. In the method, the prepared statement is complied once and cached in memory to speed up the jdbc operation.
Use “Select distinct * from creditcards where id = ?” to add variable into sql after creating prepared statement.

### JMeter<br>
When no login (there’s no session), we use JMeter to request 10 times to a “/genres” service to Load Balancer in Google Cloud.
Load Balancer pass the request to the two EC2 instances in a round-robin fashion.

## System structure:
<img src="/resources/structure.png"  alt="structure">

### Load Balance<br>
Among them, a load balancer in google cloud is roled by a Apache server, and we use a round-robin sticky session fashion for polling. Requests sent from the front end are forwarded to two machines loaded on AWS EC2 in turn. By this method, the burden of access is reduced and the sustainable number of service users is increased.

### Master-Slave Mysql<br>
We enabled connection pooling and prepared statement in Master-Slave Mysql method to scale system performance.
MySQLs on two AWSs is deployed in a Master-Slave mode, in which the Master can perform all write operations and partial read operations while the Slave performs partial read operations. Data redundancy is performed on instances2 and instance3. Among them, data replication from master to slave can keep the two databases synchronized at any time.

## Service
### Search<br>
We implemented full-text search and fuzzy search service in a Database UDF method in C++, and used stored procedures for complex movies-related service handling, developed an external program for XML parsing and DB inserting.

## More related discuss:<br>
[CAP theory practical analysis](https://alleninwood.github.io/2018/04/19/CAP-theory-practical-analysis/#more)
