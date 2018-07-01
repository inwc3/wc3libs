[![Build Status](https://travis-ci.org/inwc3/wc3libs.svg?branch=master)](https://travis-ci.org/inwc3/wc3libs) [![Jit](https://jitpack.io/v/inwc3/wc3libs.svg)](https://jitpack.io/#inwc3/wc3libs) [![codebeat badge](https://codebeat.co/badges/f622675c-0de7-4dd7-9936-94b1a78a73c0)](https://codebeat.co/projects/github-com-inwc3-wc3libs-master) [![codecov](https://codecov.io/gh/inwc3/wc3libs/branch/master/graph/badge.svg)](https://codecov.io/gh/inwc3/wc3libs)


# Wc3libs

Pure Java library for general wc3 modding and tool development.
With Wc3libs we aim to offer a feature-complete, easy, plug & play solution for jvm applications to access, modify and output anz kind of Warcraft III specific game data.

# Usage

Use this library in your maven/gradle project using [JitPack](https://jitpack.io/#inwc3/wc3libs).

Gradle Example:
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
  
dependencies {
  implementation 'com.github.inwc3:wc3libs:-SNAPSHOT'
}
```

# Feature Overview

* **Map Data formats:** .w3c, .w3i, .shd, .mmp, .wpm, header and footer
* **Asset Data formats:** .mdx, .mdl, .blp
* **Object Data formats:** .w3a, .w3u, .w3b
* **Plain Text formats:** .slk, .txt, .wts, .fdf
* **Campaign Data formats:** .w3f

_mpq editing_ via [JMPQ](https://github.com/inwc3/JMPQ3)

## Contributing

Feel free to contribute fixes or additions or make tickets on the issue tracker to inform us. Any kind of documentation or specification is also welcome.

