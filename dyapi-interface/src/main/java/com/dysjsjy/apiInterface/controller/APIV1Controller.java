package com.dysjsjy.apiInterface.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.dysjsjy.clientsdk.cilent.DyApiClient;
import com.dysjsjy.clientsdk.model.RandomWallpaperParams;
import com.dysjsjy.clientsdk.model.RandomWallpaperResponse;
import com.dysjsjy.clientsdk.model.ResultResponse;
import com.dysjsjy.clientsdk.model.WeatherParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.dysjsjy.clientsdk.model.ResponseUtils.baseResponse;
import static com.dysjsjy.clientsdk.utils.RequestUtils.*;

@RestController
@RequestMapping("/v1")
public class APIV1Controller {

    @Resource
    private DyApiClient dyApiClient;

    @GetMapping("/loveQuotes/random")
    public Object liveQuoteRandom() {
        return dyApiClient.loveQuotesRandom();
    }

    @GetMapping("/poisonousChickenSoup")
    public String getPoisonousChickenSoup() {
        return get("https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json");
    }

    @GetMapping("/randomWallpaper")
    public RandomWallpaperResponse randomWallpaper(RandomWallpaperParams randomWallpaperParams) throws Exception {
        String baseUrl = "https://api.btstu.cn/sjbz/api.php";
        String url = buildUrl(baseUrl, randomWallpaperParams);
        if (StrUtil.isAllBlank(randomWallpaperParams.getLx(), randomWallpaperParams.getMethod())) {
            url = url + "?format=json";
        } else {
            url = url + "&format=json";
        }
        return JSONUtil.toBean(get(url), RandomWallpaperResponse.class);
    }

    @GetMapping("/weather")
    public ResultResponse getWeatherInfo(WeatherParams weatherParams) {
        return baseResponse("https://api.vvhan.com/api/weather", weatherParams);
    }
}
