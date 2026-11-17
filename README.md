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

`ObjMerger` moves a map's object modifications (`war3map.w3u`, `.w3t`, `.w3b`,
`.w3a`, `.w3h`, `.w3q`) into SLK tables and profile `.txt` files, which the game
loads considerably faster than it applies object-modification files.

```java
ObjMerger.ofMap(mapFile).merge().writeToMap(mapFile);
```

A map's SLK table replaces the game's table of the same name, so the merger
starts from the game's own data and writes the tables back whole. That data
ships with the library: `BundledGameData` is a snapshot of Warcraft III 3.0.1,
combined the way the game combines it: base data, then the `custom_v1` or
`melee_v0` balance set the map's `war3map.w3i` selects, then the enUS strings.
A balance set ships only what it changes (about half of `AbilityData.slk`), so
tables are combined cell by cell and profile files key by key
(`MergedGameData`); taking its files whole would drop hundreds of stock
objects. Tables and profile files the map already ships replace the game's,
as in the game.

Where the game's tables never leave a column empty, they spell "nothing" with a
placeholder (`_` or `-`), and the game relies on it: an empty `auto` in
`UnitAbilities.slk` crashes it on load. An emptied value is written with the
column's placeholder. Profile files in 3.x repeat some table columns
(`UnitSkin.txt` holds `UnitUI.slk`'s `special`, `campaign`, `teamColor`, ...);
a custom object gets the value in both.

To merge object modifications held in memory instead, as a map compiler would:

```java
ObjMerger merger = new ObjMerger(BundledGameData.forDataSet(w3i.getGameDataSet(), w3i.getFlag(MapFlag.MELEE_MAP)));
merger.add(w3u);
merger.add(w3a);

ObjMerger.Result result = merger.merge();
result.getFiles();        // game path -> bytes to put into the map
result.getRemovedFiles(); // object-modification files left empty
result.getReport();       // what moved, and what stayed and why
```

The result is equivalent to the input; the test suite compares every affected
field of every object before and after, across real maps and randomised input.
What cannot be expressed in the tables -- ability levels above four (the game
reads four levels from a map's tables, though its own have columns for six;
profile values such as tooltips have no such limit), `TRIGSTR_` references,
values the formats cannot quote,
the profile changes of modified *base* objects and their table changes a
profile file repeats, doodads and `war3mapSkin.*` files -- stays in a residual
object-modification file the game still applies on top. Strings a custom object
inherits from its base object are baked in from the snapshot, so in English.

One difference is the game's own: its table reader can land a decimal one float
step off. It reads `1.5` as 1.5000001 -- in Blizzard's tables too -- while an
object modification holds 1.5 exactly. A real moved into a table can therefore
differ from the original in its last bit, which matters only to code that
compares reals for exact equality.

The snapshot is regenerated from an install with
[casc-ts](https://github.com/wurstscript/casc-ts) checked out next to this
repository:

```bash
node tools/gamedata/extract-gamedata.mjs
```

# Contributing

Feel free to contribute fixes or additions or make tickets on the issue tracker to inform us. Any kind of documentation or specification is also welcome.

