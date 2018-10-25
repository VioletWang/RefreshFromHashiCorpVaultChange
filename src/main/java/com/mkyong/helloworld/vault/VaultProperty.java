/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mkyong.helloworld.vault;

/**
 * @author Zifan Wang - zifwang@expedia.com
 */

public class VaultProperty {

    private String refreshable;

    private String test;

    public VaultProperty(String test, String refreshable) {
        this.test = test;
        this.refreshable = refreshable;
    }
    public String getTest() {
        return test;
    }

    public String getRefreshable() {return refreshable;}

}