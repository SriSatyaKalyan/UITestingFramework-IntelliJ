# UITestingFramework-IntelliJ #
---------------
:octocat: A testing framework which runs few tests which put to test different UI aspects of the following website: http://www.qaclickacademy.com/

## DESCRIPTION ##
The test cases were not built based on a set of specific test cases. They were designed to learn and to test each and every specific webpage of the website, which gives ample scope to leverage the multiple features of Selenium.

The testing framework consists of 5 pages of a website. They are:
1. [Home Page Test](#1. Home Page Test "Go to Home Page Test")
2. [Login Page Test](#2. Login Page Test)
3. [Practice Page Test I](#3. Practice Page Test I)
4. Practice Page Test II
5. Sign Up Page Test

The Practice Page aspect of the website contains a lot of information and hence, it was decided to split it into two different test scripts

We will deep-dive into what the respective page scripts test below:

#### 1. Home Page Test ####
This complete test script consists of three tests on the http://www.qaclickacademy.com/ page which is referred to as the Home Page:
* ***NavigationBarPresence***
This test script verifies whether the Navigation Bar of the web page is displayed or not.
* ***PopupPresence***
This test script verifies whether the pop-up which displays the information of Newsletter and asks the user to subscribe to it, appears on the web page. The script verifies if the button displaying "NO THANKS" is present and clicks on it to close the pop up.
* ***TitleTextValidation***
This test script extracts the text present on one of the headers of the web page and verifies it with an expected one called "Featured Courses".

#### 2. Login Page Test ####
This complete test script consists of five tests on the https://sso.teachable.com/secure/9521/users/sign_in?clean_login=true&reset_purchase_session=1 page which is referred to as the Login Page:
* ***ForgotPassword***
This test script verifies if clicking on the 'Forgot Password' link on the login page re-directs the user to the Reset Password page.
* ***LoginButton***
This test script basically starts at the Login Page, closes the pop-up and then clicks on the Login option on the page. We are re-directed to the Login Page and here is where a verification that the text 'Rahul Shetty Academy' is present is made. This test is invoked twice using the 'invocationCount' annotation.
* ***LoginDetails***
This test verifies whether a particular set of {username, password} credentials are triggering the expected 'Credentials Invalid' message. This test is invoked twice using the 'dataProvider' annotation as the data is provided using a 2x3 matrix consisting of [username, password, text] data sets. One set has the 'text: Restricted User' and the other has 'text: UnRestricted User' as the attribute of the credentials.
* ***LoginDetailswithExcelSheet***
This test verifies whether a particular set of {username, password} credentials are triggering the expected 'Credentials Invalid' message. This test is invoked thrice using the 'dataProvider' annotation as the data is extracted from 3 sets which are present in an excel sheet consisting of [username, password, text] data sets. Of these 3, two belong to 'text: Restricted User' and the other has 'text: UnRestricted User' as the attribute of the credentials.
* ***SendInstructions***
This test verifies whether the error message shows up after a random email is given in the 'Send Me Instructions' web page.

#### 3. Practice Page Test I ####
This complete test script consists of nine tests on the http://www.qaclickacademy.com/practice.php page which is referred to as the Practice Page:
* ***CheckboxExampleTitle***
This test script verifies if the title for the 'Checkbox Example' contains the expected text 'Checkbox'.
* ***DropDownExampleTitle***
This test script verifies if the title for the 'Dropdown Example' contains the expected text 'Dropdown Example'.
* ***RadioButtonTitle***
This test script verifies if the title for the 'Radio Button Example' contains the expected text 'Radio Button'.
* ***RadioButtons***
This test clicks on one of the {1,2,3} numbers and then takes a screenshot using the 'baseClass' where the screenshot serves as an artifact for later verification.
* ***SuggestionClassTitle***
This test script verifies if the title for the 'Suggession Class Example' contains the expected text 'Suggession Class'.
* ***TitleTextValidation***
This test script verifies if the title of the web page consists of the expected text 'Practice Page'
* ***getSuggestedCountry***
:question: This test script clicks on the 'Suggession Class Example' box and enter the keyword which triggers a few suggestions. Select a specific country out of those and verify if the text in the box is the specific country which has been selected. THIS TEST IS NOT WORKING.
* ***selectCheckbox***
:question: This test script clicks on specific checkbox out of the three, driven by the 'data.properties' file. THERE IS NO VERIFICATION THOUGH.
* ***selectDropDownOptions***
This test script clicks on specific dropdown option out of the three, driven by the 'data.properties' file.

### Results ###
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
