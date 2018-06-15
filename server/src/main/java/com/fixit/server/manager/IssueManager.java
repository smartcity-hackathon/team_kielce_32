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
import java.util.Date;
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
        issue.setCreated(new Date());
        issueRepository.save(issue);
    }

    public void changeStatus(Issue issue, Issue.Status status) {
        issue.setStatus(status);
        issueRepository.save(issue);

        //@todo eventy, powiadomienia do userow itp
    }

    private File generateFile(MultipartFile multipartFile) throws IOException {
        File file = new File(imgDir, UUID.randomUUID().toString()+".jpg");

        FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile(), false);

        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.flush();

        return file;
    }



}
