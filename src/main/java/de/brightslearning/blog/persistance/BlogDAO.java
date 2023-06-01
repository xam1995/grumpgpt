package de.brightslearning.blog.persistance;

import de.brightslearning.blog.model.BlogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogDAO extends JpaRepository<BlogEntry, Integer> {

}
