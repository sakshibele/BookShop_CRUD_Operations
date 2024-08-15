package com.demo.CURD.service;

import com.demo.CURD.DTO.BookDTO;
import com.demo.CURD.entity.BookEntity;
import com.demo.CURD.exception.BookAlreadyExistException;
import com.demo.CURD.exception.BookNotFoundException;
import com.demo.CURD.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    private final ModelMapper mapper;

    public void saveBook(List<BookDTO> bookDTOs) {

        for(var book : bookDTOs) {
            BookEntity bookEntity= mapper.map(book,BookEntity.class);

            if(bookRepository.findById(book.getBookId()).isPresent()){
                throw new BookAlreadyExistException("Book Already exist with this bookId");
            }else{
                bookRepository.save(bookEntity);
            }
        }
    }
    public BookDTO getBookById(Integer bookId){

        return mapper.map(
                bookRepository.findById(bookId)
                        .orElseThrow(() -> new BookNotFoundException("Book does not exist with given bookId")),
                BookDTO.class
        );

    }



    public List<BookDTO> getAllBooks(int pageNumber){
        Pageable pageElements = PageRequest.of(pageNumber,10, Sort.by("bookId"));
        return bookRepository.findAll(pageElements).getContent()
                .stream()
                .map(bookEntity->mapper.map(bookEntity,BookDTO.class))
                .toList();
    }


    public void delete(Integer bookId){
        bookRepository.deleteById(bookId);
    }

    public void deleteBookById(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    public boolean updateBook(BookDTO book) {
        Optional<BookEntity> PreviousBook= bookRepository.findById(book.getBookId());
        if(PreviousBook.isPresent()){
       bookRepository.updateBook(book.getBookId(), book.getName(), book.getPrice(), book.getAuthor());
       return true;
        }
        else{
            return false;
        }
    }

public List<BookDTO> getBooksByName(String name) {
    List<BookEntity> bookEntities = bookRepository.findByName(name);

    // Check if no books are found
    if (bookEntities.isEmpty()) {
        throw new BookNotFoundException("No books found with the given name");
    }

    // Map the list of BookEntity to a list of BookDTO
    return bookEntities.stream()
            .map(entity -> mapper.map(entity, BookDTO.class))
            .collect(Collectors.toList());
}
}
