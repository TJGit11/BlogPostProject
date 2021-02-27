package com.tts.techtalentblog.repo;

import com.tts.techtalentblog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
To post the info from the form to the database, we need to create an
interface that will aid in adding data to our database.  This is called
a repository. In the repository, we want to extend the functionality to
include the functionality of the Crud Repository. This will import the
Spring CrudRepository and the methods needed to modify data in our
database. You could annotate as repository or not.
 */

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {

    @Query(value = "SELECT * from BLOG_POST where TITLE LIKE ?1 OR AUTHOR LIKE ?1 OR BLOG_ENTRY LIKE ?1", nativeQuery = true)
    public List<BlogPost> search(String keyword);
}
