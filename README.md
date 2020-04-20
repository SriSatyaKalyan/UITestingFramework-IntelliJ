# UITestingFramework-IntelliJ #
---------------
:octocat: A testing framework which runs few tests which put to test different UI aspects of the following website: http://www.qaclickacademy.com/

## DESCRIPTION ##
The test cases were not built based on a set of specific test cases. They were designed to learn and to test each and every specific webpage of the website, which gives ample scope to leverage the multiple features of Selenium.

The testing framework consists of 5 pages of a website. They are:
1. Home Page Test
2. Login Page Test
3. Practice Page Test I
4. Practice Page Test II
5. Sign Up Page Test

The Practice Page aspect of the website contains a lot of information and hence, it was decided to split it into two different test scripts

We will deep-dive into what the respective page files test below:

#### 1. Home Page Test ####
This complete test file consists of three tests on the http://www.qaclickacademy.com/ page which is referred to as the Home Page:
* ***NavigationBarPresence***
This test script verifies whether the Navigation Bar of the web page is displayed or not.
* ***PopupPresence***
This test script verifies whether the pop-up which displays the information of Newsletter and asks the user to subscribe to it, appears on the web page. The script verifies if the button displaying "NO THANKS" is present and clicks on it to close the pop up.
* ***TitleTextValidation***
This test script extracts the text present on one of the headers of the web page and verifies it with an expected one called "Featured Courses".

#### 2. Login Page Test ####
This complete test file consists of five tests on the https://sso.teachable.com/secure/9521/users/sign_in?clean_login=true&reset_purchase_session=1 page which is referred to as the Login Page:
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
This complete test file consists of nine tests on the http://www.qaclickacademy.com/practice.php page which is referred to as the Practice Page:
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

#### 4. Practice Page Test II ####
* ***OpenNewTab***
This test script verifies if the title of the new tab which opens after clicking on the 'Open Tab' button is 'Rahul Shetty Academy'
* ***OpenNewWindow***
This test script verifies if the title of the new tab which opens after clicking on the 'Open Window' button is 'Selenium'
* ***accessAllLinks***
This test script verifies if the links in the footer of the page are working. The links are 'Practice Page', 'Apache JMeter' page, 'Appium page', SOAP UI' page and 'REST API' page
* ***elementHide***
This test script verifies if clicking on the 'Hide' button in the 'Element Displayed Example' will result in the corresponding text-entry box to be NOT displayed
* ***elementShow***
This test script verifies if clicking on the 'Show' button in the 'Element Displayed Example' will result in the corresponding text-entry box to be displayed
* ***getAlert***
This test script verifies if clicking on the 'Alert' button in the 'Switch to Alert Example' is generating an alert with the text entered in the corresponding text-entry box
* ***getConfirm***
This test script verifies if clicking on the 'Confirm' button in the 'Switch to Alert Example' is generating a pop-up with the text entered in the corresponding text-entry box
* ***getElementDisplayedTitle***
This test script verifies that the title of 'Element Displayed Example' section contains 'Element Displayed'
* ***getSwitchAlertTitle***
This test script verifies that the title of 'Switch To Alert Example' section contains 'Switch To Alert'
* ***getSwitchTabTitle***
This test script verifies that the title of 'Switch Tab Example' section contains 'Switch Tab Example'
* ***getSwitchWindowTitle***
This test script verifies that the title of 'Switch Window Example' section contains 'Switch Window Example'
* ***getTable***
This test script verifies that the combined price of all the courses in the 'Web Table Example' table is 235
* ***getTableTitle***
This test script verifies that the title of 'Web Table Example' section contains 'Web Table'
* ***iFrameOperation***
:question: This test script used to verify the text of the home page in the iframe but the homepage has changed. THIS TEST IS NOW FAILING.
* ***iFrameTitle***
:question: This test used to verify the title of the iframe with the expected text of "". This test is now marked as FAILED.
* ***mouseHoverTitle***
This test script verifies that the title of 'Mouse Hover Example' section contains 'Mouse Hover'
* ***mouseHoverSelectReload***
This test script verifies if clicking on the 'Reload' option of the 'Mouse Hover' button reloads the page
* ***mouseHoverSelectTop***
This test script verifies if clicking on the 'Top' option of the 'Mouse Hover' button takes us to the top of the page

#### 5. Sign Up Page Test ####
This test file consists of two tests on the "https://sso.teachable.com/secure/9521/users/sign_up?flow_school_id=9521" which is referred to as the SignUp page:
* ***SignUpTest***
:question: This test script verifies that different sets of credentials with their corresponding 'Subscription' and 'Terms of Use' checkbox selections are throwing the corresponding error messages
* ***TitleTextValidation***
This test script verifies the title of the sign up page with the expected text 'Rahul Shetty Academy'

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
