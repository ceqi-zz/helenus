# helenus

Helenus is a web application predicting England Premier League football game outcomes. 
This was my final projct, check out my final project report for design and implementation details
https://drive.google.com/file/d/0B83uTSV_seT5M2FJeHdaYm4yZWc/view?usp=sharing

## Installation

### Before you begin

Install mahout on your Linux Distro / Mac machine and set up the environment, find details from the link below: 
http://mahout.apache.org/general/downloads.html

Install gwt sdk, deteails can be found from the link below:
http://www.gwtproject.org/doc/latest/tutorial/

Betfair account and an application key are needed to fetch fixture, 
https://developer.betfair.com/get-started/

then put them in war/config.properties file, the file format is (or any format that a property file accepts):
```
username=xxx
password=xxx
X-Application=xxx
```

### Compile

Put these libs under war/WEB-INF/lib/ folder and add them in classpath:
 - mahout-core-0.9.jar
 - mahout-math-0.11.1.jar
 - hadoop-common-2.4.1.jar
 - commons-math3-3.5.jar
 - log4j-1.2.17.jar
 - slf4j-log4j12-1.7.13.jar
 - slf4j-api-1.7.13.jar
 - guava-18.0.jar
 - gson-2.5.jar
 
Compile the code, run as web application (assuming using Eclipse).
Copy and paste the generated url to browser to check out upcoming games' preditions. 

## TODO List
1. Deploy the application to a server (servlet container more specificly)
2. Add draw match prediction
and more...


## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
