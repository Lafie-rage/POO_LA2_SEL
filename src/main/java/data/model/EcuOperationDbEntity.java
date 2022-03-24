package data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_operation_ecu", schema = "bdd_sel_from_teacher", catalog = "")
public class EcuOperationDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_OPERATION_ECU")
    private int id;
    @Basic
    @Column(name = "DATE_HEURE_OPERATION")
    private Timestamp dateTime;
    @Basic
    @Column(name = "ID_MEMBRE_DEBITEUR")
    private int debtorId;
    @Basic
    @Column(name = "ID_MEMBRE_CREDITEUR")
    private int creditorId;
    @Basic
    @Column(name = "MONTANT_OPERATION")
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(int debtorId) {
        this.debtorId = debtorId;
    }

    public int getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(int creditorId) {
        this.creditorId = creditorId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcuOperationDbEntity that = (EcuOperationDbEntity) o;
        return id == that.id && debtorId == that.debtorId && creditorId == that.creditorId && amount == that.amount && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, debtorId, creditorId, amount);
    }

    @Override
    public String toString() {
        return "EcuOperationDbEntity{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", debtorId=" + debtorId +
                ", creditorId=" + creditorId +
                ", amount=" + amount +
                '}';
    }
}
