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

package javax.microedition.ims.messages.history;

public interface MessageData {

    public static final int STATE_RECEIVED = 3;
    public static final int STATE_SENT = 2;
    public static final int STATE_UNSENT = 1;

    /**
     * Adds a HEADER value, either on a new HEADER or appending a new value to an already existing HEADER.
     */
    public abstract void addHeader(String key, String value);

    /**
     * Creates a new MessageBodyPart and adds it to the message.
     *
     * @return created BodyPartData
     */

    public abstract BodyPartData createBodyPart();

    /**
     * Returns all body parts that are added to the message.
     *
     * @return BodyPartData[] - body parts
     */
    public abstract BodyPartData[] getBodyParts();

    /**
     * Returns the value(s) of a HEADER in this message.
     *
     * @param key - HEADER name
     * @return String[] - array of HEADER's values or null if no values exist
     */

    public abstract String[] getHeaders(String key);

    /**
     * Returns message method.
     *
     * @return String - method's name
     */
    public abstract String getMethod();

    /**
     * Returns response REASON phrase.
     *
     * @return String - REASON phrase for responses
     *         null - for requests and for responses with empty REASON phrase
     */
    public abstract String getReasonPhrase();

    /**
     * Returns message state.
     *
     * @return int - message state (STATE_RECEIVED, STATE_SENT, STATE_UNSENT).
     */
    public abstract int getState();

    /**
     * Returns response status code.
     *
     * @return int - status code for responses
     *         0 - for requests
     */
	public abstract int getStatusCode();

}