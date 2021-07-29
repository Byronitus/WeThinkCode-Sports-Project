package com.byronitis.wethinkcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilFunctionsTest {

    @Test
    public void testURLReplacement() throws Exception {
        String before = "https://www.thesportsdb.com/images/media/event/thumb/x.jpg";

        String expected = "https://www.thesportsdb.com/images/media/event/thumb/small/x.jpg";

        Assertions.assertEquals(expected, UtilFunctions.changeURL(before, "https://www.thesportsdb.com/images/media/event/thumb/"));
    }
}
