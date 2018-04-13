cs122b project

# project package structure:

dao: database access object: about access database

pojo: plain ordinary java object: one class corresponds to one database

service: about business service logics

servlet: controller: control to select service and assemble Vo and forward to pages

vo: view object: include all data to be viewed in pages (to be sent to front web by ajax)

common: contains constants and C-S interaction object and Error Code

# technique detail:
* connection pooling
Because we use Intellij as IDE, it doesn’t contain a default META-INF folder, we add a Tomcat Context Descriptor in the Module Setting and the folder and its corresponding context.xml come out. We did the following three modification to allow connection pooling:
1)	Add a <Resource> configuration in <Context> to indicate the url, username, password and so on.
2)	Add a <resource-ref> node in web.xml to indicate the resources name.
3)	get a connection from the pool in each jdbc operation in dao files.

Using Context.lookup to get data source, and use getConnection factory function to get each connection. Use connection.close() to return the connection to the pool.


* Prepared Statements
We enabled the cache function of prepared statement, and used a ‘?’ style of prepare statement to put variables into sql. In the method, the prepared statement is complied once and cached in memory to speed up the jdbc operation.
Use “Select distinct * from creditcards where id = ?” to add variable into sql after creating prepared statement.

* JMeter
When no login (there’s no session), we use JMeter to request 10 times to a “/genres” service to Load Balancer in Google Cloud.
Load Balancer pass the request to the two EC2 instances in a round-robin fashion.