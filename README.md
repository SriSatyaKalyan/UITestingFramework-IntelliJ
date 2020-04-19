# UITestingFramework-IntelliJ #
=============
A testing framework which runs few tests which put to test different UI aspects of the following page:
http://www.qaclickacademy.com/

## DESCRIPTION ##
---------------
The test cases were not built based on a set of specific test cases. They were designed to learn and to test each and every specific webpage of the website, which gives ample scope to leverage the multiple features of Selenium.

The testing framework consists of 5 pages of a website. They are:
1. Home Page Test
2. Login Page Test
3. Practice Page Test 1
4. Practice Page Test 2
5. Sign Up Page Test

The Practice Page aspect of the website contains a lot of information and hence, it was decided to split it into two different test scripts

We will deep-dive into what the respective page scripts test below:

#### 1. Home Page Test ####
This page consists of three tests on the http://www.qaclickacademy.com/ page:
* Navigation Bar Presence
The *NavigationBarPresence* test script verifies whether the Navigation Bar of the web page is displayed or not.
* Pop Up Presence
The *PopupPresence* test script verifies whether the pop-up which displays the information of Newsletter and asks the user to subscribe to it, appears on the web page. The script verifies if the button displaying "NO THANKS" is present and clicks on it to close the pop up.
* Title Text Validation
The *TitleTextValidation* test script extracts the text present on one of the headers of the web page and verifies it with an expected one called "Featured Courses".


### Observations ###
1) The tests will be run using Jenkins tool and the reports will be generated
2) Extent Test reports will be used for better representation

**Upcoming Modifications**
- [ ] Headless Browser Integration
- [ ] Establish a working connection with Jenkins
- [ ] Use Log4j2 to handle logs -> This is buggy at the moment

**Pointers to be Addressed**
- [ ] DO NOT use a static web-driver reference
- [ ] DO NOT use Implicit Waits
- [ ] Let the TestNG library handle error messages
