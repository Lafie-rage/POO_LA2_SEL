package data.dao;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.util.List;

public interface Dao<T> {
    boolean create(T item);

    /**
     * Retrieve a single element from the data source by its id.
     *
     * @param id The id of the wished element.
     * @return The element or null if non is found.
     */
    @Nullable
    T find(Class<T> c, int id);

    /**
     * Retrieve all element from the data source.
     *
     * @param c The destination type of the objects.
     * @return A list containing every item.
     */
    @NotNull
    List<T> findAll(@NotNull Class<T> c);

    /**
     * Update the value of an item in the data source.
     *
     * @param item The new value of the item
     * @return True if it is updated, false otherwise
     */
    boolean update(@NotNull T item);

    /**
     * Delete an element using the provided item's id.
     *
     * @param item The item with the ID that you want to delete
     * @return True if it is deleted, false otherwise
     */
    boolean delete(@NotNull T item);

    /**
     * Delete every item of this type from the data source
     * @return True if it is deleted, false otherwise
     */
    boolean deleteAll(Class<T> c);

    /**
     * Close connexion to the data source.
     */
    void close();
}
