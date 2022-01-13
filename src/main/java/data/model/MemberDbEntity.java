package data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_membre", schema = "bdd_sel_from_teacher")
public final class MemberDbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_MEMBRE")
    private int idMember;
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
    @Column(name = "TYPE_MEMBRE")
    private String typeMember;
    @Basic
    @Column(name = "HABITANT")
    private Integer parcCitizen;
    @Basic
    @Column(name = "RAISON_SOCIALE")
    private String corporateName;
    @Basic
    @Column(name = "CLIENT_COMPTE_ECU")
    private int clientCountEcu;

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String nomMember) {
        this.lastName = nomMember;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String prenomMember) {
        this.firstName = prenomMember;
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

    public String getTypeMember() {
        return typeMember;
    }

    public void setTypeMember(String typeMember) {
        this.typeMember = typeMember;
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
        return idMember == that.idMember && hourBalance == that.hourBalance && ecuBalance == that.ecuBalance && clientCountEcu == that.clientCountEcu && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(mail, that.mail) && Objects.equals(status, that.status) && Objects.equals(typeMember, that.typeMember) && Objects.equals(parcCitizen, that.parcCitizen) && Objects.equals(corporateName, that.corporateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMember, lastName, firstName, mail, status, hourBalance, ecuBalance, typeMember, parcCitizen, corporateName, clientCountEcu);
    }
}
