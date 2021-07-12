package borneo.document.indexer.services;

import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.ParserData;

/**
 * The Parser interface for parsing and extracting data from teh files.
 */
public interface Parser {

    /**
     * Description: Parses the file and returns the parsed data.
     *
     * @param path
     * @return ParserData
     * @throws ServiceException
     */
    public ParserData parseStringFromFile(String path) throws ServiceException;
}
