package com.abnd.maso.inventory;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InventoryActivityTest {

    @Rule
    public ActivityTestRule<InventoryActivity> mActivityTestRule = new ActivityTestRule<>(InventoryActivity.class);

    @Test
    public void inventoryActivityTest() {
    }

}
