package Data_Access;

public class DataAccessException extends Exception {
    DataAccessException(String message)
    {
        super(message);
    }

    DataAccessException()
    {
        super();
    }
}
