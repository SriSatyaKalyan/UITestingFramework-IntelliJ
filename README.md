# UITestingFramework-IntelliJ #
=============
A testing framework which runs few tests which put to test different UI aspects of the following website: http://www.qaclickacademy.com/

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
This complete test script consists of three tests on the http://www.qaclickacademy.com/ page which is referred to as the Home Page:
* *NavigationBarPresence*
This test script verifies whether the Navigation Bar of the web page is displayed or not.
* *PopupPresence*
This test script verifies whether the pop-up which displays the information of Newsletter and asks the user to subscribe to it, appears on the web page. The script verifies if the button displaying "NO THANKS" is present and clicks on it to close the pop up.
* *TitleTextValidation*
This test script extracts the text present on one of the headers of the web page and verifies it with an expected one called "Featured Courses".

#### 2. Login Page Test ####
This complete test script consists of five tests on the https://sso.teachable.com/secure/9521/users/sign_in?clean_login=true&reset_purchase_session=1 page which is referred to as the Login Page:
* *ForgotPassword*
This test script verifies if clicking on the 'Forgot Password' link on the login page re-directs the user to the Reset Password page.
* *LoginButton*
This test script basically starts at the Login Page, closes the pop-up and then clicks on the Login option on the page. We are re-directed to the Login Page and here is where a verification that the text 'Rahul Shetty Academy' is present is made. This test is invoked twice using the 'invocationCount' annotation.
* *LoginDetails*
This test verifies whether a particular set of {username, password} credentials are triggering the expected 'Credentials Invalid' message. This test is invoked twice using the 'dataProvider' annotation as the data is provided using a 2x3 matrix consisting of [username, password, text] data sets. One set has the 'text: Restricted User' and the other has 'text: UnRestricted User' as the attribute of the credentials.
* *LoginDetailswithExcelSheet*
This test verifies whether a particular set of {username, password} credentials are triggering the expected 'Credentials Invalid' message. This test is invoked thrice using the 'dataProvider' annotation as the data is extracted from 3 sets which are present in an excel sheet consisting of [username, password, text] data sets. Of these 3, two belong to 'text: Restricted User' and the other has 'text: UnRestricted User' as the attribute of the credentials.
* *SendInstructions*
This test verifies whether the error message shows up after a random email is given in the 'Send Me Instructions' web page.

### Observations ###
1) The tests will be run using Jenkins tool and the reports will be generated
2) Extent Test reports will be used for better representation

**Upcoming Modifications**
* Headless Browser Integration
* Establish a working connection with Jenkins
* Use Log4j2 to handle logs -> This is buggy at the moment

**Pointers to be Addressed**
* DO NOT use a static web-driver reference
* DO NOT use Implicit Waits
* Let the TestNG library handle error messages
