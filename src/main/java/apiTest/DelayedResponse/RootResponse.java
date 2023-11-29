package apiTest.DelayedResponse;

import apiTest.MainData.Support;
import apiTest.MainData.UserData;

import java.util.ArrayList;

public class RootResponse {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<UserData> data;
    public Support support;

    public RootResponse() {
        super();
    }
}
