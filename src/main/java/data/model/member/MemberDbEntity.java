package data.model.member;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_MEMBRE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "t_membre", schema = "bdd_sel_from_teacher")
public abstract class MemberDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_MEMBRE")
    private int id;
    @Basic
    @Column(name = "NOM_MEMBRE")
    private String lastName;
    @Basic
    @Column(name = "PRENOM_MEMBRE")
    private String firstName;
    @Basic
    @Column(name = "MAIL_MEMBRE")
    private String mail;
    @Basic
    @Column(name = "STATUT")
    private String status;
    @Basic
    @Column(name = "SOLDE_HEURE")
    private int hourBalance;
    @Basic
    @Column(name = "SOLDE_ECU")
    private int ecuBalance;
    @Basic
    @Column(name = "HABITANT")
    private Integer parcCitizen;
    @Basic
    @Column(name = "RAISON_SOCIALE")
    private String corporateName;
    @Basic
    @Column(name = "CLIENT_COMPTE_ECU")
    private int clientCountEcu;

    public int getId() {
        return id;
    }

    public void setId(int idMember) {
        this.id = idMember;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mailMember) {
        this.mail = mailMember;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statut) {
        this.status = statut;
    }

    public int getHourBalance() {
        return hourBalance;
    }

    public void setHourBalance(int hourBalance) {
        this.hourBalance = hourBalance;
    }

    public int getEcuBalance() {
        return ecuBalance;
    }

    public void setEcuBalance(int ecuBalance) {
        this.ecuBalance = ecuBalance;
    }

    public Integer getParcCitizen() {
        return parcCitizen;
    }

    public void setParcCitizen(Integer parcCitizen) {
        this.parcCitizen = parcCitizen;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public int getClientCountEcu() {
        return clientCountEcu;
    }

    public void setClientCountEcu(int clientCountEcu) {
        this.clientCountEcu = clientCountEcu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDbEntity that = (MemberDbEntity) o;
        return id == that.id && hourBalance == that.hourBalance && ecuBalance == that.ecuBalance && clientCountEcu == that.clientCountEcu && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(mail, that.mail) && Objects.equals(status, that.status) && Objects.equals(parcCitizen, that.parcCitizen) && Objects.equals(corporateName, that.corporateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, mail, status, hourBalance, ecuBalance, parcCitizen, corporateName, clientCountEcu);
    }

    @Override
    public String toString() {
        return "MemberDbEntity{" + "idMember=" + id + ", lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + ", mail='" + mail + '\'' + ", status='" + status + '\'' + ", hourBalance=" + hourBalance + ", ecuBalance=" + ecuBalance + ", parcCitizen=" + parcCitizen + ", corporateName='" + corporateName + '\'' + ", clientCountEcu=" + clientCountEcu + '}';
    }
}
