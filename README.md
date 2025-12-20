# Happy Ghast Improvements

A Minecraft mod that makes Happy Ghasts quieter and allows you to boost their speed by feeding them different items.

## Features

**Quieter Happy Ghasts**
Normal ambient sounds occur every 36 seconds instead of 6 seconds (6x quieter). When being ridden, sounds occur every 60 seconds instead of 36 seconds (1.67x quieter).

**Speed Boost System**
Feed Happy Ghasts to give them temporary speed boosts with different power levels:
- Sugar gives Speed II for 20 seconds (2x speed boost)
- Honey gives Speed III for 20 seconds (2.5x speed boost)
- Dragon's Breath gives Speed IV for 20 seconds (3x speed boost)

**Enhanced Riding Experience**
- Increased reach distance of +1 block when riding a Happy Ghast
- Sound effect plays when speed effect expires to let you know it's time to feed your happy ghast again

**Feeding Methods**
- Ground feeding: Right-click on a Happy Ghast with any feedable item
- Mounted feeding: Use items while riding a Happy Ghast

**Visual & Audio Feedback**
- Cloud particles appear when feeding
- Audio feedback when feeding
- Items are not consumed when feeding in creative mode

## Compatibility

- Minecraft: 1.21.10
- Loaders: Fabric, NeoForge
- Dependencies: Amber

## How to Use

1. Tame a Happy Ghast (if not already tamed)
2. Feed for speed boost: Right-click the ghast or use the item while riding
3. Choose your fuel: Sugar (moderate), Honey (fast), Dragon's Breath (fastest)
4. Enjoy enhanced mobility for 20 seconds

## Technical Details

This mod uses mixins to modify Happy Ghast behavior and platform-specific events for item usage detection with multi-loader architecture for broad compatibility.

## Directory Layout

- `common/` contains code shared between all loaders
- `fabric/`, `neoforge/` contain loader-specific entry points and build logic
- `buildSrc/` holds the Gradle scripts that wire everything together

## License

MIT
