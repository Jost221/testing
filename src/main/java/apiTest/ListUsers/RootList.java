package apiTest.ListUsers;

import apiTest.MainData.Support;
import apiTest.MainData.UserData;

import java.util.ArrayList;

public class RootList {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<UserData> data;
    public Support support;

    public RootList() {
        super();
    }

    public RootList(int page, int per_page, int total, int total_pages, ArrayList<UserData> data, Support support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support = support;
    }


}
