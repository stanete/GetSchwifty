# schwiftyandroid 😏

**DISCLAIMER: This repo is currently under an exploratory construction. Please don't take very seriously what you see here. I'll make an official anouncement when everything is production ready.**

An android app written in Kotlin where I test ideas, architectures, patterns and do some experiments.

## [Renderers](https://github.com/pedrovgs/Renderers) & [Kotlin Android Extensions](https://antonioleiva.com/kotlin-android-extensions/)

In your BaseRenderer you only have to implement LayoutContainer and its containerView member.

```kotlin
abstract class BaseRenderer<T> : Renderer<T>(), LayoutContainer {

  override val containerView: View
    get() = rootView
}
```

## [Coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md)

One of the reasons I love and use @kotlin's coroutines in android is because they allow you to write asynchronous code in a synchronous way. The syntax is really beautiful 😍

```kotlin
fun doSomething() = launch {
  async { getWhatever() }.await()
    .fold(::handleFailure, ::handleSuccess)
}
```

But to be able to test them properly you have to provide a CoroutineContext. So you end up injecting some ContextProvider and specifying the CoroutineContext whenever you use launch and async. The syntax isn't that beautiful anymore 😢

```kotlin
fun doSomething() = launch(contextProvider.MAIN) {
  async(contextProvider.IO) { getWhatever() }.await()
    .fold(::handleFailure, ::handleSuccess)
}
```

What comes next is just a question I want to raise... What about having your own launch and async functions and a static object that holds the CoroutineContexts?

```kotlin
/**
 * Static object that holds the [CoroutineContext]s of the coroutines.
 */
object CoroutineContexts {
  val MAIN: CoroutineContext = UI
  val IO: CoroutineContext = CommonPool
}

fun launch(block: suspend CoroutineScope.() -> Unit) = launch(MAIN, block = block)

fun <T> async(block: suspend CoroutineScope.() -> T) = async(IO, block = block)
```

And whenever you want to run and test your code synchronously you set whatever CoroutineContext you want:

```kotlin
@BeforeTest
fun setUp() {
  CoroutineContexts.MAIN = Unconfined
  CoroutineContexts.IO = Unconfined
}
```

Suddenly that beautiful syntax is back! What doesn't smell good is that static object... But the syntax... 🤔. Also... Mutable properties ugh. But immutable properties for that global object would require reflection to be able to mock them during testing... 🙄

Losing the beautiful syntax bothers me quite a lot. But does keeping it justifies this approach?

If you are worried about lifecycles and you are using ViewModel... you can make an extension function for your BaseViewModel and specify a parent Job:

```kotlin
fun BaseViewModel.launch(block: suspend CoroutineScope.() -> Unit) =
  launch(MAIN, parent = job, block = block)
```

Then you will cancel that parent job in the onCleared function of the BaseViewModel:

```kotlin
abstract class BaseViewModel : ViewModel() {

  // The parent of any coroutine.
  internal val job: Job = Job()

  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}
```

I'm still thinking about this. But I just want to use the same syntax for async await in android with Kotlin as in NodeJs with TypeScript.
