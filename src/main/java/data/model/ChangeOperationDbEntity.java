package data.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "t_operation_change", schema = "bdd_sel_from_teacher", catalog = "")
public class ChangeOperationDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_OPERATION_CHANGE")
    private int id;
    @Basic
    @Column(name = "DATE_HEURE_OPERATION_CHANGE")
    private Timestamp dateTime;
    @Basic
    @Column(name = "ID_MEMBRE")
    private Integer memberId;
    @Basic
    @Column(name = "MONTANT_OPERATION_CHANGE")
    private int amount;
    @Basic
    @Column(name = "TYPE_OPERATION_CHANGE")
    private String type;
    @Basic
    @Column(name = "SOLDE_BANQUE")
    private Integer bankBalance;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(Integer bankBalance) {
        this.bankBalance = bankBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeOperationDbEntity that = (ChangeOperationDbEntity) o;
        return id == that.id && amount == that.amount && Objects.equals(dateTime, that.dateTime) && Objects.equals(memberId, that.memberId) && Objects.equals(type, that.type) && Objects.equals(bankBalance, that.bankBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, memberId, amount, type, bankBalance);
    }

    @Override
    public String toString() {
        return "ChangeOperationDbEntity{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", bankBalance=" + bankBalance +
                '}';
    }
}
