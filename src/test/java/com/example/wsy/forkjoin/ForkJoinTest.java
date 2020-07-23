package com.example.wsy.forkjoin;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

    static ForkJoinPool pool =  new ForkJoinPool
            (Runtime.getRuntime().availableProcessors(),
                    ForkJoinPool.defaultForkJoinWorkerThreadFactory,
                    null, true);

    public static void main(String [] args) throws ExecutionException, InterruptedException {
        ArrayList<Integer> data = new ArrayList<>();
        for(int i = 0;i < 100;i++) {
            data.add(i);
        }
        ForkJoinTask<Integer> result = pool.submit(new MyForkJoinTask(data, pool));
        System.out.println(result.get());
    }
}
class MyForkJoinTask extends RecursiveTask<Integer>{

    private ArrayList<Integer> data;

    private Integer maxTask = Runtime.getRuntime().availableProcessors();

    private static volatile boolean needFork = true;

    private Integer start;

    private Integer end;

    private ForkJoinPool pool;

    public MyForkJoinTask(ArrayList<Integer> data, ForkJoinPool pool) {
        this.data = data;
        this.start = 0;
        this.end = data.size();
        this.pool = pool;
    }

    public MyForkJoinTask(ArrayList<Integer> data, Integer start, Integer end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer result =0;
        int length = data.size();
        if (needFork && length > maxTask) {
            needFork = false;
            int num = length / maxTask;
            int start = 0;
            int end = start + num;
            for (int i = 0; i < maxTask; i++) {
                if (i + 1 == maxTask) {
                    end = length;
                }
                MyForkJoinTask task = new MyForkJoinTask(data, start, end);
                task.fork();
                start = end;
                end = end + num;
                result += task.join();
            }
            needFork = false;
        } else {
            for (int i = start;i < end;i++) {
                result += data.get(i);
            }
        }
        return result;
    }
}
