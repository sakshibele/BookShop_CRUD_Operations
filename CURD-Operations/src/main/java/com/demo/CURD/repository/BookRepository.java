package com.demo.CURD.repository;

import com.demo.CURD.entity.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE BookEntity b SET b.name = :name, b.price = :price, b.author = :author WHERE b.bookId = :bookId")
    void updateBook(@Param("bookId") Integer bookId,
                    @Param("name") String name,
                    @Param("price") Double price,
                    @Param("author") String author);

//    @Transactional
//    @Query("SELECT b FROM BookEntity b WHERE b.name = :name")
//    List<BookEntity> findByName(@Param("name") String name);

    @Transactional
    @Query("SELECT b FROM BookEntity b WHERE b.name = :name")
    List<BookEntity> findByName(@Param("name") String name);

//List<BookEntity> findByName(String name);
}
