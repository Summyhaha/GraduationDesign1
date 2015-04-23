/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.catalina.ha;

import java.io.Serializable;

import org.apache.catalina.tribes.ChannelListener;
import org.apache.catalina.tribes.Member;


/**
 * Receive SessionID cluster change from other backup node after primary session
 * node is failed.
 *
 * @author Peter Rossbach
 */
public abstract class NewClusterListener implements ChannelListener {

    private static final org.apache.juli.logging.Log log =
        org.apache.juli.logging.LogFactory.getLog(NewClusterListener.class);

    //--Instance Variables--------------------------------------

    /**
     * The string manager for this package.
     */

    protected NewCatalinaCluster cluster = null;

    //--Constructor---------------------------------------------

    public NewClusterListener() {
        // NO-OP
    }

    //--Instance Getters/Setters--------------------------------

    public NewCatalinaCluster getCluster() {
        return cluster;
    }

    public void setCluster(NewCatalinaCluster cluster) {
        if (log.isDebugEnabled()) {
            if (cluster != null)
                log.debug("add ClusterListener " + this.toString() +
                        " to cluster" + cluster);
            else
                log.debug("remove ClusterListener " + this.toString() +
                        " from cluster");
        }
        this.cluster = cluster;
    }

    @Override
    public boolean equals(Object listener) {
        return super.equals(listener);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //--Logic---------------------------------------------------

    @Override
    public final void messageReceived(Serializable msg, Member member) {
        if ( msg instanceof NewClusterMessage ) messageReceived((NewClusterMessage)msg);
    }
    @Override
    public final boolean accept(Serializable msg, Member member) {
        if ( msg instanceof NewClusterMessage ) return true;
        return false;
    }



    /**
     * Callback from the cluster, when a message is received, The cluster will
     * broadcast it invoking the messageReceived on the receiver.
     *
     * @param msg
     *            ClusterMessage - the message received from the cluster
     */
    public abstract void messageReceived(NewClusterMessage msg) ;


    /**
     * Accept only SessionIDMessages
     *
     * @param msg
     *            ClusterMessage
     * @return boolean - returns true to indicate that messageReceived should be
     *         invoked. If false is returned, the messageReceived method will
     *         not be invoked.
     */
    public abstract boolean accept(NewClusterMessage msg) ;

}
