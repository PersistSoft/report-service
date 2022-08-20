package com.persist.report.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.persist.report.dto.PdfDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

@Slf4j
@Service
public class PdfService {

    /**
     * Greate pdf from html template
     * @param pdfDTO
     * @return
     */
    public byte[] createPdf (PdfDTO pdfDTO){
        try {
            Document document = new Document(PageSize.A4);
            document.setMargins(45, 45, 50, 50);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            document.addAuthor("Top Tech Inc");
            document.addCreator("Top Tech Inc - creator");
            document.addSubject("Contract");
            document.addCreationDate();
            document.addTitle("Contract");

            XMLWorkerHelper.getInstance()
                    .parseXHtml(writer, document, new StringReader(pdfDTO.getHtmlTemplate()));
            document.close();

            return out.toByteArray();
        }catch (DocumentException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }
}
