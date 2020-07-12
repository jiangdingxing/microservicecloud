package com.atguigu.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRule_JDX extends AbstractLoadBalancerRule {

    // total =0// 当total==5，我们指针才能往下走
    // index =0 //当前对外提供的服务器地址
    // total需要重新置换为零，但是已经达到过5次，我们index = 1
    // 分析：我们5次，但是微服务只有8001，8002，8003三台，OK?

    private int total = 0; //总共被调用的次数，目前需求要求每台被调用5次
    private int currentIndex = 0; //当前提供服务的机器号

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) { //中短的线程
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            //这句代码的意思是：拿到微服务的数量，然后使用java.util.Random().nextInt()的方式
            // 转为2，因为程序中是0开始，所有使用这个将3个微服务转为2
            /*int index=rand.nextInt(serverCount);// java.util.Random().nextInt(3);
            server=upList.get(index);*/

            if (total < 5) {
                server = upList.get(currentIndex);
                total++;
            } else {
                total = 0;
                currentIndex++;
                if (currentIndex >= upList.size()) {
                    currentIndex = 0;
                }
            }


            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}

