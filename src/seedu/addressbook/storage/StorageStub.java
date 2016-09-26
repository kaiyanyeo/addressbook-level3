package seedu.addressbook.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

/**
 * Represents the Storage object for testing the Logic class.
 */
public class StorageStub extends Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

    private final JAXBContext jaxbContext;

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageStub() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageStub(String filePath) throws InvalidStorageFilePathException {
        try {
            jaxbContext = JAXBContext.newInstance(AdaptedAddressBook.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }

        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    @Override
    public void save(AddressBook addressBook) throws StorageOperationException {
        
    }

    @Override
    public AddressBook load() throws StorageOperationException {
        return new AddressBook();
    }

    @Override
    public String getPath() {
        return path.toString();
    }
}
