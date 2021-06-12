package com.dataparser.spring.contoller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
public class UploadController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV filed to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `Number` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {


                // create csv bean reader
                CsvToBean<Number> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Number.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list users
                List<Number> numbers = csvToBean.parse();


                //TODO: save numbers in DB?

                // save numbers list to model
                model.addAttribute("numbers", numbers);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }
}
