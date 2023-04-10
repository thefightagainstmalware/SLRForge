# This project is discontinued, use [NoSession](https://github.com/thefightagainstmalware/NoSession) instead 
NoSession accomplishes what SLRForge was trying to do, but in the proper way without cutting corners

# SLR Forge
SLR (short for Slightly Less Risky) Forge is a fork of 1.8.9 Forge that tries to reduce the attack surface for getting your information stolen.<br>
It works with (almost) all existing 1.8.9 Forge mods!

TL;DR: Don't use (for now)

## What protections we have right now
- Token methods return an empty string
- Token is hidden in a class that can't be accessed except by the Session
- Token is hidden from the tweakers
- SecurityManager can't be replaced

## Problems that still exist
- No protection against unauthorized file access (Discord, etc. can still be stolen)
- No protection against native loaders (Probably a complete bypass)
- No protection against getting the token from the launch args
