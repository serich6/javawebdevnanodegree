package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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


	public void signupNewUser() {

	}

	public void signupNewUserWithSuccessMsg() {

	}

	public void signupDuplicateUserMessage() {

	}

	public void loginSuccess() {

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
