package common;

/**
 * Represent a void return as an instantiable object.
 */
final public class Nothing {
    private Nothing() {}

    /**
     * Return the single instance of Nothing
     */
    public final static Nothing INSTANCE = new Nothing();
}
