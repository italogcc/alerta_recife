package teste;

import Usuario.Funcionario;
import Usuario.TipoFuncionario;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("JPQLValidation")
public class TesteInsereFunc {

    private static EntityManagerFactory emf;
    private static Logger logger = Logger.getGlobal();
    private EntityManager em;
    private EntityTransaction et;

    public TesteInsereFunc() {
    }

    @BeforeClass
    public static void setUpClass() {
        //logger.setLevel(Level.INFO);
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("consultorio");
        DbUnitUtil.inserirDados();
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
    public void InsereFuncionario() {
        logger.info("Inserir funcionário");

        Funcionario funcionario = new Funcionario();

        funcionario.setNome(JOptionPane.showInputDialog("Digite o nome completo do usuário:"));
        funcionario.setLogin(JOptionPane.showInputDialog("Digite o login do usuário:"));
        funcionario.setEmail(JOptionPane.showInputDialog("Digite o e-mail do usuário:"));
        funcionario.setSenha(JOptionPane.showInputDialog("Cadastre a senha do usuário (visível ao digitar):"));
        funcionario.setSexo(JOptionPane.showInputDialog("Digite o sexo do usuário:"));
        
        

        //String tipofunc = null;
        //String[] tipoescfunc = {TipoFuncionario.SECRETARIA.toString(), TipoFuncionario.TECNICO.toString(),
        //            TipoFuncionario.MEDICO.toString()};
        
        funcionario.setTipofunc(TipoFuncionario.TECNICO.toString()); //Corrigir para escolher

        /*
                // String tipofunc;  //Desativada nesta linha porque nao inicializava
                // String[] tipofunc = { "TipoA", "TipoB", "TipoC", "TipoD"};
                String[] tipoescfunc = {TipoFuncionario.SECRETARIA.toString(), TipoFuncionario.TECNICO.toString(),
                    TipoFuncionario.MEDICO.toString()};

                tipofunc = (String) JOptionPane.showInputDialog(null, "Escolha o tipo do funcionário:",
                "Consultório médico", JOptionPane.QUESTION_MESSAGE, null,
                tipoescfunc, // Lista array para escolha
                tipoescfunc[0]); // Escolha inicial
        */
        
        em.persist(funcionario);
        em.flush();
        assertNotNull(funcionario.getId());
        logger.log(Level.INFO, "Cadastro realizado com sucesso.", funcionario);
    }
}