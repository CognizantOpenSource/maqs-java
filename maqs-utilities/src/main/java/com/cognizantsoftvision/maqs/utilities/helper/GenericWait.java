/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.helper;

import com.cognizantsoftvision.maqs.utilities.helper.exceptions.FunctionException;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.TimeoutException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * The Generic Wait class.
 */
public final class GenericWait {

  private GenericWait() {
  }

  private static final long RETRY_TIME_FROM_CONFIG = Long.parseLong(Config.getGeneralValue("WaitTime", "2"));

  private static final long TIMEOUT_FROM_CONFIG = Long.parseLong(Config.getGeneralValue("Timeout", "2"));

  /**
   * Wait until boolean.
   *
   * @param <T>         the type parameter
   * @param waitForTrue the wait for true
   * @param arg         the arg
   * @return the boolean
   * @throws InterruptedException the interrupted exception
   * @throws FunctionException    the function exception
   */
  public static <T> boolean waitUntil(Predicate<T> waitForTrue, T arg) throws InterruptedException {
    return wait(waitForTrue, RETRY_TIME_FROM_CONFIG, TIMEOUT_FROM_CONFIG, false, arg);
  }

  /**
   * Wait until boolean.
   *
   * @param waitForTrue the wait for true
   * @return the boolean
   * @throws InterruptedException the interrupted exception
   * @throws FunctionException    the function exception
   */
  public static boolean waitUntil(BooleanSupplier waitForTrue) throws InterruptedException {
    return wait(waitForTrue, RETRY_TIME_FROM_CONFIG, TIMEOUT_FROM_CONFIG, false);
  }

  /**
   * Wait for true.
   *
   * @param waitForTrue the wait for true
   * @throws InterruptedException the interrupted exception
   * @throws FunctionException    the function exception
   * @throws TimeoutException     the timeout exception
   */
  public static void waitForTrue(BooleanSupplier waitForTrue) throws InterruptedException {
    if (!wait(waitForTrue, RETRY_TIME_FROM_CONFIG, TIMEOUT_FROM_CONFIG, true)) {
      throw new TimeoutException("Timed out waiting for the function to return true");
    }
  }

  /**
   * Wait for true.
   *
   * @param <T>         the type parameter
   * @param waitForTrue the wait for true
   * @param arg         the arg
   * @throws InterruptedException the interrupted exception
   * @throws FunctionException    the function exception
   * @throws TimeoutException     the timeout exception
   */
  public static <T> void waitForTrue(Predicate<T> waitForTrue, T arg) throws InterruptedException {
    if (!wait(waitForTrue, RETRY_TIME_FROM_CONFIG, TIMEOUT_FROM_CONFIG, true, arg)) {
      throw new TimeoutException("Timed out waiting for the function to return true");
    }
  }

  /**
   * Wait until match t.
   *
   * @param <T>              the type parameter
   * @param waitForTrue      the wait for true
   * @param comparativeValue the comparative value
   * @return the t object
   */
  public static <T> T waitUntilMatch(Supplier<T> waitForTrue, T comparativeValue) {
    // Set start time and exception holder
    LocalDateTime start = LocalDateTime.now();

    T value = waitForTrue.get();

    // Checks if the two values are equal
    boolean paramsAreEqual = paramsEqual(value, comparativeValue);

    // While the params are not equal & the timeout hasn't met, keep checking
    while (!paramsAreEqual && (ChronoUnit.MILLIS.between(start, LocalDateTime.now())) < TIMEOUT_FROM_CONFIG) {
      // If they aren't, wait


      value = waitForTrue.get();

      // Check if they are equal
      // (running them through another function because we can't use an operator with T)
      if (paramsEqual(value, comparativeValue)) {
        return value;
      }
    }

    // return the value regardless
    return value;
  }

  /**
   * Wait until match t.
   *
   * @param <T>              the type parameter
   * @param waitForTrue      the wait for true
   * @param retryTime        the retry time
   * @param timeout          the timeout
   * @param comparativeValue the comparative value
   * @return the t object
   */
  public static <T> T waitUntilMatch(Supplier<T> waitForTrue, long retryTime, long timeout, T comparativeValue)
      throws InterruptedException {
    // Set start time and exception holder
    LocalDateTime start = LocalDateTime.now();

    T value = waitForTrue.get();

    // Checks if the two values are equal
    boolean paramsAreEqual = paramsEqual(value, comparativeValue);

    // While the params are not equal & the timeout hasn't met, keep checking
    while (!paramsAreEqual && (ChronoUnit.MILLIS.between(start, LocalDateTime.now())) < timeout) {
      // If they aren't, wait
      Thread.sleep(retryTime);

      value = waitForTrue.get();

      // Check if they are equal
      // (running them through another function because we can't use an operator with T
      paramsAreEqual = paramsEqual(value, comparativeValue);
    }

    // return the value regardless
    return value;
  }

  /**
   * Wait for match.
   *
   * @param <T>              the type parameter
   * @param waitForTrue      the wait for true
   * @param comparativeValue the comparative value
   * @throws InterruptedException the interrupted exception
   * @throws TimeoutException     the timeout exception
   */
  public static <T> void waitForMatch(Supplier<T> waitForTrue, T comparativeValue) throws InterruptedException {
    // Set start time and exception holder
    LocalDateTime start = LocalDateTime.now();

    // Checks if the two values are equal
    boolean paramsAreEqual = paramsEqual(waitForTrue.get(), comparativeValue);

    // While the params are not equal & the timeout hasn't met, keep checking
    while (!paramsAreEqual && (ChronoUnit.MILLIS.between(start, LocalDateTime.now())) < TIMEOUT_FROM_CONFIG) {
      // If they aren't, wait
      Thread.sleep(RETRY_TIME_FROM_CONFIG);

      // Check if they are equal
      // (running them through another function because we can't use an operator with T
      paramsAreEqual = paramsEqual(waitForTrue.get(), comparativeValue);
    }

    if (!paramsAreEqual) {
      throw new TimeoutException("Timed out waiting for the supplier to return expected value of " + comparativeValue);
    }
  }

  /**
   * Wait for match.
   *
   * @param <T>              the type parameter
   * @param waitForTrue      the wait for true
   * @param retryTime        the retry time
   * @param timeout          the timeout
   * @param comparativeValue the comparative value
   * @throws InterruptedException the interrupted exception
   * @throws TimeoutException     the timeout exception
   */
  public static <T> void waitForMatch(Supplier<T> waitForTrue, long retryTime, long timeout, T comparativeValue)
      throws InterruptedException {
    // Set start time and exception holder+
    LocalDateTime start = LocalDateTime.now();

    // Checks if the two values are equal
    boolean paramsAreEqual = paramsEqual(waitForTrue.get(), comparativeValue);

    // While the params are not equal & the timeout hasn't met, keep checking
    while (!paramsAreEqual && (ChronoUnit.MILLIS.between(start, LocalDateTime.now())) < timeout) {
      // Check if they are equal (running them through another function because we can't use an operator with T
      paramsAreEqual = paramsEqual(waitForTrue.get(), comparativeValue);

      // If they aren't, wait
      Thread.sleep(retryTime);
    }

    if (!paramsAreEqual) {
      throw new TimeoutException(
          "Timed out waiting for the supplier to return the expected value of " + comparativeValue);
    }
  }

  /**
   * Wait for t.
   *
   * @param <T>     the type parameter
   * @param waitFor the wait for
   * @return the t
   * @throws InterruptedException the interrupted exception
   * @throws TimeoutException     the timeout exception
   */
  public static <T> T waitFor(Supplier<T> waitFor) throws InterruptedException {
    return wait(waitFor, RETRY_TIME_FROM_CONFIG, TIMEOUT_FROM_CONFIG);
  }

  /**
   * Wait for t.
   *
   * @param <T>     the type parameter
   * @param <U>     the type parameter
   * @param waitFor the wait for
   * @param arg     the arg
   * @return the t
   * @throws InterruptedException the interrupted exception
   * @throws TimeoutException     the timeout exception
   */
  public static <T, U> T waitFor(Function<U, T> waitFor, U arg) throws InterruptedException {
    return wait(waitFor, RETRY_TIME_FROM_CONFIG, TIMEOUT_FROM_CONFIG, arg);
  }

  /**
   * Wait boolean.
   *
   * @param <T>            the type parameter
   * @param waitForTrue    the wait for true
   * @param retryTime      the retry time
   * @param timeout        the timeout
   * @param throwException the throw exception
   * @param arg            the arg
   * @return the boolean
   * @throws InterruptedException the interrupted exception
   * @throws FunctionException    the function exception
   */
  public static <T> boolean wait(Predicate<T> waitForTrue, long retryTime, long timeout, boolean throwException, T arg)
      throws InterruptedException {
    // Set start time and exception holder
    LocalDateTime start = LocalDateTime.now();
    FunctionException exception = null;

    do {
      try {
        // Clear out old exception
        exception = null;

        // Check if the function returns true
        if (waitForTrue.test(arg)) {
          return true;
        }
      } catch (Exception e) {
        // Save of the exception if we want to throw exceptions
        if (throwException) {
          exception = new FunctionException("Predicate exception caught.", e);
        }
      }

      // Give the system a second before checking if the page is updating
      Thread.sleep(retryTime);
    } while ((ChronoUnit.MILLIS.between(start, LocalDateTime.now())) < timeout);

    // Check if we had an exceptions
    if (throwException && exception != null) {
      throw exception;
    }

    // We timed-out waiting for the function to return true
    return false;
  }

  /**
   * Wait boolean.
   *
   * @param waitForTrue    the wait for true
   * @param retryTime      the retry time
   * @param timeout        the timeout
   * @param throwException the throw exception
   * @return the boolean
   * @throws InterruptedException the interrupted exception
   * @throws FunctionException    the function exception
   */
  public static boolean wait(BooleanSupplier waitForTrue, long retryTime, long timeout, boolean throwException)
      throws InterruptedException {
    // Set start time and exception holder
    LocalDateTime start = LocalDateTime.now();
    FunctionException exception = null;

    do {
      try {
        // Clear out old exception
        exception = null;

        // Check if the function returns true
        if (waitForTrue.getAsBoolean()) {
          return true;
        }
      } catch (Exception e) {
        // Save of the exception if we want to throw exceptions
        if (throwException) {
          exception = new FunctionException("BooleanSupplier exception caught.", e);
        }
      }

      // Give the system a second before checking if the page is updating
      Thread.sleep(retryTime);
    } while (ChronoUnit.MILLIS.between(start, LocalDateTime.now()) < timeout);

    // Check if we had an exceptions
    if (throwException && exception != null) {
      throw exception;
    }

    // We timed-out waiting for the function to return true
    return false;
  }

  /**
   * Wait t.
   *
   * @param <T>       the type parameter
   * @param waitFor   the wait for
   * @param retryTime the retry time
   * @param timeout   the timeout
   * @return the t
   * @throws InterruptedException the interrupted exception
   */
  public static <T> T wait(Supplier<T> waitFor, long retryTime, long timeout) throws InterruptedException {
    // Set start time and exception holder
    LocalDateTime start = LocalDateTime.now();
    Exception exception = new Exception();

    do {
      try {
        T value = waitFor.get();

        if (value != null) {
          return value;
        }

      } catch (Exception e) {
        exception = e;
      }

      // Give the system a second before checking if the page is updating
      Thread.sleep(retryTime);
    } while ((ChronoUnit.MILLIS.between(start, LocalDateTime.now())) < timeout);

    throw new TimeoutException("Timed out waiting for the supplier to return", exception);
  }

  /**
   * Wait t.
   *
   * @param <T>       the type parameter
   * @param <U>       the type parameter
   * @param waitFor   the wait for
   * @param retryTime the retry time
   * @param timeout   the timeout
   * @param arg       the arg
   * @return the t
   * @throws InterruptedException the interrupted exception
   * @throws TimeoutException     the timeout exception
   */
  public static <T, U> T wait(Function<U, T> waitFor, long retryTime, long timeout, U arg) throws InterruptedException {
    // Set start time and exception holder
    LocalDateTime start = LocalDateTime.now();
    Exception exception;

    do {
      try {
        return waitFor.apply(arg);
      } catch (Exception e) {
        exception = e;
      }

      // Give the system a second before checking if the page is updating
      Thread.sleep(retryTime);
    } while ((ChronoUnit.MILLIS.between(start, LocalDateTime.now())) < timeout);

    throw new TimeoutException("Timed out waiting for the function to return", exception);
  }

  private static boolean paramsEqual(Object... param) {
    // For each item
    for (Object item : param) {
      // and each item
      for (Object item2 : param) {
        // Compare each item
        if (!item.equals(item2)) {
          // If any do not match, then they are not equal
          return false;
        }
      }
    }

    // If we get here, then we had no mismatches
    return true;
  }
}
