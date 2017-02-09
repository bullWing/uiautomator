package com.wing.uitest;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiTest {

    private UiDevice mDevice;

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.wing.uitest";

    //执行测试方法前调用
    @Before
    public void startMainActivityFromHomeScreen() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    }

    @Test
    public void logInTest() throws UiObjectNotFoundException, InterruptedException {
        mDevice.pressHome();
        findApp();
        logIn();
        inputNumber();
        getVerifictionCode();
        weixinLogin();
        qqLogin();
        probation();
    }

    public void qqLogin() throws UiObjectNotFoundException, InterruptedException {
        UiObject qqLogin = mDevice.findObject(new UiSelector().className("android.widget.ImageView").instance(2));
        if (isExist(qqLogin)) {
            qqLogin.click();
            Thread.sleep(2000L);
        }

        UiObject accreditQQ = mDevice.findObject(new UiSelector().text("授权并登录").className("android.widget.Button"));
        if (isExist(accreditQQ)) {
            String currentPackageName = mDevice.getCurrentPackageName();
            if (currentPackageName.equals("com.tencent.mqq")) {
                accreditQQ.click();
            }
        }


    }

    public void weixinLogin() throws UiObjectNotFoundException, InterruptedException {
        UiObject weixinLogin = mDevice.findObject(new UiSelector().className("android.widget.ImageView").instance(1));
        if (isExist(weixinLogin)) {
            weixinLogin.click();
            Thread.sleep(3000L);
        }
        String currentPackageName = mDevice.getCurrentPackageName();
        if (currentPackageName.equals("com.tencent.mm")) {
            int height = mDevice.getDisplayHeight() / 2;
            int width = mDevice.getDisplayWidth() / 2;
            mDevice.click(width, height + 200);
        }

    }

    public void getVerifictionCode() throws UiObjectNotFoundException {
        UiObject getVerificationCode = mDevice.findObject(new UiSelector().text("获取").className("android.widget.TextView"));
        if (isExist(getVerificationCode)) {
            getVerificationCode.click();
            onKeyBack(3000L);
        }
    }

    public void inputNumber() {
        // 控件名找控件
//        UiObject inputPhone = mDevice.findObject(new UiSelector().text("输入手机号码").className("android.widget.EditText"));
//        if (isExist(inputPhone)) {
//            inputPhone.click();
//            inputPhone.setText("12345678901");
//            onKeyBack(1000L);
//        }
        UiObject2 inputPhone = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "btn_username")), 500);
        if (inputPhone != null && inputPhone.isFocused()) {
            inputPhone.click();
            inputPhone.setText("12345678901");
            onKeyBack(2000L);
        }
    }

    public void logIn() {
        //id找控件
        UiObject2 login = mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "btn_login_enter")), 500);
        if (login != null && login.isEnabled()) {
            login.click();
        }
    }

    public void findApp() throws UiObjectNotFoundException, InterruptedException {
        UiObject paoWord = mDevice.findObject(new UiSelector().text("泡单词").className("android.widget.TextView"));
        //点击并等待打开应用  
        if (isExist(paoWord)) {
            paoWord.clickAndWaitForNewWindow();
            Thread.sleep(4000);
        } else {
            //        滑动屏幕    
            while (true) {
                UiScrollable appViews = new UiScrollable(new UiSelector());
                if (isExist(paoWord)) {
                    paoWord.clickAndWaitForNewWindow();
                    Thread.sleep(4000);
                    break;
                } else {
                    appViews.swipeLeft(5);
                }
            }
        }
    }

    public void probation() throws UiObjectNotFoundException {
        UiObject probation = mDevice.findObject(new UiSelector().text("游客试用").className("android.widget.TextView"));
        if (isExist(probation)) {
            probation.click();
        }
    }


    public void onKeyBack(long delayTime) {
        try {
            Thread.sleep(delayTime);
            mDevice.pressBack();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isExist(UiObject uiObj) {
        try {
            return uiObj.exists() && uiObj.isEnabled() ? true : false;
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


   
    public void HomeUiTest() throws UiObjectNotFoundException {
        UiObject arena = mDevice.findObject(new UiSelector().text("竞技场").className("android.widget.RadioButton"));
        if (isExist(arena)) {
            arena.click();
        }

        UiScrollable re = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));
        if (isExist(re)) {
            while (true) {
                SystemClock.sleep(2000);
                re.swipeDown(30);
            }
        }
    }

}