# Happy Ghast Improvements

A Minecraft mod that makes Happy Ghasts quieter and allows you to boost their speed by feeding them sugar or honey.

## Features

**Quieter Happy Ghasts**
Normal ambient sounds occur every 36 seconds instead of 6 seconds (6x quieter). When being ridden, sounds occur every 60 seconds instead of 36 seconds (1.67x quieter).

**Speed Boost System**
Feed Happy Ghasts to give them temporary speed boosts:
- Sugar gives Speed II for 30 seconds, making movement 3x faster
- Honey gives Speed III for 30 seconds, making movement 4x faster

**Feeding Methods**
- Ground feeding: Right-click on a Happy Ghast with sugar or honey
- Mounted feeding: Use sugar/honey while riding a Happy Ghast

**Visual & Audio Feedback**
- Cloud particles appear when feeding
- Different eating sounds for sugar vs honey
- Items are not consumed when feeding in creative mode

## Compatibility

- Minecraft: 1.21.10
- Loaders: Fabric, NeoForge
- Dependencies: Amber library

## How to Use

1. Tame a Happy Ghast (if not already tamed)
2. Feed for speed boost: Hold sugar or honey in your main hand, right-click the ghast, or use the item while riding
3. Enjoy faster travel for 30 seconds

## Technical Details

This mod uses mixins to modify Happy Ghast behavior and platform-specific events for item usage detection with multi-loader architecture for broad compatibility.

## Directory Layout

- `common/` contains code shared between all loaders
- `fabric/`, `neoforge/` contain loader-specific entry points and build logic
- `buildSrc/` holds the Gradle scripts that wire everything together

## License

MIT
