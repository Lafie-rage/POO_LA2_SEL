package data.dao;

import common.Nothing;
import data.DbUtils;
import data.Result;
import data.model.member.MemberDbEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.io.Serializable;

public interface MemberDao {

    /**
     * Provide a safe thread singleton of the dao.
     *
     * @return The unique instance of the DAO.
     */
    static MemberDao getInstance() {
        return MemberDaoImpl.INSTANCE;
    }

    /**
     * Try to retrieve a MemberDbEntity by its identifier.
     *
     * @param id The id of the member you are looking for.
     * @return A Result element depending on the result of the DB query. If it's a success, result will contain the retrieved member.
     * @see MemberDbEntity
     * @see Result
     */
    Result<MemberDbEntity> getById(int id);

    /**
     * Try to insert a MemberDbEntity in DB.
     *
     * @param member The member you wish to insert in DB.
     * @return A Result element depending on the result of the DB query. If it's a success, result will contain the identifier of the inserted member.
     * @see MemberDbEntity
     * @see Result
     */
    Result<Integer> insert(MemberDbEntity member);

    /**
     * Try to update a MemberDbEntity in DB.
     *
     * @param member The member you wish to update in DB.
     * @return A Result element depending on the result of the DB query. If it's a success, result will contain a Nothing.
     * @see MemberDbEntity
     * @see Nothing
     * @see Result
     */
    Result<Nothing> update(MemberDbEntity member);

    /**
     * Try to delete a MemberDbEntity in DB.
     *
     * @param member The member you wish to delete in DB.
     * @return A Result element depending on the result of the DB query. If it's a success, result will contain a Nothing.
     * @see MemberDbEntity
     * @see Nothing
     * @see Result
     */
    Result<Nothing> remove(MemberDbEntity member);

}

class MemberDaoImpl implements MemberDao {
    private final Session dbSession = DbUtils.getSessionInstance();
    public final static MemberDao INSTANCE = new MemberDaoImpl();

    private MemberDaoImpl() {
    }

    @Override
    public Result<MemberDbEntity> getById(int id) {
        Transaction transaction = dbSession.getTransaction();
        MemberDbEntity member;

        try {
            transaction.begin();

            member = dbSession.get(MemberDbEntity.class, id);

            transaction.commit();
        } catch (PersistenceException e) {
            return Result.error(e);
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        if (member == null) {
            return Result.empty();
        }
        return Result.success(member);
    }

    @Override
    public Result<Integer> insert(MemberDbEntity member) {
        Transaction transaction = dbSession.getTransaction();
        Serializable generatedIdentifier;

        try {
            transaction.begin();

            generatedIdentifier = dbSession.save(member);

            transaction.commit();

            if (generatedIdentifier instanceof Integer) {
                return Result.success((Integer) generatedIdentifier);
            } else {
                return Result.empty();
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
    public Result<Nothing> update(MemberDbEntity member) {
        Transaction transaction = dbSession.getTransaction();

        try {
            transaction.begin();

            dbSession.update(member);

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
    public Result<Nothing> remove(MemberDbEntity member) {
        Transaction transaction = dbSession.getTransaction();

        try {
            transaction.begin();

            dbSession.remove(member);

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
