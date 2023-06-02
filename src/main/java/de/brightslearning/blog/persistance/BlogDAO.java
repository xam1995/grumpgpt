package de.brightslearning.blog.persistance;

import de.brightslearning.blog.model.BlogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogDAO extends CrudRepository<BlogEntry, Integer> {
    List<BlogEntry> findAllByOrderByTimestampDesc();
}
