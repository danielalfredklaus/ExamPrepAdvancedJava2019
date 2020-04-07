import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SoftTestMidTermPrep {

    /*
    @Test
    public void invoiceNotPaid() {
        int anyId = 1;
        double tax = 10.0;
        double amount = 25.0;
        Invoice inv = new Invoice(anyId,tax,amount);

        boolean paid = inv.isPaid();

        Assertions.assertFalse(paid);
    }
    */

    /*

    @Test
    public void testJarVisitor() throws Exception{

        URL jarUrl;
        try{
            jarUrl = new URL ("file:./target/packages/default_par.par");
        } catch (MalformedURLException e) {
            jarUrl = null;
        }
        JarVisitor.setupFilters();
        JarVisitor jarVisitor = JarVisitorFactory.getVisitor(jarUrl,
                JarVisitor.getFilters(), null);
        assertEquals(FileZippedJarVisitor.class.getName(),
                jarVisitor.getClass().getName());

        try{
            jarUrl = new URL ("file:./target/packages/exploded_par.par");
        } catch (MalformedURLException e) {
            jarUrl = null;
        }
        ExplodedJarVisitor jarVisitor2 = JarVisitorFactory.getVisitor(
                jarUrl,
                ExplodedJarVisitor.getFilters(),
                null);
        assertEquals(ExplodedJarVisitor.class.getName(),
                jarVisitor2.getClass().getName());
    }
    */
}
