package com.example.vuthyra.bakingtime;


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.vuthyra.bakingtime.fragment.Fragment_ingredients;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class RecyclerViewTestMain {

        public static final String YELLOW_CAKE = "Yellow Cake";

        public static final String INGREDIENT_INSTRUCTION = "unsalted butter";

        @Rule
        public ActivityTestRule<MainActivity> mActivityTestRule =
                new ActivityTestRule<>(MainActivity.class);

        @Test
        public void scrollToItem_checkItsText() {
                // First scroll to the position that needs to be matched and check if the item appreared
//                 correctly at that position.


                onView(withId(R.id.recyclerView_items))
                        .perform(RecyclerViewActions.scrollToPosition(2));
                onView(withText(YELLOW_CAKE)).check(matches(isDisplayed()));




//
//                onView(withId(R.id.recyclerView_items))
//                        .perform(RecyclerViewActions.scrollToPosition(1), click());
//                onView(withId(R.id.recyclerView_ingredients))
//                        .perform(RecyclerViewActions.scrollToPosition(1));
//                onView(withText(INGREDIENT_INSTRUCTION)).check(matches(isDisplayed()));
//

        }

//        @Before
//        public void yourSetUPFragment() {
//                mActivityTestRule.getActivity()
//                        .getFragmentManager().beginTransaction();
//
//                mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
//                        .add(R.id.ingredient_container, new Fragment_ingredients()).commit();
//        }
//
////
//        @Test
//        public void selectedItem_showItemIngredients() {
//
////                onView(withId(R.id.recyclerView_items))
////                        .perform(RecyclerViewActions
////                                .actionOnItemAtPosition(1, click()));
//
//
//
//                onView(withId(R.id.recyclerView_items))
//                        .perform(RecyclerViewActions.scrollToPosition(1), click());
////                onView(withId()), isDisplayed().matches()
//
//
//        }






}
