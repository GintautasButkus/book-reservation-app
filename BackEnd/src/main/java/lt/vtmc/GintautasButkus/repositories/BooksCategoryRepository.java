package lt.vtmc.GintautasButkus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.models.BooksCategory;



@Repository
public interface BooksCategoryRepository extends JpaRepository<BooksCategory, Long> {

}
