package com.fixit.server.controller.moderator;

import com.fixit.server.database.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IssueController {

    @Autowired
    IssueRepository issueRepository;

    @RequestMapping("/")
    public String issues(Map<String, Object> model) {

        model.put("issues", issueRepository.findAll());

        return "issues";
    }

}
