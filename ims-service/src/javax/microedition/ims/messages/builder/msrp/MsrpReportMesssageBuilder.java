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

package javax.microedition.ims.messages.builder.msrp;

import javax.microedition.ims.core.StackContext;
import javax.microedition.ims.core.msrp.MSRPSession;
import javax.microedition.ims.messages.builder.BaseMessageBuilder;
import javax.microedition.ims.messages.utils.MessageUtils;
import javax.microedition.ims.messages.wrappers.msrp.ChunkTerminator;
import javax.microedition.ims.messages.wrappers.msrp.MsrpMessage;
import javax.microedition.ims.messages.wrappers.msrp.MsrpMessageType;


public class MsrpReportMesssageBuilder extends BaseMessageBuilder implements IMsrpResponseAndReportMessageBuilder {
    private final MSRPSession msrpSession;

    public MsrpReportMesssageBuilder(final MSRPSession msrpSession, final StackContext context) {
        super(msrpSession.getMsrpDialog(), context);
        this.msrpSession = msrpSession;
    }

    public MsrpMessage buildMessage(MsrpMessage request, int statusCode,
                                    String reasonPhrase) {
        MsrpMessage ret = new MsrpMessage();
        ret.setTerminator(ChunkTerminator.FINISHED);
        ret.setType(MsrpMessageType.REPORT);
        ret.setCode(statusCode);
        if (reasonPhrase != null) {
            ret.setReasonPhrase(reasonPhrase);
        }
        else {
            ret.setReasonPhrase(MessageUtils.getMessageByCode(statusCode));
        }
        ret.setMessageId(request.getMessageId());
        ret.setToPath(request.getFromPath());
        ret.setFromPath(request.getToPath());
        ret.setPrevProgress(request.getPrevProgress());
        ret.setCurrentProgress(request.getCurrentProgress());
        ret.setTotalSize(request.getTotalSize());
        return ret;
    }

}
