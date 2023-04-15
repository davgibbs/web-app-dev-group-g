package ie.dcu.library.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ie.dcu.library.entity.MemberEntity;
import ie.dcu.library.model.Book;
import ie.dcu.library.repository.LibraryRepository;
import ie.dcu.library.util.LibraryServiceException;
import jakarta.transaction.Transactional;
import static ie.dcu.library.model.ErrorCode.BOOK_NOT_FOUND;
import static ie.dcu.library.model.ErrorCode.ISBN_NOT_FOUND;


@Service
public class BookService {

	private final LibraryRepository libraryRepository;
	
	@Autowired
	public BookService(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	public Iterable<Book> getAllBooks() {
	    return libraryRepository.findAll();
	 }

	public List<Book> getBooksByAuthor(String author){
	  return libraryRepository.findByAuthor(author);
	}

	public List<Book> getBooksByTitle(String title){
		  return libraryRepository.findByTitle(title);
	}
	
	public Book addBook(Book book){
		//System.out.print(book);		
		if(book.getISBN()==null) {
			throw new LibraryServiceException("ISBN missing", ISBN_NOT_FOUND);
		}
		Book newbook = libraryRepository.save(book);	
		return newbook;
	}

	public void deleteBook(int isbn){
		//System.out.print(id);
		boolean exists = libraryRepository.existsByIsbn(isbn);
		if(!exists) {
			throw new LibraryServiceException("Book not found", BOOK_NOT_FOUND);
		}
		Book b = libraryRepository.findByIsbn(isbn);
		libraryRepository.deleteById(b.getId());
	}

	@Transactional
	public Book modifyBook(Book book){
		//System.out.print(id);
        var bookEntity = libraryRepository.findById(book.getId()).orElseThrow(
                () -> new LibraryServiceException("Book not found", BOOK_NOT_FOUND));
        
        /*
        if(book.getTitle()!=null &&
        		book.getTitle().length()>0 &&
        		!Objects.equals(book.getTitle(),bookEntity.getTitle())) {
            bookEntity.setTitle(book.getTitle());        	
        }

        if(book.getAuthor()!=null &&
        		book.getAuthor().length()>0 &&
        		!Objects.equals(book.getAuthor(),bookEntity.getAuthor())) {
            bookEntity.setAuthor(book.getAuthor());        	
        }*/

        bookEntity.setTitle(book.getTitle());        	
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setAvailable(book.getAvailable());
        bookEntity.setPublish_date(book.getPublish_date());
        bookEntity.setImage_path(book.getImage_path());
        libraryRepository.save(bookEntity);
        return bookEntity;        
        
	}
	
	  public Book getBookByIsbn(int isbn)
	  {
			boolean exists = libraryRepository.existsByIsbn(isbn);
			if(!exists) {
				throw new LibraryServiceException("Book not found", BOOK_NOT_FOUND);
			}
	      return libraryRepository.findByIsbn(isbn);
	  }

}
