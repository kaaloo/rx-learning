@Grab('com.netflix.rxjava:rxjava-groovy:0.20.6')

import rx.*
import rx.subscriptions.*
import rx.schedulers.*

import java.util.concurrent.*

// Hello using Observable.from with array
def hello1(String... names) {
  Observable.from(names)
  .subscribe {
    println "Hello " + it + "!"
  }
}

// Hello using Observable.create
def hello2(String... names) {
  Observable.create { subscriber ->
    try {
      names.each {
        if (!subscriber.isUnsubscribed()) {
          subscriber.onNext(it)
        }
      }
      if (!subscriber.isUnsubscribed()) {
        subscriber.onCompleted()
      }
    } catch (Throwable t) {
      if (!subscriber.isUnsubscribed()) {
        subscriber.onError(t)
      }
    }
  }.subscribe {
    println "Hello " + it + "!"
  }
}

// Hello using Observable.just
def hello3(String... names) {
  Observable.just(names)
  .subscribe {
    it.each { val ->
      println "Hello " + val + "!"
    }
  }
}

// Hello using Observable.from with iterator
def hello4(String... names) {
  Observable.from(names.iterator())
  .subscribe {
    it.each { val ->
      println "Hello " + val + "!"
    }
  }
}

// Hello using Observable.from with future (blocking)
def hello5(String... names) {
  Observable.from(new FutureTask ({
    names
  }), 3, TimeUnit.SECONDS).subscribe {
    println "Hello " + it + "!"
  }
}

// Hello using Observable.repeat
def hello6(String... names) {
  Observable.from(names)
  .repeat(3)
  .subscribe {
    println "Hello " + it + "!"
  }
}

def hello7(String... names) {
  Observable.defer {
    Observable.from(names)
  }.subscribe {
    println "Hello " + it + "!"
  }
}