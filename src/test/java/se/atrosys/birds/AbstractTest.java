package se.atrosys.birds;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by IntelliJ IDEA.
 * User: ola
 * Date: 12/4/11
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = "classpath:spring/context.xml")
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {
}
