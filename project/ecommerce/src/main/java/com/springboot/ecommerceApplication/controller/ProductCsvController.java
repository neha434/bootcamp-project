package com.springboot.ecommerceApplication.controller;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.springboot.ecommerceApplication.dto.ProductDto;
import com.springboot.ecommerceApplication.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ProductCsvController {
    private ProductService productService;
    // private TestService testService;

    public ProductCsvController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping("/csvTest")
//    public ResponseEntity<String> registerCustomer( @RequestParam ("email") String email, WebRequest webRequest){
//        return testService.sendEmail(email);
//    }

    @GetMapping("/export-product-details")
    public void exportCSV(HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "users.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<ProductDto> writer = new StatefulBeanToCsvBuilder<ProductDto>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(productService.listProduct());

    }
//    @GetMapping("/export-product-details")
//    public void exportCSV( HttpServletResponse response) throws Exception {
//
//        //set file name and content type
//        String filename = "users.csv";
//
//       response.setContentType("text/csv");
//        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + filename + "\"");
//
//        //create a csv writer
//        StatefulBeanToCsv<TestUser> writer = new StatefulBeanToCsvBuilder<TestUser>(response.getWriter())
//                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
//                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
//                .withOrderedResults(false)
//                .build();
//
//        //write all users to csv file
//        writer.write(testService.listUsers());
//
//    }

}