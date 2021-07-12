package borneo.document.indexer.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The application starts here.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"borneo.document.indexer.*"})
public class DocumentIndexerApplication {

    /**
     * The main method/entrypoint
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DocumentIndexerApplication.class, args);
    }

}
