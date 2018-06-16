package com.fixit.server.controller.api;

import com.fixit.server.database.Issue;
import com.fixit.server.database.IssueRepository;
import com.fixit.server.manager.IssueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController("api-issue")
public class IssueController {

    @Autowired
    IssueManager issueManager;

    @Autowired
    IssueRepository issueRepository;

    @RequestMapping(method = RequestMethod.PUT, value = "/issue/add-issue")
    public void uploadPost(
            @RequestPart("file") MultipartFile multipartFile,
            @RequestPart("issue") Issue issue
    ) throws IOException {

        issueManager.addIssue(issue, multipartFile);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/issue/add-issue-by-fuszerka/{latiude}/{longitude}/{description}")
    public Issue uploadPostFuszerka(
            @PathVariable("latiude") double latiude,
            @PathVariable("longitude") double longitude,
            @PathVariable("description") String description
    ) throws IOException {
        Issue issue = new Issue();

        issue.setCreated(new Date());
        issue.setImageFileName("test.jpg");
        issue.setLatiude(latiude);
        issue.setLongitude(longitude);
        issue.setDescription(description);

        issueRepository.save(issue);
        return issue;
    }

    @GetMapping("/issues")
    public Iterable<Issue> getIssues() {
        return issueRepository.findAll();
    }

    @GetMapping("/issue/{id}")
    public Issue getIssue(@PathVariable("id") int id) {
        return issueRepository.findById(id).get();
    }

    @CrossOrigin("*")
    @PatchMapping("issue/{id}")
    public Issue update(@RequestBody Issue issue, @PathVariable("id") int id) {
        issue.setId(id);
        issueRepository.save(issue);
        return issue;
    }

    @GetMapping("issue/statuses")
    public Issue.Status[] getIssueStatuses() {
        System.out.println(Issue.Status.values()[0].getPolishName());
        return Issue.Status.values();
    }

    @GetMapping("issue/set-status/{id}/{status}")
    public Issue setStatus(@PathVariable("id") int id, @PathVariable("status") Issue.Status status) {
        Issue issue = issueRepository.findById(id).get();
        issue.setStatus(status);
        issueRepository.save(issue);
        return issue;
    }

    @GetMapping("issue/set-public-service/{id}/{publicService}")
    public Issue setPublicService(
            @PathVariable("id") int id,
            @PathVariable("publicService") Issue.PublicService publicService) {

        Issue issue = issueRepository.findById(id).get();
        issue.setPublicService(publicService);
        issueRepository.save(issue);
        return issue;

    }
}
