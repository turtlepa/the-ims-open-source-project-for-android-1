# Stack configurations in general #

IMS Protocol Stack configurations related to accounts such as server addresses, user credentials and other IMS parameters are done using a  separate SIP Settings application. When re-installing the ims-service.apk, the values are reset to the default settings -
settings that are delivered with the apk; settings are not stored on device between uninstall/install, and the settings can't be exported.

It is also possible to pre-fill URI-dropdowns for Invite-, Mes-, MSRP-view. To add contacts to the selection-list, place a text-file named "contact.ini" to the root of sdcard (/sdcard/contact.ini). The format of the file is following:
```
#comment-line
sip:111111@domain.com
sip:22222222222@otherdomain.com

#other-comment
sip:me@home.com
```

# Settings in detail #

## Proxy server ##
  1. _Host or Ip_ -the proxy-server address to which the sip-connections are targetted.
  1. _Port_ -the port used for sip connections (defaults to 5060)

## Registrar Settings ##
  1. _User schema type_ -the prefix for the account/usernames/sip-uris (defaults to sip)
  1. _Username_ -user-Info field for sip-uris
  1. _User Domain_ -domain field for the registering users sip-uri
  1. _Registrar host or IP_ -registrar server's name (in practice the name that is in the Request-URI in REGISTER request)
  1. _Registrar port_ -registrar server port number
  1. _Expire time_ -expiration time for register-messages
  1. _Preferred User identity_ -can be used to set separate P-Preferred-Identity header
  1. _Protocol type_ -transport selection (UDP/TCP/TLS)
  1. _Global IP Discovery_ -should the stack try to find out the real external ip-address if running behind NAT

## Authentication ##
  1. _Auth type_ - Offers selection between Digest and AKA).If aka is selected, the Registrar settings for the username, domain etc. are overlooked, and the isim-application will provide the user-account that will be used. NOTICE: AKA-support is not available in the currently open sourced version.
  1. _User schema_ -schema for the username-field in Authentication header
  1. _User name_ -userinfo for the username-field in Authentication header
  1. _User domain_ -domain for the username-field in Authentication header
  1. _Password_ -the digest password
  1. _Realm_ -value for the realm field in the Authentication header
  1. _Auth Forses types_ -select the inital empty Authentication header that will be placed for first Register request; UAS = Authorization header, PROXY = Proxy-Authorization header


## XDM settings ##
  1. _XCAP root_ -the server address of the xcap-root, this usually contains all the possible directories up to the application selector (rls-service,pres-rules, resource-list).
  1. _XUI name_ -username segment of the xcap-paths (the IMS-test client can be run so that registration is done with different user and also to different provider even that what XDM is used and with which user-credentials)
  1. _Auth name_ -http digest authentication username used for authenticating the XDM-connection
  1. _XUI password_ -password for the xdm-services
  1. _Send full documents_ -IMS Stack can either send node-level modifications to the XDM-server or always send full documents containing the local changes.

## MSRP Settings ##
  1. _Msrp local port_ -the proposal for the local port used for msrp sessions
  1. _Msrp chunk Size_ -chunking limitter for the filetransfers; how big file chunks are sent in one MSRP-transaction

## Invite refresh settings ##
  1. _Use invite refresh_ -enable invite refreshing functionality enabling this
  1. _Refresher_ -selection of the session refresher, UAC or UAS
  1. _Session expires time_ -proposed session expiration value
  1. _Min session expires time_ -proposed minimun expiration time for the session

## User rport ##
-similar to Global IP detection; if enabled tries to use rport to
discover which port is used when running behind NAT

## Resource Reservation ##
-when enabled sets Require: 100rel header to outgoing VoIP INVITEs
and for incoming such INVITEs responds with 183 Provisional response
with SDP.

## Use simultaneous connections ##
-enable simultaneous alternative transport (aside the selected UDP or
TCP), with TLS disable this.

## local port ##
-local port for sip traffic

## Max forwards ##
-set the max forwards value for the requests

## User agent ##
-User agent string in the sip-requests

## Supported features ##
-set supported header values to contain none, one or more of the
following: 100rel, eventlist and path

## Payload type ##
-selection of the DTMF-tone type, inband (voice in RTP) or outband
(RTP telephony events).Note: Might require additional changed behaviour in the clients on providing the SDP to the stack to enable one or the
other.