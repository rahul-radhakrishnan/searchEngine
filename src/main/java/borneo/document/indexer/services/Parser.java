package borneo.document.indexer.services;

import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.ParserData;

public interface Parser {

    public ParserData parseStringFromFile(String path) throws ServiceException;
}
