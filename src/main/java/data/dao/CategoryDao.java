package data.dao;

import common.Nothing;
import data.DbUtils;
import data.Result;
import data.model.CategoryDbEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.NotYetImplementedException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public interface CategoryDao {

    /**
     * Provide a safe thread singleton of the dao.
     *
     * @return The unique instance of the DAO.
     */
    static CategoryDao getInstance() {
        return CategoryDaoImpl.INSTANCE;
    }

    /**
     * Try to retrieve a CategoryDbEntity by its identifier.
     *
     * @param id The id of the category you are looking for.
     * @return A Result element depending on the result of the DB query.
     * @see CategoryDbEntity
     * @see Result
     */
    Result<CategoryDbEntity> getById(int id);

    Result<CategoryDbEntity> getByName(String name);

    Result<Integer> insert(CategoryDbEntity category);

    Result<Nothing> update(CategoryDbEntity category);

    Result<Nothing> remove(CategoryDbEntity category);

}

class CategoryDaoImpl implements CategoryDao {
    private final Session dbSession = DbUtils.getSessionInstance();
    public final static CategoryDao INSTANCE = new CategoryDaoImpl();

    private CategoryDaoImpl() {
    }

    @Override
    public Result<CategoryDbEntity> getById(int id) {
        Transaction transaction = dbSession.getTransaction();
        CategoryDbEntity category;

        try {
            transaction.begin();

            category = dbSession.get(CategoryDbEntity.class, id);

            transaction.commit();
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        if (category != null)
            return Result.success(category);
        else
            return Result.empty();
    }

    @Override
    public Result<CategoryDbEntity> getByName(String name) {
        Transaction transaction = dbSession.getTransaction();
        CategoryDbEntity category = null;

        CriteriaBuilder builder = dbSession.getCriteriaBuilder();
        CriteriaQuery<CategoryDbEntity> criteriaQuery = builder.createQuery(CategoryDbEntity.class);
        Root<CategoryDbEntity> root = criteriaQuery.from(CategoryDbEntity.class);

        Predicate filteringByNameEquality = builder.equal(root.get("name"), name);

        criteriaQuery.select(root).where(filteringByNameEquality);

        Query query = dbSession.createQuery(criteriaQuery);

        try {
            transaction.begin();

            Object result = query.getSingleResult();

            transaction.commit();

            if (result instanceof CategoryDbEntity) category = (CategoryDbEntity) result;
        } catch (NoResultException e) {
            return Result.empty();
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        return Result.success(category);
    }

    @Override
    public Result<Integer> insert(CategoryDbEntity category) {
        Transaction transaction = dbSession.getTransaction();
        Serializable generatedIdentifier;
        try {
            transaction.begin();

            generatedIdentifier = dbSession.save(category);

            transaction.commit();

            if (generatedIdentifier instanceof Integer) {
                return Result.success((Integer) generatedIdentifier);
            } else {
                return Result.success(-1);
            }
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Result<Nothing> update(CategoryDbEntity category) {
        Transaction transaction = dbSession.getTransaction();
        try {
            transaction.begin();

            dbSession.update(category);

            transaction.commit();
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        return Result.success(Nothing.INSTANCE);
    }

    @Override
    public Result<Nothing> remove(CategoryDbEntity category) {
        Transaction transaction = dbSession.getTransaction();
        try {
            transaction.begin();

            dbSession.remove(category);

            transaction.commit();
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        return Result.success(Nothing.INSTANCE);
    }
}
