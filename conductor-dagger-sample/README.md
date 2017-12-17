# Sample MVP Dagger app (with ViewState) using Mosby and Conductor

This sample app has 2 screens and packages are organized by feature:
1) Looking up a list of Github repositories for a given username
2) Getting more detail about a Github repository

Industry-standard Retrofit is used to query Github API and Dagger with custom scopes manages DI.

Below is the Model-View-Presenter setup:

## Model

Our Model is in the **domain** package. 

`GithubDataStore` uses `GithubService` to populate `GithubRepo` POJOs.

`GithubDataStore` is injected into Use Cases which are in turn injected into Presenters via Dagger 2.

## View

Conductor is used to manage Views. We have created a Base Controller View `BaseViewController` which extends Mosby's abstract `MvpViewStateController`. The Controller allows to manage ViewState.

Instead of extending `MvpViewStateController`, the View could implement Mosby's `MvpViewStateConductorDelegateCallback`. 
 
## Presenter

Our Presenters extend Mosby's `MvpBasePresenter`.

Instead of extending `MvpBasePresenter`, the Presenters could implement `MvpPresenter`.

Alternatively, the Presenters could extend Mosby's `MvpNullObjectBasePresenter` to avoid checks to see if the View is attached. However, this *could* result in some performance degradation as the this Presenter uses Reflection internally. This requires adding a new Gradle dependency `com.hannesdorfmann.mosby3:mvp-nullobject-presenter`.

Alternatively, the Presenters could extend Mosby's `MvpQueuingBasePresenter` to guarantee that the calls to View will be queued up and replayed if and when the View gets attached again. This requires adding a new Gradle dependency `com.hannesdorfmann.mosby3:mvp-queueing-presenter`.
