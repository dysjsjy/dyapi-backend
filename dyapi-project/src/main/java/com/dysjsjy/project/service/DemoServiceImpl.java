package com.dysjsjy.project.service;

import com.dysjsjy.project.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

@DubboService
public class DemoServiceImpl implements DemoService {


    @Override
    public String sayHello(String name) {
        System.out.println("hello" + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "hello" + name;
    }

    @Override
    public String sayHello2(String name) {
        return "dyapi";
    }
}
