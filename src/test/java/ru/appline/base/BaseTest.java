package ru.appline.base;

import static ru.appline.framework.managers.InitManager.initFramework;
import static ru.appline.framework.managers.InitManager.quitFramework;

import org.junit.After;
import org.junit.Before;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.ManagerPages;

public class BaseTest {

    protected ManagerPages app = ManagerPages.getManagerPages();



    @Before
    public void before(){
        InitManager.initFramework();

    }

    @After
    public void after(){
        InitManager.quitFramework();
    }

}
