@Grab('com.netflix.rxjava:rxjava-groovy:0.20.6')

import rx.*
import rx.subscriptions.*
import rx.schedulers.*

// Based on http://www.grahamlea.com/2014/07/rxjava-threading-examples/

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

Observable.metaClass.debug = { message ->
	delegate.lift {
		
	}
}

maxNumber = 5
generator = Observable.from(1..maxNumber)
levelUp = 0
shiftedUp = generator.subscribeOn(Schedulers.io()).map {
  println "[${Thread.currentThread().name}] Shifted Up:    ${"".padRight(4*levelUp++, '.')} ${it + 10}"
  it + 10
}
levelDown = 0
shiftedDown = shiftedUp.map {
  println "[${Thread.currentThread().name}] Shifted Down:  ${"".padRight(4*levelDown++, '.')} ${it - 10}"
  it - 10
}
levelSubscribe = 0
shiftedDown.subscribe {
  println "[${Thread.currentThread().name}] Received:      ${"".padRight(4*levelSubscribe++, '.')} ${it}"
  it
}
null


