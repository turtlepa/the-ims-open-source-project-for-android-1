# Running & Features #

---


The test client is simply done to test specific features in the IMS Protocol Stack without focusing on fancy UI features that Android might offer, and it is not intended to be a basis for any actual client
application. It's sole purpose is to trigger various functionality
that the JSR-compatible API of the IMS Stack project offers.  It is also to provide the any developer interested in writing IMS applications reference code.

IMS Protocol Stacks Test Client is started by clicking the IMS Test Client icon.

# Sip register #
After the test client is started, pressing "Connect" intiates a SIP REGISTER to the configured server with configured account.

After that the stack keeps re-reshing the registration until the "Disconnect" is pressed.

After successful registration, the test client will show the main-view filled with buttons enabling various activites that can be tested.

Accessing the initial view, which has the 'Disconnect"-button, press Back-button (Android hard-key).

# VoIP #
The main-view offers a selection to make voice-calls; this call can be
target to a URI inputted to the "Remote-Identity"-field or the target
URI can be selected from the list. Media selection is done with the
Initial media checkboxes.

After that the call view is displayed, first indicating the states of
the call establishment, later then offering possible actions to be done during the call, such as  keypad to produce DTMF-events to the
call, place the call on hold and end the call.

# Call Transfer #
This view can be used to trigger out-of-dialog REFER-requets (with
method=INVITE)

# Subscribe 281 (as in JSR-281) #
JSR-281-like subscription functionality is accessible with the view
accessible with the 'Subscribe 281'-button.

Subscribe target and event-type selection should be done before the
'Subscr' is clicked. Subscription dialog is maintained until the
'UnSubscr'-button is clicked. 'Close'-button leads back to the
main-view.

# Presence Hard #
To create XDM operation to pidf-manipulation document, pressing
"Presence Hard" opens a view that allows a selection of status-icon
(presence/person/status-icon -element in the XML-document) and a text-field for the Tagline
(presence/person/note -element in the XML-document). After the
selection, the "Update"-button triggers the XCAP-PUT.

# 'Phbk' #
Contact list management (Resource-Lists)
Currently the Test Client can manage the content of two
resource-lists, the phbk and allow\_icontag.

To access this feature, press "phbk"-button. The view defaults to
handling /phbk-document. Document can be selected with "Doc"-button.

Contacts can be added to the resource-list documents with "Add
Contact".

If there is contact in the active document, the URIs and  display-names are
listed. A context menu pops up when press & hold
over a contact. Edit or remove contact operations are available
through this context menu.

# Presence jsr325 #

Sends SIP PUBLISH with pidf+xml content matching the originally agreed
to indicate presence availability. The publication is refreshed until
the "Unpublish" is pressed. This demonstrates sending publication according the JSR-325 API.

# MSRP #
MSRP activities are placed under MSRP-button. To be able to accept
incoming MSRP invitation, also the recipient should be in the
MSRP-view.

## MSRP-Chat ##

Like in VoIP, the target-URI can be either set to the text-field or
selected from the list. After the remote party is selected, 'Start
Chat' will send out SIP INVITE to msrp-chat session. The text-area at
the bottom of the screen will indicate the progress, and later also
indicate messaging within the MSRP CHAT.

'Stop'-button will terminate the chat.

Text field (pre-filled with "Test page message") is used to compose
the chat messages. 'Send'-button sends the message to the chat
session.

## Filetransfer ##

Filetransfer requires that SD-Card is in place on the device and that
it contains files for transfer.

MSRP filetransfer is out of bound (not inside a chat-session) filetransfer. To initiate a filetransfer, remote target is selected with the list or inputted to the 'Remote user address'-text field. 'File'-button allows the selection of file. After file selection (click 'ok' to confirm the file) a filetransfer dialog is opened. 'Start'-button will initiate SIP INVITE that offers MSRP filetransfer to the recipient. Accepting the offer at remote end will lead to MSRP-session and the file is transferred. Progress is indicated during the progress. If the recipient is using similar Test Client, the transferred file is
written to the root of SD-Card with the same name that was present in the file-selector:name:-parameter in negotiating SDP.

Recent revisions have added a possibility to send the same file to two
recipients; thus two targets can be selected. Buttons 'Start' and
'Start 2x' trigger transfer to either one (top-most) target or for
both (Start 2x).

Content-type (to the SDP) for the transferred file on android is using
built-in mime-type API available on android, which might in some cases make false assumptions based on filename.


# Conf #
Conference call can be tested with this view. Conference-service-uri
is inputted to the first field; the two ordinary users are inputted to
the other two text-fields.. The call-flow follows the agreed flows,
Remote1 is called first, then held, remote2 is called, and when placed
on hold the conference-server is INVITED and eventually remote1 and
remote2 are referred to the conf-leg.

# Mes #
To send SIP MESSAGE requests (also known as PageMessage) access the
message-view by pressing 'Mes'. Recipient can be inputted to the
text-field or selected from the list. Message content is typed to the
'Text to send'-field. Pressing 'Send' will send out SIP MESSAGE.

NOTE: As per request related to the original deployment target, the request-URI will always contain User=Phone parameter.

# SubList #
Through SubList-view any service-URI can se subscribed, and the
subscription is maintained until 'unsubscribe' is selected.  This feature demonstrated JSR-325 APIs CreateListWatcher-funtionality.


# SubCon #
Through SubCon individual contacts can be subscribed.
Subscription-dialog is maintained until 'unsubscribe' is pressed. Demonstrate JSR-325 APIs CreateWatcher.


# XDM Document #
The Test Client is capable of uploading RLS-document and
PresAuth-document to the XDM. The format of these document currently match the initial email-discussions concerning XMD and Presence usage in
XXXXX IMS. The format will be change when the Video-capability and
availability related changes to the Pres-rules document are realized
on the server-side.

# ContList #
Contact list view with presence indications will fetch the
phbk-document when this view is accessed. After triggering the
RLS-subscription with a click of 'Subscribe'-button presence
information is shown. Status field lists the availability based on the
list of agreed soft-state strings and Note is linked with the tag-line
in Hard-state publishes.