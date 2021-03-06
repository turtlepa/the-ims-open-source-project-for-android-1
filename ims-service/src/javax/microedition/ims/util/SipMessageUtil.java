/*
 * This software code is (c) 2010 T-Mobile USA, Inc. All Rights Reserved.
 *
 * Unauthorized redistribution or further use of this material is
 * prohibited without the express permission of T-Mobile USA, Inc. and
 * will be prosecuted to the fullest extent of the law.
 *
 * Removal or modification of these Terms and Conditions from the source
 * or binary code of this software is prohibited.  In the event that
 * redistribution of the source or binary code for this software is
 * approved by T-Mobile USA, Inc., these Terms and Conditions and the
 * above copyright notice must be reproduced in their entirety and in all
 * circumstances.
 *
 * No name or trademarks of T-Mobile USA, Inc., or of its parent company,
 * Deutsche Telekom AG or any Deutsche Telekom or T-Mobile entity, may be
 * used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" AND "WITH ALL FAULTS" BASIS
 * AND WITHOUT WARRANTIES OF ANY KIND.  ALL EXPRESS OR IMPLIED
 * CONDITIONS, REPRESENTATIONS OR WARRANTIES, INCLUDING ANY IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT CONCERNING THIS SOFTWARE, ITS SOURCE OR BINARY CODE
 * OR ANY DERIVATIVES THEREOF ARE HEREBY EXCLUDED.  T-MOBILE USA, INC.
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE
 * OR ITS DERIVATIVES.  IN NO EVENT WILL T-MOBILE USA, INC. OR ITS
 * LICENSORS BE LIABLE FOR LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES,
 * HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT
 * OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF T-MOBILE USA,
 * INC. HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * THESE TERMS AND CONDITIONS APPLY SOLELY AND EXCLUSIVELY TO THE USE,
 * MODIFICATION OR DISTRIBUTION OF THIS SOFTWARE, ITS SOURCE OR BINARY
 * CODE OR ANY DERIVATIVES THEREOF, AND ARE SEPARATE FROM ANY WRITTEN
 * WARRANTY THAT MAY BE PROVIDED WITH A DEVICE YOU PURCHASE FROM T-MOBILE
 * USA, INC., AND TO THE EXTENT PERMITTED BY LAW.
 */

package javax.microedition.ims.util;

import javax.microedition.ims.common.MessageType;
import javax.microedition.ims.common.Protocol;
import javax.microedition.ims.config.Configuration;
import javax.microedition.ims.core.StackContext;
import javax.microedition.ims.core.dialog.Dialog;
import javax.microedition.ims.core.registry.CommonRegistry;
import javax.microedition.ims.core.sipservice.subscribe.NotifyInfo;
import java.io.IOException;
import java.util.List;

/**
 * User: Pavel Laboda (pavel.laboda@gmail.com)
 * Date: 03-Mar-2010
 * Time: 12:11:17
 */
public interface SipMessageUtil<T> {
    int getStatusCode(T msg);

    boolean isClientClassResponse(T msg);

    boolean isResponse(T msg);

    boolean isRequest(T msg);

    boolean isMessagesEqual(T firstMsg, T secondMsg);

    boolean isDuplicateDetected(StackContext stackContext, T msg);

    String messageShortDescription(T msg);

    T buildResponse(Dialog dialog, T msg, int statusCode, String message);

    T buildStatelessResponse(StackContext context, T msg, int statusCode, String message);

    String getCallId(T msg);

    Protocol getMessageTransport(T msg);

    /**
     * The branch parameter in the topmost Via HEADER field of the request
     * is examined.  If it is present and begins with the magic cookie
     * "z9hG4bK", the request was generated by a client transaction
     * compliant to RFC 3261.
     */
    boolean isMessageRFC3261Compilant(T msg);

    int calcMessageHash(T msg);

    //boolean isSupportedRequest(T msg);

    boolean isSuccessResponse(T msg);

    String getMessageBranch(T msg) throws NullPointerException;

    NotifyInfo getNotifyInfo(T msg);

    String getResponseLine(T triggeringMessage);

    boolean isWellFormedRequest(T msg);

    //TODO for test

    List<String> retrievePidfDocsBodies(T msg) throws IOException;

    MessageType[] getAllowedMessages(CommonRegistry registry, Configuration configuration);

    T buildDummyMessage();
}