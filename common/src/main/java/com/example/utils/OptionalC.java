package com.example.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author _lizy
 * @version 1.0
 * @description
 *      改进链式操作Optional Chain.
 *      ifPresent(),orElse()链式操作,builder模式
 *      get()可以获取空值，去掉为空抛异常
 * @date 2020/11/27 22:04
 */
public class OptionalC<T> {
    /**
     * Common instance for {@code empty()}.
     */
    private static final OptionalC<?> EMPTY = new OptionalC<>();

    /**
     * If non-null, the value; if null, indicates no value is present
     */
    private final T value;

    /**
     * Constructs an empty instance.
     *
     * @implNote Generally only one empty instance, {@link OptionalC#EMPTY},
     * should exist per VM.
     */
    private OptionalC() {
        this.value = null;
    }

    /**
     * Returns an empty {@code OptionalCollections} instance.  No value is present for this
     * OptionalCollections.
     *
     * @param <T> Type of the non-existent value
     * @return an empty {@code OptionalCollections}
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     * Instead, use {@link #isPresent()}.
     */
    public static <T> OptionalC<T> empty() {
        @SuppressWarnings("unchecked")
        OptionalC<T> t = (OptionalC<T>) EMPTY;
        return t;
    }

    /**
     * Constructs an instance with the value present.
     *
     * @param value the non-null value to be present
     * @throws NullPointerException if value is null
     */
    private OptionalC(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * Returns an {@code OptionalCollections} with the specified present non-null value.
     *
     * @param <T>   the class of the value
     * @param value the value to be present, which must be non-null
     * @return an {@code OptionalCollections} with the value present
     * @throws NullPointerException if value is null
     */
    public static <T> OptionalC<T> of(T value) {
        return new OptionalC<>(value);
    }

    /**
     * Returns an {@code OptionalCollections} describing the specified value, if non-null,
     * otherwise returns an empty {@code OptionalCollections}.
     *
     * @param <T>   the class of the value
     * @param value the possibly-null value to describe
     * @return an {@code OptionalCollections} with a present value if the specified value
     * is non-null, otherwise an empty {@code OptionalCollections}
     */
    public static <T> OptionalC<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     *
     * 改为可以返回空, 去掉为空抛错
     * If a value is present in this {@code OptionalCollections}, returns the value,
     * otherwise throws {@code NoSuchElementException}.
     *
     * @see OptionalC#isPresent()
     */
    public T get() {
//        if (value == null) {
//            throw new NoSuchElementException("No value present");
//        }
        return value;
    }

    /**
     * Return {@code true} if there is a value present, otherwise {@code false}.
     *
     * @return {@code true} if there is a value present, otherwise {@code false}
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * If a value is present, invoke the specified consumer with the value,
     * otherwise do nothing.
     *
     * @param consumer block to be executed if a value is present
     * @throws NullPointerException if value is present and {@code consumer} is
     *                              null
     */
    public OptionalC<T> ifPresent(Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
        return this;
    }

    /**
     * If a value is present, and the value matches the given predicate,
     * return an {@code OptionalCollections} describing the value, otherwise return an
     * empty {@code OptionalCollections}.
     *
     * @param predicate a predicate to apply to the value, if present
     * @return an {@code OptionalCollections} describing the value of this {@code OptionalCollections}
     * if a value is present and the value matches the given predicate,
     * otherwise an empty {@code OptionalCollections}
     * @throws NullPointerException if the predicate is null
     */
    public OptionalC<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent()) {
            return this;
        } else {
            return predicate.test(value) ? this : empty();
        }
    }

    /**
     * If a value is present, apply the provided mapping function to it,
     * and if the result is non-null, return an {@code OptionalCollections} describing the
     * result.  Otherwise return an empty {@code OptionalCollections}.
     *
     * @param <U>    The type of the result of the mapping function
     * @param mapper a mapping function to apply to the value, if present
     * @return an {@code OptionalCollections} describing the result of applying a mapping
     * function to the value of this {@code OptionalCollections}, if a value is present,
     * otherwise an empty {@code OptionalCollections}
     * @throws NullPointerException if the mapping function is null
     * @apiNote This method supports post-processing on optional values, without
     * the need to explicitly check for a return status.  For example, the
     * following code traverses a stream of file names, selects one that has
     * not yet been processed, and then opens that file, returning an
     * {@code OptionalCollections<FileInputStream>}:
     *
     * <pre>{@code
     *     OptionalCollections<FileInputStream> fis =
     *         names.stream().filter(name -> !isProcessedYet(name))
     *                       .findFirst()
     *                       .map(name -> new FileInputStream(name));
     * }</pre>
     * <p>
     * Here, {@code findFirst} returns an {@code OptionalCollections<String>}, and then
     * {@code map} returns an {@code OptionalCollections<FileInputStream>} for the desired
     * file if one exists.
     */
    public <U> OptionalC<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return OptionalC.ofNullable(mapper.apply(value));
        }
    }

    /**
     * If a value is present, apply the provided {@code OptionalCollections}-bearing
     * mapping function to it, return that result, otherwise return an empty
     * {@code OptionalCollections}.  This method is similar to {@link #map(Function)},
     * but the provided mapper is one whose result is already an {@code OptionalCollections},
     * and if invoked, {@code flatMap} does not wrap it with an additional
     * {@code OptionalCollections}.
     *
     * @param <U>    The type parameter to the {@code OptionalCollections} returned by
     * @param mapper a mapping function to apply to the value, if present
     *               the mapping function
     * @return the result of applying an {@code OptionalCollections}-bearing mapping
     * function to the value of this {@code OptionalCollections}, if a value is present,
     * otherwise an empty {@code OptionalCollections}
     * @throws NullPointerException if the mapping function is null or returns
     *                              a null result
     */
    public <U> OptionalC<U> flatMap(Function<? super T, OptionalC<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    /**
     * Return the value if present, otherwise return {@code other}.
     *
     * @param other the value to be returned if there is no value present, may
     *              be null
     * @return the value, if present, otherwise {@code other}
     */
    public OptionalC<T> orElse(T other) {
        return value != null ? this : of(other);
    }

    /**
     * Return the value if present, otherwise invoke {@code other} and return
     * the result of that invocation.
     *
     * @param other a {@code Supplier} whose result is returned if no value
     *              is present
     * @return the value if present otherwise the result of {@code other.get()}
     * @throws NullPointerException if value is not present and {@code other} is
     *                              null
     */
    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    /**
     * Return the contained value, if present, otherwise throw an exception
     * to be created by the provided supplier.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier The supplier which will return the exception to
     *                          be thrown
     * @return the present value
     * @throws X                    if there is no value present
     * @throws NullPointerException if no value is present and
     *                              {@code exceptionSupplier} is null
     * @apiNote A method reference to the exception constructor with an empty
     * argument list can be used as the supplier. For example,
     * {@code IllegalStateException::new}
     */
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * Indicates whether some other object is "equal to" this OptionalCollections. The
     * other object is considered equal if:
     * <ul>
     * <li>it is also an {@code OptionalCollections} and;
     * <li>both instances have no value present or;
     * <li>the present values are "equal to" each other via {@code equals()}.
     * </ul>
     *
     * @param obj an object to be tested for equality
     * @return {code true} if the other object is "equal to" this object
     * otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OptionalC)) {
            return false;
        }

        OptionalC<?> other = (OptionalC<?>) obj;
        return Objects.equals(value, other.value);
    }

    /**
     * Returns the hash code value of the present value, if any, or 0 (zero) if
     * no value is present.
     *
     * @return hash code value of the present value or 0 if no value is present
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * Returns a non-empty string representation of this OptionalCollections suitable for
     * debugging. The exact presentation format is unspecified and may vary
     * between implementations and versions.
     *
     * @return the string representation of this instance
     * @implSpec If a value is present the result must include its string
     * representation in the result. Empty and present Optionals must be
     * unambiguously differentiable.
     */
    @Override
    public String toString() {
        return value != null
                ? "OptionalCollections{value=" + value + '}'
                : "OptionalCollections.empty";
    }

}