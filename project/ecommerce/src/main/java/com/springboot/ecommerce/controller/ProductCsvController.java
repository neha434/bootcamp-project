package com.springboot.ecommerce.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.springboot.ecommerce.domain.product.Product;
import com.springboot.ecommerce.repositories.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@RestController
public class ProductCsvController {
    @Autowired
    ProductRepo productRepo;


    private final Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/export-product-details")
    public void exportCSV(@RequestParam("id") Integer id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, HttpServletResponse response) throws Exception {
        String filename = "product.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<Product> writer = new StatefulBeanToCsvBuilder<Product>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = simpleDateFormat.format(date);
        LocalDate localDate = LocalDate.parse(currentTime);
        LocalDateTime startTime= LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        LocalDateTime endTime= LocalDateTime.of(localDate, LocalTime.MAX);
        writer.write(productRepo.sendProductDetailsCreated24Hours(id, startTime,endTime));
        System.out.println("############################################..............done");
    }


}