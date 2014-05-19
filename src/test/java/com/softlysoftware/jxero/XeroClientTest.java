package com.softlysoftware.jxero;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.softlysoftware.jxero.XeroClient;
import com.softlysoftware.jxero.ContactsEndpoint;

@RunWith(JUnit4.class)
public class XeroClientTest {

	@Test(expected=RuntimeException.class)
	public void testClasspathConstructor() {
		new XeroClient(false);
	}

}