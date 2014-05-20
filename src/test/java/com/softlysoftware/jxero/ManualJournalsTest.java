package com.softlysoftware.jxero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.softlysoftware.jxero.core.JournalLine;
import com.softlysoftware.jxero.core.ManualJournal;

@RunWith(JUnit4.class)
public class ManualJournalsTest {

    @Test
    public void testFromXml() throws IOException {
    	String xml = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("ManualJournals.xml"), "UTF-8");
    	ManualJournalsEndpoint manualJournals = (ManualJournalsEndpoint)Xml.fromXml(xml, ManualJournalsEndpoint.class);
    	assertEquals(1, manualJournals.getList().size());
    	ManualJournal mj = manualJournals.getList().get(0);


    	assertEquals("Reversal: test-narration", mj.getNarration());
    	assertEquals("POSTED", mj.getStatus().name());
    	assertTrue(mj.isShowOnCashBasisReports());
    	assertEquals("NoTax", mj.getLineAmountTypes());
    	assertEquals("bdf954e3-ef4d-44b9-8bb6-671a5facf975", mj.getId());


    	List<JournalLine> journalLines = mj.getJournalLines();
    	assertEquals(2, journalLines.size());
    	JournalLine line = journalLines.get(0);
    	assertEquals("320", line.getAccountCode());
    	assertEquals("test-narration", line.getDescription());
    	assertEquals(-123, new Double(line.getLineAmount()).intValue());
    	assertEquals("NONE", line.getTaxType());


    }

    @Test
    public void testLookup() throws IOException {
        XeroClient client = new XeroClient(true);
        ManualJournalsEndpoint manualJournals = new ManualJournalsEndpoint(client);
        ManualJournal mj = manualJournals.getById("b20cd82b-73d4-4902-8cf2-86d8a2f763d7");
        Assert.assertEquals("Coded incorrectly Office Equipment should be Computer Equipment", mj.getNarration());
        Assert.assertEquals(2569, mj.getJournalLines().get(0).getLineAmount(), 0);
    }

}