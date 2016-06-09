package jammazwan.xal;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XalTest extends CamelSpringTestSupport {

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

	@Test
	public void testSplitThenAggregate() throws Exception {
		MockEndpoint mock = getMockEndpoint("mock:assert1");
		mock.expectedBodiesReceived("Every-good-boy-does-fine");

		template.sendBodyAndHeader("direct:apart", everyGoodBoyDoesFine(), "myKey", "123");

		mock.assertIsSatisfied();
	}

	@Test
	public void testSplitAggregate() throws Exception {
		String reply = template.requestBody("direct:together", everyGoodBoyDoesFine(), String.class);
		assertEquals("Every-good-boy-does-fine", reply);
	}

	List<String> everyGoodBoyDoesFine() {
		// Using a list of words, so that route won't need a tokenizer
		List<String> everyGoodBoyDoesFine = new ArrayList<String>();
		everyGoodBoyDoesFine.add("Every");
		everyGoodBoyDoesFine.add("good");
		everyGoodBoyDoesFine.add("boy");
		everyGoodBoyDoesFine.add("does");
		everyGoodBoyDoesFine.add("fine");
		return everyGoodBoyDoesFine;
	}

}
