package com.fixit.server.controller;

import com.fixit.server.database.Issue;
import com.fixit.server.database.IssueRepository;
import com.fixit.server.manager.IssueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("/issue")
public class IssueController {

    @Autowired
    IssueManager issueManager;

    @Autowired
    IssueRepository issueRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/add-issue")
    public void uploadPost(
            @RequestPart("file") MultipartFile multipartFile,
            @RequestPart("issue") Issue issue
    ) throws IOException {

        issueManager.addIssue(issue, multipartFile);

    }
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Issue> getIssues() {
        return issueRepository.findAll();
    }

}
