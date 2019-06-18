[![CircleCI](https://circleci.com/gh/inwc3/wc3libs.svg?style=svg)](https://circleci.com/gh/inwc3/wc3libs) [![Jit](https://jitpack.io/v/inwc3/wc3libs.svg)](https://jitpack.io/#inwc3/wc3libs) [![codebeat badge](https://codebeat.co/badges/f622675c-0de7-4dd7-9936-94b1a78a73c0)](https://codebeat.co/projects/github-com-inwc3-wc3libs-master) [![codecov](https://codecov.io/gh/inwc3/wc3libs/branch/master/graph/badge.svg)](https://codecov.io/gh/inwc3/wc3libs)


# Wc3libs

Pure Java library for general wc3 modding and tool development.
With Wc3libs we aim to offer a feature-complete, easy, plug & play solution for jvm applications to access, modify and output any kind of Warcraft III specific game data.

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

## Reading maps

This project uses another one of our projects, [JMPQ](https://github.com/inwc3/JMPQ3) to read and write mpq archives, ie warcraft 3 maps.

## Data Formats

Wc3libs supports read and write operations for the following data types:

* **Map Data formats:** .w3c, .w3i, .w3r, .w3s, .w3v, .shd, .mmp, .wpm, .doo, .imp, header and footer
* **Asset Data formats:** .mdx, .mdl, .blp, .jpg, .tga
* **Object Data formats:** .w3a, .w3b, .w3d, .w3h, .w3q, .w3t, .w3u 
* **Plain Text formats:** .slk, profile .txt, .fdf
* **Trigger Data formats:** .wct, .wtg, .j, .wts
* **Campaign Data formats:** .w3f

## Object Data Transformation

Wc3libs can transform object definitions between the binary objectmod format and raw slk/txt formats.
The raw file handling also comes with inbuilt cleaning to prevent bloating maps.

# Contributing

Feel free to contribute fixes or additions or make tickets on the issue tracker to inform us. Any kind of documentation or specification is also welcome.

