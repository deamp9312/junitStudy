package com.junitTest.demo.thread.ch1;

import com.junitTest.demo.thread.ch1.config.ChannelDist;
import com.junitTest.demo.thread.ch1.config.ChannelManager;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyVirtualThreadTest {

    public static void main(String[] args) throws InterruptedException {

        // 1. 초기 데이터 셋팅
        Set<String> channelData = ChannelManager.selectChannelDist();
        ChannelDist.refreshNotice(channelData);

        int threadCount = 100_000;
        int checkCount = 1_000_000;
        /**
         * Task :MyVirtualThreadTest.main()
         * notice.list = [N00002, N00003, N00004, N00001]
         * 총 소요 시간 (virtual threads): 277ms
         *
         * BUILD SUCCESSFUL in 1s
         */
        /**
         * Task :MyVirtualThreadTest.main()
         * 총 소요 시간 (virtual threads): 21939ms
         *
         * BUILD SUCCESSFUL in 22s
         *
         *
         * Task :MyThread.main()
         * 총 소요 시간: 32753ms
         *
         * BUILD SUCCESSFUL in 34s
         */

        // 2. Virtual Thread Executor
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        long start = System.currentTimeMillis();

        // 3. 작업 제출
        for (int i = 0; i < threadCount; i++) {
            String target = (i == 5) ? "N00011" : "N00001"; // 5번째는 false 나오게
            executor.submit(() -> {
                for (int j = 0; j < checkCount; j++) {
                    boolean result = ChannelDist.isNotice(target);
                    if (!result) {
//                        System.out.println(Thread.currentThread().getName() + " → " + target + " = false");
                    }
                }
            });
        }

        // 4. 모든 작업 종료 대기
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(10);
        }

        long end = System.currentTimeMillis();
        System.out.println("총 소요 시간 (virtual threads): " + (end - start) + "ms");
    }
}
