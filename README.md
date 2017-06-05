[![Build Status](https://travis-ci.org/inwc3/wc3libs.svg?branch=master)](https://travis-ci.org/inwc3/wc3libs)
# wc3libs
Java library for general wc3 modding.

Supports parsing and generating various wc3 formats including:

map formats:
  .w3c, .w3i, .shd, .mmp, ...
  objMods: .w3a, .w3u, ...
  slk, profile files, .wts, .fdf, ...

campaign formats: 
  .w3f

mpq extracting/inserting (uses Ladik or https://github.com/inwc3/JMPQ3)

lots of interfaces/abstract classes for the data types etc.

Basically the idea is that you can modify the single files or on a higher layer see it from a map's or campaign's perspective.
