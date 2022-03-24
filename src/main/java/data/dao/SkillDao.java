package data.dao;

import common.Nothing;
import data.DbUtils;
import data.Result;
import data.model.CategoryDbEntity;
import data.model.SkillDbEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public interface SkillDao {

    /**
     * Provide a safe thread singleton of the dao.
     *
     * @return The unique instance of the DAO.
     */
    static SkillDao getInstance() {
        return SkillDaoImpl.INSTANCE;
    }

    /**
     * Try to retrieve a SkillDbEntity by its identifier.
     *
     * @param id The id of the category you are looking for.
     * @return A Result element depending on the result of the DB query.
     * @see SkillDbEntity
     * @see Result
     */
    Result<SkillDbEntity> getById(int id);

    Result<SkillDbEntity> getByName(String name);

    Result<Integer> insert(SkillDbEntity category);

    Result<Nothing> update(SkillDbEntity category);

    Result<Nothing> remove(SkillDbEntity category);

}

// Package private class
class SkillDaoImpl implements SkillDao {
    private final Session dbSession = DbUtils.getSessionInstance();
    public final static SkillDao INSTANCE = new SkillDaoImpl();

    private SkillDaoImpl() {
    }


    @Override
    public Result<SkillDbEntity> getById(int id) {
        Transaction transaction = dbSession.getTransaction();
        SkillDbEntity skill;

        try {
            transaction.begin();

            skill = dbSession.get(SkillDbEntity.class, id);

            transaction.commit();
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        if (skill == null) {
            return Result.empty();
        }
        return Result.success(skill);
    }

    @Override
    public Result<SkillDbEntity> getByName(String name) {
        Transaction transaction = dbSession.getTransaction();
        SkillDbEntity skill = null;

        CriteriaBuilder builder = dbSession.getCriteriaBuilder();
        CriteriaQuery<SkillDbEntity> criteriaQuery = builder.createQuery(SkillDbEntity.class);
        Root<SkillDbEntity> root = criteriaQuery.from(SkillDbEntity.class);

        Predicate filteringByNameEquality = builder.equal(root.get("name"), name);

        criteriaQuery.select(root).where(filteringByNameEquality);

        Query query = dbSession.createQuery(criteriaQuery);

        try {
            transaction.begin();

            Object result = query.getSingleResult();

            transaction.commit();

            if (result instanceof SkillDbEntity) skill = (SkillDbEntity) result;
        } catch (NoResultException e) {
            return Result.empty();
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        return Result.success(skill);
    }

    @Override
    public Result<Integer> insert(SkillDbEntity category) {
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
    public Result<Nothing> update(SkillDbEntity category) {
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
    public Result<Nothing> remove(SkillDbEntity category) {
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
