# Introduction #
IMS Protocol Stack provides a selection of interfaces that applications can use for accessing various IMS-services, such as sessions, messaging, presence and XDM-access.

# General structure #

IMS Protocol Stack provides API similar to JSR-218 and JSR-325. Operations are handled in two separate processes. The JSR-api runs in its own process. The IMS functionality, server interactions and containers for resources (e.g. XDM-document content, Presence documents transaction history, etc...) are provided by a working process through AIDL.

The underlying functional parts of the IMS Protocol Stack are divided into the following sub-components: **ims-common**, **ims-parser** and **ims-service**. **ims-common** provides common utilities and generic type definitions. **ims-parser** contains all the parser functionality for supported protocols and content-types. **ims-service** contains the actual IMS-functionality.

The sub-components are separately listed and explained in the following chapters.

# Division to separate components and sub-projects #

## JSR-API ##
The interface to access the functionality is in JSR-API. This is a library project which the client application declares to use. The actual **ims-service** and stack inside it are accessed from JSR-API.

## Test-client ##
The test client provides a reference implementation of a simple IMS  application that utilizes most of the IMS-functionality.

## ims-common ##
**ims-common** is plain Java project which contains common utilities, various type definitions etc.

## ims-parser ##
This java project provides code for parsing different types of messages. The most valuable part of this module is parsers. All parsers have been written using the Ragel language. The Ragel state machine compiler is used for generating source-code from Ragel syntax. These sources are located under ragel\_sources directory. Ragel is available at http://www.complang.org/ragel/

## ims-service ##

**ims-service** is a shared system-wide IMS-service, that runs as a background process. This module contains two main parts, _IMS-stack_ and
_Android integration part_.


## Overall structure illustrated ##

The JSR-API, applications and IMS-services relationships are illustrated in the figure below. IMS-service content is denoted in the green background. The JSR-API is denoted in the yellow background. An additional Custom mediator that is used with unit-tests is also shown in the figure.

  * JSR-like API is implemented as library project
  * Client applications declare `<use-library>` in their manifest file to link against the library
  * System-wide service is accessed/activated through these APIs, using Android's IPC mechanisms
  * Local Mediator handles client multiplexing (clients share a session), whenever this makes sense (streaming from two clients at the same time is not ideal).
  * Android mediator is responsible for acting as remote process mediator on top of the communications stack inside IMS-service. Android mediator also interacts with Android platform regarding task scheduling and network state observations.
  * Custom mediator are used for unit-tests.

![http://the-ims-open-source-project-for-android.googlecode.com/files/overallStructure.jpg](http://the-ims-open-source-project-for-android.googlecode.com/files/overallStructure.jpg)


# IMS-Service structure #

IMS-Service contains two main parts:
  * _IMS-stack_ is the part that provides IMS-stack implementation. This stack was implemented on pure Java and can be used in different environments. Currently only android- and Java-runtime environments are supported.

  * _Android integration_ part is responsible for utilizing the above stack in android environment. This part collects environment specific implementations to manage connections, power managements and some another hardware specific features. This component also provides programming interface that both the client and service share in order to communicate by using inter-process communication(IPC). This inter-process communication API is defined in .aidl files under javax.microedition.ims.android package. Aidl is defined in http://developer.android.com/guide/developing/tools/aidl.html .

![http://the-ims-open-source-project-for-android.googlecode.com/files/imsServiceStructure.png](http://the-ims-open-source-project-for-android.googlecode.com/files/imsServiceStructure.png)

# Example #

The following Figure illustrates  the call flow of an IMS Application sending a Page Message (SIP MESSAGE).

![http://the-ims-open-source-project-for-android.googlecode.com/files/PagemessageSD.jpg](http://the-ims-open-source-project-for-android.googlecode.com/files/PagemessageSD.jpg)

# Package contents #

Following chapters list the packages and brief description of content for **ims-common**, **ims-parser** and **ims-service** packages.

## ims-common ##

  * _javax.microedition.ims.common_ – provides common entities
  * _javax.microedition.ims.common.codec_ - provides utilities for encoding and  decoding the Base64 representation of binary data.
  * _javax.microedition.ims.common.sip_ – provides common sip entities
  * _javax.microedition.ims.common.streamutil_ – provides utilities for streams
  * _javax.microedition.ims.common.util_ – provides utilities for collection, files and etc.

## ims-parser ##
  * _javax.microedition.ims.messages.parser_ – provides parsers for SIP, MSRP, CPIM, SDP and raw body.
  * _javax.microedition.ims.messages.wrappers_ - provides wrappers for SIP, MSRP, SDP messages.


## ims-service ##
  * _javax.microedition.ims.android_ - provides environment specific implementations and programming interface that both the client and service are using
  * _javax.microedition.ims.config_ - provides wrappers for configuration
  * _javax.microedition.ims.core_ – contains common entities for core stuff
  * _javax.microedition.ims.core.auth_ – provides classes which are responsible for handling authentication
  * _javax.microedition.ims.core.connection_ – collects classes which are responsible for tracking connection state
  * _javax.microedition.ims.core.dialog_ – provides classes and interfaces for dialog and related events
  * _javax.microedition.ims.core.dialog_ – provides classes and interfaces for dialog and related events
  * _javax.microedition.ims.core.dispatcher_ – provides classes and interfaces that allow stack to dispatch SIP/MSRP/XDM messages
  * _javax.microedition.ims.core.env_ – provides interfaces which depends on environment: connection manager, schedule service, location service and hardware service. Environment party is responsible for providing implementations for these services.
  * _javax.microedition.ims.core.messagerouter_ – provides classes and interfaces that allows app look-up proper route for messages. Provides routers for SIP and MSRP messages.
  * _javax.microedition.ims.core.msrp_ – collects interfaces and classes that enable an application requiring MSRP-support for session based chats or file transfers.
  * _javax.microedition.ims.core.msrp.filetransfer_ – contains classes and interfaces that enable an application to transfer files
  * _javax.microedition.ims.core.regestry_ - collects interfaces and classes that allow to clients app dynamically change IMS-application properties.
  * _javax.microedition.ims.core.sipservice_ - collects interfaces and classes common to all SIP services
  * _javax.microedition.ims.core.sipservice.invite_ - collects interfaces and classes for SIP session (INVITE) subsystem
  * _javax.microedition.ims.core.sipservice.options_ - collects interfaces and classes for SIP OPTION method subsystem
  * _javax.microedition.ims.core.sipservice.pagemessage_ - collects interfaces and classes for SIP MESSAGE method subsystem
  * _javax.microedition.ims.core.sipservice.publish_ - collects interfaces and classes for SIP publish subsystem
  * _javax.microedition.ims.core.sipservice.refer_ - collects interfaces and classes for SIP reference (REFER) subsystem
  * _javax.microedition.ims.core.sipservice.register_ - collects interfaces and classes for SIP registration subsystem
  * _javax.microedition.ims.core.sipservice.subscribe_ - collects interfaces and classes for SIP subscription subsystem
  * _javax.microedition.ims.core.sipservice.timer_ – provided implementation for timeout timer
  * _javax.microedition.ims.core.transaction_ – collects interfaces and classes common to all transactions
  * _javax.microedition.ims.core.transaction.client_ – collects interfaces and classes that enable an application to proceed with client transactions
  * _javax.microedition.ims.core.transaction.server_ – collects interfaces and classes that enable an application to proceed with server transactions
  * _javax.microedition.ims.core.transaction.state.invite.client_ – provide state machine for client invite transaction
  * _javax.microedition.ims.core.transaction.state.invite.server_ – provide state machine for server invite transaction
  * _javax.microedition.ims.core.transaction.state.noninvite.client_ – provide state machine for client non-invite transaction
  * _javax.microedition.ims.core.transaction.state.noninvite.server_ – provide state machine for server non-invite transaction
  * _javax.microedition.ims.core.xdm_ – collects interfaces and classes that enable an application to access with XDM-services.
  * _javax.microedition.ims.dns_ – collects interfaces and classes that enable an application to resolve dns
  * _javax.microedition.ims.entrypoint_ – collects interfaces and classes that tests some test scenarios
  * _javax.microedition.ims.messages.builder_ – collects interfaces and classes that enable app to build SIP and MSRP-messages
  * _javax.microedition.ims.messages.history_ – collects interfaces and classes that enable app to track message history within dialog
  * _javax.microedition.ims.transport_ – collects interfaces and classes common to transport
  * _javax.microedition.ims.transport.impl_ – collects interfaces and classes that provide default transport implementation
  * _javax.microedition.ims.transport.messagerouter_ – collects common interfaces for message routing
  * _javax.microedition.ims.transport.sigcomp_ – collects interfaces and classes that allow an application to compress/decompress messages. This package is speculative and in place for housing future implementations for sigcomp.
  * _javax.microedition.ims.util_ – provides common utilities classes