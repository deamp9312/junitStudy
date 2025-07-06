package com.junitTest.demo.thread.ch1;

import com.junitTest.demo.thread.ch1.config.ChannelDist;
import com.junitTest.demo.thread.ch1.config.ChannelManager;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("running : !!" +getName() + "!!!");
    }

    /**
     * Task :MyVirtualThreadTest.main()
     * notice.list = [N00002, N00003, N00004, N00001]
     * 총 소요 시간 (virtual threads): 69ms
     *
     * BUILD SUCCESSFUL in 857ms
     */

    /**
     * MyThread.main()
     * notice.list = [N00002, N00003, N00004, N00001]
     * 총 소요 시간: 62ms
     *
     * BUILD SUCCESSFUL in 900ms
     */


    public static void main(String[] args) {

        ChannelDist.refreshNotice(ChannelManager.selectChannelDist());

        /**
         * Task :MyThread.main()
         * notice.list = [N00002, N00003, N00004, N00001]
         * 총 소요 시간: 32753ms
         *
         * BUILD SUCCESSFUL in 34s
         */
        // 2. 쓰레드 개수 및 반복 횟수
        int threadCount = 100_000;
        int checkCount = 1_000_000;

        Thread[] threads = new Thread[threadCount];

        long start = System.currentTimeMillis();

        // 3. 스레드 생성 및 실행
        for (int i = 0; i < threadCount; i++) {
            if(i==5){
                threads[i] = new Thread(new NoticeCheckTask("N00011", checkCount));
            }else{
                threads[i] = new Thread(new NoticeCheckTask("N00001", checkCount));
            }
            threads[i].start();
        }

        // 4. 모든 스레드 종료 대기
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("총 소요 시간: " + (end - start) + "ms");
    }

    // 5. Runnable 클래스: 특정 값을 반복적으로 검사
    static class NoticeCheckTask implements Runnable {
        private final String valueToCheck;
        private final int loopCount;

        public NoticeCheckTask(String valueToCheck, int loopCount) {
            this.valueToCheck = valueToCheck;
            this.loopCount = loopCount;
        }

        @Override
        public void run() {
            boolean notice = true;
            for (int i = 0; i < loopCount; i++) {
                notice= ChannelDist.isNotice(valueToCheck);

            }
            if (!notice) {
//                System.out.println("notice = " + notice);
            }
        }
    }

}
