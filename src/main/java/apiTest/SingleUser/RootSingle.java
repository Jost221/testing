package apiTest.SingleUser;

import apiTest.MainData.Support;
import apiTest.MainData.UserData;

public class RootSingle {
    public UserData data;
    public Support support;

    public RootSingle(UserData data, Support support) {
        this.data = data;
        this.support = support;
    }

    public RootSingle() {
        super();
    }
}
