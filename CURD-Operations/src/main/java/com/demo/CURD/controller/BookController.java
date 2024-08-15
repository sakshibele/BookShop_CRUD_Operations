package com.demo.CURD.controller;

import com.demo.CURD.DTO.BookDTO;
import com.demo.CURD.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/book")
@RestController
@RequiredArgsConstructor
public class BookController{


    private final BookService bookService;


    @PostMapping("/saveBook")
    public ResponseEntity<String> saveBook(@RequestBody @Valid List<BookDTO> bookDTOs, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("validation failed") ;
        }
        bookService.saveBook(bookDTOs);
        return ResponseEntity.status(HttpStatus.OK).body("Books Added");
    }



    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<String> getBookById(@PathVariable Integer bookId){
        return ResponseEntity.status(HttpStatus.OK)
                .body("Here's Your Requested Book \n" + bookService.getBookById(bookId));
    }

    @GetMapping("/getAllBooks/{pageNumber}")
    public List<BookDTO> getAllBooks(@PathVariable Integer pageNumber){

        return bookService.getAllBooks(pageNumber);
    }


    @GetMapping("/getBooksByName/{name}")
    public List<BookDTO> getBooksByName(@PathVariable String name){

        return bookService.getBooksByName(name);
    }

   @GetMapping("/deleteBookById/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Integer bookId){
         bookService.deleteBookById(bookId);
       return ResponseEntity.status(HttpStatus.OK)
               .body("Book Deleted ");
   }

   @PutMapping("/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody BookDTO book){

         bookService.updateBook(book);
       return ResponseEntity.status(HttpStatus.OK)
               .body("Book Updated ");
    }

}
