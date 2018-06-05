package com.stanete.schwifty.core

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R>

/**
 * Represents the left side of [Either] class which by convention is a "Failure".
 */
data class Left<out T>(val value: T) : Either<T, Nothing>()

/**
 * Represents the right side of [Either] class which by convention is a "Success".
 */
data class Right<out T>(val value: T) : Either<Nothing, T>()

inline fun <L, R, T> Either<L, R>.fold(
  left: (L) -> T,
  right: (R) -> T
): T =
  when (this) {
    is Left -> left(value)
    is Right -> right(value)
  }

inline fun <L, R, T> Either<L, R>.flatMap(f: (R) -> Either<L, T>): Either<L, T> =
  fold({ this as Left }, f)

inline fun <L, R, T> Either<L, R>.map(f: (R) -> T): Either<L, T> = flatMap { Right(f(it)) }
