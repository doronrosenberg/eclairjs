# EclairJS

The [EclairJS project](http://eclairjs.org) provides JavaScript and Node.js developers with an API for [Apache Spark](http://spark.apache.org/), and enables them to take advantage of Spark's unique data-processing environment that includes streaming, SQL, Machine Learning, and a graph database. Using EclairJS, developers can write applications entirely in JavaScript, some of which will be executed in the local JavaScript environment such as Node.js, and some of which will be executed on Spark. EclairJS is composed of a client component that runs in the local JavaScript environment, and can be simply installed from npm, and server components that can be remote from the client and handle JavaScript in Spark.

## Installation

```bash
$ npm install eclairjs
```

EclairJS requires Node 0.12 or higher and also requires a running instance of [EclairJS Server](https://github.com/EclairJS/eclairjs/tree/master/server).

## Example
EclairJS Client's api mirrors the Spark api.  Here is the simple code example:

```node
var eclairjs = require('eclairjs');

// Create a new, stand alone Spark instance
var spark = new eclairjs();

var sc = new spark.SparkContext("local[*]", "Basic Spark example");

// inject some data
var data = sc.parallelize([1.10, 2.2, 3.3, 4.4]);

// doble the values stored in our Spark dataset.
var doubleddata = data.map(function(num) {
    return num * 2;
});

// retrieve the data and write it out
doubleddata.collect().then(function(results) {
    sc.stop();
    console.log("Results: ", results);
}).catch(function(e) {
    sc.stop();
    console.error(e);
});
```

## Try It
EclairJS provides a Docker container that contains all of its dependencies on 
[Dockerhub](https://hub.docker.com/r/eclairjs/minimal-gateway/).

The Docker image supports the latest released version of EclairJS Client and may not work with `master`.   You can 
simply check out the appropriate branch (` git checkout branch-0.8` for example).

```bash
docker pull eclairjs/minimal-gateway:0.8
docker run -p 8888:8888 eclairjs/minimal-gateway:0.8
```

After retrieving Docker's IP address (`docker-machine ip`), you will need to set two environment variables:

```bash
export JUPYTER_HOST=??.??.??.?? (your docker ip)
export JUPYTER_PORT=8888
```

Now you can run the Word count example (run these commands from the top level directory):

```bash
npm install
node --harmony examples/wordcount/wordcount.js ./data/dream.txt
```

# Deploy

EclairJS provide a convenient Docker container (see [Using the Docker Container](https://github.com/EclairJS/eclairjs/wikis/Using-the-Docker-Container)) which contains Apache Spark 2.0 and the EclairJS Server pre-configured.  You can also manually build and setup your own environment (see [Build and Package](https://github.com/EclairJS/eclairjs/wikis/Build-and-Package)).

## Documentation
* [Developing with EclairJS](https://github.com/EclairJS/eclairjs/wiki/Developing-With-EclairJS-Client)
* [API Docs](https://github.com/EclairJS/eclairjs/wiki/Client-API-Documentation)
* [Wiki](https://github.com/EclairJS/eclairjs/wiki)
* [API Examples](https://github.com/EclairJS/eclairjs/tree/master/examples)
* [Example Applications](https://github.com/EclairJS/eclairjs-examples)

## Community
* [EclairJS Project](http://eclairjs.org/)
* [Google Group](https://groups.google.com/forum/#!forum/eclairjs)
* [Slack](https://eclairjs.slack.com)

## Progress

|Spark Feature    |EclairJS Client Status|
|-----------------|--------------------|
|RDD/Dataset      | Partial Support    |
|SQL/DataFrames   | Partial Support    |
|Streaming        | Partial Support    |
|ml               | Partial Support    |
|mllib            | Partial Support    |
|GraphX           | Unsupported        |

Refer to the [API Documentation](https://github.com/EclairJS/eclairjs/wikis/Client-API-Documentation) for a list of what is currently implemented.  Please note as new APIs are implemented for EclairJS Client they will be added to the master branch.

<!--The apps directory contains a number of relatively full-function example applications that include UIs, Node.js applications using the EclairJS client, plus the EclairJS server and Spark. The examples directory contains smaller code examples that demonstrate how-to take advantage of various Spark capabilities such as streaming, ML, from a JavaScript or a Node.js environment. There is also an examples/server directory that contains smaller code examples that can be run directly on the EclairJS server.-->

Note: This repository supports Apache Spark 2.0, and it supercedes an set of repositories ([EclairJS/eclairjs-node](https://github.com/EclairJS/eclairjs-node) and [EclairJS/eclairjs-nashorn](https://github.com/EclairJS/eclairjs-nashorn)) that supported an earlier version of Spark, namely 1.6. The focus of the EclairJS work going forward will be on supporting Spark 2.0.
