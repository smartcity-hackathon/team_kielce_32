package com.fixit.server.manager;

import com.fixit.server.database.Issue;
import com.fixit.server.database.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class IssueManager {

    @Autowired
    IssueRepository issueRepository;

    @Value( "${imgDir}" )
    private String imgDir;


    public void addIssue(Issue issue, MultipartFile multipartFile) throws IOException {

        File file = generateFile(multipartFile);

        issue.setImage(file);
        issueRepository.save(issue);
    }

    private File generateFile(MultipartFile multipartFile) throws IOException {
        File file = new File(imgDir, UUID.randomUUID().toString()+".jpg");

        FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile(), false);

        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.flush();

        return file;
    }

}
