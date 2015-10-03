package org.groovy.pdf

import com.itextpdf.text.pdf.PdfReader
import org.junit.Test

import static org.junit.Assert.assertEquals

/**
 * Created by cle on 14/07/15.
 */
class PdfReaderTest {
    @Test
    public void testReadFile(){
        File pdfFile=new File(this.class.getResource("/pdf-sample.pdf").toURI())
        assert pdfFile!=null
        PdfReader reader=new PdfReader(new FileInputStream(pdfFile))
        assertEquals(1,reader.getNumberOfPages());
    }
}
