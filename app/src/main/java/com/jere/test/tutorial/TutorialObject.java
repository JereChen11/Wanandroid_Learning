package com.jere.test.tutorial;

import com.jere.test.R;

/**
 * @author jere
 */

public enum TutorialObject {
    /**
     * tutorial left notifications point
     */
    LEFT(R.layout.tutorial_page_1),
    /**
     * tutorial center notifications point
     */
    CENTER(R.layout.tutorial_page_2),
    /**
     * tutorial right notifications point
     */
    RIGHT(R.layout.tutorial_page_3);

    private int mLayoutResId;

    TutorialObject(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
