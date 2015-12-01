SDK COMPOSE
===========

This project includes the code for the COMPOSE SDK. See: http://www.compose-project.eu/

This component is a Java Library (built by maven) to get access and control of different COMPOSE APIs. The aim is to provide a SDK for developers to create applications that could run on sensors, devices and gateways and connect them to COMPOSE platform. 

# APIs included

* IDM API: http://docs.composeidmusers.apiary.io
* LCM API: http://docs.composelifecycle.apiary.io
* LCM-SDK API: http://docs.composesdk.apiary.io
* ServIoTicy API: http://docs.servioticy.com

You can find this APIs clients on:
	
	package org.bdigital.compose.sdk.client

# Service Object Models

This project also includes Java POJOs for Service Objects representation in JSON. This object are the main model used on COMPOSE project to define sensors and devices.  

You can find this models on:
	
	package org.bdigital.compose.sdk.model

## Quick Start

### Getting the code

To get the code run: 

	$ git clone https://github.com/compose-eu/compose-sdk
	$ cd compose-sdk
	
### Build the Library

	$ mvn clean install
	
### Import dependency

	<dependency>
		<groupId>org.bdigital.compose</groupId>
		<artifactId>compose-sdk</artifactId>
		<version>1.0.0</version>
	</dependency>

