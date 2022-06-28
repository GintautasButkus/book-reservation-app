package lt.vtmc.GintautasButkus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.vtmc.GintautasButkus.models.Books;
import lt.vtmc.GintautasButkus.models.FavoriteBooks;

@Repository
public interface FavoriteBooksRepository extends JpaRepository<FavoriteBooks, Long> {

}
