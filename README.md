# wc3libs
general java wc3libs

What do we have here? Rudimentary stuff for reading/writing the various wc3 formats and wc3 modding in general:

map formats:
  .w3c, .w3i, .shd, .mmp, ...
  objMods: .w3a, .w3u, ...
  slk, profile files, .wts, .fdf, ...

campaign formats: 
  .w3f

mpq extracting/inserting (uses Ladik or jmpq3)

lots of interfaces/abstract classes for the data types etc.

Basically the idea is that you can modify the single files or on a higher layer see it from a map's or campaign's perspective.
