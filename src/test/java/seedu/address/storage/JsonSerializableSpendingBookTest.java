package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.SpendingBook;
import seedu.address.testutil.TypicalSpendings;

public class JsonSerializableSpendingBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSpendingBookTest");
    private static final Path TYPICAL_SPENDINGS_FILE = TEST_DATA_FOLDER.resolve("typicalSpendingBook.json");
    private static final Path DUPLICATE_SPENDING_FILE = TEST_DATA_FOLDER.resolve("duplicateSpendingBook.json");
    private static final Path INVALID_SPENDING_FILE = TEST_DATA_FOLDER.resolve("invalidSpendingBook.json");


    @Test
    public void toModelType_typicalSpendingsFile_success() throws Exception {
        JsonSerializableSpendingBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_SPENDINGS_FILE,
                JsonSerializableSpendingBook.class).get();
        SpendingBook spendingBookFromFile = dataFromFile.toModelType();
        SpendingBook typicalSpendingBook = TypicalSpendings.getTypicalSpendingBook();
        assertEquals(spendingBookFromFile, typicalSpendingBook);
    }

    @Test
    public void toModelType_invalidSpendingFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSpendingBook dataFromFile = JsonUtil.readJsonFile(INVALID_SPENDING_FILE,
                JsonSerializableSpendingBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSpendings_throwsIllegalValueException() throws Exception {
        JsonSerializableSpendingBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SPENDING_FILE,
                JsonSerializableSpendingBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSpendingBook.MESSAGE_DUPLICATE_SPENDING,
                dataFromFile::toModelType);
    }

}
