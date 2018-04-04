cs122b project

project package structure:

dao: database access object: about access database

pojo: plain ordinary java object: one class corresponds to one database

service: about business service logics

servlet: controller: control to select service and assemble Vo and forward to pages

vo: view object: include all data to be viewed in pages (to be sent to front web by ajax)

common: contains constants and C-S interaction object and Error Code

Time log txt file is in the Catsneeze/src/main/webapp/WEB-INF/timeLog.txt,
to calculate the average TS TJ time, use the following script in this filder:
python calAverageTJTS.py