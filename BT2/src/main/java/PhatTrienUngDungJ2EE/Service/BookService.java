package PhatTrienUngDungJ2EE.Service;
import PhatTrienUngDungJ2EE.Model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
    @Service
    public class BookService {
        private List<Book> books = new ArrayList<>();

        public List<Book> getAllBooks() {
            return books;
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public Book getBookById(int id) {
            return books.stream()
                    .filter(book -> book.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        public void updateBook(int id, Book updatedBook) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId() == id) {
                    updatedBook.setId(id); 
                    books.set(i, updatedBook);
                    return;
                }
            }
        }

        public void deleteBook(int id) {
            books.removeIf(book -> book.getId() == id);
        }

        
    

    
}
