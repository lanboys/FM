/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.chenupt.springindicator.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import java.lang.reflect.Field;

/**
 * Created by chenupt@gmail.com on 2015/3/7.
 * Description TODO
 */
public class ScrollerViewPager extends ViewPager {

    private static final String TAG = ScrollerViewPager.class.getSimpleName();

    private int duration = 1000;

    public ScrollerViewPager(Context context) {
        super(context);
    }

    public ScrollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void fixScrollSpeed() {
        fixScrollSpeed(duration);
    }

    public void fixScrollSpeed(int duration) {
        this.duration = duration;
        setScrollSpeedUsingRefection(duration);
    }

    private void setScrollSpeedUsingRefection(int duration) {
        try {
            Field localField = ViewPager.class.getDeclaredField("mScroller");
            localField.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator(1.5F));
            scroller.setDuration(duration);
            localField.set(this, scroller);
            return;
        } catch (IllegalAccessException localIllegalAccessException) {
        } catch (IllegalArgumentException localIllegalArgumentException) {
        } catch (NoSuchFieldException localNoSuchFieldException) {
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // Log.e(TAG, "onInterceptTouchEvent in IllegalArgumentException");
            return false;
        }
    }
}
