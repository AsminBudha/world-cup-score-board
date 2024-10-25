package utils;

import org.junit.jupiter.api.Test;
import org.worldcupscoreboard.utils.ObjectUtil;

public class ObjectUtilTest {

    @Test
    public void testNotNullNull() {
        try {
            ObjectUtil.notNull(null);
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Object must not be null");
        }
    }

    @Test
    public void testNotNullNullWithMessage() {
        try {
            ObjectUtil.notNull(null, "Match must not be null");
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Match must not be null");
        }
    }

    @Test
    public void testNotNullNotNull() {
        ObjectUtil.notNull(new Object());
    }

    @Test
    public void testNotNullNotNullWithMessage() {
        ObjectUtil.notNull(new Object(), "Match must not be null");
    }

    @Test
    public void testNotNullNotNullWithMessageNull() {
        ObjectUtil.notNull(new Object(), null);
    }

}
