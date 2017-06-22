[![Build Status](https://travis-ci.org/mfalcier/neo4j-kotlin-procedure-example.svg?branch=master)](https://travis-ci.org/mfalcier/neo4j-kotlin-procedure-example)
[![Dependency Status](https://www.versioneye.com/user/projects/594bce0a6725bd0063d1f2ae/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/594bce0a6725bd0063d1f2ae)
[![GitHub version](https://badge.fury.io/gh/mfalcier%2Fneo4j-kotlin-procedure-example.svg)](https://badge.fury.io/gh/mfalcier%2Fneo4j-kotlin-procedure-example)

# Neo4j Kotlin Procedure Example

This is an example project about Neo4j procedures written in Kotlin.

Here you can find a Procedure `example.connect` and a User Function `example.concat`.

The first one will create a relationship with the given type between two given nodes; the second one will concatenate a list of strings with a given delimiter.

## Installation

1. Download the latest [release](https://github.com/mfalcier/neo4j-kotlin-procedure-example/releases);
2. Put the downloaded jar file into `$NEO4J_HOME/plugins` folder;
3. Start/Restart Neo4j.

## License

Apache License V2, see LICENSE
