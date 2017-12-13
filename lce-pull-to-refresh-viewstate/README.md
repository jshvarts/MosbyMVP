# LCE Pull-to-Refresh MVP (with ViewState) using Mosby

Below is the Model-View-Presenter setup:

## Model

The Model is used by the Presenter. Model lives in its own Gradle module: **common** in *domain* and *data* packages. 

For the sake of simplicity, Model code is implemented as Singletons and accessed directly. However, in a production app, the Domain layer components, such as Use Cases or Interactors, would be injected into Presenter and Data layer components such as Repository would be injected into Use Cases/Interactors. 

## View

In this example, the View is an Activity. It extends Mosby's abstract `MvpLceViewStateActivity` which extends `AppCompatActivity`. The View defines custom `ViewState` to be preserved on device configuration changes such as rotation. If ViewState was not important, we could extend `MvpLceActivity` instead.

Instead of extending `MvpLceViewStateActivity`, the View could implement Mosby's `MvpViewStateDelegateCallback` instead. 
 
## Presenter

In this example, the Presenter extends Mosby's `MvpBasePresenter`.

Instead of extending `MvpBasePresenter`, the Presenter could implement `MvpPresenter`.

Alternatively, the Presenter could extend Mosby's `MvpNullObjectBasePresenter` to avoid checks to see if the View is attached. However, this *could* result in some performance degradation as the this Presenter uses Reflection internally. This requires adding a new Gradle dependency `com.hannesdorfmann.mosby3:mvp-nullobject-presenter`.

Alternatively, the Presenter could extend Mosby's `MvpQueuingBasePresenter` to guarantee that the calls to View will be queued up and replayed if and when the View gets attached again. This requires adding a new Gradle dependency `com.hannesdorfmann.mosby3:mvp-queueing-presenter`.

## Gradle

Be sure to add `com.hannesdorfmann.mosby3:mvp-lce` dependency.