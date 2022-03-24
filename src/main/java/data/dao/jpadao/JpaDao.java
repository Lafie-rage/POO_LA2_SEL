package data.dao.jpadao;

import data.DbUtils;
import data.dao.Dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract public class JpaDao<T> implements Dao<T> {

    public Session getSession() {
        return DbUtils.getSessionInstance();
    }

    @Override
    public boolean create(T item) {
        Transaction transaction = getSession().beginTransaction();
        Serializable generatedIdentifier;
        try {
            generatedIdentifier = getSession().save(item);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return generatedIdentifier instanceof Integer;
    }

    @Override
    public T find(Class<T> c, int id) {
        Transaction transaction = getSession().getTransaction();
        T item;

        try {
            transaction.begin();

            item = getSession().get(c, id);

            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public List<T> findAll(Class<T> c) {
        try {
            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            CriteriaQuery<T> intermediateQuery = builder.createQuery(c);
            Root<T> rootEntry = intermediateQuery.from(c);
            CriteriaQuery<T> finalQuery = intermediateQuery.select(rootEntry);
            return getSession().createQuery(finalQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(T item) {
        Transaction transaction = getSession().beginTransaction();

        try {
            getSession().update(item);

            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return true;
    }

    @Override
    public boolean delete(T item) {
        Transaction transaction = getSession().beginTransaction();

        try {
            getSession().remove(item);

            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        return true;
    }

    @Override
    public boolean deleteAll(Class<T> c) {
        try {
            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            builder.createQuery(c);
            CriteriaDelete<T> deleteQuery = builder.createCriteriaDelete(c);
            deleteQuery.from(c);
            return getSession().createQuery(deleteQuery).executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() {
        getSession().close();
    }
}
