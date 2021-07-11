package borneo.document.indexer.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "borneo.document.indexer.*" })
public class DocumentIndexerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentIndexerApplication.class, args);
    }

}
