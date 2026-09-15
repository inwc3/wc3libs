# Warcraft III 3.0 map-format notes

This document records facts established from the corrected 3.0 World Editor
fixture in `src/test/resources`. It is intended as a migration guide for other
repositories. Do not infer a record layout from an empty file, and do not call
opaque byte preservation semantic support.

## Support matrix

| Map member | Observed version | Current handling | Evidence and limitation |
| --- | ---: | --- | --- |
| `war3map.w3i` | 39 (`0x27`) | Structured read/write | Populated, deliberately asymmetric properties, players, and forces are asserted semantically and byte-for-byte. |
| `war3map.doo` | 13 (`0x0D`), sub-version 11 | Structured read/write | A populated 16-doodad fixture establishes the fixed v13 extension and cycles byte-for-byte. Some new fields remain raw-named. |
| `war3mapUnits.doo` | 13 (`0x0D`), sub-versions 9 and 11 observed | Opaque read/write | Simple records expose part of the fixed v13 extension, but populated loot/ability/random subrecords diverge from v8. Bytes are preserved rather than partially decoded. |
| `war3map.w3c` | 3 | Structured read/write | A one-camera fixture gives all 16 camera floats distinct sentinels, proving every base and v3 slot; the populated map also establishes camera type 0/1. Both cycle byte-for-byte. |
| `war3map.w3e` | 12 (`0x0C`) | Structured read/write | Populated terrain cycles byte-for-byte using the v12 tile layout. The read format is retained when writing. |
| `war3map.w3r` | 7 | Structured read/write | A populated region establishes two appended dwords. Both are `1` in the sample, so their individual UI meanings remain raw-named. |
| `war3map.wtg` | `0x80000004` | Opaque read/write | This is a new hierarchical trigger layout, not WTG v4. The supplied file is preserved exactly. |
| `war3map.w3l` | 3 | Structured positional read/write | `W3L!`, two model-path strings, and three option dwords are established by empty and populated Light Editor fixtures. One-change saves are still needed to name their individual roles safely. |
| `war3map.w3grp` | no established signature | Fully opaque read/write | The supplied empty-map member is three zero dwords. No field names are assigned without evidence. |
| `war3map.imp` | 1, entry flag `0x15` | Structured read/write with raw flag preservation | The Light Editor's generated MDL uses a previously unknown import flag. Unknown flag bytes no longer become `null` and crash the writer. |
| `war3map.mmp`, `.shd`, `.wct`, `.wpm` | existing layouts | Structured read/write | The corrected fixtures cycle byte-for-byte. |

`war3map.w3l` and `war3map.w3grp` are also standard archive members now. Map
rebuild code must include them even when an archive's `(listfile)` is absent or
incomplete.

The populated fixtures use the `_v3_filled` suffix in their format-specific
resource directories. Archive metadata and the generated Light Editor MDL are
kept under `wc3data/Map/v3_filled_dump`.

## Other established record extensions

Version 13 doodads retain the legacy placement prefix, require a skin ID, then
add one dword before the flags/life bytes. After the legacy editor ID they add
four dwords. In the populated sample the first is a sequential placement ID and
the remaining three are zero; the pre-flags dword is `0xFFFFFFFF`. The API names
these conservatively as v13 color, ID, and unknown A/B/C until differential
saves prove the UI semantics.

Version 3 camera records append the following values after the legacy near-Z
field and before the camera name:

```text
float32 localPitch
float32 localYaw
float32 localRoll
float32 depthOfFieldDistance
float32 depthOfFieldScale
float32 absoluteZ
cstring cameraName
int32   cameraType       // 0 standard, 1 free in the fixture
```

These names are corroborated by the World Editor's generated JASS
`CameraSetupSetField` calls. The free camera has absolute Z `4096` and camera
type `1`; the standard camera has zero for both. A separate sentinel fixture
sets the UI fields from Target X through Field of View to `1` through `8`, Far
Clipping to `101`, and Near Clipping through Pos Absolute Z to `10` through
`16`. This confirms the complete serialized order, including that the legacy
field previously called `unknown` is Near Clipping (`nearZ`).

Version 7 region records retain the version 5 record and append two int32
values. The generated JASS enables the region's weather effect and adds a
camera blocker, while both serialized values are `1`. A one-change save is
needed before assigning those two meanings to their individual slots.

W3L version 3 consists of `W3L!`, the version dword, two NUL-terminated model
paths, and three option dwords. The populated fixture references two generated
UUID-named MDLs and has option values `0, 1, 0`. This establishes the record
shape but not whether path zero/one or option zero/one/two corresponds to the
Terrain and Unit panes or their enabled/imported state.

## W3I version 39

The version 39 prefix through the legacy terrain-fog color is the expected
modern W3I prefix. The verified continuation is:

```text
int32   terrainFogStyle
int32   drawTerrainFogOverSky
float32 terrainFogLinearStart
float32 terrainFogLinearEnd
float32 terrainFogMaxOpacity
float32 terrainFogHeight
id      globalWeatherId
cstring soundEnvironment
char    lightEnvironment
ubyte[4] waterColor (RGBA)
uint32  scriptLanguage       // 0 JASS, 1 Lua
uint32  supportedGraphics
uint32  gameDataVersion      // 2 = Forsaken Kingdom in the fixture
int32   forcedDefaultCameraZoom
int32   forcedMaximumCameraZoom
int32   forcedMinimumCameraZoom
int32   waterMinOpacity
int32   waterMaxOpacity
int32   waterReflectivity
int32   waterEmissivity
int32   waterEdgeSoftness
int32   waterWavesVertexDisplacement
int32   waterWavesNormalMapStrength
int32   waterOverrideColor
int32   waterEnvironmentMapReflectivity
int32   waterUnknown
```

Two details are easy to get wrong before this continuation:

- The dword immediately after the campaign loading-background index is the
  loading-screen crest race. It is not the alpha-tile minimap color.
- There is one serialized fog-height float in this fixture. Adding separate
  height-start/height-end fields, sky-display fields, or time-of-day fields
  shifts every subsequent value.

The v39 player record order is:

```text
int32 playerNumber
int32 controller
int32 race
int32 hudSkin
int32 fixedStartPosition
cstring name
float32 startX
float32 startY
int32 allyLowPriorityMask
int32 allyHighPriorityMask
int32 enemyLowPriorityMask
int32 enemyHighPriorityMask
```

In particular, `hudSkin` precedes `fixedStartPosition`. The fixture gives each
of six players different controller/race/HUD/fixed combinations so a symmetric
read/write bug cannot hide a swapped slot.

Map-option bits 24 and 25 are, respectively, alpha-tile default minimap color
and dynamic minimap. Preserve unknown flag bits as usual. The forced camera
zoom values are serialized as default, maximum, minimum—not in the UI's visual
default, minimum, maximum order.

Force player masks can contain bits for player slots which are not defined by
the map. When presenting force membership, intersect the mask with the actual
player numbers instead of treating every set bit as a player record.

The editor displayed water emissivity `10` for this map while the corresponding
serialized dword is `0`. The library deliberately preserves the serialized
value; it must not rewrite the file to match an assumed UI transform.

## Testing rules

A byte-identical read/write cycle is necessary but not sufficient. A reader and
writer with the same incorrect slot order can reproduce identical bytes. Each
new structured format therefore needs both:

1. exact byte comparison after a read/write cycle; and
2. semantic assertions populated with asymmetric sentinel values.

For W3I, assert every adjacent field that could plausibly be swapped, all player
controller/race/HUD/fixed tuples, and force membership and flags. Unknown enum
values should retain their raw numeric value for writing even if the typed API
returns an `UNKNOWN` value.

Opaque handling is a corruption-prevention fallback. An opaque instance can be
copied through a map rebuild, but its internal records cannot yet be safely
edited. Never parse a new version by merely aliasing it to an old record layout
unless a populated differential fixture proves that layout.

## Fixtures needed for complete semantic support

Use separate maps or save-before/save-after pairs with only the named feature
changed. Keep all names and numeric values distinctive.

- `war3map.doo` v13: one-change saves for any 3.0-only tint/color and placement
  options, to replace the remaining raw v13 field names with proven semantics.
- `war3mapUnits.doo` v13: one ordinary unit, hero, building, item, resource
  unit, waygate, and random unit/item. Populate skin ID, owner, life/mana,
  acquisition range, hero level/attributes, inventory, modified ability,
  dropped-item sets, custom color, and waygate destination with distinctive
  values. A legacy-editor save of the same placements is especially valuable.
- `war3map.w3r` v7: save two otherwise identical maps where only camera blocker
  is toggled, and another pair where only weather enabled is toggled. This is
  needed to distinguish the two appended dwords without guessing their order.
- `war3map.wtg` `0x80000004`: multiple folders, a disabled folder, comment and
  normal triggers, disabled/initially-off/run-on-init combinations, variables
  including arrays, and nested event/condition/action parameters. Include a
  custom-text trigger if supported. A sequence of one-change saves is much more
  useful than one densely populated file for discovering flags and indexes.
- `war3map.w3l`: save one-change pairs toggling `Enable Custom Light` separately
  for Terrain and Unit, then change/export only one pane's values. This will map
  the two path slots and three option slots to their UI meanings.
- `war3map.w3grp`: at least two groups/folders and nested membership if this is
  the trigger grouping member. Rename, reorder, and nesting-only comparison
  saves will reveal IDs and parent/order fields.

Include the complete extracted archive members and `(listfile)` for every
sample, plus a short text file describing the exact editor actions and values.
Do not normalize or hex-edit the binaries before committing them as fixtures.

## Migration checklist

- Add version 39 W3I fields in the verified order above.
- Put HUD skin before fixed-start-position in v39 player records.
- Add map flags 24 and 25.
- Treat game-data version as numeric and preserve unknown raw values.
- Retain each concrete source format on default write; do not silently emit an
  older version.
- Apply that rule to legacy variants too: for example, a parsed WTG v4 must
  write v4 rather than silently upgrading to the library's v7 default.
- Recognize WTG `0x80000004` as distinct from legacy WTG v4.
- Add the six named v3 camera fields and preserve the numeric camera type.
- Add the v13 doodad extension and the two v7 region dwords without guessing
  still-ambiguous UI meanings.
- Preserve unknown W3GRP, v13 unit-placement, and trigger payloads exactly until
  differential fixtures support structured decoding.
- Preserve unknown import-table flag bytes such as the Light Editor's `0x15`.
- Include `war3map.w3l` and `war3map.w3grp` in standard archive-member lists.
- Pair cycle tests with asymmetric semantic assertions.
