package ifpe.recife.tads.test;

import ifpe.recife.tads.alerta_recife.Administrador;
import ifpe.recife.tads.alerta_recife.Cargo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

public class AdministradorCRUDTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public AdministradorCRUDTest() {
    }

    @BeforeClass
    public static void setUpClass() {

        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("alerta_recife");
        DataSet.inserirDados();

    }

    @AfterClass
    public static void tearDownClass() {

        emf.close();

    }

    @Before
    public void setUp() {

        em = emf.createEntityManager();
        beginTransaction();

    }

    @After
    public void tearDown() {

        commitTransaction();
        em.close();

    }

    private void beginTransaction() {

        et = em.getTransaction();
        et.begin();

    }

    private void commitTransaction() {

        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            et.rollback();
            fail(ex.getMessage());
        }

    }

    @Test
    public void t01_criaAdministrador() {

        logger.info("Executando: criaAdministrador");
        Administrador admin = new Administrador();
        admin.setEmail("paulo.gomes@gmail.com");
        admin.setSenha("iUyBm12");
        admin.setPrimeiroNome("Paulo");
        admin.setUltimoNome("Gomes");
        admin.setHabilitado(true);
        admin.setMatricula("12098651");
        admin.setCargo(Cargo.TECNICO.numCargo);
        em.persist(admin);
        em.flush();
        assertNotNull(admin.getId());

    }

    @Test
    public void t02_atualizaCargo() {

        logger.info("Executando: atualizaCargo");
        Administrador adm;
        Query query = em.createNamedQuery("Administrador.RecuperarPorMatriculaSQL");
        query.setParameter(1, "96785321");
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        adm = (Administrador) query.getSingleResult();
        adm.setCargo(Cargo.ANALISTA.numCargo);
        em.flush();
        adm = (Administrador) query.getSingleResult();
        assertEquals(Cargo.ANALISTA.numCargo, adm.getCargo());

    }

    @Test
    public void t03_atualizaCargoMerge() {

        logger.info("Executando: atualizaCargoMerge");
        Administrador adm;
        Query query = em.createNamedQuery("Administrador.RecuperarPorMatriculaSQL");
        query.setParameter(1, "96785321");
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        adm = (Administrador) query.getSingleResult();
        assertNotNull(adm);
        em.clear();
        adm.setCargo(Cargo.GERENTE.numCargo);
        em.merge(adm);
        em.flush();
        adm = (Administrador) query.getSingleResult();
        assertEquals(Cargo.GERENTE.numCargo, adm.getCargo());

    }

    @Test
    public void t04_removeAdministrador() {

        logger.info("Executando: removeAdministrador");
        TypedQuery<Administrador> query = em.createNamedQuery("Administrador.RecuperarPorMatricula", Administrador.class);
        query.setParameter("matricula", "96225321");
        Administrador adm = (Administrador) query.getSingleResult();
        assertNotNull(adm);
        em.remove(adm);
        em.flush();
        assertEquals(0, query.getResultList().size());

    }

}
