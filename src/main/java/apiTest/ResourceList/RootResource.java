package apiTest.ResourceList;

import apiTest.MainData.ResourceData;
import apiTest.MainData.Support;

import java.util.ArrayList;

public class RootResource {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<ResourceData> data;
    public Support support;
}
