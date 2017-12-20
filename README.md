# Getting Started with Mosby MVP

[![Build Status](https://travis-ci.org/jshvarts/MosbyMVP.svg?branch=master)](https://travis-ci.org/jshvarts/MosbyMVP)

My Medium article to accompany this repo is here: [Get your MVP right with Mosby](https://proandroiddev.com/get-your-mvp-right-with-mosby-1928eaf9daec)

This repo explores [Mosby](https://github.com/sockeqwe/mosby/) and [Mosby Conductor plugin](https://github.com/sockeqwe/mosby-conductor/) implementing MVP pattern.

Both Mosby and Conductor have excellent documentation on GitHub and at [Hannes Dorfmann's blog](http://hannesdorfmann.com/mosby/).
 
## Why use a library to implement MVP?

Model-View-Presenter (MVP) is hard to implement correctly (bug-free). Handling view state is another big challenge. There are many different implementations of MVP out there.

If there is a library that manages MVP wiring and view state for you so you can concentrate on your app logic and UI, why not check it out? 

## Definitions 
* `ViewState` is used to preserve current view data such as loading in progress, data displayed, error displayed, etc. after configuration changes such as device rotation. 
More details at [ViewState](http://hannesdorfmann.com/mosby/viewstate/)
* LCE describes Loading-Content-Error flow typical of many Android apps where loading indicator (such as `ProgressBar`) is followed by displaying content or error. 
* Delegate Callbacks is a set of interfaces that Mosby uses internally which you are welcome to implement yourself instead of inheriting from base MVP classes. For instance, instead of extending `MvpActivity`, implement `MvpDelegateCallback` or instead of extending `MvpViewStateController` implement `MvpViewStateConductorDelegateCallback` and so forth.
* `MvpNullObjectBasePresenter` is a special kind of Presenter which implements the Null Object Pattern. It makes it unnecessary for you to check if the View is attached prior to calling methods on it. Any calls on the View that is not attached will do nothing (no op); no NullPointerExceptions will be thrown.
* `MvpQueuingBasePresenter` is a special kind of Presenter which lets you queue the calls on the View and replay them in same order once the View becomes available.

## Modules in this repo

*All examples implement MVP pattern*

* **Hello World**
    * Simple Hello World sample. [[link]](https://github.com/jshvarts/MosbyMVP/tree/master/hello-world)
* **Hello World with ViewState**
    * Hello World with `ViewState` preserved across configuration changes. [[link]](https://github.com/jshvarts/MosbyMVP/tree/master/hello-world-viewstate)
* **LCE pull-to-refresh with ViewState**
    * Todo-style notes screen using RecyclerView with `ViewState` preserved across configuration changes. [[link]](https://github.com/jshvarts/MosbyMVP/tree/master/lce-pull-to-refresh-viewstate)
* **Mosby Conductor Hello World**
    * Mosby with Conductor plugin sample. [[link]](https://github.com/jshvarts/MosbyMVP/tree/master/conductor-hello-world) 
* **Mosby Conductor Hello World with ViewState**
    * Mosby with Conductor plugin with `ViewState` preserved across configuration changes. [[link]](https://github.com/jshvarts/MosbyMVP/tree/master/conductor-hello-world-viewstate)
* **Mosby Conductor LCE pull-to-refresh with ViewState**
    * Mosby with Conductor plugin LCE pull-to-refresh with `ViewState` preserved across configuration changes. [[link]](https://github.com/jshvarts/MosbyMVP/tree/master/conductor-lce-pull-to-refresh-viewstate)
* **Mosby Conductor Dagger Sample**
    * Mosby with Conductor plugin used in a small Clean Architecture sample app with [Dagger](https://google.github.io/dagger/) and [Retrofit](http://square.github.io/retrofit/). Uses custom Dagger scopes. [[link]](https://github.com/jshvarts/MosbyMVP/tree/master/conductor-dagger-sample)

## License

    Copyright 2017 James Shvarts

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
