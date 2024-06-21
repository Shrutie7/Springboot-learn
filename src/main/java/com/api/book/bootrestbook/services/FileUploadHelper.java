package com.api.book.bootrestbook.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component

public class FileUploadHelper {
    // public final String Upload_Dir = "D:\\bootrestbook\\src\\main\\resources\\static\\image";
    public final String Upload_Dir = new ClassPathResource("static/image").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException{
        
    }
    public boolean uploadFile(MultipartFile file){
        boolean f = false;
        try {
            InputStream is = file.getInputStream();
            byte data[] = new byte[is.available()];
            is.read(data);

            //write
            FileOutputStream fos = new FileOutputStream(Upload_Dir + "\\"+file.getOriginalFilename());
            fos.write(data);

            fos.flush();
            fos.close();

            f = true;

            
        } catch (Exception e) {
          e.printStackTrace();
        }
        return f;

    }
    
}
