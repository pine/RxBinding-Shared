# RxBinding-Shared [![Bintray](https://img.shields.io/bintray/v/pinemz/maven/rxbinding-shared-kotlin.svg?style=flat-square)](https://bintray.com/pinemz/maven/rxbinding-shared-kotlin/view) [![Build Status](https://img.shields.io/travis/pine613/RxBinding-Shared/master.svg?style=flat-square)](https://travis-ci.org/pine613/RxBinding-Shared)

[RxBinding](https://github.com/JakeWharton/RxBinding) of stream shareable version

## Getting Started
Please type it in your build.gradle file.

```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'moe.pine.rxbinding.shared:rxbinding-shared-kotlin:0.0.2'
}
```

## Usage

```kotlin
val view = this.findViewById(R.id.foobarbaz)

view.sharedClicks().subscribe {
    // This line will be executed.
    // You also can use it `clicks` method.
    println("clicked #1")
}

view.sharedClicks().subscribe {
    // This line also will be executed if you use `sharedClicks` method instead of `clicks` method.
    println("clicked #2")
}
```

## Methods
RxBinding-Shared has shared version methods in [RxBinding](https://github.com/JakeWharton/RxBinding). The methods have `shared` prefix (ex. sharedClicks, sharedTouchs) as Kotlin extensions.

## Generate

```
$ git submodule update --init
$ ./gradlew generate
```

## Test

```
$ ./gradlew clean check
```

## Release

```
$ ./gradlew clean assemble bintrayUpload
```

## License

```
Copyright (C) 2015 Jake Wharton
Copyright (C) 2016 Pine Mizune

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
