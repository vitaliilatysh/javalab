package com.epam.cdp.module4.hw2.controller;

import com.epam.cdp.module4.hw2.exceptions.EventNotFoundException;
import com.epam.cdp.module4.hw2.facade.impl.BookingFacadeImpl;
import com.epam.cdp.module4.hw2.model.Ticket;
import com.epam.cdp.module4.hw2.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/tickets/pdf")
public class TicketPdfController {

    private static Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private BookingFacadeImpl bookingFacade;

    private static Document writeResponseToPdf(List<Ticket> result) throws Exception {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("./tickets_response.pdf"));
        document.open();
        document.add(new Paragraph("table"));
        document.add(new Paragraph(new Date().toString()));
        PdfPTable table = new PdfPTable(2);

        PdfPCell cell = new PdfPCell(new Paragraph("table"));

        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10.0f);
        cell.setBackgroundColor(new BaseColor(140, 221, 8));

        table.addCell(cell);

        List<String[]> row = new ArrayList<>();
        for (Ticket ticket : result) {
            String[] data = new String[1];
            data[0] = ticket.toString();
            row.add(data);
        }

        for (String[] cols : row) {
            for (String col : cols) {
                table.addCell(col);
            }
        }

        document.add(table);
        document.close();

        return document;
    }

    /**
     * Get pdf response
     *
     * @param userId   user id
     * @param pageSize size
     * @param page     page
     * @param response response
     * @throws Exception ex
     */
    @GetMapping(params = {"userId", "pageSize", "page"})
    public void generateReport(
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "page") int page,
            HttpServletResponse response) throws Exception {
        User user = bookingFacade.getUserById(userId);
        if (user == null) {
            throw new EventNotFoundException(userId);
        }
        List<Ticket> result = bookingFacade.getBookedTickets(user, pageSize, page);
        writeResponseToPdf(result);
        File file = new File("./tickets_response.pdf");
        byte[] contents = Files.readAllBytes(file.toPath());

        streamReport(response, contents, "tickets_response.pdf");

    }

    private void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
}
