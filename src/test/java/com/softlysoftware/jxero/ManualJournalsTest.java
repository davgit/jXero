package com.softlysoftware.jxero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.softlysoftware.jxero.core.ManualJournal;

@RunWith(JUnit4.class)
public class ManualJournalsTest {

    @Test
    public void testFromXml() throws IOException {
    	String xml = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("ManualJournals.xml"), "UTF-8");
    	ManualJournalsEndpoint manualJournals = (ManualJournalsEndpoint)Xml.fromXml(xml, ManualJournalsEndpoint.class);
    	assertEquals(2, manualJournals.getList().size());
    	ManualJournal mj = manualJournals.getList().get(0);
    	assertEquals("Reversal: test-narration", mj.getNarration());
    	assertTrue(mj.isShowOnCashBasisReports());
    }

}