package com.leetcode.concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
class WebCrawler {


    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {


        try {
            return  executorService.submit(() -> new ArrayList<String>(crawl(startUrl, startUrl, htmlParser, new HashSet<String>()))).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Set<String> crawl(String startUrl, String url, HtmlParser htmlParser, Set<String> result) {
        System.out.println(result.toString() + " " + url);
        for(String childUrl: htmlParser.getUrls(url)) {
            if(isSameHostName(startUrl, childUrl) && !result.contains(childUrl)) {
                crawl(startUrl, childUrl, htmlParser, result);
            }
        }
        return result;
    }


    private boolean isSameHostName(String baseUrl, String test) {
        // System.out.println(baseUrl.split("/")[2] + "  " + test.split("/")[2]);
        // System.out.println(baseUrl.split("/")[2].equals(test.split("/")[2]));

        return baseUrl.split("/")[2].equals(test.split("/")[2]);
    }



}