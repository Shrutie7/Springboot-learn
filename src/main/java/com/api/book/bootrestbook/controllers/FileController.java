package com.api.book.bootrestbook.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.bootrestbook.services.FileUploadHelper;

@RestController
public class FileController {

    @Autowired
    private FileUploadHelper fileUploadHelper;
    @PostMapping("/upload-file")

    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getName());
        System.out.println(file.getContentType());

        try{
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if(!(file.getContentType().equals("image/jpeg")||file.getContentType().equals("image/png"))){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please upload only jpeg or png image");
            }
            boolean f = fileUploadHelper.uploadFile(file);
            if(f){
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
            }
    
    
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try Again ! something went wrong");


    }
    
}
