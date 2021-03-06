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

package javax.microedition.ims.core.transaction.state.noninvite.sip.server;

import javax.microedition.ims.core.sipservice.State;
import javax.microedition.ims.core.sipservice.StateChangeReason;
import javax.microedition.ims.core.sipservice.TUEvent;
import javax.microedition.ims.core.sipservice.TransactionState;
import javax.microedition.ims.core.sipservice.TransactionStateChangeEvent;
import javax.microedition.ims.core.sipservice.invite.TUResponseEvent;
import javax.microedition.ims.core.sipservice.invite.TUResponseEvent.OperationType;
import javax.microedition.ims.core.transaction.server.ServerTransaction;
import javax.microedition.ims.core.transaction.state.noninvite.CompletedState;
import javax.microedition.ims.messages.utils.StatusCode;
import javax.microedition.ims.messages.wrappers.sip.BaseSipMessage;

public class TryingState extends TransactionState<ServerTransaction, BaseSipMessage> {
    public TryingState(ServerTransaction transaction) {
        super(transaction);
    }

    protected State getTransactionStateName() {
        return State.TRYING;
    }

    protected void onFirstInit(final TransactionStateChangeEvent<BaseSipMessage> triggeringEvent) {

    }

    public void onStateInitiated(final TransactionStateChangeEvent<BaseSipMessage> triggeringEvent) {

        super.onStateInitiated(triggeringEvent);
        if (transaction.isAutoAcceptable()) {
            transaction.sendResponseNonInvite(getTransactionStateName());

            final TransactionStateChangeEvent<BaseSipMessage> event = createStateChangeEvent(
                    StateChangeReason.INITIALIZATION, null);

            transaction.transitToState(new CompletedState<BaseSipMessage>(transaction), event);

        } else {
            // waiting respose from TU
        }

    }

    public void onTUReceived(TUEvent event) {
        BaseSipMessage triggeringMessage = null;
        if (event instanceof TUResponseEvent) {
            triggeringMessage = ((TUResponseEvent)event).getTriggeringMessage();
        }

        TUResponseEvent inviteEvent = (TUResponseEvent)event;
        if (inviteEvent.getOpType() == OperationType.ACCEPT_UPDATE) {
            sendResponse(RequestState.ACCEPTED_UPDATE, StatusCode.OK, triggeringMessage);
        } else if (inviteEvent.getOpType() == OperationType.REJECT_UPDATE) {
            sendResponse(RequestState.REJECTED_UPDATE, inviteEvent.getStatusCode(),
                    triggeringMessage);
        }
    }

    private void sendResponse(final RequestState requestState, final int customCode,
            final BaseSipMessage triggeringMessage) {
        switch (requestState) {
            case TIMEOUT: {
                transaction.sendResponse(triggeringMessage, StatusCode.REQUEST_TIMEOUT, false);

                final TransactionStateChangeEvent<BaseSipMessage> event = createStateChangeEvent(
                        StateChangeReason.TIMER_TIMEOUT, null);

                transaction.transitToState(new CompletedState<BaseSipMessage>(transaction), event);
            }
                break;
            case ACCEPTED_UPDATE: {
                transaction.sendResponse(triggeringMessage, StatusCode.OK, true);

                final TransactionStateChangeEvent<BaseSipMessage> event = createStateChangeEvent(
                        StateChangeReason.CLIENT_UPDATE_SUCCESS, null);

                transaction.transitToState(new CompletedState<BaseSipMessage>(transaction), event);
                // transaction.notifyTU(event);
            }
                break;
            case REJECTED_UPDATE: {
                transaction.sendResponse(triggeringMessage, customCode, true);

                final TransactionStateChangeEvent<BaseSipMessage> event = createStateChangeEvent(
                        StateChangeReason.CLIENT_UPDATE_FAILED, null);

                // transaction.notifyTU(event);
                transaction.transitToState(new CompletedState<BaseSipMessage>(transaction), event);
            }
                break;
            default:
                break;
        }
    }

}
