package jammazwan.xal;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class XalRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// A more typical split, followed by an aggregate
		from("direct:apart").split().body().aggregate(header("myKey"), new MyAggregationStrategy()).completionSize(5)
				.completionTimeout(1000).ignoreInvalidCorrelationKeys().to("mock:assert1");

		// split and aggregate in one request/reply
		from("direct:together").split(body(), new MyAggregationStrategy()).log("Done");
	}

	public static class MyAggregationStrategy implements AggregationStrategy {

		public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
			if (oldExchange == null) {
				return newExchange;
			}
			String body1 = oldExchange.getIn().getBody(String.class);
			String body2 = newExchange.getIn().getBody(String.class);

			oldExchange.getIn().setBody(body1.trim() + "-" + body2.trim());
			return oldExchange;
		}
	}
}