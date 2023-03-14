package kpn.ctrlf.data.monitoring;

import kpn.ctrlf.client.conversation.notifier.DomainChangeNotificationTask;
import kpn.ctrlf.client.conversation.notifier.DomainChangeNotifier;
import kpn.ctrlf.client.conversation.response.value.Value;
import kpn.ctrlf.subscription.SubscriptionHolderImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ChangeMonitoringImplTest {
	private static final int SUBSCRIBERS_AMOUNT = 3;

	private SubscriptionHolderImpl<String> subscriptionHolder;

	@BeforeEach
	void setUp() {
		subscriptionHolder = new SubscriptionHolderImpl<>();
		for (int i = 0; i < SUBSCRIBERS_AMOUNT; i++) {
			subscriptionHolder.subscribe(String.valueOf(i));
		}
	}

	@Test
	void shouldCheckTraceCreation() {
		TestNotifier notifier = new TestNotifier();
		String prefix = "CREATION";
		ChangeMonitoringImpl<Object> changeMonitoring = new ChangeMonitoringImpl<>(
			notifier,
			subscriptionHolder,
			new TestConverter(prefix),
			null,
			null);

		changeMonitoring.traceCreation(new Object());
		assertThat(notifier.getTasks()).isEqualTo(createExpectedTasks(prefix));
	}

	@Test
	void shouldCheckTraceUpdating() {
		TestNotifier notifier = new TestNotifier();
		String prefix = "UPDATING";
		ChangeMonitoringImpl<Object> changeMonitoring = new ChangeMonitoringImpl<>(
			notifier,
			subscriptionHolder,
			null,
			new TestConverter(prefix),
			null);

		changeMonitoring.traceUpdating(new Object());
		assertThat(notifier.getTasks()).isEqualTo(createExpectedTasks(prefix));
	}

	@Test
	void shouldCheckTraceRemoving() {
		TestNotifier notifier = new TestNotifier();
		String prefix = "DELETING";
		ChangeMonitoringImpl<Object> changeMonitoring = new ChangeMonitoringImpl<>(
			notifier,
			subscriptionHolder,
			null,
			null,
			new TestConverter(prefix));

		changeMonitoring.traceDeleting(new Object());

		assertThat(notifier.getTasks()).isEqualTo(createExpectedTasks(prefix));
	}

	private static List<TestTask> createExpectedTasks(String prefix){
		return IntStream.range(0, SUBSCRIBERS_AMOUNT)
			.mapToObj(i -> {
				return new TestTask(prefix + String.valueOf(i), null);
			}).collect(Collectors.toList());
	}

	@Getter
	private static class TestNotifier implements DomainChangeNotifier {
		private final List<DomainChangeNotificationTask> tasks = new ArrayList<>();

		@Override
		public boolean append(DomainChangeNotificationTask task) {
			tasks.add(task);
			return false;
		}

		@Override
		public void dispose() throws InterruptedException {}
	}

	@RequiredArgsConstructor
	@Getter
	private static class TestTask implements DomainChangeNotificationTask {
		private final String destination;
		private final Value payload;

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			TestTask testTask = (TestTask) o;

			return Objects.equals(destination, testTask.destination);
		}

		@Override
		public int hashCode() {
			return destination != null ? destination.hashCode() : 0;
		}
	}

	@RequiredArgsConstructor
	private static class TestConverter implements ChangeMonitoringImpl.Converter<Object>{
		private final String destinationPrefix;

		private int counter = 0;

		@Override
		public DomainChangeNotificationTask convert(Object domain, String subscriber) {
			return new TestTask(destinationPrefix + String.valueOf(counter++), null);
		}
	}
}
