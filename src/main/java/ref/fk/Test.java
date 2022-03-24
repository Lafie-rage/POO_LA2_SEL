package ref.fk;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ref.fk.data.model.ForeignKeyDbEntity;
import ref.fk.data.model.ReferenceDbEntity;

import java.util.Scanner;

public class Test {
    /**
     * Retrieve db session.
     *
     * @return db session
     */
    private static Session getSession() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            return configuration.buildSessionFactory().openSession();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static void main(String[] args) {
        testPersistence();
        testRefresh();
        testMerge();
        testRemove();
    }

    /**
     * Test de CascadeType.PERSISTENCE
     * <p>
     * ATTENTION : Il ne faut pas oublier de vérifier le type de mise à jour en cascade définit dans les classes modèles...
     */
    private static void testPersistence() {
        System.out.println("--- TEST MODIFY REF ---");
        testModifyRef();
        System.out.println("--- TEST MODIFY FK ---");
        testModifyFk();
    }

    private static void testModifyRef() {
        Session session = getSession();
        System.out.println("Retrieve fk element with id 1");
        Transaction transaction = session.beginTransaction();
        try {
            ForeignKeyDbEntity fk = session.get(ForeignKeyDbEntity.class, 1);
            transaction.commit();

            System.out.println(fk);

            System.out.println("Retrieve ref element associated with it");
            transaction.begin();
            ReferenceDbEntity ref = session.get(ReferenceDbEntity.class, fk.getRef().getId());
            transaction.commit();
            System.out.println(ref);

            System.out.println("Set ref lib to \"JEAN-PIERRE\"...");
            transaction.begin();
            ref.setLib("JEAN-PIERRE");
            transaction.commit();
            System.out.println(ref);

            System.out.println("FK associated to this ref");
            System.out.println(fk);

            System.out.println("Retrieving again the FK");
            transaction.begin();
            ForeignKeyDbEntity modifiedFk = session.get(ForeignKeyDbEntity.class, 1);
            transaction.commit();

            System.out.println(modifiedFk);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private static void testModifyFk() {
        Session session = getSession();
        System.out.println("Retrieve ref element with id 1");
        Transaction transaction = session.beginTransaction();
        try {
            ReferenceDbEntity ref = session.get(ReferenceDbEntity.class, 1);
            transaction.commit();

            System.out.println(ref);

            System.out.println("Retrieve fk element associated with it");
            transaction.begin();
            ForeignKeyDbEntity fk = session.get(ForeignKeyDbEntity.class, ref.getFk().get(0).getId());
            transaction.commit();
            System.out.println(fk);

            System.out.println("Set fk lib to \"LAST EVENT\"...");
            transaction.begin();
            fk.setLib("LAST EVENT");
            transaction.commit();
            System.out.println(fk);

            System.out.println("ref associated to this fk");
            System.out.println(ref);

            System.out.println("Retrieving again the FK");
            transaction.begin();
            ReferenceDbEntity modifiedRef = session.get(ReferenceDbEntity.class, 1);
            transaction.commit();

            System.out.println(modifiedRef);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    /**
     * Test de CascadeType.REFRESH
     * <p>
     * Il faut lancer cette fonction en débogage en stoppant l'exécution entre les affichage.
     * ATTENTION : Il ne faut pas oublier de vérifier le type de mise à jour en cascade définit dans les classes modèles...
     */
    private static void testRefresh() {
        Session session = getSession();
        System.out.println("Retrieve fk element with id 1");
        Transaction transaction = session.beginTransaction();
        Scanner scanner = new Scanner(System.in);
        try {
            ForeignKeyDbEntity fk = session.get(ForeignKeyDbEntity.class, 1);
            transaction.commit();

            System.out.println(fk);

            System.out.println("Retrieve ref element associated with it");
            transaction.begin();
            ReferenceDbEntity ref = session.get(ReferenceDbEntity.class, fk.getRef().getId());
            transaction.commit();
            System.out.println(ref);

            // Temps de pause entre la lecture et le refresh
            // Il faut alors modifier les valeur de la FK avec id 1 dans la BDD
            System.out.println("Waiting for you to change values in the DB... Press enter when done...");
            scanner.nextLine();

            // On raffrachit uniquement fk
            session.refresh(fk);
            System.out.println("Here is the new FK retrieved through ref");
            System.out.println(ref.getFk().get(0));
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    /**
     * Test de CascadeType.MERGE
     * <p>
     * ATTENTION : Il ne faut pas oublier de vérifier le type de mise à jour en cascade définit dans les classes modèles...
     */
    private static void testMerge() {
        Session session = getSession();
        System.out.println("Retrieve fk element with id 1");
        Transaction transaction = session.beginTransaction();
        try {
            ForeignKeyDbEntity fk = session.get(ForeignKeyDbEntity.class, 1);
            transaction.commit();

            System.out.println(fk);

            System.out.println("Retrieve ref element associated with it");
            transaction.begin();
            ReferenceDbEntity ref = session.get(ReferenceDbEntity.class, fk.getRef().getId());
            transaction.commit();
            System.out.println(ref);

            System.out.println("Modifying fk lib for FK with id equals to 1 and merging it");
            if (fk.getLib().equals("EVENEMENT 1")) {
                fk.setLib("EVENT 1");
            } else { // Equals to "EVENT 1"
                fk.setLib("EVENEMENT 1");
            }
            session.merge(fk);
            System.out.println("Here is the new FK retrieved through ref");
            System.out.println(ref.getFk().get(0));
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    /**
     * Test de CascadeType.REMOVE
     * <p>
     * ATTENTION : Il ne faut pas oublier de vérifier le type de mise à jour en cascade définit dans les classes modèles...
     */
    private static void testRemove() {
        Session session = getSession();
        System.out.println("Retrieve fk element with id 1");
        Transaction transaction = session.beginTransaction();
        try {
            System.out.println("Retrieve ref element associated with it");
            ReferenceDbEntity ref = session.get(ReferenceDbEntity.class, 1);
            transaction.commit();

            System.out.println(ref);

            System.out.println("Removing FK from DB");
            transaction.begin();
            session.remove(ref);
            transaction.commit();

            System.out.println("Does FK still exist ?");
            System.out.println("Trying to retrieve FK with id 1");
            transaction.begin();
            ForeignKeyDbEntity fk = session.get(ForeignKeyDbEntity.class, 1);
            transaction.commit();

            System.out.println("Is fk null : " + (fk == null));
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
