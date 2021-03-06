package ifpe.recife.tads.test;

import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseEJBTest {

    protected static EJBContainer container;
    protected final Logger logger = Logger.getGlobal();

    @BeforeClass
    public static void setUpClass() {
        container = EJBContainer.createEJBContainer();
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }
}
