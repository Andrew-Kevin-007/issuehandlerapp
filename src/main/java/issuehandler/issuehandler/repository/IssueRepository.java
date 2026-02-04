package issuehandler.issuehandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import issuehandler.issuehandler.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}