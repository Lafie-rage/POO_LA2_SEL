package data.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "t_transaction", schema = "bdd_sel_from_teacher", catalog = "")
public class TransactionDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_TRANSACTION")
    private int id;
    @Basic
    @Column(name = "DATE_CREATION")
    private Date creationDate;
    @Basic
    @Column(name = "ETAT")
    private String status;
    @Basic
    @Column(name = "DATE_VALIDATION")
    private Date validationDate;
    @Basic
    @Column(name = "ID_PROPOSITION")
    private int proposalId;
    @Basic
    @Column(name = "MONTANT_TRANSACTION")
    private int amountHour;
    @Basic
    @Column(name = "ID_MEMBRE_BENEFICIAIRE")
    private int beneficiaryId;
    @Basic
    @Column(name = "MONTANT_ECU")
    private int amountEcu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public int getAmountHour() {
        return amountHour;
    }

    public void setAmountHour(int amountHour) {
        this.amountHour = amountHour;
    }

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public int getAmountEcu() {
        return amountEcu;
    }

    public void setAmountEcu(int amountEcu) {
        this.amountEcu = amountEcu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDbEntity that = (TransactionDbEntity) o;
        return id == that.id && proposalId == that.proposalId && amountHour == that.amountHour && beneficiaryId == that.beneficiaryId && amountEcu == that.amountEcu && Objects.equals(creationDate, that.creationDate) && Objects.equals(status, that.status) && Objects.equals(validationDate, that.validationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, status, validationDate, proposalId, amountHour, beneficiaryId, amountEcu);
    }

    @Override
    public String toString() {
        return "TransactionDbEntity{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", status='" + status + '\'' +
                ", validationDate=" + validationDate +
                ", proposalId=" + proposalId +
                ", amountHour=" + amountHour +
                ", beneficiaryId=" + beneficiaryId +
                ", amountEcu=" + amountEcu +
                '}';
    }
}
