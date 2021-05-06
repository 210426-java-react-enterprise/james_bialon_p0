package com.revature.p0.util.scenemgmt;

import com.revature.p0.screens.Screen;
import com.revature.p0.util.datastructs.linkedlist.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/1/2021
 * Time: 11:22 AM
 * Description: {Insert Description}
 */
public class ScreenRouter {

    private LinkedList<Screen> screens = new LinkedList<>();

    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    public void navigate(String route) {
        for (int i = 0; i < screens.size(); i++) {
            Screen screen = screens.get(i);
            if (screen.getRoute().equals(route)) {
                screen.render();
            }
        }
    }
}