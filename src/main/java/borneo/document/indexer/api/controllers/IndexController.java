package borneo.document.indexer.api.controllers;


import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.IndexDocumentDrive;
import borneo.document.indexer.models.IndexDocumentLocal;
import borneo.document.indexer.services.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class IndexController {

    @Autowired
    private Index index;

    @PostMapping(value = "/indexLocal", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<IndexFromLocalResponse> indexDocumentFromLocal(@RequestBody IndexDocumentLocal indexDocumentLocal) throws ServiceException {
        IndexFromLocalResponse response = index.indexFromLocal(indexDocumentLocal);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/indexDrive", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<IndexFromDriveResponse> indexDocumentFromDrive(@RequestBody IndexDocumentDrive indexDocumentDrive) throws ServiceException {
        IndexFromDriveResponse response = index.indexFromDrive(indexDocumentDrive);
        return ResponseEntity.ok(response);
    }

}
