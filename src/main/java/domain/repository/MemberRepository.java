package domain.repository;

import common.Nothing;
import data.Result;
import data.dao.MemberDao;
import data.model.member.MemberDbEntity;

public interface MemberRepository {
    /**
     * Provide a safe thread singleton of the repository.
     *
     * @return The unique instance of the Repository.
     */
    static MemberRepository getInstance() {
        return MemberRepositoryImpl.INSTANCE;
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
     * @return A Result element depending on the result of the DB query. If it's a success, result will contain the inserted member.
     * @see MemberDbEntity
     * @see Result
     */
    Result<MemberDbEntity> insert(MemberDbEntity member);

    /**
     * Try to update a MemberDbEntity in DB.
     *
     * @param member The member you wish to update in DB.
     * @return A Result element depending on the result of the DB query. If it's a success, result will contain the updated member.
     * @see MemberDbEntity
     * @see Nothing
     * @see Result
     */
    Result<MemberDbEntity> update(MemberDbEntity member);


    /**
     * Try to delete a MemberDbEntity in DB providing its ID.
     *
     * @param id The identifier of the member you wish to delete in DB.
     * @return A Result element depending on the result of the DB query. If it's a success, result will contain a Nothing.
     * @see MemberDbEntity
     * @see Nothing
     * @see Result
     */
    Result<Nothing> removeById(int id);

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

class MemberRepositoryImpl implements MemberRepository {
    private final MemberDao dao = MemberDao.getInstance();
    public final static MemberRepository INSTANCE = new MemberRepositoryImpl();

    private MemberRepositoryImpl() {
    }

    @Override
    public Result<MemberDbEntity> getById(int id) {
        return dao.getById(id);
    }

    @Override
    public Result<MemberDbEntity> insert(MemberDbEntity member) {
        Result<Integer> insertResult = dao.insert(member);
        if(insertResult.isSuccess()) {
            return getById(insertResult.getValue());
        }
        if(insertResult.isEmpty()) {
            return Result.empty();
        }
        return Result.error(insertResult.getException()); // Converting it from Result<Integer> to Result<MemberDbEntity>
    }

    @Override
    public Result<MemberDbEntity> update(MemberDbEntity member) {
        Result<Nothing> updateResult = dao.update(member);
        if (updateResult.isSuccess()) {
            // Return the updated element if it exists
            return getById(member.getId());
        }
        if(updateResult.isEmpty()) {
            // Return an empty result if nothing has changed
            return Result.empty();
        }
        return Result.error(updateResult.getException());
    }

    @Override
    public Result<Nothing> removeById(int id) {
        // retrieving the member to remove
        Result<MemberDbEntity> searchResult = getById(id);
        if (searchResult.isSuccess()) {
            // Removing it if it exists
            // Keeping business logic to the remove by entity function
            return remove(searchResult.getValue());
        }
        if (searchResult.isEmpty()) {
            return Result.empty();
        }
        return Result.error(searchResult.getException());
    }

    @Override
    public Result<Nothing> remove(MemberDbEntity member) {
        // Retrieving the member to remove
        Result<MemberDbEntity> searchResult = getById(member.getId());
        if(searchResult.isSuccess()) {
            // Removing it if it exists
            return dao.remove(member);
        }
        if(searchResult.isEmpty()) {
            return Result.empty();
        }
        return Result.error(searchResult.getException());
    }
}
