# SLR Forge
SLR (short for Slightly Less Risky) Forge is a fork of 1.8.9 Forge that tries to reduce the attack surface for getting your information stolen.<br>
It works with (almost) all existing 1.8.9 Forge mods!

TL;DR: Don't use (for now)

## What protections we have right now
- Token methods return an empty string
- SecurityManager can't be replaced

## Problems that still exist
- A mixin could access the session token
- No protection against unauthorized file access (Discord, etc. can still be stolen)
- No protection against native loaders (Probably a complete bypass)
- Reflection can be used to get the session field
