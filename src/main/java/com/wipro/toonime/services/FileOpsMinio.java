package com.wipro.toonime.services;

import com.amazonaws.util.IOUtils;
import io.minio.MinioClient;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Service
public class FileOpsMinio {
    @Autowired
    MinioClient minioClient;

    String defaultbucketName = "videos";

    String defaultBaseFolder = "/";

    public List<Bucket> getAllBuckets(){
        try{
            return minioClient.listBuckets();
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void uploadFile(String name, byte[] context){
        File file = new File("/tmp/"+name);
        file.canWrite();
        file.canRead();
        try {
            FileOutputStream iofs = new FileOutputStream(file);
            iofs.write(context);
            minioClient.putObject(defaultbucketName, defaultBaseFolder + name, file.getAbsolutePath());
        }
        catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public byte[] getFile(String key){
        try{
            InputStream obj = minioClient.getObject(defaultbucketName,defaultBaseFolder +"/" + key);
            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostConstruct
    public void init(){}
}
