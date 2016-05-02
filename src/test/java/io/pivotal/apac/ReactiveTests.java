package io.pivotal.apac;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.test.TestSubscriber;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class ReactiveTests {

//========================================================================================

	@Test
	public void empty() {
		Flux<String> flux = emptyFlux();
		TestSubscriber<String> testSubscriber = new TestSubscriber<>();
		testSubscriber
				.bindTo(flux)
				.assertValueCount(0)
				.assertComplete();
	}

	Flux<String> emptyFlux() {
		return Flux.empty();
	}

//========================================================================================

	@Test
	public void fromValues() {
		Flux<String> flux = fooBarFluxFromValues();
		TestSubscriber<String> testSubscriber = new TestSubscriber<>();
		testSubscriber
				.bindTo(flux)
				.assertValues("foo", "bar")
				.assertComplete();
	}

	Flux<String> fooBarFluxFromValues() {
		return Flux.just("foo", "bar");
	}

//========================================================================================

	@Test
	public void fromList() {
		Flux<String> flux = fooBarFluxFromList();
		TestSubscriber<String> testSubscriber = new TestSubscriber<>();
		testSubscriber
				.bindTo(flux)
				.assertValues("foo", "bar")
				.assertComplete();
	}

	Flux<String> fooBarFluxFromList() {
		return Flux.fromIterable(Arrays.asList("foo", "bar"));
	}

//========================================================================================

	@Test
	public void error() {
		Flux<String> flux = errorFlux();
		TestSubscriber<String> testSubscriber = new TestSubscriber<>();
		testSubscriber
				.bindTo(flux)
				.assertError(IllegalStateException.class)
				.assertNotComplete();
	}

	Flux<String> errorFlux() {
		return Flux.error(new IllegalStateException());
	}

//========================================================================================

	@Test
	public void neverTerminates() {
		Flux<String> flux = neverTerminatedFlux();
		TestSubscriber<String> testSubscriber = new TestSubscriber<>();
		testSubscriber
				.bindTo(flux)
				.assertNotTerminated();
	}

	Flux<String> neverTerminatedFlux() {
        return Flux.never();
	}

//========================================================================================

	@Test
	public void countEachSecond() {
		Flux<Long> flux = counter();
		TestSubscriber<Long> testSubscriber = new TestSubscriber<>();
		testSubscriber
				.bindTo(flux)
				.assertNotTerminated()
				.awaitAndAssertNextValues(0L, 1L, 2L);
	}

	Flux<Long> counter() {
		return Flux.interval(Duration.of(100, ChronoUnit.MILLIS));
	}

//========================================================================================

    @Test
    public void empty2() {
        Mono<String> mono = emptyMono();
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        testSubscriber
                .bindTo(mono)
                .assertValueCount(0)
                .assertComplete();
    }

    // TODO Return an empty Mono
    Mono<String> emptyMono() {
        return null;
    }

//========================================================================================

    @Test
    public void fromValue() {
        Mono<String> mono = fooMono();
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        testSubscriber
                .bindTo(mono)
                .assertValues("foo")
                .assertComplete();
    }

    // TODO Return a Mono that contains a "foo" value
    Mono<String> fooMono() {
        return null;
    }

//========================================================================================

    @Test
    public void error2() {
        Mono<String> mono = errorMono();
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        testSubscriber
                .bindTo(mono)
                .assertError(IllegalStateException.class)
                .assertNotComplete();
    }

    // TODO Create a Mono that emits an IllegalStateException
    Mono<String> errorMono() {
        return null;
    }

}