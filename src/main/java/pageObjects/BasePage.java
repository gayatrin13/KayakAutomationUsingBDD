package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BasePage {
	public static Properties props;

	public BasePage() throws FileNotFoundException, IOException {
		props = new Properties();
		props.load(new FileInputStream(new File(System.getProperty("user.dir") + "//src//main//resources")));

	}

}
