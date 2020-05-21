package io.github.jzdayz.jdk.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo2 {

    static class Task extends RecursiveTask<Long> {

        private long start;
        private long end;

        public Task(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long m = end - start;
            long sum = 0;
            if (m < 10){
                for (long i = start; i < end; i++) {
                    sum+=i;
                }
            }else{
                long mid = (end + start) / 2;
                ForkJoinTask<Long> fork1 = new Task(start, mid).fork();
                ForkJoinTask<Long> fork = new Task(mid + 1, end).fork();


                sum+=fork.getRawResult()+fork1.getRawResult();
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception{
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> submit = pool.submit(new Task(1,100));
        System.out.println(submit.get());
        System.out.println((1+100)*100/2);
    }
}
