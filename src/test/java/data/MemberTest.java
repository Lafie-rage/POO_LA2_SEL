package data;

import common.Nothing;
import data.model.member.MemberDbEntity;
import data.model.member.VacationerDbEntity;
import domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    private final MemberRepository repository = MemberRepository.getInstance();

    @Test
    public void launchMemberTest() {
        int memberId = testInsertMember();
        testRetrieveMember(memberId);
        testUpdateMember(memberId);
        testRemoveMember(memberId);
    }

    private int testInsertMember() {
        return testInsertNonExistentMember();
    }

    private void testRetrieveMember(int id) {
        testRetrieveExistentMember(id);
        testRetrieveNonexistentMember();
    }

    private void testUpdateMember(int id) {
        testUpdateExistentMember(id);
        testUpdateNonExistentMember();
    }

    private void testRemoveMember(int id) {
        testRemoveExistentMember(id);
        testRemoveNonExistentMember();
    }

    private void testRetrieveExistentMember(int id) {

        Result<MemberDbEntity> result = repository.getById(id);

        // Item exists in DB
        // It should be a success Result
        assertTrue(result.isSuccess());
        assertFalse(result.isEmpty());
        assertFalse(result.isError());
        assertNotNull(result.getValue());
        assertNull(result.getException());

        MemberDbEntity member = result.getValue();

        // Testing item values
        assertNotEquals(null, member);
        assertEquals("Roger", member.getFirstName());
        assertEquals("Federer", member.getLastName());
        assertEquals(id, member.getId());
    }

    private void testRetrieveNonexistentMember() {
        Result<MemberDbEntity> result = repository.getById(0);

        // Item doesn't exist in DB
        // It should be an empty Result
        assertFalse(result.isSuccess());
        assertTrue(result.isEmpty());
        assertFalse(result.isError());
        assertNull(result.getValue());
        assertNull(result.getException());
    }

    private int testInsertNonExistentMember() {
        // Defining a new Member
        final MemberDbEntity member = new VacationerDbEntity();
        member.setFirstName("Roger");
        member.setLastName("Federer");
        member.setStatus("V");
        member.setHourBalance(10);
        member.setEcuBalance(0);
        member.setClientCountEcu(0);

        Result<MemberDbEntity> result = repository.insert(member);

        // Verifying return
        // Should be a success Result
        assertTrue(result.isSuccess());
        assertFalse(result.isEmpty());
        assertFalse(result.isError());
        assertNotNull(result.getValue());
        assertNull(result.getException());

        MemberDbEntity insertedMember = result.getValue();

        // Verifying return
        assertNotEquals(-1, insertedMember.getId());
        assertEquals(member.getFirstName(), insertedMember.getFirstName());
        assertEquals(member.getLastName(), insertedMember.getLastName());
        return insertedMember.getId();
    }

    private void testUpdateExistentMember(int id) {
        Result<MemberDbEntity> searchResult = repository.getById(id);

        assertNotNull(searchResult.getValue());

        MemberDbEntity member = searchResult.getValue();
        member.setFirstName("Petter");

        Result<MemberDbEntity> insertResult = repository.update(member);

        // Verifying result
        // Should be a success result
        assertTrue(insertResult.isSuccess());
        assertFalse(insertResult.isEmpty());
        assertFalse(insertResult.isError());
        assertNotNull(insertResult.getValue());
        assertNull(insertResult.getException());

        assertEquals(insertResult.getValue(), member);
    }

    private void testUpdateNonExistentMember() {
        MemberDbEntity member = new VacationerDbEntity();
        member.setId(0);

        Result<MemberDbEntity> insertResult = repository.update(member);

        // Verifying result
        // Should be an empty result
        assertFalse(insertResult.isSuccess());
        assertFalse(insertResult.isEmpty());
        assertTrue(insertResult.isError());
        assertNull(insertResult.getValue());
        assertNotNull(insertResult.getException());

        repository.insert(member);
    }

    private void testRemoveExistentMember(int id) {
        MemberDbEntity member = new VacationerDbEntity();

        Result<MemberDbEntity> searchResult = repository.getById(id);

        if(searchResult.isEmpty()) {
            searchResult = repository.insert(member);
        }

        assertNotNull(searchResult.getValue());

        member = searchResult.getValue();

        Result<Nothing> removeResult = repository.remove(member);

        // Verifying result
        // Should be a success result
        assertTrue(removeResult.isSuccess());
        assertFalse(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNotNull(removeResult.getValue());
        assertNull(removeResult.getException());
        assertInstanceOf(Nothing.class, removeResult.getValue());
    }

    private void testRemoveNonExistentMember() {
        MemberDbEntity member = new VacationerDbEntity();
        member.setId(0);
        Result<Nothing> removeResult = repository.remove(member);

        // Verifying result
        // Should be an empty result
        assertFalse(removeResult.isSuccess());
        assertTrue(removeResult.isEmpty());
        assertFalse(removeResult.isError());
        assertNull(removeResult.getValue());
        assertNull(removeResult.getException());
    }
}
