# CSYE 6225 Spring 2018 LYL Repository
## Team member:
RuixinLi li.ruix@husky.neu.edu.    
BoxuanLu lu.bo@husky.neu.edu.       
Jarvis Yao:yao.jiaw@husky.neu.edu    

## Prerequisites for this project:
1.install and set up Java in your computer.   
    
2.install and set up Mysql in your computer. 
    
3.import Webapp into your IDE(making sure the IDE supports Apache Tomcat),select "Import project from external model"-->"Gradle".    
    
4.Click "edit configuration" in the top-right bar-->Create a Tomcat Server-->Configure the Tomcat directory-->move to the deployment label, add artifact as Gradle:ROOT.war(exploded).    
    
5.Before running this application: make sure you set mysql username and password（The users would be the database name in your mysql:
![](https://content.screencast.com/users/RuixinLi/folders/Jing/media/778de175-ed6e-48cf-b607-59d8c704ef74/00000003.png) 
    
6.Mysql Table structure:   

 |Field         | Type          | Null  | Key | Default  |    Extra     |
 |--------------|---------------|-------|-----|----------|--------------|
 |id            | int(11)       |   No  |PRI  |  NULL    |auto_increment|
 |email_addr    | varchar(255)  |   Yes |UNI  |  NULL    |              |
 |img_addr      | varchar(255)  |   Yes |     |  NULL    |              |
 |password      | varchar(255)  |   Yes |     |  NULL    |              |
 
     
 7.For tests: follow these steps to install latest Jmeter in Ubuntu(make sure you have Java in your Ubuntu:
 ```Java
 sudo apt-get update
 sudo apt-get install openjdk-7-jre-headless
 wget -c http://ftp.ps.pl/pub/apache//jmeter/binaries/apache-jmeter-3.0.tgz
 tar -xf apache-jmeter-3.0.tgz
 ```
 Register load tests:    
 Create a new test plan-->add thread group-->set number of threads as 100-->set Ramp-Up Period and loop count as 1-->Add CSV data Set Config(the CSV file should include 100 pairs of username and password-->set variable names as "account,password"-->create open registration page request(port number:8080;Http request method:POST;Path:/signupPost),click the Parameter label and add 3 parameter:account, password, imgAddr. Value format for them should be ${account}, ${password}-->create a View Result Tree-->You are good to go: click the Start button.
 ![](https://content.screencast.com/users/RuixinLi/folders/Jing/media/8d72c880-efaa-4004-aeda-434b290067b5/00000004.png)
 ![](https://content.screencast.com/users/RuixinLi/folders/Jing/media/115b2dfa-5c10-43f9-be13-af1dbf6873f0/00000005.png)
     
     
 Login load tests:
 Create a new test plan-->create 2 HTTP Request as login and gohome, set gohome Request method as GET, Path as /;set login  Request method as POST, Path as /loginPost (other procedures are same with register)-->You are good to go.
 ![](https://content.screencast.com/users/RuixinLi/folders/Jing/media/5cff1e2f-0d1d-45b6-bfda-dea432d42a90/00000006.png)
 
8. Set up TravisCI    
Make sure you have added this particular repository to your Travis account.
    
Enter the directory by command line:csye6225-spring2018->create file by command "vim .travis.yml"-->add following content to this file:    
```Bash
language: java
jdk:
- oraclejdk8

sudo: false
install: true

script:
- cd webapp
- ./gradlew build

notifications:
username:
recipients:
-
```
Push this change to github

Link to Travis CI for this project    

You are all set.
