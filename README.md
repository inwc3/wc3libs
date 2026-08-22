[![CircleCI](https://circleci.com/gh/inwc3/wc3libs.svg?style=svg)](https://circleci.com/gh/inwc3/wc3libs) [![Jit](https://jitpack.io/v/inwc3/wc3libs.svg)](https://jitpack.io/#inwc3/wc3libs) [![codecov](https://codecov.io/gh/inwc3/wc3libs/branch/master/graph/badge.svg)](https://codecov.io/gh/inwc3/wc3libs)


# Wc3libs

Pure Java library for general wc3 modding and tool development.
With Wc3libs we aim to offer a feature-complete, easy, plug & play solution for jvm applications to access, modify and output any kind of Warcraft III specific game data.

# Requirements

| wc3libs | Java |
|---|---|
| 2.0.0 and newer | 25 |
| 1.2.x | 17 |

The Java 25 baseline comes from [JMPQ3](https://github.com/inwc3/JMPQ3) 2.0,
which wc3libs uses to read and write MPQ archives. What took JMPQ3 past Java 21
was adopting the foreign function and memory API in its read layer: an archive
is mapped into an `Arena`-scoped `MemorySegment` rather than a
`MappedByteBuffer`, so the mapping is released the moment the archive is closed
instead of whenever the garbage collector gets round to it. That is what makes
rebuilding a map in place work on Windows, where the old mapping kept the file
locked long after `close()` had returned.

Nothing here calls a restricted method, so no `--enable-native-access` flag is
needed to read archives.

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

`JMpqPort` is the entry point. Reading never modifies the archive, and writing
happens when the port is committed rather than as a side effect of closing
anything.

An archive addresses its files by a hash of their name, and the names themselves
live in an optional `(listfile)` that map protectors strip. Such a map still
plays, because the game asks for its files by name, but it cannot list itself --
so rebuilding it from its own listing would drop everything the listing does not
mention. `War3MapFiles` holds the paths the game asks for, and both `listFiles`
and the rebuild consult it, which recovers the terrain, objects and script of an
unlisted map. Imported assets, whose names are arbitrary, cannot be recovered
this way.

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

