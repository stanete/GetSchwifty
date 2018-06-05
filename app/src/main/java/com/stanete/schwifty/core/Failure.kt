package com.stanete.schwifty.core

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
  object NetworkFailure : Failure()
  object ServerFailure : Failure()

  /**
   * Extend this class for feature specific failures.
   */
  abstract class FeatureFailure : Failure()
}
