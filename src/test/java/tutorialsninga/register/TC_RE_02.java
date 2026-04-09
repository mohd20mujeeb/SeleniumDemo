package tutorialsninga.register;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import jakarta.mail.Address;
import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMultipart;

public class TC_RE_02 {
	public static void main(String[] args) {
		String email="mohd.14mujeeb@gmail.com";
		String appPass="xndv fgjz ozkv qpwc";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//span[.='Hello, sign in']")).click();
		driver.findElement(By.id("ap_email_login")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.id("auth-fpp-link-bottom")).click();
		driver.findElement(By.id("continue")).click();
		  String host = "imap.gmail.com";
	        String username = email;
	        String appPassword = appPass;

	        try {
	            // Step 1: Properties
	            Properties props = new Properties();
	            props.put("mail.store.protocol", "imaps");
	            props.put("mail.imap.host", host);
	            props.put("mail.imap.port", "993");
	            props.put("mail.imap.ssl.enable", "true");

	            // Step 2: Session
	            Session session = Session.getInstance(props);

	            // Step 3: Connect
	            Store store = session.getStore("imaps");
	            store.connect(host, username, appPassword);

	            // Step 4: Open Inbox
	            Folder inbox = store.getFolder("INBOX");
	            inbox.open(Folder.READ_ONLY);

	            Message[] messages = inbox.getMessages();

	            // Read last 5 emails
	            int start = Math.max(0, messages.length - 5);

	            for (int i = start; i < messages.length; i++) {
	                Message msg = messages[i];

	                System.out.println("===================================");

	                // ✅ Subject
	                System.out.println("Subject: " + msg.getSubject());

	                // ✅ From Email
	                Address[] from = msg.getFrom();
	                if (from != null && from.length > 0) {
	                    InternetAddress addr = (InternetAddress) from[0];
	                    System.out.println("From: " + addr.getAddress());
	                }

	                // ✅ Body
	                String body = getEmailBody(msg);
	                System.out.println("Body: " + body);
	            }

	            inbox.close(false);
	            store.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // Extract body (handles text + HTML + multipart)
	    private static String getEmailBody(Message message) throws Exception {

	        if (message.isMimeType("text/plain")) {
	            return message.getContent().toString();
	        }

	        if (message.isMimeType("text/html")) {
	            return message.getContent().toString();
	        }

	        if (message.isMimeType("multipart/*")) {
	            MimeMultipart multipart = (MimeMultipart) message.getContent();
	            return extractFromMultipart(multipart);
	        }

	        return "";
	    }

	    private static String extractFromMultipart(MimeMultipart multipart) throws Exception {

	        StringBuilder result = new StringBuilder();

	        for (int i = 0; i < multipart.getCount(); i++) {
	            BodyPart part = multipart.getBodyPart(i);

	            if (part.isMimeType("text/plain")) {
	                return part.getContent().toString(); // prefer plain text
	            } else if (part.isMimeType("text/html")) {
	                result.append(part.getContent().toString());
	            } else if (part.getContent() instanceof MimeMultipart) {
	                result.append(extractFromMultipart((MimeMultipart) part.getContent()));
	            }
	        }
	        return result.toString();
	}
     
}
