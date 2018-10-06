# UITestingFramework-IntelliJ
A testing framework which runs few tests which put to test different UI aspects of the following page: 
http://www.qaclickacademy.com/

**The only caveat being that the framework is now built with IntelliJ which I think was easier to work on than Eclipse** 

Observations:
1) The tests will be run using Jenkins tool and the reports will be generated
2) Extent Test reports will be used for better representation

To be noted:
1) The tests are purely made for learning purposes and so, validating them and questioning the author is a strict no-no
2) An upcoming testing framework which tests the APIs is in the works and will be released shortly
3) Though questioning the author is prohibited, pointing the errors and coming up with a better solution is encouraged

Upcoming Modifications:
1) Headless Browser Integration
2) Data-Driven Framework for LoginPageTest-LoginDetails test && PracticePageTest1-getSuggestedCountry test
-> This would follow a feature workflow in Git
-> Also make sure that an Excel sheet is added for data-handling and ApachePOI is used to handle it (I honestly don't know how else to handle it)
3) Establish a working connection with Jenkins
4) Make Log4j2 working

Groups:
Important -> Solely created to understand how groups are supposed to be handled in TestNG. I went head with including the @Before and @After methods into the groups as well. But I strongly believe that there is a much better way to handle the methods
Modification1 -> Changed @Before and @After methods into 'must' groups and then made sure that this group is included into every testng.xml file handling groups