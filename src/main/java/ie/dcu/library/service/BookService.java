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
		if(book.getISBN()==null) {
			throw new LibraryServiceException("ISBN missing", ISBN_NOT_FOUND);
		}
		if (book.getImage_path()== null) {
			book.setImage_path("./images/book.png");
		}
		Book newbook = libraryRepository.save(book);	
		return newbook;
	}

	public void deleteBook(int id){
		boolean exists = libraryRepository.existsById(id);
		if(!exists) {
			throw new LibraryServiceException("Book not found", BOOK_NOT_FOUND);
		}
		Book b = libraryRepository.findById(id);
		libraryRepository.deleteById(b.getId());
	}

	@Transactional
	public Book modifyBook(int id, Book book){
		boolean exists = libraryRepository.existsById(id);
		if(!exists) {
			throw new LibraryServiceException("Book not found", BOOK_NOT_FOUND);
		}
        var bookEntity = libraryRepository.findById(id);
        
        bookEntity.setTitle(book.getTitle());        	
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setPublish_date(book.getPublish_date());
        bookEntity.setImage_path(book.getImage_path());
        bookEntity.setISBN(book.getISBN());
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
	  
	  public Book getBookById(int id)
	  {
			boolean exists = libraryRepository.existsById(id);
			if(!exists) {
				throw new LibraryServiceException("Book not found", BOOK_NOT_FOUND);
			}
	      return libraryRepository.findById(id);
	  }

}
