package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import javax.validation.constraints.AssertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	/// REQUIRED TESTS
	/// 1- Verify that an unauthorized user can only access the login and signup page
	@Test
	public void checkUnauthorizedAccess() {

	}

	/// 2 - Sign up a new user, log in, check the homepage is present. Log out, verify home page can't be accessed
	@Test
	public void checkHomeScreenAccess() {

	}

	/// 3 - Create a note, verify it displays
	@Test
	public void createAndDisplayNote() {

	}

	/// 4 - Edits an existing note and check display
	@Test
	public void editAndDisplayNote() {

	}

	/// 5 - Delete note and check list
	@Test
	public void deleteNoteInList() {

	}

	/// 6 - Create credentials, verify diplayed
	@Test
	public void createAndDisplayCredentials() {

	}
	/// 7 - View existing creds, verify viewable password is unencrypted, edit the cred, verify changes displayed
	@Test
	public void viewPasswordAndEditCredentials() {

	}
	/// 8 - Delete existing set of creds, verify the creds are no longer displayed.
	@Test
	public void deleteCredentials() {

	}


	/********************************
	 *
	 *
	 * NON RUBRIC TESTS BELOW HERE
	 *
	 *
	 ********************************/


	public void signUpTestUser(User user) {
		driver.findElement(By.id("inputFirstName")).sendKeys(user.getFirstName());
		driver.findElement(By.id("inputLastName")).sendKeys(user.getLastName());
		driver.findElement(By.id("inputUsername")).sendKeys(user.getUsername());
		driver.findElement(By.id("inputPassword")).sendKeys(user.getPassword());
		driver.findElement(By.id("signUpButton")).click();
	}

	public void enterLoginCreds(User user) {
		driver.findElement(By.id("inputUsername")).sendKeys(user.getUsername());
		driver.findElement(By.id("inputPassword")).sendKeys(user.getPassword());
		driver.findElement(By.id("loginButton")).click();
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignUpPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void getHomePageTOBEREMOVED() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void createUserObject() {
		User user = new User(1, "username", "pass", "1234", "first", "last");
		Assertions.assertEquals(user.getUserId(), 1);
		Assertions.assertEquals(user.getUsername(), "username");
		Assertions.assertEquals(user.getPassword(), "pass");
		Assertions.assertEquals(user.getSalt(), "1234");
		Assertions.assertEquals(user.getFirstName(), "first");
		Assertions.assertEquals(user.getLastName(), "last");
	}

	@Test
	public void createFileObject() {
		File file = new File(1, "testFileName", "docx", "2MB", "test");
		Assertions.assertEquals(file.getFileId(), 1);
		Assertions.assertEquals(file.getFilename(), "testFileName");
		Assertions.assertEquals(file.getContenttype(), "docx");
		Assertions.assertEquals(file.getFilesize(), "2MB");
		Assertions.assertEquals(file.getFiledata(), "test");
	}

	@Test
	public void createNoteObject() {
		Note note = new Note(1, "title", "desc", 1);
		Assertions.assertEquals(note.getNoteid(), 1);
		Assertions.assertEquals(note.getNotedescription(), "desc");
		Assertions.assertEquals(note.getUserid(), 1);
	}

	@Test
	public void createCredentialObject() {
		Credential credential = new Credential(1, "google.com", "user", "key", "pwd", 1);
		Assertions.assertEquals(credential.getCredentialid(), 1);
		Assertions.assertEquals(credential.getUrl(), "google.com");
		Assertions.assertEquals(credential.getUsername(), "user");
		Assertions.assertEquals(credential.getKey(), "key");
		Assertions.assertEquals(credential.getPassword(), "pwd");
		Assertions.assertEquals(credential.getUserid(), 1);
	}

	@Test
	public void signupNewUserWithSuccessMsg() {
		driver.get("http://localhost:" + this.port + "/signup");
		User user = new User(1, "username", "pass", "1234", "first", "last");
		signUpTestUser(user);
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).isDisplayed());
	}

	@Test
	public void signupDuplicateUserMessage() {
		driver.get("http://localhost:" + this.port + "/signup");
		User user = new User(1, "username", "pass", "1234", "first", "last");
		signUpTestUser(user);
		signUpTestUser(user);
		Assertions.assertTrue(driver.findElement(By.id("error-msg")).isDisplayed());
	}


	@Test
	public void loginSuccess() {
		driver.get("http://localhost:" + this.port + "/signup");
		User user = new User(1, "username", "pass", "1234", "first", "last");
		signUpTestUser(user);
		driver.get("http://localhost:" + this.port + "/login");
		enterLoginCreds(user);
		// TODO: Add verification here
		Assertions.assertTrue(false);
	}

	@Test
	public void returnToLoginButton() {
		driver.get("http://localhost:" + this.port + "/signup");
		User user = new User(1, "username", "pass", "1234", "first", "last");
		signUpTestUser(user);
		driver.findElement(By.id("backToLoginButton")).click();
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void returnToLoginBanner() {
		driver.get("http://localhost:" + this.port + "/signup");
		User user = new User(1, "username", "pass", "1234", "first", "last");
		signUpTestUser(user);
		driver.findElement(By.id("loginBannerLink")).click();
		Assertions.assertEquals("Login", driver.getTitle());
	}

	public void loginFailureBadUser() {

	}

	public void loginFailureBadPassword() {

	}

	public void loginFailureUserDoesntExist() {

	}

	public void addFile() {

	}

	public void addNote() {

	}

	public void addCredential() {

	}

}
