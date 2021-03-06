# Introduction
* [IMS Protocol Stack structure](https://github.com/hdost/the-ims-open-source-project-for-android/wiki/IMS-Protocol-Stack-for-Android) - Gives a short  description of IMS Protocol Stack Architecture    
* [Supported specifications](https://github.com/hdost/the-ims-open-source-project-for-android/wiki/Supported-Standard-Specifications) - A list of supported standard specifications

# How to
* [Settings Tool Guide](https://github.com/hdost/the-ims-open-source-project-for-android/wiki/Sip-Settings---User-Guide) - Guide about Sip Settings parameters
* [Test Client Instructions](https://github.com/hdost/the-ims-open-source-project-for-android/wiki/Test-Client-Instructions) - Short list of features about test client

# Development
* [Build the Source](https://github.com/hdost/the-ims-open-source-project-for-android/wiki/Build-IMS-Stack-and-Testclient-From-Source)


## JSR References
* https://jcp.org/en/jsr/detail?id=281
* https://jcp.org/en/jsr/detail?id=325

## Related Google Group
* https://groups.google.com/forum/#!forum/discuss-the-ims-open-source-project-for-android

# License
This project is licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0)
T-Mobile USA and Movial are releasing this IMS source code to the Android developer community to provide the opportunity to leverage our best practices. This code has been deployed with T-Mobile’s WiFi Calling feature on the Samsung Galaxy S II, HTC Amaze 4G, myTouch and myTouch Q. We welcome the contribution and discussion of the Android community.

# Solution Design Principles
The IMS Stack and IMS API have been designed to provide the community with a tool that allows development of IMS applications. JSR 281 and JSR 325 were the starting point for the API design. In order to be suitable for Android development some functionality was omitted and some new functionality was defined. The stack API is separated into packages hosting core services, messaging, presence, xdm and media. All these are in their own logical java packages. Internally the JSR-API is running in its own process. The communication between the JSR-API-layer and the IMS-service running on separate process is using AIDL (Android Interface Definition Language). All parser functionality is generated using Ragel state machine compiler. The generated code is based on the BNF notation used in RFCs that are relevant for the supported features.



# Highlights
* TCP and UDP support for signaling
* RTP/SRTP support for media
* Proxy-CSCF discovery using DNS (NAPTR + SRV + A-record)
* Initial Registration using SIP Digest with and without TLS
* Initial Registration using IMS AKA with ISIM (Note: OEM support is needed in order to access the ISIM application on UICC)
* Addressing: SIP URI, tel-URI and SIP URI associated with a tel-URI (e.g. sip:+12065550055@example.com;user=phone)
* MMTEL - Evolved telephony services (voice, video, supplementary services)
* MSRP for media transfer
* Presence & XCAP document management

# Getting involved
We believe this project is a benefit for the anyone in Android community who is interested in IMS hence we welcome any contribution (discussions, code, documentation, …)

Send emails to http://groups.google.com/group/discuss-the-ims-open-source-project-for-android
