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

package javax.microedition.ims.core.sipservice.invite;

import javax.microedition.ims.core.IMSStackException;
import javax.microedition.ims.core.dialog.Dialog;
import javax.microedition.ims.messages.wrappers.sip.BaseSipMessage;

/**
 * User: Pavel Laboda (pavel.laboda@gmail.com)
 * Date: 25-Feb-2010
 * Time: 13:00:33
 */
public class DialogStateException extends IMSStackException {

    public enum Error {
        INVITE_FOR_STATED_DIALOG,
        REINVITE_FOR_EARLY_DIALOG,
        REINVITE_DURING_PREVIOUS_REINVITE,
        REQUEST_FOR_UNKNOWN_DIALOG,
        SEND_MESSAGE_FOR_STATED_DIALOG,
        UPDATE_FOR_STATED_DIALOG,
        UPDATE_DURING_PREVIOUS_UPDATE,
        ADDRESSEE_NOT_FOUND, REQUEST_CANNOT_BE_HANDLED
    }

    private final Dialog dialog;
    private final Error error;
    private final BaseSipMessage sipMsg;

    public DialogStateException(final Dialog dialog, final Error error, final BaseSipMessage sipMsg) {
        this(dialog, error, sipMsg, "");
    }

    public DialogStateException(final Dialog dialog, final Error error, final BaseSipMessage sipMsg, final String message) {
        super(message);
        this.dialog = dialog;
        this.error = error;
        this.sipMsg = sipMsg;
    }

    public Error getError() {
        return error;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public BaseSipMessage getTriggeringMessage() {
        return sipMsg;
    }

    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("DialogStateException");
        sb.append("{DIALOG=").append(dialog);
        sb.append(", error=").append(error);
        sb.append(", sipMsg=").append(sipMsg);
        sb.append('}');
        return sb.toString();
    }
}
