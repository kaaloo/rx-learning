@Grab('com.netflix.rxjava:rxjava-groovy:0.20.6')

import rx.*
import rx.subscriptions.*

def fetchWikipediaArticleAsynchronouslyWithErrorHandling(String... wikipediaArticleNames) {
    return Observable.create({ Observer<String> observer ->
        Thread.start {
            try {
                for(articleName in wikipediaArticleNames) {
                    observer.onNext(new URL("http://en.wikipedia.org/wiki/"+articleName).getText());
                }
                observer.onCompleted();
            } catch(Exception e) {
                observer.onError(e);
            }
        }
            return Subscriptions.empty();
    });
}

fetchWikipediaArticleAsynchronouslyWithErrorHandling("NonExistentTitle", "Tiger", "Elephant")
    .subscribe(
        { println "--- Article ---\n" + it.substring(0, 125)}, 
        { println "--- Error ---\n" + it.getMessage()})