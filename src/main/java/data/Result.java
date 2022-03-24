package data;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

/**
 * Represents the result of a request depending on its status.
 * The class is based on two other classes. Check the see section for further information. <br/> <br/>
 * <p>
 * This class define 3 types of items : Error, Success, Empty. <br/>
 * <p>
 * Success represents a successful request with its return. <br/>
 * Empty represents a successful request returning an empty result. <br/>
 * Error represents any error that occurred during requesting. <br/>
 *
 * @param <T> The type of the value that must be stored in case of success.
 * @see java.util.Optional
 * @see <a href="https://github.com/android/architecture-samples/blob/main/app/src/main/java/com/example/android/architecture/blueprints/todoapp/data/Result.kt">Result Kotlin Class</a>
 */
final public class Result<T> {
    private static final Result<?> EMPTY = new Result<>(null, false, null);

    private final @Nullable T value;
    private final boolean isError;
    private final @Nullable Exception exception;


    private Result(@Nullable T value, boolean isError, @Nullable Exception exception) {
        this.value = value;
        this.isError = isError;
        this.exception = exception;
    }

    /**
     * Create and return an instance of a Result as a success one. <br/>
     * A success means that the query worked well and data have changed in the DB. <br/>
     * This type of Result defines the return of the query. Your can access to this value with getValue().
     *
     * @param value The return of the query
     * @param <T>   The type of return of the query.
     * @return A new instance of Result defined as a success
     * @see #getValue()
     */
    @NotNull
    public static <T> Result<T> success(@NotNull T value) {
        return new Result<>(value, false, null);
    }

    /**
     * Create and return an instance of a Result as an error one. <br/>
     * An error Result means that there were an exception thrown during querying the DB. <br/>
     * This type of Result defines an exception property that you can access using getException().
     *
     * @param exception The exception that occurred while querying.
     * @param <T>       The normal type of return of the query.
     * @return A new instance of Result defined as an error.
     * @see #getException()
     */
    @NotNull
    public static <T> Result<T> error(@NotNull Exception exception) {
        return new Result<>(null, true, exception);
    }

    /**
     * Return the instance of a Result as an empty one. <br/>
     * An empty result means that the query worked well but data haven't changed in the DB.
     *
     * @param <T> The type of return of the query.
     * @return A new instance of Result defined as an empty one.
     */
    @NotNull
    @SuppressWarnings("unchecked")
    public static <T> Result<T> empty() {
        return (Result<T>) EMPTY;
    }

    /**
     * Test whether the Result is an error or not. <br/>
     * A Result as error means the something gone wrong while querying the DB.
     *
     * @return True if it's an error, false otherwise.
     */
    public boolean isError() {
        return isError && value == null;
    }

    /**
     * Test whether the Result is an empty result or not. <br/>
     * An empty result means the query worked but nothing changed in the DB.
     *
     * @return True if it's an empty result, false otherwise.
     */
    public boolean isEmpty() {
        return !isError && value == null;
    }

    /**
     * Test whether the Result is a success or not. <br/>
     * A Result as Success means the query worked well and the data have changed.
     *
     * @return True if it's an error, false otherwise.
     */
    public boolean isSuccess() {
        return !isError && value != null;
    }


    /**
     * Return the value of the Result item.
     * Its value is only defined for a success Result.
     *
     * @return The value of the Result or null if it's not a success Result.
     */
    @Nullable
    public T getValue() {
        return value;
    }

    /**
     * The exception that occurred while querying.
     * The exception is only defined for an error Result.
     *
     * @return The Exception that occurred while querying or null if it's not an error Result.
     */
    @Nullable
    public Exception getException() {
        return exception;
    }
}
