package kpn.ctrlf.client.conversation.notifier;

import lombok.Getter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

class DomainChangeNotifierImplTest {

	@SneakyThrows
	@Test
	void shouldCheckCreation() {
		TestExecutorService taskExecutor = new TestExecutorService();
		DomainChangeNotifier notifier = DomainChangeNotifierImpl.create(
			createQueue(1),
			taskExecutor,
			new TestSender()
		);
		assertThat(notifier.getClass()).isEqualTo(DomainChangeNotifierImpl.class);
		assertThat(taskExecutor.isSubmitCalled()).isTrue();

		notifier.dispose();
	}

	@SneakyThrows
	@Test
	void shouldCheckDisposing() {
		TestExecutorService taskExecutor = new TestExecutorService();
		DomainChangeNotifier notifier = DomainChangeNotifierImpl.create(
			createQueue(1),
			taskExecutor,
			new TestSender()
		);

		notifier.dispose();

		Field inProcessField = DomainChangeNotifierImpl.class.getDeclaredField("inProcess");
		inProcessField.setAccessible(true);
		Object rawInProcess = inProcessField.get(notifier);
		assertThat(rawInProcess.getClass()).isEqualTo(AtomicBoolean.class);
		AtomicBoolean inProcess = (AtomicBoolean) rawInProcess;
		assertThat(inProcess).isFalse();
		assertThat(taskExecutor.isShutdownCalled()).isTrue();
	}

	@SneakyThrows
	@Test
	void shouldCheckAppend_ifItIsDisposed() {
		BlockingQueue<DomainChangeNotificationTask> queue = createQueue(1);
		DomainChangeNotifier notifier = DomainChangeNotifierImpl.create(
			queue,
			new TestExecutorService(),
			new TestSender()
		);
		notifier.dispose();

		boolean result = notifier.append(new DomainChangeNotificationTaskImpl(null, null));
		assertThat(result).isFalse();
		assertThat(queue).isEmpty();
	}

	@SneakyThrows
	@Test
	void shouldCheckAppend_ifQueueIsOverflow() {
		BlockingQueue<DomainChangeNotificationTask> queue = createQueue(1);
		DomainChangeNotifier notifier = DomainChangeNotifierImpl.create(
			queue,
			new TestExecutorService(),
			new TestSender()
		);

		notifier.append(new DomainChangeNotificationTaskImpl(null, null));
		boolean result = notifier.append(new DomainChangeNotificationTaskImpl(null, null));
		assertThat(result).isFalse();
		assertThat(queue.size()).isEqualTo(1);

		notifier.dispose();
	}

	@SneakyThrows
	@Test
	void shouldCheckAppend() {
		BlockingQueue<DomainChangeNotificationTask> queue = createQueue(1);
		DomainChangeNotifier notifier = DomainChangeNotifierImpl.create(
			queue,
			new TestExecutorService(),
			new TestSender()
		);

		boolean result = notifier.append(new DomainChangeNotificationTaskImpl(null, null));
		assertThat(result).isTrue();
		assertThat(queue.size()).isEqualTo(1);

		notifier.dispose();
	}

	@SneakyThrows
	@Test
	void shouldCheckTaskExecution() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		BlockingQueue<DomainChangeNotificationTask> queue = createQueue(10);
		TestSender sender = new TestSender();
		DomainChangeNotifier notifier = DomainChangeNotifierImpl.create(
			queue,
			executorService,
			sender
		);

		int expectedCounter = 5;
		for (int i = 0; i < expectedCounter; i++) {
			notifier.append(new DomainChangeNotificationTaskImpl(null, null));
		}

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		assertThat(sender.getCounter()).isEqualTo(expectedCounter);

		notifier.dispose();
	}

	private static BlockingQueue<DomainChangeNotificationTask> createQueue(int size){
		return new ArrayBlockingQueue<>(size);
	}

	@Getter
	private static class TestSender implements Sender {
		private int counter;

		@Override
		public void send(DomainChangeNotificationTask task) {
			this.counter++;
		}
	}

	@Getter
	private static class TestExecutorService implements ExecutorService {
		private boolean submitCalled;
		private boolean shutdownCalled;

		@Override
		public Future<?> submit(Runnable task) {
			this.submitCalled = true;
			return null;
		}

		@Override
		public void shutdown() {
			this.shutdownCalled = true;
		}

		@Override
		public List<Runnable> shutdownNow() { return null; }
		@Override
		public boolean isShutdown() {return false; }
		@Override
		public boolean isTerminated() {return false; }
		@Override
		public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException { return false; }
		@Override
		public <T> Future<T> submit(Callable<T> task) {return null;}
		@Override
		public <T> Future<T> submit(Runnable task, T result) {return null;}
		@Override
		public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {return null;}
		@Override
		public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {return null;}
		@Override
		public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {return null;}
		@Override
		public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {return null;}
		@Override
		public void execute(Runnable command) {}
	}
}
