package com.Well.Engine;
import java.util.Map;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;


/**
 * When an assertion fails, don't throw an exception but record the failure.
 * Calling {@code assertAll()} will cause an exception to be thrown if at least
 * one assertion failed.
 */
public class SoftAssert extends Assertion {
    // LinkedHashMap to preserve the order
    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
    @SuppressWarnings("unused")
	private String assertMessage = null;

    @Override
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            assertMessage = a.getMessage();
            a.doAssert();
            onAssertSuccess(a);
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            m_errors.put(ex, a);
            CommonMethod.takeScreenshot("ABC"+CommonMethod.randomNumber());
            //saveScreenshot(assertMessage);
        } finally {
            onAfterAssert(a);
        }
    }

    public void assertAll() {
        if (!m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            boolean first = true;
            for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("\n\t");
                sb.append(ae.getKey().getMessage());
            }
            throw new AssertionError(sb.toString());
        }
    }

    
    
}
