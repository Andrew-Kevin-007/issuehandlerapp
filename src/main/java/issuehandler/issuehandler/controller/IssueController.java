package issuehandler.issuehandler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import issuehandler.issuehandler.model.Issue;
import issuehandler.issuehandler.repository.IssueRepository;

@Controller
public class IssueController {

    private final IssueRepository repo;

    public IssueController(IssueRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/issues")
    public String viewIssues(Model model) {
        model.addAttribute("issues", repo.findAll());
        return "issues";
    }

    @GetMapping("/issues/new")
    public String newIssue(Model model) {
        model.addAttribute("issue", new Issue());
        return "create";
    }

    @PostMapping("/issues")
    public String saveIssue(@ModelAttribute Issue issue) {
        issue.setStatus("Open");
        repo.save(issue);
        return "redirect:/issues";
    }
    // Load Update Page
@GetMapping("/issues/update/{id}")
public String updateForm(@PathVariable Long id, Model model) {
    Issue issue = repo.findById(id).orElseThrow();
    model.addAttribute("issue", issue);
    return "update";
}

// Save Updated Status
@PostMapping("/issues/update")
public String updateIssue(@ModelAttribute Issue issue) {
    repo.save(issue);
    return "redirect:/issues";
}
}