@Grab('com.netflix.rxjava:rxjava-groovy:0.20.6')

import rx.*
import rx.functions.*
import rx.subscriptions.*
import rx.schedulers.*

// Based on http://www.grahamlea.com/2014/07/rxjava-threading-examples/

// Pimp Observable with debug
// See http://groovy.codehaus.org/Pimp+my+Library+Pattern

class EnhancedObservable {
  static indent = 0
  static Observable debug(Observable self, String message) {
    println "self : ${self}"
    println "message: ${message}"
    self.doOnNext(new Action1() {
      void call(value) {
        // println "value: ${value}"
      }
    })
  }
}

// Single thread

/*
maxNumber = 5
generator = Observable.from(1..maxNumber)
shiftedUp = generator.map {
  println "Shifted Up: ${it + 10}"
  it + 10
}
shiftedDown = shiftedUp.map {
  println "Shifted Down : ${it - 10}"
  it - 10
}
shiftedDown.subscribe {
  println "Received: ${it}"
  it
}
*/

// IOScheduler

use (EnhancedObservable) {
  maxNumber = 5
  generator = Observable.from(1..maxNumber).debug("Generated")
  // shiftedUp = generator.subscribeOn(Schedulers.io()).map { it + 10 }.debug("Shifted Up")
  // shiftedDown = shiftedUp.map { it - 10 }.debug("Shifted Down")
  // shiftedDown.subscribe { it }.debug("Received")
}


