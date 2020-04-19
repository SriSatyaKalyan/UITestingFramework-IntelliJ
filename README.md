# UITestingFramework-IntelliJ #
A testing framework which runs few tests which put to test different UI aspects of the following page:
http://www.qaclickacademy.com/

## DESCRIPTION ##


### Observations ###
1) The tests will be run using Jenkins tool and the reports will be generated
2) Extent Test reports will be used for better representation

** Upcoming Modifications **
- [ ] Headless Browser Integration
- [ ] Establish a working connection with Jenkins
- [ ] Use Log4j2 to handle logs -> This is buggy at the moment

** Pointers to be Addressed **
- [ ] DO NOT use a static web-driver reference
- [ ] DO NOT use Implicit Waits
- [ ] Let the TestNG library handle error messages



## Groups: ##

Important -> Solely created to understand how groups are supposed to be handled in TestNG. I went head with including the @Before and @After methods into the groups as well. But I strongly believe that there is a much better way to handle the methods

Modification1 -> Changed @Before and @After methods into 'must' groups and then made sure that this group is included into every testng.xml file handling groups
