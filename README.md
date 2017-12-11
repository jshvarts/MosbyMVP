# MosbyMVP

[![Build Status](https://travis-ci.org/jshvarts/MosbyMVP.svg?branch=master)](https://travis-ci.org/jshvarts/MosbyMVP)

This is a work-in-progress project.

This repo is to explore [Mosby](https://github.com/sockeqwe/mosby/) and [Mosby Conductor plug-in](https://github.com/sockeqwe/mosby-conductor/) implementing MVP pattern.

Both Mosby and Conductor have excellent documentation on GitHub and at [Hannes Dorfmann's blog](http://hannesdorfmann.com/mosby/).
 
Basic definitions: 
* `ViewState` is used to preserve current view data such as loading in progress, data displayed, error displayed, etc. after configuration changes such as device rotation. 
More details at [ViewState](http://hannesdorfmann.com/mosby/viewstate/)
* LCE describes Loading-Content-Error flow typical of many Android apps where loading indicator (such as `ProgressBar`) is followed by displaying content or error. 

## Modules in this repo

*All examples implement MVP pattern*

* Hello World
    * Simple Hello World without handling `ViewState`
* Hello World ViewState
    * Simple Hello World with `ViewState` preserved across configuration changes.
* LCE pull-to-refresh ViewState
    * Todo-style notes screen using RecyclerView with `ViewState` preserved across configuration changes.
* Mosby Conductor Hello World
    * Simple Conductor using Mosby plug-in without handling `ViewState`
* Mosby Conductor Hello World example with ViewState
    * Simple Conductor using Mosby plug-in with `ViewState` preserved across configuration changes.

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
