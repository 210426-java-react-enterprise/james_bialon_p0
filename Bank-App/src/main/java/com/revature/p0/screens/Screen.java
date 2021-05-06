package com.revature.p0.screens;

/**
 * Created by IntelliJ IDEA.
 * User: Jbialon
 * Date: 5/5/2021
 * Time: 9:06 AM
 * Description: {Insert Description}
 */
public abstract class Screen {

    protected String name;
    protected String route;

    public Screen(String name, String route) {
        this.name = name;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public String getRoute() {
        return route;
    }

    public abstract void render();
}
