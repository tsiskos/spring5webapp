package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    /**
     *  Function that is called when the Refresh event is triggered from the spring.
     *  This usually happens at start time. When spring boot is up some dummy data will be stored in the database.
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeDummyData();
    }

    private void initializeDummyData(){

        Publisher p1 = new Publisher("George Allen & Unwin");
        publisherRepository.save(p1);

        Author a1 =  new Author("J. R. R.", "Tolkien");
        Book b1 = new Book("The Silmarillion", "0-04-823139-8",p1);


        a1.getBooks().add(b1);
        authorRepository.save(a1);

        b1.getAuthors().add(a1);
        bookRepository.save(b1);


        Publisher p2 = new Publisher("Secker & Warburg");
        publisherRepository.save(p2);

        Author a2 =  new Author("George", "Orwell");
        Book b2 = new Book("Nineteen Eighty-Four: A Novel", "470015866",p2);


        a2.getBooks().add(b2);
        authorRepository.save(a2);

        b2.getAuthors().add(a2);
        bookRepository.save(b2);


    }
}
