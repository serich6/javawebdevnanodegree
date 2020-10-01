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

	public void stageUserAndLogin(User user) {
		driver.get("http://localhost:" + this.port + "/login");
		signUpTestUser(user);
		driver.get("http://localhost:" + this.port + "/signup");
		enterLoginCreds(user);
	}

	public void createNote(Note note) {
		driver.findElement(By.id("nav-notes-tab")).click();
		driver.findElement(By.id("addNewNoteButton")).click();
		driver.findElement(By.id("note-title")).sendKeys(note.getNotetitle());
		driver.findElement(By.id("note-description")).sendKeys(note.getNotedescription());
		driver.findElement(By.id("noteSubmit")).click();
	}

	public void editNote(Note note) {
		driver.findElement(By.id("editNoteButton")).click();
	}

	public void deleteNote(Note note) {
		driver.findElement(By.id("deleteNoteButton")).click();
	}

	public void createCredential(Credential cred) {
		driver.findElement(By.id("nav-credentials-tab")).click();
		driver.findElement(By.id("addNewCredentialButton")).click();
		driver.findElement(By.id("credential-url")).sendKeys(cred.getUrl());
		driver.findElement(By.id("credential-username")).sendKeys(cred.getUsername());
		driver.findElement(By.id("credential-password")).sendKeys(cred.getPassword());
		driver.findElement(By.id("credentialSubmit")).click();
	}

	public void editCredential(Credential cred) {
		driver.findElement(By.id("editCredentialButton")).click();
	}

	public void deleteCredential(Credential cred) {
		driver.findElement(By.id("deleteCredentialButton")).click();
	}

	/// REQUIRED TESTS
	/// 1- Verify that an unauthorized user can only access the login and signup page
	@Test
	public void checkUnauthorizedAccess() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/result");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/// 2 - Sign up a new user, log in, check the homepage is present. Log out, verify home page can't be accessed
	@Test
	public void checkHomeScreenAccess() {
		driver.get("http://localhost:" + this.port + "/signup");
		User user = new User(1, "username", "pass", "1234", "first", "last");
		signUpTestUser(user);
		driver.get("http://localhost:" + this.port + "/login");
		enterLoginCreds(user);
		Assertions.assertEquals("Home", driver.getTitle());
		driver.findElement(By.id("logoutButton")).click();
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/// 3 - Create a note, verify it displays
	@Test
	public void createAndDisplayNote() {
		driver.get("http://localhost:" + this.port + "/signup");
		User user = new User(1, "username", "pass", "1234", "first", "last");
		signUpTestUser(user);
		driver.get("http://localhost:" + this.port + "/login");
		enterLoginCreds(user);
		Note note = new Note(1, "testTitle", "testDesc", 1);
		createNote(note);
		// Added for now as TC is in progress
		Assertions.assertTrue(false);
	}

	/// 4 - Edits an existing note and check display
	@Test
	public void editAndDisplayNote() {
		User user = new User(1, "username", "pass", "1234", "first", "last");
		stageUserAndLogin(user);
		Note note = new Note(1, "testTitle", "testDesc", 1);
		createNote(note);
		editNote(note);
		Assertions.assertTrue(false);
	}

	/// 5 - Delete note and check list
	@Test
	public void deleteNoteInList() {
		User user = new User(1, "username", "pass", "1234", "first", "last");
		stageUserAndLogin(user);
		Note note = new Note(1, "testTitle", "testDesc", 1);
		createNote(note);
		deleteNote(note);
		Assertions.assertTrue(false);
	}

	/// 6 - Create credentials, verify displayed
	@Test
	public void createAndDisplayCredentials() {
		User user = new User(1, "username", "pass", "1234", "first", "last");
		stageUserAndLogin(user);
		Credential credential = new Credential(1, "google.com", "testUser", "abc", "pwd", 1);
		createCredential(credential);
		Assertions.assertTrue(false);
	}
	/// 7 - View existing creds, verify viewable password is unencrypted, edit the cred, verify changes displayed
	@Test
	public void viewPasswordAndEditCredentials() {
		User user = new User(1, "username", "pass", "1234", "first", "last");
		stageUserAndLogin(user);
		Credential credential = new Credential(1, "google.com", "testUser", "abc", "pwd", 1);
		createCredential(credential);
		// TODO: do an assertions for enc
		editCredential(credential);
		Assertions.assertTrue(false);
	}
	/// 8 - Delete existing set of creds, verify the creds are no longer displayed.
	@Test
	public void deleteCredentials() {
		User user = new User(1, "username", "pass", "1234", "first", "last");
		stageUserAndLogin(user);
		Credential credential = new Credential(1, "google.com", "testUser", "abc", "pwd", 1);
		createCredential(credential);
		deleteCredential(credential);
		Assertions.assertTrue(false);
	}


	/********************************
	 *
	 *
	 * NON RUBRIC TESTS BELOW HERE
	 *
	 *
	 ********************************/

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

//	public void loginFailureBadUser() {
//		Assertions.assertTrue(false);
//	}
//
//	public void loginFailureBadPassword() {
//		Assertions.assertTrue(false);
//	}
//
//	public void loginFailureUserDoesntExist() {
//		Assertions.assertTrue(false);
//	}
//
//	public void addFile() {
//		Assertions.assertTrue(false);
//	}
}
