package lt.vtmc.GintautasButkus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.models.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

}
