# LCE Pull-to-Refresh MVP (with ViewState) using Mosby and Conductor

Below is the Model-View-Presenter setup:

## Model

The Model is used by the Presenter. Model lives in its own Gradle module: **common** in *domain* and *data* packages. 

For the sake of simplicity, Model code is implemented as Singletons and accessed directly. However, in a production app, the Domain layer components, such as Use Cases or Interactors, would be injected into Presenter and Data layer components such as Repository would be injected into Use Cases/Interactors. 

## View

The View extends Mosby's abstract `MvpLceViewStateController`. The View defines basic `ParcelableListLceViewState`. 

Instead of extending `MvpLceViewStateController`, the View could implement Mosby's `MvpViewStateConductorDelegateCallback`. 
 
## Presenter

In this example, the Presenter extends Mosby's `MvpBasePresenter`.

Instead of extending `MvpBasePresenter`, the Presenter could implement `MvpPresenter`.

Alternatively, the Presenter could extend Mosby's `MvpNullObjectBasePresenter` to avoid checks to see if the View is attached. However, this *could* result in some performance degradation as the this Presenter uses Reflection internally. This requires adding a new Gradle dependency `com.hannesdorfmann.mosby3:mvp-nullobject-presenter`.

Alternatively, the Presenter could extend Mosby's `MvpQueuingBasePresenter` to guarantee that the calls to View will be queued up and replayed if and when the View gets attached again. This requires adding a new Gradle dependency `com.hannesdorfmann.mosby3:mvp-queueing-presenter`.

## LCE
"LCE" describes Loading-Content-Error flow typical of many Android apps where loading indicator (such as `ProgressBar`) is followed by displaying content or error. 

Mosby can take care of managing these values for you. For this to work out-of-the-box, define View IDs in your layout file as follows:
* `@+id/loadingView` for loading indicator view
* `@+id/contentView` for content view
* `@+id/errorView` for error view

## Gradle

Be sure to add dependencies for `com.bluelinelabs:conductor` and `com.hannesdorfmann.mosby3` and `com.hannesdorfmann.mosby3:viewstate-conductor`.
