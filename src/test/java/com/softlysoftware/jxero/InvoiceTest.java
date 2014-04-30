package com.softlysoftware.jxero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.softlysoftware.jxero.core.Invoice;
import com.softlysoftware.jxero.core.LineItem;
import com.softlysoftware.jxero.core.Invoice.Status;

@RunWith(JUnit4.class)
public class InvoiceTest {

    @Test
    public void testFromXml() throws IOException {
    	String xml = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("Invoices.xml"), "UTF-8");
    	InvoicesEndpoint invoices = (InvoicesEndpoint)Xml.fromXml(xml, InvoicesEndpoint.class);
    	assertEquals(invoices.getList().size(), 1);

    	// Invoice properties
    	Invoice invoice = invoices.getList().get(0);
    	assertEquals(invoice.getType(), Invoice.Type.ACCREC);
    	assertEquals("Ariki Properties", invoice.getContact().getName());
    	assertEquals(1252368000000l, invoice.getDate().getTime());
        assertEquals(87.11d, invoice.getSubTotal(), 0);
        assertEquals(10.89, invoice.getTotalTax(), 0);
        assertEquals(98, invoice.getTotal(), 0);
        assertEquals(2, invoice.getLineItems().size());
        assertEquals("OIT:01065", invoice.getNumber());
        assertEquals("Ref:SMITHK", invoice.getReference());
        assertEquals(Status.SUBMITTED, invoice.getStatus());

        // Line Item 1
        LineItem lineItem = invoice.getLineItems().get(0);
        assertEquals("3 copies of OS X 10.6 Snow Leopard", lineItem.getDescription());
        assertEquals(3d, lineItem.getQuantity(), 0);
        assertEquals(59, lineItem.getUnitAmount(), 0);
        assertEquals(19.67, lineItem.getTaxAmount(), 0);

        // Assert that Line Amount value is correct
        assertEquals(177, lineItem.getLineAmount(), 0);
        assertEquals("200", lineItem.getAccountCode());

        // Line Item 2
        LineItem lineItem2 = invoice.getLineItems().get(1);
        assertEquals("Returned Apple Keyboard with Numeric Keypad (faulty)", lineItem2.getDescription());
        assertEquals(1d, lineItem2.getQuantity(), 0);
        assertEquals(-79, lineItem2.getUnitAmount(), 0);
        assertEquals(-8.78, lineItem2.getTaxAmount(), 0);

        // Assert that Line Amount value is correct
        assertEquals(-79, lineItem2.getLineAmount(), 0);
        assertEquals("200", lineItem2.getAccountCode());

        String xmlBounced = Xml.toXml(invoices);

        // Xero have a bug about how LineAmounts are calculated. 
        // The workaround would be to simply leave this item out of the XML when sending it back. 
        // Uncomment the following line when testing implementations of this workaround (some JAXB annotations tweak in LineItem.java?).
        assertFalse("LineAmount element shouldn't be present", xmlBounced.contains("<LineAmount>"));

        // doesn't work just yet as the order seems to change on the contact element
        //assertThat(XmlConverters.the(xml), XmlMatchers.isEquivalentTo(XmlConverters.the(xmlBounced)));
    }

}