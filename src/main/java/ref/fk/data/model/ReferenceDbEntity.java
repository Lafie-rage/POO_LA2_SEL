package ref.fk.data.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_ex_cascade_ref", schema ="bdd_sel_from_teacher")
public class ReferenceDbEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="ID_REF")
    private int id;

    @Basic
    @Column(name="LIB_REF")
    private String lib;

    @OneToMany(mappedBy="ref", cascade={CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<ForeignKeyDbEntity> fk;

    public int getId() {
        return id;
    }

    public String getLib() {
        return lib;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public List<ForeignKeyDbEntity> getFk() {
        return fk;
    }

    public void setFk(List<ForeignKeyDbEntity> fk) {
        this.fk = fk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceDbEntity that = (ReferenceDbEntity) o;
        return id == that.id && Objects.equals(lib, that.lib);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lib);
    }

    @Override
    public String toString() {
        return "ReferenceDbEntity{" +
                "id=" + id +
                ", lib='" + lib + '\'' +
                '}';
    }
}
